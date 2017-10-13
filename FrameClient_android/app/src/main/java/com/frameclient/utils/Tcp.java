package com.frameclient.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Xml;

public class Tcp
{
	/**
	public Socket socket = null;
	public InputStream is = null;
	public OutputStream os = null;
	*/
	public boolean workstatus = true;
	private String TAG = "TCP";
	
	private SendData send_thread;
	
    //定义检测socketchannel的selector对象
    public Selector selector=null;
    
    public Selector selector_write = null;
    //客户端socketchannel
    public SocketChannel client=null;
    //定义处理编码和解码的字符集
    public Charset charset=Charset.forName("GBK");
	
	public static byte[] int2byteArray(int num) {  
	    byte[] result = new byte[4];  
	    result[0] = (byte)(num >>> 24);//取最高8位放到0下标  
	    result[1] = (byte)(num >>> 16);//取次高8为放到1下标  
	    result[2] = (byte)(num >>> 8); //取次低8位放到2下标   
	    result[3] = (byte)(num );      //取最低8位放到3下标  
	    return result;  
	} 
	
    public static int byteArray2Int(byte[] array) {  
        int result = 0;  
        byte loop;  
  
        for (int i = 0; i < 4; i++) {  
            loop = array[i];  
            int offSet = array.length -i -1;  
            result += (loop & 0xFF) << (8 * offSet);  
  
        }  
        return result;  
    } 

    public static String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
  
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
  
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
  
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
	    
	@SuppressLint("DefaultLocale")
	public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{ 
	
		MessageDigest md = MessageDigest.getInstance("SHA-1");
	    md.update(text.getBytes("UTF-8"));
	    byte[] result = md.digest();
	 
	    StringBuffer sb = new StringBuffer();
	 
	    for (byte b : result) 
	    {
	        int i = b & 0xff;
	        if (i <= 0xf)
	        {
	            sb.append(0);
	        }
	        sb.append(Integer.toHexString(i));
	    }
	    return sb.toString().toUpperCase();
	} 

    public Tcp()
    {
    	send_thread = new SendData();// 启动收听线程线程
    }
    
	class SendData implements Runnable
	{
		ByteBuffer com = null;
		int count = 0;
		LinkedList<ByteBuffer> commandList = new  LinkedList<ByteBuffer>();
		
		public SendData()
		{
			new Thread(this).start();
		}
		
		public void insertCommand(ByteBuffer bf)
		{
			commandList.add(bf);
		}
		
