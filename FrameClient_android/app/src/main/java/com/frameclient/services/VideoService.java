package com.frameclient.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.frameclient.services.NetWorkService.KeepAlive;
import com.frameclient.services.NetWorkService.MsgReceiver;
import com.frameclient.utils.Frame;
import com.frameclient.utils.FrameInfo;
import com.frameclient.utils.H264Decoder;
import com.frameclient.utils.LoginRsp;
import com.frameclient.utils.SoftResource;
import com.frameclient.utils.Tcp;
import com.frameclient.utils.VideoInfo;

import android.R.bool;
import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;

public class VideoService extends IntentService
{

	public VideoService() {
		super("VideoService");
		// TODO Auto-generated constructor stub
	}

	private String TAG = "VideoService";
    private Thread keepalive;
    private Thread msgReceiver;
    private Thread dataDecoder;
    private String uid;
    private String cameraid;
    private String encoder_ip;
    
    int width = 352;
    int height = 288;
	
    int data_buf_len = 0;
    
	private Tcp tcp = null;
    private H264Decoder decoder = null;
    private ConcurrentLinkedQueue<Frame> h264_queue = new ConcurrentLinkedQueue<Frame>();
    
    boolean respons = false;
    boolean first_frame = false;
    
	byte [] mPixel = null;      
	Bitmap bitmap = null;
	ByteBuffer buffer = null;
	
	@Override
	 protected void onHandleIntent(Intent arg0) {
	    	int res = 0;
	    	int opera = arg0.getExtras().getInt("opera");  
	        switch (opera)
	        {
	        case 0:   //主循环
	        	
			    if(tcp != null)
	        	{
	        		tcp = null;
	        	}
			    
	        	tcp = new Tcp();
	        	
	        	if(decoder != null)
	        	{
	        		decoder = null;
	        	}
	        	decoder = new H264Decoder();
	        	
 				String ipaddr = arg0.getExtras().getString("stream");
 	 			uid = arg0.getExtras().getString("uid");
 	 			cameraid = arg0.getExtras().getString("id");
 	 			
 	 			Log.i(TAG, "get connect params ipaddr =  "+ipaddr+ " uid "+uid+" cameraid =  "+cameraid);
 	 		
	 			res = ipaddr.indexOf(":");
	 	
	 			if(res < 0)
	 			{
	 				if(ipaddr.equals("127.0.0.1"))
	 				{
	 					ipaddr = SoftResource.login_ip;
	 				}
	 				Log.i(TAG, "connect "+ipaddr+ " port "+SoftResource.videostream_socket_port);
	 				res = tcp.connect_sever(ipaddr, SoftResource.videostream_socket_port);
	 			}
	 			else
	 			{
	 				String [] str = null;  
	 	 			str = ipaddr.split(":");  
	 	 			Log.i(TAG,"split ipaddr string  ip = "+str[0]+" port = "+str[1]);
	 	 			if(str[0].equals("127.0.0.1"))
	 	 			{
	 	 				str[0] = SoftResource.login_ip;
	 	 			}
	 	 			
	 				res = tcp.connect_sever(str[0],Integer.parseInt(str[1]));
	 				Log.i(TAG, "connect "+str[0]+ " : port "+Integer.parseInt(str[1]));
	 			}
	 
	 			if(res < 0)
	 			{
	 				Intent it = new Intent("com.frameclient.videostart.rsp");
	 				it.putExtra("error", SoftResource.err_net_failure);
	 				Log.i(TAG,"connect stream server failure");
	 				sendBroadcast(it);
	 				
	 			}
	 			else
	 			{			
					
	 				
					msgReceiver = new Thread(new MsgReceiver());// 启动收听线程线程
					msgReceiver.start();
					Log.i(TAG,"msgReceiver start !!!");
					
					dataDecoder = new Thread(new DataDecoder());
					dataDecoder.start();
					
					Log.i(TAG,"dataDecoder start !!!");
					
					tcp.sendGetVideoMsg(uid, cameraid);
					Log.i(TAG,"sendGetVideoMsg start cameraid = "+cameraid);
					
					keepalive = new Thread(new KeepAlive()); //启动心跳包
					keepalive.start();
					Log.i(TAG,"keep alive thread start!!!");
	 			}
		
	        	
	        	break;
	        }
	        Looper.loop(); 
	}
	
