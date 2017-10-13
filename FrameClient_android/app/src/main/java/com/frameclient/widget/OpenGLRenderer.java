package com.frameclient.widget;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.LinkedList;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.util.Log;

import com.frameclient.activitys.VideoActivity;
import com.frameclient.utils.BufferUtil;
import com.frameclient.utils.GLImage;
import com.frameclient.utils.SoftResource;


public class OpenGLRenderer implements Renderer
{	
	int width;
	int height;
	Buffer pixels;
	Bitmap mBitmap = null;
	Bitmap scaler_bitmap;
	boolean start = false;
	Context context;
	
	String TAG = "OpenGLRenderer"; 
	
    private IntBuffer vertexBuffer;  
    private IntBuffer texBuffer;  
    private float xrot, yrot, zrot;  
    private int one = 0x10000;  
    
    FloatBuffer vertices;  
    FloatBuffer texture;  
    ShortBuffer indices;  
    int textureId;  
    
    public LinkedList<Bitmap> bit_queue = new LinkedList<Bitmap>();
  
    
    public OpenGLRenderer(Context context) {  
        this.context = context;  
        
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 2 * 4);  
        byteBuffer.order(ByteOrder.nativeOrder());              
        vertices = byteBuffer.asFloatBuffer();  
//        vertices.put( new float[] {  -80f,   -120f,0,1f,  
//                                     80f,  -120f, 1f,1f,  
//                                     -80f, 120f, 0f,0f,  
//                                     80f,120f,   1f,0f});  
        vertices.put( new float[] {  -240f,   -240f,  
                                     240f,  -240f,   
                                     -240f, 240f,  
                                     240f,  240f});  
          
        ByteBuffer indicesBuffer = ByteBuffer.allocateDirect(6 * 2);  
        indicesBuffer.order(ByteOrder.nativeOrder());   
        indices = indicesBuffer.asShortBuffer();  
        indices.put(new short[] { 0, 1, 2,1,2,3});  
          
        ByteBuffer textureBuffer = ByteBuffer.allocateDirect(4 * 2 * 4);  
        textureBuffer.order(ByteOrder.nativeOrder());              
        texture = textureBuffer.asFloatBuffer();  
        texture.put( new float[] { 0,1f,  
                                    1f,1f,  
                                    0f,0f,  
                                    1f,0f});  
        indices.position(0);  
        vertices.position(0);  
        texture.position(0);  
    } 


    public void setSourceData(Buffer buf)
	{
		pixels = buf;
		start = true;
		
	}  
    
	 @Override
	 public void onDrawFrame(GL10 gl) {
	  // TODO Auto-generated method stub
		  	
	       gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);  

	       
	       
       
       		gl.glViewport(0, 0, width, height);   
            gl.glMatrixMode(GL10.GL_PROJECTION);  
            gl.glLoadIdentity();  
            gl.glOrthof(-240, 240, -240, 240, 1, -1);  

            int textureIds[] = new int[1]; 
            gl.glEnable(GL10.GL_TEXTURE_2D);  
            
            gl.glGenTextures(1, textureIds, 0);  
	        // 3.绑定要使用的纹理  
		    gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIds[0]); 
		    
		 
		    
		    GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, GLImage.iBitmap, 0); 
		    
		    /*
		     mBitmap = SoftResource.bitmap;
		    if(mBitmap == null)
		    {
		    	return;
		    }
		    else
		    {
		    	GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap, 0); 
	  	   
		    }
		    */
		    
		    //Log.i(TAG,"run...........");
		    //gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIds[0]);
			gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST);  
			gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_NEAREST);
		
			//gl.glTranslatef(0.0f, 0.0f, -2.0f); 
			//gl.glBindTexture(GL10.GL_TEXTURE_2D, 0); 
		  
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);  
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);  
  
            gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertices);  
  
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texture);
		            
		    // gl.glRotatef(1, 0, 1, 0);  
            gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 6,  
		    GL10.GL_UNSIGNED_SHORT, indices); 
            //gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0,4); 
		            	        
		  
		        // 9.关闭顶点和纹理功能  
			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);  
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	        
        //mBitmap.recycle();
         
         
	 }

	 @Override
	 public void onSurfaceChanged(GL10 gl, int width, int height) {
	  // TODO Auto-generated method stub
		 	this.width = width;
		 	this.height = height;
		 	//设置OpenGL场景的大小       
	        gl.glViewport(0, 0, width, height);//设置视口
	        
	        float ratio = (float) (width)/height;  
	        //设置投影矩阵  
	        gl.glMatrixMode(GL10.GL_PROJECTION);  
	        //重置投影矩阵  
	        gl.glLoadIdentity();  
	        // 设置视口的大小  
	        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);  
	        // 选择模型观察矩阵  
	        gl.glMatrixMode(GL10.GL_MODELVIEW);   
	        // 重置模型观察矩阵  
	        gl.glLoadIdentity(); 
		 
       
	 }

	@Override
	public void onSurfaceCreated(GL10 gl,
			javax.microedition.khronos.egl.EGLConfig config) {
		// TODO Auto-generated method stub
		
	      // 告诉系统对透视进行修正     
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);    
  
        // 绿色背景  
        gl.glClearColor(1, 1, 0, 0);  
          
        gl.glEnable(GL10.GL_CULL_FACE);  
        // 启用阴影平滑  
        gl.glShadeModel(GL10.GL_SMOOTH);  
        // 启用深度测试  
        gl.glEnable(GL10.GL_DEPTH_TEST);  
          
        //启用纹理映射  
        gl.glClearDepthf(1.0f);  
        //深度测试的类型  
        gl.glDepthFunc(GL10.GL_LEQUAL);  
         
      
        /*
        // 1.允许2D贴图,纹理  
        gl.glEnable(GL10.GL_TEXTURE_2D);  
        // 2.创建纹理  
        gl.glGenTextures(1, textureids, 0);  
        // 3.绑定要使用的纹理  
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureids[0]);  
        // 4.生成纹理  
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, GLImage.iBitmap, 0);  
          
        // 5.线性滤波  
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST);  
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_NEAREST);  */
         
		  

	}
	

}