    	@Override
    	public void run()
    	{
    		Log.v(TAG,"send commond thread start !!!! ");
			while (true)
    		{
    			if(!commandList.isEmpty())
    			{		
    				com =  commandList.removeFirst();
    				try {
						count = send(com);
			  			//Log.v(TAG,"command list send size   = " + count);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				
    			
    			}
    			else
    			{
    				try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    			
    		}
    		///Log.v(TAG,"SendData thread game over....");
		}
	}
	
	public int recv(ByteBuffer data,SocketChannel channel,int datalen) throws IOException
	{       
	       int read_num = 0;
	       int total_read = 0;
	       int dst_num = datalen;
	       
	       while(total_read  < dst_num)
	       {
	    	   read_num = channel.read(data);
    		   if(read_num > 0)
    		   {
	    		   total_read += read_num;
    		   }
    		     			  	    	   
	       }
	       
	       data.flip();    
	       
	       return total_read;
	}
	
	private int send(ByteBuffer buff) throws IOException
    {
    	int count = 0;
    	
		while(selector_write.select() >0)
		{
			  Iterator<SelectionKey> it = selector_write.selectedKeys().iterator();
			  while(it.hasNext())
			  {
				   SelectionKey key = it.next(); //使用迭代器遍历
				   it.remove(); //删除迭代
				   try{
					   if(key.isWritable())
					   {
						   if(workstatus == true)
						   {
							   count = client.write(buff);
							   return count;
						   }
						  
					   }
				   }catch (Exception e) {  
					   
				        key.cancel();  
				     }  
			  }
		}
		return 0;
    }
	
    private void sendMessage(int opera_num,String content)
	{
    	ByteBuffer bytebuf;
    	int nBytes =0;
    	
		if("".equals(content))			
		{
			byte[] len = int2byteArray(4); //include content and opera len
			byte[] opera = int2byteArray(opera_num);
			byte[] com = new byte[8];
			
			System.arraycopy(len,0,com,0,len.length);
			System.arraycopy(opera,0,com,len.length,opera.length);
		
			bytebuf = ByteBuffer.wrap(com);
			
			//Log.v(TAG,"send keep alive...");
			
			send_thread.insertCommand(bytebuf);
			
			
		}
		else
		{	
			byte[] len = int2byteArray(content.length()+4); //include content and opera len
			byte[] opera = int2byteArray(opera_num);
			byte[] bytes = content.getBytes();
			byte[] com = new byte[content.length()+8];
			System.arraycopy(len,0,com,0,len.length);
			System.arraycopy(opera,0,com,len.length,opera.length);
			System.arraycopy(bytes, 0, com, len.length+opera.length, bytes.length);	
			
			Log.v(TAG,"len len = "+len.length);
			Log.v(TAG,"opear len = "+opera.length);
			Log.v(TAG,"bytes len = "+bytes.length);
			Log.v(TAG,"com len = "+com.length);
			
			String str = new String(com);
			
			//Log.v(TAG,"send command < "+str+" >");
			
			bytebuf = ByteBuffer.wrap(com);
			
			send_thread.insertCommand(bytebuf);
		
		}
	
	}

    public void sendLoginMsg(String name,String password)
    {
    	String str = name + password;
    	String sha1 = "";
    	Log.v(TAG,"=====> str = "+str);
    	//String md5Pass = MD5(password);
    	try 
    	{
			sha1 = SHA1(str);
			Log.v(TAG,"=====> sha1 = "+sha1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "no sha1 algorithm");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "UnsupportedEncodingException error");
			e.printStackTrace();
		}
    	String login = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?><C1 user=\"%s\" password=\"%s\" type=\"2\"/>",name,sha1);
    	//String login = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?><C1 user=\"%s\" password=\"%s\"/>",name,sha1);
    	if(client.isConnected())
    	{
    		sendMessage(SoftResource.EVENT_LOGIN,login);
    	}
    	else
    	{
    		Log.e(TAG,"sendLoginMsg error");
    	}
    	
    }
    
	public void sendGetVideoMsg(String uid,String id)
    {
    	String video_str = String.format("<C12 uid=\"%s\" camera_id =\"%s\" type=\"1\"/>",uid,id);
    	if(client.isConnected())
    	{
    		sendMessage(SoftResource.EVENT_GET_REAL_VIDEO,video_str); //opera 12
    	}
    	else
    	{
    		Log.e(TAG,"sendGetVideoMsg error");
    	}
    }
    
	public void sendGetResourceMsg(String uid,String resource_id,int type)
    {
    	String resource = String.format("<C11 uid=\"%s\" resource_id=\"%s\" type=\"%d\"/>",uid,resource_id,type);
    	if(client.isConnected())
    	{
    		sendMessage(SoftResource.EVENT_GET_CAMERA_SOURCE,resource); //opera 11
    	}
    	else
    	{
    		Log.e(TAG,"sendGetResourceMsg error");
    	}
    }
	
	public void sendGetResourceMsg(String uid,String resource_id,String stream_server)
    {
    	String resource = String.format("<C11 uid=\"%s\" resource_id=\"%s\" stream_server=\"%s\"/>",uid,resource_id,stream_server);
    	if(client.isConnected())
    	{
    		sendMessage(SoftResource.EVENT_GET_CAMERA_SOURCE,resource); //opera 11
    	}
    	else
    	{
    		Log.e(TAG,"sendGetResourceMsg error");
    	}
    }
    
    public void sendChangePasswordMsg(String user,String curr_pass,String new_pass) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
    	String sha1_curr = SHA1(user+curr_pass);
    	String sha1_new = SHA1(user+new_pass);
    	
