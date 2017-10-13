package  com.frameclient.services;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import com.frameclient.utils.LoginRsp;
import com.frameclient.utils.NotifyInfo;
import com.frameclient.utils.SoftResource;
import com.frameclient.utils.Tcp;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;

public class NetWorkService extends IntentService{  
    private static final String TAG = "networkservice" ;  
    public static final String ACTION = "com.frameclient.services.networkservice"; 
    private Thread keepalive;
    private Thread msgReceiver;
	private Tcp tcp = null;
    
    private Context mContext = this;
    private String user = "";
    
    public NetWorkService() {  
        // 必须定义一个无参数的构造方法，并调用super(name)进行初始化，否则出错。  
        super("NetWorkService");  
        
    }  
    
    
    @Override
    protected void onHandleIntent(Intent arg0) {
    	
    	int opera = arg0.getExtras().getInt("opera");  
        switch (opera) {
        case 0:  //connection 
        {
            Log.i(TAG,"onHandleIntent conn");
            
            tcp = new Tcp();
            
            String ipaddr = arg0.getExtras().getString("ipaddr");
            
            int res = 0;
 			res = ipaddr.indexOf(":");
 	
 			if(res < 0)
 			{
 				SoftResource.login_ip = ipaddr;
 				res = tcp.connect_sever(ipaddr, SoftResource.login_socket_port);
 			}
 			else
 			{
 				String [] str = null;  
 	 			str = ipaddr.split(":");  
 	 			SoftResource.login_ip = str[0];
 	 			Log.i(TAG,"login ip split ipaddr string  ip = "+str[0]+" port = "+str[1]);
 				res = tcp.connect_sever(str[0],Integer.parseInt(str[1]));
 			}
			
 			if(res == 0)
 			{			
				if(keepalive == null)
				{
    				keepalive = new Thread(new KeepAlive()); //启动心跳包
    				keepalive.start();
    				Log.i(TAG,"keep alive thread start!!!");
				}
    			
				if(msgReceiver == null)
				{
    				msgReceiver = new Thread(new MsgReceiver());// 启动收听线程线程
    				msgReceiver.start();
    				Log.i(TAG,"msgReceiver thread start!!!");
				}
				
		       	String username = arg0.getExtras().getString("username");
				String password = arg0.getExtras().getString("password");
				
				tcp.sendLoginMsg(username,password);   			
				user = username;
 			}
 			else
 			{
 				Log.v(TAG,"res = "+res+" ipaddr = "+ipaddr);
 				
 				Intent conn_rsp = new Intent("com.frameclient.connection.rsp");
 				conn_rsp.putExtra("result", res);
 				
 				sendBroadcast(conn_rsp);	
 			}

            break;
        }
        case 2: //get resource
        {
        	
        	Log.i(TAG,"onHandleIntent get resource");
        	String uid = arg0.getExtras().getString("uid");
 			String resource_id = arg0.getExtras().getString("resource_id");
 			int type = arg0.getExtras().getInt("type", -1);
 			int from = arg0.getExtras().getInt("from", 0);
 			
 			if(from == 0)
 			{
 				Log.v(TAG,"recv com.frameclient.getresource message uid ="+uid+" resource_id = "+resource_id+" type = " + type + " from = "+from);
 				tcp.sendGetResourceMsg(uid,resource_id,type);
 			}
 			else
 			{
 				
 				String stream_server = arg0.getExtras().getString("stream_server");
 				Log.v(TAG,"recv com.frameclient.getresource message uid ="+uid+" resource_id = "+resource_id+" stream_server = " + stream_server + " from = "+from);
 				tcp.sendGetResourceMsg(uid,resource_id,stream_server);
 				
 			}
        	break;
        }
        case 3:
        {
        	  Log.i(TAG,"onHandleIntent change pwd");
        	String curr_pass = arg0.getExtras().getString("curr_pass");
 			String new_pass = arg0.getExtras().getString("new_pass");    			
 			try {
				tcp.sendChangePasswordMsg(user, curr_pass, new_pass);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	break;
        }
        default:
            break;
        }
        
        Looper.loop(); 
    }
    
    
    private BroadcastReceiver broadcastRec = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
	 		if(intent.getAction().equals("com.frameclient.connection"))
    		{
	 			Socket so = null;
	 			InputStream in = null;
    			String ipaddr = intent.getStringExtra("ipaddr");
    			
     			int res = 0;
     			res = ipaddr.indexOf(":");
     	
     			if(res < 0)
     			{
     				SoftResource.login_ip = ipaddr;
     				res = tcp.connect_sever(ipaddr, SoftResource.login_socket_port);
     			}
     			else
     			{
     				String [] str = null;  
     	 			str = ipaddr.split(":");  
     	 			SoftResource.login_ip = str[0];
     	 			Log.i(TAG,"login ip split ipaddr string  ip = "+str[0]+" port = "+str[1]);
     				res = tcp.connect_sever(str[0],Integer.parseInt(str[1]));
     			}
 			
     			if(res == 0)
     			{			
    				if(keepalive == null)
    				{
	    				keepalive = new Thread(new KeepAlive()); //启动心跳包
	    				keepalive.start();
	    				Log.i(TAG,"keep alive thread start!!!");
    				}
	    			
    				if(msgReceiver == null)
    				{
	    				msgReceiver = new Thread(new MsgReceiver());// 启动收听线程线程
	    				msgReceiver.start();
	    				Log.i(TAG,"msgReceiver thread start!!!");
    				}
     			}
    			Log.v(TAG,"res = "+res+" ipaddr = "+ipaddr);
    			
				Intent conn_rsp = new Intent("com.frameclient.connection.rsp");
				conn_rsp.putExtra("result", res);
				
				sendBroadcast(conn_rsp);
				
    		}
    		else if(intent.getAction().equals("com.frameclient.login"))
    		{
    			String username = intent.getStringExtra("username");
    			String password = intent.getStringExtra("password");
    			tcp.sendLoginMsg(username,password);   			
    			user = username;
    			
    		}
     		else if(intent.getAction().equals("com.frameclient.logout"))
    		{

    			int uid = intent.getIntExtra("uid", 0);
    		}
     		else if(intent.getAction().equals("com.frameclient.getresource"))
     		{
     			
     			String uid = intent.getStringExtra("uid");
     			String resource_id = intent.getStringExtra("resource_id");
     			int type = intent.getIntExtra("type", -1);
     			int from = intent.getIntExtra("from", 0);
     			
     			if(from == 0)
     			{
     				Log.v(TAG,"recv com.frameclient.getresource message uid ="+uid+" resource_id = "+resource_id+" type = " + type + " from = "+from);
     				tcp.sendGetResourceMsg(uid,resource_id,type);
     			}
     			else
     			{
     				
     				String stream_server = intent.getStringExtra("stream_server");
     				Log.v(TAG,"recv com.frameclient.getresource message uid ="+uid+" resource_id = "+resource_id+" stream_server = " + stream_server + " from = "+from);
     				tcp.sendGetResourceMsg(uid,resource_id,stream_server);
     				
     			}
     		}
     		else if(intent.getAction().equals("com.frameclient.changepassword"))
     		{
     			String curr_pass = intent.getStringExtra("curr_pass");
     			String new_pass = intent.getStringExtra("new_pass");    			
     			try {
					tcp.sendChangePasswordMsg(user, curr_pass, new_pass);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     		}
     		else if(intent.getAction().equals("com.frameclient.networkserver.over"))
     		{
     			tcp.sendQuitMsg(user,SoftResource.uid);
    			tcp.workstatus = false;
    			unregisterReceiver(broadcastRec);
    			stopSelf();
     		}
	 		
		}
    };
 
      
    @Override  
    public IBinder onBind(Intent intent) {  
        Log.v(TAG, "service onBind");  
        return null;  
    }  
      
