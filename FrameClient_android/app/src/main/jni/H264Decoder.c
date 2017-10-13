#include "com_frameclient_utils_H264Decoder.h"
#include <jni.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#define TEST

#ifdef TEST
#include "libavutil/opt.h"
#include "libavcodec/avcodec.h"
#include "libavutil/channel_layout.h"
#include "libavutil/common.h"
#include "libavutil/imgutils.h"
#include "libavutil/mathematics.h"
#include "libavutil/samplefmt.h"
#include "libavutil/mem.h"
#include "libswscale/swscale.h"
#include "libswscale/swscale_internal.h"

#include "android_log.h"


struct AVCodec *codec=NULL;			  // Codec
struct AVCodecContext *c=NULL;		  // Codec Context
struct AVFrame *frameYUV;
struct AVFrame *frameRGB;
struct AVPacket avpkt;// Frame

int *colortab=NULL;
int *u_b_tab=NULL;
int *u_g_tab=NULL;
int *v_g_tab=NULL;
int *v_r_tab=NULL;

int iWidth = 0;
int iHeight = 0;

//short *tmp_pic=NULL;

unsigned int *rgb_2_pix=NULL;
unsigned int *r_2_pix=NULL;
unsigned int *g_2_pix=NULL;
unsigned int *b_2_pix=NULL;
#endif

#ifdef TEST
void DeleteYUVTab()
{
//	av_free(tmp_pic);

	av_free(colortab);
	av_free(rgb_2_pix);
}

void CreateYUVTab_16()
{
	int i;
	int u, v;

//	tmp_pic = (short*)av_malloc(iWidth*iHeight*2); // ���� iWidth * iHeight * 16bits

	colortab = (int *)av_malloc(4*256*sizeof(int));
	u_b_tab = &colortab[0*256];
	u_g_tab = &colortab[1*256];
	v_g_tab = &colortab[2*256];
	v_r_tab = &colortab[3*256];

	for (i=0; i<256; i++)
	{
		u = v = (i-128);

		u_b_tab[i] = (int) ( 1.772 * u);
		u_g_tab[i] = (int) ( 0.34414 * u);
		v_g_tab[i] = (int) ( 0.71414 * v);
		v_r_tab[i] = (int) ( 1.402 * v);
	}

	rgb_2_pix = (unsigned int *)av_malloc(3*768*sizeof(unsigned int));

	r_2_pix = &rgb_2_pix[0*768];
	g_2_pix = &rgb_2_pix[1*768];
	b_2_pix = &rgb_2_pix[2*768];

	for(i=0; i<256; i++)
	{
		r_2_pix[i] = 0;
		g_2_pix[i] = 0;
		b_2_pix[i] = 0;
	}

	for(i=0; i<256; i++)
	{
		r_2_pix[i+256] = (i & 0xF8) << 8;
		g_2_pix[i+256] = (i & 0xFC) << 3;
		b_2_pix[i+256] = (i ) >> 3;
	}

	for(i=0; i<256; i++)
	{
		r_2_pix[i+512] = 0xF8 << 8;
		g_2_pix[i+512] = 0xFC << 3;
		b_2_pix[i+512] = 0x1F;
	}

	r_2_pix += 256;
	g_2_pix += 256;
	b_2_pix += 256;
}