   private BroadcastReceiver broadcastRec = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
	 		if(intent.getAction().equals("com.frameclient.videostart"))
    		{	
    				//tcp.sendGetVideoMsg(uid, cameraid);
    				//Log.i(TAG,"sendGetVideoMsg start!!!");
    		}
    		else if(intent.getAction().equals("com.frameclient.videoend"))
    		{
    		
    		}
    		else if(intent.getAction().equals("com.frameclient.videoclose"))
    		{
    			Log.i(TAG,"videoclose recivice!!!");
    			int rsp = intent.getIntExtra("respons",0);
    			if(rsp > 0)
    			{
    				respons = true;
    			}
    			
    			tcp.workstatus = false;
    			unregisterReceiver(broadcastRec);
    			stopSelf();
    		}
    		else if(intent.getAction().equals("com.frameclient.control"))
    		{
    		    int control_cmd_type = intent.getIntExtra("type", -1);
    			
    			if(control_cmd_type < 0)
    			{
    				Log.e(TAG, "Camera control command error control_cmd_type = "+control_cmd_type);
    				return;
    			}
    			
    			Log.e(TAG, "Camera control command control_cmd_type = "+control_cmd_type);
    			tcp.sendCameraControlCmdMsg(uid, cameraid, control_cmd_type);
    		}
		}
    };
	
	
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate()
	{
		super.onCreate(); 
		Log.v(TAG, "--->onCreate");  
		
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.frameclient.videostart");
        intentFilter.addAction("com.frameclient.videoend");
        intentFilter.addAction("com.frameclient.videoclose");
        intentFilter.addAction("com.frameclient.control");
        registerReceiver(broadcastRec,intentFilter);
	}


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("deprecation")
	@Override  
     public void onStart(Intent intent, int startId) 
	 {  
        Log.v(TAG, "--->onStart");  
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
    } 
	
	 
	@Override
	public void onDestroy()
	{
		
		tcp.workstatus = false;
	
		if(respons == true)
		{
			Intent intent =new Intent("com.frameclient.videoserver.dead");	                       		                  	        
	        sendBroadcast(intent);
		}
		
		Log.v(TAG, "--->onDestroy");
	}
	
	class KeepAlive implements Runnable
	{
    	@Override
    	public void run()
    	{
    		
    		try 
    		{
    			while(tcp.workstatus)
    			{
    				tcp.sendKeepAlive();
    				Thread.sleep(30000);
    			}	
    			
    			Log.i(TAG,"KeepAlive over");
			} 
    		catch (InterruptedException e)
    		{
				// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
			
		}
	}
	
	class DataDecoder implements Runnable{
		@SuppressLint("NewApi")
		@Override
		public void run(){
		
			Frame frame = null;    
        	int count = 0;
        	int size = 0;
        	boolean isDrop = false;
        	int i = 0;
			while(tcp.workstatus)
			{
			
				//Log.i(TAG,"h264 decoder is run....sizeof = "+h264_queue.size());
				i++;
				if(i % 50 == 0)
				{
					size = h264_queue.size(); //这个操作太耗时 减少调用的次数
					//Log.e(TAG,"call size() once time size = " + size);
					i = 0;
				}
				
				if(!h264_queue.isEmpty())
				{
					
					if(size > 100)
					{
						int num = 0;
						int datalen = 0;
						isDrop = true;
						Log.v(TAG,"size > 100");
						while(!h264_queue.isEmpty())
						{
							frame = h264_queue.poll();
							if(frame != null)
							{
								data_buf_len -= frame.info.datalen;
								if(frame.info.frametype == 0)
								{
									num++;
									datalen += frame.info.datalen;
									//Log.i(TAG,"draw a not idr frame data len  " +frame.info.datalen );
									continue;
								}
								else
								{
			        		       count= decoder.decode(frame.data,frame.info.datalen, mPixel,0,0,0);
			        		       //Log.v(TAG,"len:"+len+"count: "+count + " data[0]" + data_array[0]+" data[1]" + data_array[1]+" data[2]" + data_array[2]+" data[3]" + data_array[3]+" data[4]" + data_array[4]);
			        		       if(count > 0)
			        		       {
			        		    	   	//Log.i(TAG,"decode "+frame.info.frameId + "  frame SoftResource.bmp_queue.size() = "+SoftResource.bmp_queue.size());
			        		    	    //Log.i(TAG,"when delete frame decode I frame ");
			            		 		bitmap.copyPixelsFromBuffer(buffer); 
						        		buffer.position(0); 
						        		SoftResource.bmp_queue.add(bitmap);
						        		
						        		Intent intent =new Intent("com.frameclient.videodata");	                       		       
						        		intent.putExtra("data", 1);		           	        
			        		            sendBroadcast(intent);
			        		            	
			        		       }
			        		       else
			        		       {
			        		    	 	Log.i(TAG,"decode "+frame.info.frameId + " failue queue size  "+h264_queue.size());
			        		       }
								}
							}
						}
						size = 0;
						Log.v(TAG,"h264_queue is clear");
						//Log.v(TAG,"======================> end draw frame draw "+num+" frame data len "+datalen);
					
					}
					else
					{
						if(!h264_queue.isEmpty())
						{
							frame = h264_queue.poll();
							if(frame != null)
							{
								
									if(isDrop == true)
									{
										if(frame.info.frametype == 0)
										{
											//Log.v(TAG,"normal decoder but isDrop true");
											continue;
										}
										else
										{
											//Log.v(TAG,"normal decoder start find the first idr frame");
											isDrop = false;
										}
									}
								   //Log.v(TAG,"len: "+frame.info.datalen+" frameId: "+frame.info.frameId + " data[0]" + frame.data[0]+" data[1]" + frame.data[1]+" data[2]" + frame.data[2]+" data[3]" + frame.data[3]+" data[4]" + frame.data[4]);
			        		       count= decoder.decode(frame.data,frame.info.datalen, mPixel,0,0,0);
			        		       //Log.v(TAG,"len:"+len+"count: "+count + " data[0]" + data_array[0]+" data[1]" + data_array[1]+" data[2]" + data_array[2]+" data[3]" + data_array[3]+" data[4]" + data_array[4]);
			        		       if(count > 0)
			        		       {
			        		    	   	//Log.i(TAG,"decode "+frame.info.frameId + "  frame SoftResource.bmp_queue.size() = "+SoftResource.bmp_queue.size());
			        		    	   
			            		 		bitmap.copyPixelsFromBuffer(buffer); 
						        		buffer.position(0); 
						        		SoftResource.bmp_queue.add(bitmap);
						        		
						        		Intent intent =new Intent("com.frameclient.videodata");	                       		       
						        		intent.putExtra("data", 1);		           	        
			        		            sendBroadcast(intent);
			        		            	
			        		       }
			        		       else
			        		       {
			        		    	 	Log.i(TAG,"decode "+frame.info.frameId + " failue queue size  "+h264_queue.size()+" datalen = "+frame.info.datalen);
			        		       }
			        		       
			        		       if(first_frame == false)
			        		       {			                       	        	
			            	        	Intent intent = new Intent("com.frameclient.video.firstframe");
			            	        	sendBroadcast(intent);
			            	        	first_frame = true;		     
			        		       }
			        		       
			        		       data_buf_len -= frame.info.datalen;
								
							}
						}
					}

				}
				else
				{
					 try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
			Log.i(TAG,"decoder over");
		}
		
	}
	    
	    
    class MsgReceiver implements Runnable {
    	@Override
		public void run() {

        	int len = 0;
        	int opera = 0;
        	
        	int res = 0;
        	
        	int timer = 0;  //判断有无数据
        	int index = 0;
        	
        	byte msglen[] = new byte[4];
        	byte msgopera[] = new byte[4];
        	
        	
        	VideoInfo video_info = null;   	

        	
        	try
        	{
				while (tcp.workstatus)
        		{
        			
        			while(tcp.selector.select(1000)>0 && tcp.workstatus)
        			//while(true && tcp.workstatus)
        			{
        				  Iterator<SelectionKey> it = tcp.selector.selectedKeys().iterator();    				 
        				  while(it.hasNext())
        				  { 
        					   SelectionKey key = it.next(); //使用迭代器遍历      					      					   
        					   it.remove(); //删除迭代器
        					   timer = 0;
        					   if(key.isReadable())
        					   {
        	
        						   	SocketChannel channel = (SocketChannel) key.channel();
        						   	ByteBuffer h = ByteBuffer.allocateDirect(8);
        						   	int ret = tcp.recv(h, channel, 8);
        						    
        						   	h.get(msglen,0,4);
        						   	h.get(msgopera,0,4);
	                       			
	                       			len = tcp.byteArray2Int(msglen);
	                       			opera = tcp.byteArray2Int(msgopera);	
	                       		
	                       			len = len - 4; //减去包头中oprea占用的4个字节 才是数据的真实长度
	                       			
	                       		    //Log.i(TAG,"recv opera "+opera + " len "+len);
	                       			
	                       			switch(opera)
	                       			{
	                       			case 0:
	                       				break;
	                       			/*
	                       			case 1:
	                       			{
	                       				Intent intent = new Intent("com.frameclient.video.pixel.change");
	                       				sendBroadcast(intent);
	                       				break;
	                       			}*/
	                       			
	                       			/*
	                      			case 1: // login
                        			{
                        				
										Log.v(TAG,"recv login rsp packet : len = "+len+" opera = "+opera);
										ByteBuffer content = ByteBuffer.allocateDirect(len);
										int read_total = tcp.recv(content,channel,len);
										byte[] cnt = new byte[len];
										content.get(cnt);
										//String c = new String(content.array());
										String c = new String(cnt);
										Log.v(TAG,"login rsp = "+c);
										LoginRsp rsp = tcp.parseLoginRsp(cnt);
										if(rsp.error == 0)
										{											
								 			res = encoder_ip.indexOf(":");
								 		 	
								 			if(res < 0)
								 			{
								 				if(encoder_ip.equals("127.0.0.1"))
								 				{
								 					encoder_ip = SoftResource.login_ip;
								 				}
								 				res = tcp.connect_sever(encoder_ip, SoftResource.videostream_socket_port);
								 			}
								 			else
								 			{
								 				String [] str = null;  
								 	 			str = encoder_ip.split(":");  
								 	 			Log.i(TAG,"split ipaddr string  ip = "+str[0]+" port = "+str[1]);
								 	 			if(str[0].equals("127.0.0.1"))
								 	 			{
								 	 				str[0] = SoftResource.login_ip;
								 	 			}
								 	 			
								 				res = tcp.connect_sever(str[0],Integer.parseInt(str[1]));
								 			}
								 
								 			if(res < 0)
								 			{
								 				Intent intent = new Intent("com.frameclient.videostart.rsp");
								 				intent.putExtra("error", SoftResource.err_net_failure);
								 				sendBroadcast(intent);
								 				Log.i(TAG,"connect = "+encoder_ip+" failure");
								 			}
								 			else
								 			{			
																	 									
												tcp.sendGetVideoMsg(rsp.uid, cameraid);
												Log.i(TAG,"sendGetVideoMsg start cameraid = "+cameraid);
												
								 			}
											
											Log.i(TAG,"login successful uid = "+rsp.uid);
											Intent login_intent = new Intent("com.frameclient.otherlogin.rsp");
											login_intent.putExtra("error", 0); //登陆成功
											sendBroadcast(login_intent);
											
							
										}
										else
										{
											Log.i(TAG,"login failure");
											Intent intent = new Intent("com.frameclient.otherlogin.rsp");
											intent.putExtra("error", rsp.error);
											sendBroadcast(intent);
										}
							
										break;
                        			}
                        			
	                       			*/
	                       			case 13:
	                       			{
	                       				ByteBuffer data = ByteBuffer.allocateDirect(len);   
		                       		    int total_read = tcp.recv(data, channel, len);
		                       		    
		                       		    /*
		                       			byte[] cnt = new byte[len];
		                       			data.get(cnt);
										String c = new String(cnt);
										Log.v(TAG,"13 rsp = "+c);
										*/
										break;
	                       			}
	                       			case 100: //frame info data
	                       			{
	                       			   ByteBuffer data = ByteBuffer.allocateDirect(len);   
		                       		   int total_read = tcp.recv(data, channel, len);
		                       		   
		                       		   byte data_type[] = new byte[1];
		                       		   byte data_data_type[] = new byte[1];
		                       		   byte data_array[] = new byte[len - 2];
		                       		   
		                       		   
		                       		   data.get(data_type,0,1);
		                       		   data.get(data_data_type,0,1);
		                       		   data.get(data_array,0,len-2);
		                       		  
		         
		                       		   int type1 = data_type[0] & 0xFF;
		                       		   int type2 = data_data_type[0] & 0xFF;
		                       		 
		             		                       		   
		                       		   switch(type1)
		                       		   {
		                       		   case 0:
			                       		   {
			                       		
			                       			   byte err[] = new byte[4];
			                       			   System.arraycopy(data_array,0, err, 0, 4);
			                       			   
			                       			   int errno = Tcp.byteArray2Int(err);
			                       			   Log.e(TAG,"rece video data err no. "+errno);
			                       			   Intent intent = new Intent("com.frameclient.video.err");
			                       			   intent.putExtra("error", errno);
			                       			   sendBroadcast(intent);
			                       			  
			                       			   
			                       			   break;
			                       		   }
		                       		   case 1:
			                       		   {
			                       			   if(type2 == 0)
			                       			   {
			                       				    String str = new String(data_array);
			                       				  
				                       		        if(video_info != null)
				                       		        {
				                       		        	video_info = null;
				                       		        }
				                       		        
				                       		        
				                       		        video_info = tcp.parseVideoInfo(data_array);	                      		        
				                      	        	mPixel = new byte[video_info.width*video_info.height*2];      
				                       	        	bitmap = Bitmap.createBitmap(video_info.width,video_info.height, Config.RGB_565);
				                       	        	buffer = ByteBuffer.wrap(mPixel);
				                       	        	decoder.init(video_info.vidoe_codec_id,video_info.width,video_info.height,video_info.fps,video_info.bit_rate,video_info.gop_size > 0?video_info.gop_size:100);
				                       	            Log.v(TAG,"10010 rece video stream data header: "+str);
				                       				break;
			                       				 
			                       			   }
			                       			   else if(type2 == 1)
			                       			   {
			                       				  String str = new String(data_array);
			                       				  Log.v(TAG,"rece device stream data header: "+str);
			                       			   }
			                       			   break;
			                       		   }
		                       		   case 2:
			                       		   {
			                       			   index++;
				                       		   Frame frame = new Frame(len -2 ,data_array);
			                       			   frame.info.frameId = index;
			                       			   frame.info.datalen = len - 2;
			                       			   data_buf_len += frame.info.datalen;
			                       			   
			                       			   if(frame.data[4] == 0x67)
			                       			   {
			                       				   frame.info.frametype = 1;
			                       			   }
			                       			   else
			                       			   {
			                       				   frame.info.frametype = 0;
			                       			   }
			                       			   
			                       			   //Log.v(TAG,"rece the "+index+" frame data len "+frame.info.datalen + " all buf "+data_buf_len);
			                       			   h264_queue.add(frame);
			                       			   break;
			                       			
			                       		   }
		                       		   case 3:
			                       		   {
		                       				  Log.v(TAG,"rece audio stream data header");
			                       			  break;
			                       		   }
		                       		   }
		                       		  break;  
		                       		
	                       			}
	                          		case 102: //get text
	                       			{
	                       					ByteBuffer content = ByteBuffer.allocateDirect(len);
	                       					int total_read = tcp.recv(content, channel, len);
	                       					content.flip();

	                       					CharsetDecoder decoder = tcp.charset.newDecoder();
	                       					CharBuffer charBuffer = decoder.decode(content);
	                       					String result = charBuffer.toString();
											
											break;
	                       			}
	                        		case 216: //frame info data
	                       			{
	                       				ByteBuffer videoinfo = ByteBuffer.allocateDirect(len);
	                       				int total_read = tcp.recv(videoinfo, channel, len);
	                       				
	                       				byte data_array[] = new byte[len - 2];
	                       				//videoinfo.get
	                       				System.arraycopy(videoinfo.array(),2, data_array, 0, len -2);
	                       				
	                       				String str = new String(videoinfo.array());
	                       				Log.d(TAG,"rece requst video dsp packet:"+str);
	                       				
	                       		        if(video_info != null)
	                       		        {
	                       		        	video_info = null;
	                       		        }
	                       		        
	                       		        video_info = tcp.parseVideoInfo(data_array);	                      		        
	                      	        	mPixel = new byte[video_info.width*video_info.height*2];      
	                       	        	bitmap = Bitmap.createBitmap(video_info.width,video_info.height, Config.RGB_565);
	                       	        	buffer = ByteBuffer.wrap(mPixel);
	                       	        	decoder.init(video_info.vidoe_codec_id,video_info.width,video_info.height,video_info.fps,video_info.bit_rate,video_info.gop_size > 0?video_info.gop_size:100);
	   
	                       				break;
	                       			}
	                       			
	                       			default:
	                       			 
	                       				break;
	                       			
	                       			}
        					   }
        					   else
        					   {
        						   Thread.sleep(10);
        					   }
        				  }
        				  
        			}
        			timer++;
        			if(timer > SoftResource.timeout) //超过十秒无数据
        			{
        				Log.d(TAG,"in "+SoftResource.timeout+" sec no data....");
        				Intent intent =new Intent("com.frameclient.nodata");	                       		                  	        
       		            sendBroadcast(intent);
       		            Log.e("TCP","00000001");
       		            tcp.conn_close(); 
        				break;
        			}
        			
        		} 
				
				Log.i(TAG,"reciver over");
        	}
        	catch (Exception ex)
        	{
                tcp.workstatus = false;// 如果出现异常，提示网络连接出现问题。
                ex.printStackTrace();
				Intent intent =new Intent("com.frameclient.socket.err");	                       		                  	        
		        sendBroadcast(intent);	
		        Log.e("TCP","00000002");
				tcp.conn_close(); 
            }
        	finally
        	{	Log.e("TCP","00000003");
        		tcp.conn_close(); 
        	}
        	
    	}
    	
    }   
}