    @SuppressLint("NewApi")
	@Override  
    public void onCreate() {  
        super.onCreate();  
        
        Log.v(TAG, "************service onCreate************");  

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.frameclient.connection");
        intentFilter.addAction("com.frameclient.login");
        intentFilter.addAction("com.frameclient.logout");
        intentFilter.addAction("com.frameclient.getresource");
        intentFilter.addAction("com.frameclient.networkserver.over");
        intentFilter.addAction("com.frameclient.changepassword");
        registerReceiver(broadcastRec,intentFilter);
        
        /** 在android4.0以上 访问网络不能在主线程，或者如下解决 或者另外开启一个线程*/

        
        /*
         * tcp = new Tcp();
         * 
         StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
        .detectDiskReads()  
        .detectDiskWrites()  
        .detectNetwork()   // or .detectAll() for all detectable problems  
        .penaltyLog()  
        .build());  
         StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
        .detectLeakedSqlLiteObjects()  
        .detectLeakedClosableObjects()  
        .penaltyLog()  
        .penaltyDeath()  
        .build());  
         */
        
    }  
      
    @Override  
    public void onStart(Intent intent, int startId) {  
        Log.v(TAG, "--->onStart");  
        super.onStart(intent, startId);
    }  
    
	@Override
	public void onDestroy()
	{
		Log.v(TAG, "--->onDestroy");  
        
        unregisterReceiver(broadcastRec);
        
	}
      