void DisplayYUV_16(unsigned int *pdst1, unsigned char *y, unsigned char *u, unsigned char *v, int width, int height, int src_ystride, int src_uvstride, int dst_ystride)
{
	int i, j;
	int r, g, b, rgb;

	int yy, ub, ug, vg, vr;

	unsigned char* yoff;
	unsigned char* uoff;
	unsigned char* voff;

	unsigned int* pdst=pdst1;

	int width2 = width/2;
	int height2 = height/2;

#if 0
	if(width2>iWidth/2)
	{
		width2=iWidth/2;

		y+=(width-iWidth)/4*2;
		u+=(width-iWidth)/4;
		v+=(width-iWidth)/4;
	}
#endif
	if(height2>iHeight)
		//height2=iHeight;
	for(j=0; j<height2; j++) // һ��2x2���ĸ�����
	{
		yoff = y + j * 2 * src_ystride;
		uoff = u + j * src_uvstride;
		voff = v + j * src_uvstride;
		for(i=0; i<width2; i++)
		{
			yy  = *(yoff+(i<<1));
			ub = u_b_tab[*(uoff+i)];
			ug = u_g_tab[*(uoff+i)];
			vg = v_g_tab[*(voff+i)];
			vr = v_r_tab[*(voff+i)];

			b = yy + ub;
			g = yy - ug - vg;
			r = yy + vr;
			rgb = r_2_pix[r] + g_2_pix[g] + b_2_pix[b];

			yy = *(yoff+(i<<1)+1);
			b = yy + ub;
			g = yy - ug - vg;
			r = yy + vr;

			pdst[(j*dst_ystride+i)] = (rgb)+((r_2_pix[r] + g_2_pix[g] + b_2_pix[b])<<16);

			yy = *(yoff+(i<<1)+src_ystride);
			b = yy + ub;
			g = yy - ug - vg;
			r = yy + vr;

			rgb = r_2_pix[r] + g_2_pix[g] + b_2_pix[b];
			yy = *(yoff+(i<<1)+src_ystride+1);
			b = yy + ub;
			g = yy - ug - vg;
			r = yy + vr;

			pdst [((2*j+1)*dst_ystride+i*2)>>1] = (rgb)+((r_2_pix[r] + g_2_pix[g] + b_2_pix[b])<<16);
		}
	}
}

#endif
void Java_com_frameclient_utils_H264Decoder_init(JNIEnv * env, jobject thiz, jint codec_id,jint width, jint height,jint fps,jint bit_rate,jint gop_size)
{

#ifdef TEST
	int res = 0;
	avcodec_register_all();
	codec = avcodec_find_decoder(codec_id);
	if(codec == NULL)
	{
		LOGD("----------------------> find codec failure..........");
		exit(1);
	}
#if 0
	codec->type = AVMEDIA_TYPE_VIDEO;
	c = avcodec_alloc_context3(codec);

	c->frame_number = 1; //ÿ��һ����Ƶ֡
	c->codec_type = AVMEDIA_TYPE_VIDEO;
	c->bit_rate = bit_rate;
	c->time_base.num = 1;
	c->time_base.den = fps;//֡��


	c->width = width;//��Ƶ��
	c->height = height;//��Ƶ��
#endif

	codec->type = AVMEDIA_TYPE_VIDEO;
	c = avcodec_alloc_context3(codec);

	c->frame_number = 1; //ÿ��һ����Ƶ֡
	c->codec_type = AVMEDIA_TYPE_VIDEO;
    c->bit_rate = bit_rate;   //���ò�����������������  400000  800000
	// resolution must be a multiple of two
	c->width = width;
	c->height = height;
	// frames per second
	c->time_base.num = 1;
	c->time_base.den = fps;  //��֡��Ϊ25����ʵtimebase = 1/framerate���������ڷֱ�Ϊ���Ӻͷ�ĸ��
	c->gop_size = gop_size; //�ؼ�֡�������֡��/* emit one intra frame every ten frames ��ֵ��ʾÿ10֡�����һ��I֡��intra frame)*/
	c->keyint_min =10; //�ؼ�֡����С���֡����ȡֵ��Χ10-51
	c->max_b_frames=1;  //��ֵ��ʾ��������B֮֡�䣬����������B֡�����֡��
	//c->b_frame_strategy=TRUE;  //���Ϊtrue�����Զ�����ʲôʱ�����B֡
	c->pix_fmt = PIX_FMT_YUV420P;

	c->me_method = 2;  //�˶���ⷽʽ  Me_EPZS ME_HEX ME_UMH ME_FULL ME_ESA
	c->me_range = 16;  //�˶����뾶
	c->max_qdiff = 4;
	c->qmin = 15;//��С�������� ȡֵ1-51 10-30֮��
	c->qmax = 51;////��da�������� ȡֵ1-51 10-30֮��
	c->qcompress = 0.6;

	//B��P֡��ǰԤ��ο���֡����ȡֵ��Χ1-16����ֵ��Ӱ�������ٶȣ���Խ����������ڴ�Խ��
	//���ֵһ��Խ��Ч��Խ�ã�������6�Ժ�Ч���Ͳ������ˣ���X264��Ϊi_frame_reference
	c->refs=4;

	//ffmpeg��CBR���Կ��Ƶ÷ǳ��ã����趨ֵbrʮ�ֽӽ�
	//ffmpeg��VBR���Ƶ÷ǳ����ã��������maxû������ס

	//ffmpeg��CBR���̶����ʿ��ƣ������ã�
	c->bit_rate = bit_rate;
	c->rc_min_rate =bit_rate;  //���������x264�е�λkbps��ffmpeg�е�λbps
	c->rc_max_rate = bit_rate; //��С����
	c->bit_rate_tolerance = bit_rate;
	c->rc_buffer_size=bit_rate;
	c->rc_initial_buffer_occupancy = c->rc_buffer_size*3/4;
	c->rc_buffer_aggressivity= (float)1.0;
	c->rc_initial_cplx= 0.5;


	res = avcodec_open2(c, codec, NULL);
	if (res < 0)
	{
		fprintf(stderr, "could not open codec\n");
		LOGD("----------------------> init open codec failure..........");
		exit(1);
	}
	CreateYUVTab_16();
#endif
}