       	String resource = String.format("<C3 user=\"%s\" old_password=\"%s\" new_password=\"%s\"/>",user,sha1_curr,sha1_new);
    	if(client.isConnected())
    	{
    		sendMessage(SoftResource.EVENT_CHANGE_PASSWORD,resource); //opera 11
    	}
    	else
    	{
    		Log.e(TAG,"sendChangePasswordMsg error");
    	}
    }
    
    public void sendQuitMsg(String user,String uid)
    {
       	String resource = String.format("<C2 uid=\"%s\" user=\"%s\"/>",uid,user);
    	if(client.isConnected())
    	{
    		sendMessage(SoftResource.EVENT_LOGOUT,resource); //opera 11
    	}
    	else
    	{
    		Log.e(TAG,"sendChangePasswordMsg error");
    	}
    }
    
    public void sendCameraControlCmdMsg(String uid,String cameraid,int command)
    {
    	String resource = String.format("<C13 uid=\"%s\" camera_id=\"%s\" command=\"%d\" data=\"1-100\" />",uid,cameraid,command);
    	if(client.isConnected())
    	{
    		sendMessage(SoftResource.EVENT_CONTROL_CAMERA,resource); //opera 13
    	}
    	else
    	{
    		Log.e(TAG,"sendCameraControlCmdMsg error");
    	}
    }
    
    public void sendKeepAlive()
    {
    	sendMessage(SoftResource.DATATYEP_KEEPALIVE,""); 
    }
   
	public int connect_sever(String ipaddr,int port)
    {
    	int res = -1;
    	
        try {
        	selector=Selector.open(); 
        	selector_write = Selector.open();
            InetSocketAddress socAddress = new InetSocketAddress(ipaddr, port);//InetSocketAddress.createUnresolved (ipaddr,port);	
            client = SocketChannel.open(); 
            client.configureBlocking(false);  //设置为非阻塞方式工作       
           	client.register(selector, SelectionKey.OP_READ);
        	client.register(selector_write, SelectionKey.OP_WRITE);
            client.connect(socAddress);
            if(connect()>0)
            {
            	workstatus = true;
            }
            else
            {
            	workstatus = false;
            	return -1;
            }
            
            return 0;
        } catch (SocketException ex) {
            Log.v(TAG, "socketException ");
            ex.printStackTrace();
            workstatus = false;// 如果是网络连接出错了，则提示网络连接错误
            return -1;
        } catch (SocketTimeoutException ex) {
            ex.printStackTrace();
            workstatus = false;// 如果是网络连接出错了，则提示网络连接错误
            return -1;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(TAG,"connect_sever:"+ex.getMessage());
            workstatus = false;// 如果是网络连接出错了，则提示网络连接错误
            return -1;
        }
    }
	
	public int connect()
	{  
		  
		//TODOAuto-generatedmethodstub  
		  
		if(isConnected())
		{    
			return 1;  
		}  
		  
		
		for(int i = 0; i < 100;i++)
		{
			try {
				if(client.finishConnect())
				{
					return 1;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return 0;
			}
			
			try {
				Thread.sleep(30);
				Log.d(TAG,"wait for "+i+" timer to connect server");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
				
		}
		return 0;
		
	}
    
	public int fileReceived(int len,SocketChannel channel) throws FileNotFoundException, IOException
	{
		Log.i(TAG, "fileReceived");
	    if (client.isConnected())
	    {
	    	ByteBuffer buf = ByteBuffer.allocateDirect(len);

	        try
	        {
	        	/*
	        	FileOutputStream fos = new FileOutputStream(path);
	        	BufferedOutputStream bos = new BufferedOutputStream(fos);
				*/
	            int bytesRead = recv(buf,channel,len);
	            byte[] cnt = new byte[bytesRead];
	            buf.get(cnt);
	            /*
	            bos.write(cnt, 0, bytesRead);
	            bos.flush();
	            bos.close();
	            */
	            try 
	            {
					int res = paresResourXml(cnt);
				} 
	            catch (XmlPullParserException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return bytesRead;
	        } catch (IOException ex) {
	            // Do exception handling
	        	Log.v(TAG,"fileReceived error: "+ ex.getMessage());
	        	return -1;
	        }
	    }
	    else
	    {
	    	return -1;
	    }
	}
	
	public int parseCameraStateChange(int len,SocketChannel channel) throws FileNotFoundException, IOException
	{
		//Log.i(TAG, "parseCameraStateChange");
	    if (client.isConnected())
	    {
	    	ByteBuffer buf = ByteBuffer.allocateDirect(len);

	        try
	        {
	        	/*
	        	FileOutputStream fos = new FileOutputStream(path);
	        	BufferedOutputStream bos = new BufferedOutputStream(fos);
				*/
	            int bytesRead = recv(buf,channel,len);
	            byte[] cnt = new byte[bytesRead];
	            buf.get(cnt);
	            /*
	            bos.write(cnt, 0, bytesRead);
	            bos.flush();
	            bos.close();
	            */
	            try 
	            {
					int res = paresStateChange(cnt);
				} 
	            catch (XmlPullParserException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return bytesRead;
	        } catch (IOException ex) {
	            // Do exception handling
	        	Log.v(TAG,"paresStateChange error: "+ ex.getMessage());
	        	return -1;
	        }
	    }
	    else
	    {
	    	return -1;
	    }
	}
	
	
	public int parseChangePasswordRsp(byte content[]) throws Exception
	{
		int err = 0;
		String uid = "";
		
		XmlPullParser parser = Xml.newPullParser();
		String mm=new String(content);
		ByteArrayInputStream  byte1=new ByteArrayInputStream(mm.getBytes());
		parser.setInput(byte1,"UTF-8");
		
		int event = parser.getEventType();//产生第一个事件  
        while(event!=XmlPullParser.END_DOCUMENT)
        {  
            switch(event)
            {  
            case XmlPullParser.START_DOCUMENT://判断当前事件是否是文档开始事件  
            	//初始化books集合  
                break;  
            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件  
                if("C3".equals(parser.getName()))
                {//判断开始标签元素是否是book  
                	 uid = parser.getAttributeValue(null,"uid");
                     err = Integer.parseInt(parser.getAttributeValue(null,"error"));
                     Log.v(TAG,"uid = "+uid +" error = " + err);
                }  
     
                break;  
            case XmlPullParser.END_TAG://判断当前事件是否是标签元素结束事件  
                if("C3".equals(parser.getName())){//判断结束标签元素是否是C1 
                   //to do
                    
                }  
                break;  
            }  
            event = parser.next();//进入下一个元素并触发相应事件  
        }//end while  
        return err;
	}
	
	public NotifyInfo parseNotifyRsp(byte content[]) throws Exception
	{
		int err = 0;
		NotifyInfo info = null;

		info = new NotifyInfo();
		
		XmlPullParser parser = Xml.newPullParser();
		String mm=new String(content);
		ByteArrayInputStream  byte1=new ByteArrayInputStream(mm.getBytes());
		parser.setInput(byte1,"UTF-8");
		
		int event = parser.getEventType();//产生第一个事件  
        while(event!=XmlPullParser.END_DOCUMENT)
        {  
            switch(event)
            {  
            case XmlPullParser.START_DOCUMENT://判断当前事件是否是文档开始事件  
            	//初始化books集合  
                break;  
            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件  
                if("C11011".equals(parser.getName()))
                {//判断开始标签元素是否是book  
                	 info.type = Integer.parseInt(parser.getAttributeValue(null,"type"));
                     info.url = parser.getAttributeValue(null,"url");
                     Log.v(TAG,"type = "+info.type +" url = " + info.url);
                }  
     
                break;  
            case XmlPullParser.END_TAG://判断当前事件是否是标签元素结束事件  
                if("C11011".equals(parser.getName())){//判断结束标签元素是否是C1 
                   //to do
                    
                }  
                break;  
            }  
            event = parser.next();//进入下一个元素并触发相应事件  
        }//end while  
        return info;
	}
	
	public LoginRsp parseLoginRsp(byte content[]) throws Exception
	{
		LoginRsp rsp;
		XmlPullParser parser = Xml.newPullParser();
		String mm=new String(content);
		Log.v(TAG,"mm:"+mm);
		ByteArrayInputStream  byte1=new ByteArrayInputStream(mm.getBytes());
		parser.setInput(byte1,"UTF-8");
		
		rsp = new LoginRsp();
		int event = parser.getEventType();//产生第一个事件  
        while(event!=XmlPullParser.END_DOCUMENT)
        {  
            switch(event)
            {  
            case XmlPullParser.START_DOCUMENT://判断当前事件是否是文档开始事件  
            	//初始化books集合  
                break;  
            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件  
                if("C1".equals(parser.getName()))
                {//判断开始标签元素是否是book 
                	 rsp.error = Integer.parseInt(parser.getAttributeValue(null,"error"));
                	 if(rsp.error != 0)
                	 {
                		 return rsp;
                	 }
                	 rsp.uid = parser.getAttributeValue(null, "uid");
                	 SoftResource.uid = rsp.uid;
                     rsp.source_id =  parser.getAttributeValue(null,"resource_id");
                     rsp.type = Integer.parseInt(parser.getAttributeValue(null,"type"));
                     
                     Log.v(TAG,"uid = "+rsp.uid + " source_id = " + rsp.source_id + " type = " + rsp.type+ " error = " + rsp.error);
                }  
     
                break;  
            case XmlPullParser.END_TAG://判断当前事件是否是标签元素结束事件  
                if("C1".equals(parser.getName())){//判断结束标签元素是否是C1 
                   //to do
                    
                }  
                break;  
            }  
            event = parser.next();//进入下一个元素并触发相应事件  
        }//end while  
        return rsp;
	}

	public FrameInfo parseFrameInfo(byte content[]) throws XmlPullParserException
	{
		FrameInfo info = null;
		
		XmlPullParser parser = Xml.newPullParser();
		String mm=new String(content);
		ByteArrayInputStream  byte1=new ByteArrayInputStream(mm.getBytes());
		parser.setInput(byte1,"UTF-8");
		
		info = new FrameInfo();
		
		int event = parser.getEventType();
        while(event!=XmlPullParser.END_DOCUMENT)
        {  
            switch(event)
            {  
            case XmlPullParser.START_DOCUMENT://判断当前事件是否是文档开始事件  
            	//Log.v(TAG,"parseFrameInfo clear SoftResource");
            	//SoftResource.clearList();
            	//初始化books集合  
                break;  
            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件  
                if("FrameInfo".equals(parser.getName()))
                {//判断开始标签元素是否是  
                	info.version = parser.getAttributeValue(0);
                	info.frameId = Integer.parseInt(parser.getAttributeValue(1));
                	info.frametype = Integer.parseInt(parser.getAttributeValue(2));
                	info.datalen = Integer.parseInt(parser.getAttributeValue(3));
                    //Log.v(TAG,"version = "+info.version + " frameid = " + info.frameId + " type = " + info.frametype+ " datalen  = " + info.datalen);
                }   
                break;  
            case XmlPullParser.END_TAG://判断当前事件是否是标签元素结束事件          
                break;  
            default:  
                break; 
            }  
            
            try {
				event = parser.next();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//进入下一个元素并触发相应事件  
        }//end while
        
        parser = null;
        return info;
		
	}
	
	
	public VideoInfo parseVideoInfo(byte content[]) throws XmlPullParserException
	{
		VideoInfo info = null;
		
		XmlPullParser parser = Xml.newPullParser();
		String mm=new String(content);
		ByteArrayInputStream  byte1=new ByteArrayInputStream(mm.getBytes());
		parser.setInput(byte1,"UTF-8");
		
		info = new VideoInfo();
		info.isSupportAudio = false;
		
		int event = parser.getEventType();
        while(event!=XmlPullParser.END_DOCUMENT)
        {  
            switch(event)
            {  
            case XmlPullParser.START_DOCUMENT://判断当前事件是否是文档开始事件  
            	//Log.v(TAG,"parseFrameInfo clear SoftResource");
            	//SoftResource.clearList();
            	//初始化books集合  
                break;  
            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件  
                if("VideoInfo".equals(parser.getName()))
                {//判断开始标签元素是否是  
                	info.version = parser.getAttributeValue(0);
                	info.cameraid = parser.getAttributeValue(1);
                	info.status = Integer.parseInt(parser.getAttributeValue(2));
                  	info.error = Integer.parseInt(parser.getAttributeValue(3));
                  	
                	info.width = Integer.parseInt(parser.getAttributeValue(4));
                	info.height = Integer.parseInt(parser.getAttributeValue(5));
                	info.fps = Integer.parseInt(parser.getAttributeValue(6));
                	info.bit_rate = Integer.parseInt(parser.getAttributeValue(7));
                	info.vidoe_codec_id = Integer.parseInt(parser.getAttributeValue(8));
                    //Log.v(TAG,"version = "+info.version + " frameid = " + info.frameId + " type = " + info.frametype+ " datalen  = " + info.datalen);
                }
                else if("Head".equals(parser.getName()))
                {
                	//<Head version="1"><Video codec_id="" bit_rate="" width="" height="" fps="" gop_size=""/><Audio codec_id="" bit_rate="" sample_rate="" sample_fmt="" channels="" channel_layout=""/></Head>
                	info.version = parser.getAttributeValue(0);
                }
                else if("Video".equals(parser.getName()))
                {
                	info.vidoe_codec_id = Integer.parseInt(parser.getAttributeValue(0));
                	info.bit_rate = Integer.parseInt(parser.getAttributeValue(1));
                 	info.width = Integer.parseInt(parser.getAttributeValue(2));
                	info.height = Integer.parseInt(parser.getAttributeValue(3));
                	info.fps = Integer.parseInt(parser.getAttributeValue(4));
                	info.gop_size = Integer.parseInt(parser.getAttributeValue(5));
                	
                	Log.v(TAG,"video: version = "+info.version + " voideo_codec_id = " + info.vidoe_codec_id + " bitrate = " + info.bit_rate+ " width  = " + info.width + " height = "+info.height+" fps = "+info.fps+" gopsize = "+info.gop_size);
                	
                }
                else if("Audio".equals(parser.getName()))
                {
                	info.isSupportAudio = true;
                	info.audio_codec_id = Integer.parseInt(parser.getAttributeValue(0));
                	info.audio_bit_rate = Integer.parseInt(parser.getAttributeValue(1));
                 	info.audio_sample_rate = Integer.parseInt(parser.getAttributeValue(2));
                	info.audio_sample_fmt = Integer.parseInt(parser.getAttributeValue(3));
                	info.audio_channels = Integer.parseInt(parser.getAttributeValue(4));
                	info.audio_channel_layout = Integer.parseInt(parser.getAttributeValue(5));
                }
                break;  
            case XmlPullParser.END_TAG://判断当前事件是否是标签元素结束事件          
                break;  
            default:  
                break; 
            }  
            
            try {
				event = parser.next();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//进入下一个元素并触发相应事件  
        }//end while
        
        parser = null;
        return info;
		
	}
	
	private int paresResourXml(byte content[]) throws IOException, XmlPullParserException
    {
    	ResourceItemInfo rii = null;
    	ResourceItemInfo tmp = null;
    	Stack<ResourceItemInfo> sk = new Stack<ResourceItemInfo>(); 
    	int first_elem = 0;
    	int num = SoftResource.getListSize();
    	
		XmlPullParser parser = Xml.newPullParser();
		String mm=new String(content);
		ByteArrayInputStream  byte1=new ByteArrayInputStream(mm.getBytes());
		parser.setInput(byte1,"UTF-8");
		
		String str = new String(content);
		Log.v(TAG,"========>Resource Xml = "+str);
		
		int event = parser.getEventType();
        while(event!=XmlPullParser.END_DOCUMENT)
        {  
        	String nodeName=parser.getName();
            switch(event)
            {  
            case XmlPullParser.START_DOCUMENT://判断当前事件是否是文档开始事件 
            	//Log.v(TAG,"paresResourXml cleam list in netservice");
            	//SoftResource.clearList();
            	//初始化books集合  
                break;  
            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件  
                if("C11".equals(parser.getName()))
                {//判断开始标签元素是否是  
                	
                	if(SoftResource.nextResource == null)  //初级资源
                	{
	                	int count = parser.getAttributeCount();
	                	if(count <= 0)
	                	{
	                		break;
	                	}
	                	
	                	 rii = new ResourceItemInfo();              	           	 
	                	 rii.id  = parser.getAttributeValue(null,"id");             	    	 
	                     rii.name =  parser.getAttributeValue(null,"name");              
	                     String err = parser.getAttributeValue(null,"error");
	                     if("".equals(err))
	                     {
	                    	 rii.error = 0;
	                     }
	                     else
	                     {
	                    	 rii.error = Integer.parseInt(err);
	                     }
	                	 
	                     rii.stream_server="";
	                     rii.type = SoftResource.type_resource;
	                     
	                     //tmp = sk.lastElement();
	                     rii.name = "设备";
	                 	 rii.parent_index = -1;
	                     rii.isShow = true;
	                     rii.indexInList = num;
	                     rii.level = 1;
	                     sk.push(rii);
	                     
	                     SoftResource.insertElem(rii);
	                     SoftResource.insertAdapterElem(rii); 
	                     
	                     num++;
	                     //Log.v(TAG,"C11 index "+num+" id = "+rii.id + " name = " + rii.name + " type = " + "resource "+ " stream_sever = " + rii.stream_server);
                	}
                	else  //下级平台资源
                	{
                		sk.push(SoftResource.nextResource);
                	}
                } 
                else if("Address".equals(parser.getName()))
                {
               	 	rii = new ResourceItemInfo();
               	 	rii.isShow = false;

                 	rii.id = parser.getAttributeValue(null,"id");
                    rii.name =  parser.getAttributeValue(null,"name");

                    rii.type = SoftResource.type_address;
                    rii.indexInList = num;
                    
                    
                  	tmp = sk.lastElement();
                	rii.parent_index = tmp.indexInList;
                
                          
                    sk.push(rii);
                    SoftResource.insertElem(rii);
                    num++;
                    //Log.v(TAG,"address index "+num+" id = "+rii.id +  " parent index = "+rii.parent_index+" name = " + rii.name + " type = " + "address"+ " stream_sever = " + rii.stream_server);
                }
                else if("Resource".equals(parser.getName()))
                {
               	 	rii = new ResourceItemInfo();
               	 	rii.isShow = false;

                 	rii.id = parser.getAttributeValue(null,"id");
                    rii.name =  parser.getAttributeValue(null,"name");
                    
                    

                    rii.stream_server=parser.getAttributeValue(null,"stream_server");;
                    rii.type = SoftResource.type_resource;
                    rii.indexInList = num;
                    
                  	tmp = sk.lastElement();
                	rii.parent_index = tmp.indexInList;
                
                          
                    sk.push(rii);
                    SoftResource.insertElem(rii);
                    num++;
                    //Log.v(TAG,"resource index "+num+" id = "+rii.id +  " parent index = "+rii.parent_index+" name = " + rii.name + " type = " + "address"+ " stream_sever = " + rii.stream_server);
                }
                else if("Camera".equals(parser.getName()))
                {
                	rii = new ResourceItemInfo();
                	rii.isShow = false;
                	 
                 	rii.id = parser.getAttributeValue(null,"id");
                    rii.name =  parser.getAttributeValue(null,"name");
                    rii.stream_server= parser.getAttributeValue(null,"stream_server");
                    
                    
                    String state = parser.getAttributeValue(null,"state");
                    if(state != null)
                    {
                    	rii.status = Integer.parseInt(state);
                    }
                    else
                    {
                    	rii.status = 2;
                    }
                    rii.type = SoftResource.type_camera;
                    //rii.unfold = false;
                	tmp = sk.lastElement();
                	rii.parent_index = tmp.indexInList;
                	rii.indexInList = num;
                    SoftResource.insertElem(rii);
                    sk.push(rii);
                    num++;
                    
                    //Log.v(TAG,"caemra state = "+rii.status+" value "+state);
                    
                    //Log.v(TAG,"camera index "+num+" id = "+rii.id + " parent index = "+rii.parent_index+" name = " + rii.name + " type = " + "camera"+ " stream_sever = " + rii.stream_server);
                }
     
                break;  
            case XmlPullParser.END_TAG://判断当前事件是否是标签元素结束事件  
            	if("Camera".equals(nodeName))
            	{
            		if(sk.size() > 0)
            		{
            			//Log.v(TAG,"camera pop ");
            			sk.pop();
            		}
            	}
            	else if("Resource".equals(nodeName))
            	{
            		if(sk.size() > 0)
            		{
            			//Log.v(TAG,"Resource pop ");
            			sk.pop();
            		}
            	}
            	else if("Address".equals(nodeName))
            	{
            		if(sk.size() > 0)
            		{
            			//Log.v(TAG,"Address pop ");
            			sk.pop();
            		}
            	}
            	else if("C11".equals(nodeName))
            	{
            		if(sk.size() > 0)
            		{
            			//Log.v(TAG,"C11 pop ");
            			sk.pop();
            		}
            	}
            	
                rii = null;             
                break;  
            default:  
                break; 
            }  
            
            
            try {
				event = parser.next();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//进入下一个元素并触发相应事件  
        }//end while
          
        return 0;
    	
    }
	
	private int paresStateChange(byte content[]) throws IOException, XmlPullParserException
    {
		XmlPullParser parser = Xml.newPullParser();
		String mm=new String(content);
		ByteArrayInputStream  byte1=new ByteArrayInputStream(mm.getBytes());
		parser.setInput(byte1,"UTF-8");
		
		String str = new String(content);
		//Log.v(TAG,"========>camera change xml = "+str);
		
		int event = parser.getEventType();
        while(event!=XmlPullParser.END_DOCUMENT)
        {  
            switch(event)
            {  
            case XmlPullParser.START_DOCUMENT://判断当前事件是否是文档开始事件 
                break;  
            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件  
                if("C1031".equals(parser.getName()))
                {//判断开始标签元素是否是  

                  
                } 
                else if("Camera".equals(parser.getName()))
                {
                 	String id = parser.getAttributeValue(null,"id");
                 	
                    int state = Integer.parseInt(parser.getAttributeValue(null,"state"));
                   
                    if(SoftResource.setElemState(id, state))
                    {
                    	//Log.v(TAG,"the camera "+id+" state change to "+state);
                    }
                
                    //Log.v(TAG,"num "+num+" id = "+rii.id + " parent id = "+rii.parent_id+" name = " + rii.name + " type = " + "camera"+ " stream_sever = " + rii.stream_server);
                }
     
                break;  
            case XmlPullParser.END_TAG://判断当前事件是否是标签元素结束事件           
                break;  
            default:  
                break; 
            }  
            
            try {
				event = parser.next();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//进入下一个元素并触发相应事件  
        }//end while
        

        
        return 0;
    	
    }
	
	public boolean isConnected()
	{  
		return client!=null&&client.isConnected();    
	}  
		  
	
	public int conn_close()
	{
		
		if(!isConnected())
		{
			return -1;
		}
		Log.e(TAG,"++++++++++++++++++++++ close socket");
    	Socket socket = client.socket();
    	if (socket != null)  
    	{  
    		
    	    try {
				socket.shutdownInput();
				socket.shutdownOutput();    
				client.socket().close();  
				client.close();
				return 0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}  
    	             
    	}
    	else
    	{
    		return -1;
    	}
    	        	               	
	}

}