    @Override  
    public int onStartCommand(Intent intent, int flags, int startId) {  
        Log.v(TAG, "service onStartCommand");  
        return super.onStartCommand(intent, flags, startId);  
    }  
     
	class KeepAlive implements Runnable
	{
		int num = 0;
		
    	@Override
    	public void run()
    	{
    		
    		try 
    		{
    			while(tcp.workstatus)
    			{
    				
					//Log.v(TAG,"<<<<<<<<<<<<< keepAlive run "+num+" >>>>>>>>>>>>>>>");
    				tcp.sendKeepAlive();
    				Thread.sleep(30000);
    				num++;
    			}			
				
			} 
    		catch (InterruptedException e)
    		{
				// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
			
		}
	}
	
	
    class MsgReceiver implements Runnable {
    	@Override
		public void run() {
        	int len = 0;
        	int opera = 0;       
        	
        	byte msglen[] = new byte[4];
        	byte msgopera[] = new byte[4];
        
        	try
        	{
        		while(tcp.workstatus)
        		{
        			
        			
        			while(tcp.selector.select(1000)>0 && tcp.workstatus)
        			//while(true && tcp.workstatus)
        			{
        				  Iterator<SelectionKey> it = tcp.selector.selectedKeys().iterator();    				 
        				  while(it.hasNext())
        				  { 
        					   SelectionKey key = it.next(); //使用迭代器遍历      					      					   
        					   it.remove(); //删除迭代器
        					   if(key.isReadable())
        					   {
        						   	SocketChannel channel = (SocketChannel) key.channel();
    
        						   	ByteBuffer h = ByteBuffer.allocateDirect(8);
        						   	h.clear();
        						   	
        						   	int ret = tcp.recv(h, channel, 8);
        						    
        						   	h.get(msglen,0,4);
        						   	h.get(msgopera,0,4);
        						   	//System.arraycopy(h.array(),0,msglen,0,4);
	                       			//System.arraycopy(h.array(),4,msgopera,0,4); 	
	                  				                 			
        						   	len = tcp.byteArray2Int(msglen);
	                       			opera = tcp.byteArray2Int(msgopera);
	                       		
	                       			
	                       			len = len - 4; //减去包头中oprea占用的4个字节 才是数据的真实长度	
	                       			
	                       			//Log.v(TAG, "recv opera "+opera+" len "+len);
	                       			
                        			switch(opera)
                        			{
	                        			case 0: //keep alive
	                        			{
	                        				//Log.v(TAG,"recv keepalive packet : len = "+len+" opera = "+opera);
	                        				break;
	                        			}
	                        			case 1: // login
	                        			{
											//Log.v(TAG,"recv login rsp packet : len = "+len+" opera = "+opera);
											ByteBuffer content = ByteBuffer.allocateDirect(len);
											int read_total = tcp.recv(content,channel,len);
											byte[] cnt = new byte[len];
											content.get(cnt);
											String str = new String(content.array());
											//String str = new String(cnt);
											Log.v(TAG,"login rsp = "+str);
											LoginRsp rsp = tcp.parseLoginRsp(cnt);
											if(rsp.error == 0)
											{
												/*
												Intent intent = new Intent("com.frameclient.getresource");
												intent.putExtra("uid", rsp.uid);
												intent.putExtra("resource_id", rsp.source_id);
												intent.putExtra("from", 0);
												intent.putExtra("type", rsp.type);
												sendBroadcast(intent);
												*/
												
												tcp.sendGetResourceMsg(rsp.uid, rsp.source_id,rsp.type);
												
												
												Intent login_intent = new Intent("com.frameclient.login.rsp");
												login_intent.putExtra("error", 0); //登陆成功
												sendBroadcast(login_intent);
								
											}
											else
											{
												Intent intent = new Intent("com.frameclient.login.rsp");
												intent.putExtra("error", rsp.error);
												sendBroadcast(intent);
											}
											break;
	                        			}
	                        			case 3:
	                        			{
	                        				ByteBuffer content = ByteBuffer.allocateDirect(len);
											int read_total = tcp.recv(content,channel,len);
											String str = new String(content.array());
											Log.v(TAG,"change password rsp = "+str);
											int error = tcp.parseChangePasswordRsp(content.array());
											Intent intent = new Intent("com.frameclient.changepassword.rsp");
											intent.putExtra("rsp", error);
											sendBroadcast(intent);
	                        				break;
	                        			}
	                        			case 11: //get source xml
	                        			{
	                        				
	                        				Log.v(TAG,"recv get resrouce rsp packet from resource server");
											int res = tcp.fileReceived(len,channel);
											if(res < 0)
											{
												Intent intent = new Intent("com.frameclient.getresource.rsp");
												intent.putExtra("result", -1); //获取失败
												sendBroadcast(intent);
											}
											else
											{
												Log.v(TAG,"resource lisst count = "+SoftResource.getListSize());
												SoftResource.isGetResource = true;
												Intent intent = new Intent("com.frameclient.getresource.rsp");
												intent.putExtra("result", 0); //登陆成功
												sendBroadcast(intent);
												
											}
											break;
											
	                        			}
	                        			case 1011:
	                        			{
	                        				Log.v(TAG,"====> get 1011");
	                        				Intent intent = new Intent("com.frameclient.getresource.rsp");
											intent.putExtra("result", -1); //获取失败
											sendBroadcast(intent);
	                        				break;
	                        			}
	                        			case 1031:
	                        			{
	                        				tcp.parseCameraStateChange(len,channel);
	                        				Intent intent = new Intent("com.frameclient.camera.statechange");
											sendBroadcast(intent);
	                        				break;
	                        			}
	                        			case 11011:
	                        			{
	                         				ByteBuffer content = ByteBuffer.allocateDirect(len);
											int read_total = tcp.recv(content,channel,len);
											String str = new String(content.array());
											Log.v(TAG,"get notify  = "+str);
											NotifyInfo info = tcp.parseNotifyRsp(content.array());
											Intent intent = new Intent("com.frameclient.notify");
											intent.putExtra("type", info.type);
											intent.putExtra("url", info.url);
											sendBroadcast(intent);
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
        		}
        		tcp.conn_close();       
        	}
        	catch (Exception ex)
        	{
                tcp.workstatus = false;// 如果出现异常，提示网络连接出现问题。
                ex.printStackTrace();
            }
        	finally
        	{		
        		tcp.conn_close(); 
        	}
        	
    	}
    }    

}  