jint Java_com_frameclient_utils_H264Decoder_decode(JNIEnv *env, jobject thiz, jbyteArray in, jint size, jbyteArray out, jint scaler_width ,jint scaler_height,jint needScaler)
{
#ifdef TEST
	AVFrame *frameYUV;
	AVFrame *frameRGB;
	uint8_t    *fill_buffer;
	AVPacket avpkt;
	int i;
	int got_picture;
	int len;
	int ret;
	int got_frame;
	SwsContext* scaler_context;

	jbyte * Buf = (jbyte*)(*env)->GetByteArrayElements(env, in, 0);
	jbyte * Pixel= (jbyte*)(*env)->GetByteArrayElements(env, out, 0);


	frameYUV = avcodec_alloc_frame();

	if(needScaler)
	{
		frameRGB = avcodec_alloc_frame();

		int numBytes = avpicture_get_size(PIX_FMT_RGB565, scaler_width,scaler_height);
		fill_buffer = (uint8_t *)av_malloc(numBytes * sizeof(uint8_t));
		avpicture_fill((AVPicture *)frameRGB, fill_buffer, PIX_FMT_RGB565,scaler_width, scaler_height);
	}



	av_init_packet(&avpkt);
	avpkt.size = size;
	avpkt.data = (uint8_t*)Buf;

	len = avcodec_decode_video2(c, frameYUV, &got_picture, &avpkt);

	if (got_picture)
	{

		if(needScaler)
		{
			scaler_context = sws_getContext(c->width, c->height, c->pix_fmt,scaler_width, scaler_height, PIX_FMT_RGB565, SWS_BICUBIC, NULL, NULL, NULL);
			sws_scale(scaler_context, frameYUV->data, frameYUV->linesize, 0,  c->height, frameRGB->data, frameRGB->linesize);
			memcpy(Pixel, frameRGB->data[0], scaler_width * scaler_height * 2);

			av_free(frameRGB);
			frameRGB = NULL;

		}
		else
		{
			DisplayYUV_16((int*)Pixel, frameYUV->data[0], frameYUV->data[1], frameYUV->data[2], c->width, c->height, frameYUV->linesize[0], frameYUV->linesize[1], c->width);
		}

	    (*env)->ReleaseByteArrayElements(env, in, Buf, 0);
	    (*env)->ReleaseByteArrayElements(env, out, Pixel, 0);
		av_free(frameYUV);
		frameYUV = NULL;


		return 1;

	}
	else
	{
	    (*env)->ReleaseByteArrayElements(env, in, Buf, 0);
	    (*env)->ReleaseByteArrayElements(env, out, Pixel, 0);
		av_free(frameYUV);
		frameYUV = NULL;

		if(needScaler)
		{
			av_free(frameRGB);
			frameRGB = NULL;
		}


		return 0;
	}


#endif
}


void Java_com_frameclient_utils_H264Decoder_uninit(JNIEnv *env, jobject thiz)
{
#ifdef TEST
	if(c)
	{
	    free(c->priv_data);
		free(c);
		c = NULL;
	}

	DeleteYUVTab();
#endif
}
