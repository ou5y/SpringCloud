package com.frameclient.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.graphics.Bitmap;

public class SoftResource
{
	public static String version = "1.0.0";
	
	/** camrea source xml tag*/
	public static int type_resource = 0;
	public static int type_address = 1;
	public static int type_camera  = 2;
	
	public static String uid = "";
	public static String cameraid = "";
	public static String stream = "";
	public static int camera_pos = 0;
	
	public static String login_ip= "";
	public static boolean login = false;
	
	
	public static ResourceItemInfo nextResource = null;
	
	/** adapter info 
	 * 
	 */
	
	public static int level = 1;
	
	/** error code */
	public static int success = 0;
	public static int err_user_noexist = -1;
	public static int err_passwrod_failure = -2;
	public static int err_net_failure = -3;
	public static int err_getresource_failure = -4;
	public static int err_ip_empty = -5;
	public static int err_username_empty = -6;
	public static int err_passowrd_empty = -7;
	
	/**network opera code*/
	public static int EVENT_LOGIN  = 1;
	public static int EVENT_LOGOUT = 2;
	public static int EVENT_CHANGE_PASSWORD = 3;
	public static int EVENT_GET_CAMERA_SOURCE = 11;
	public static int EVENT_GET_REAL_VIDEO = 12;
	public static int EVENT_CONTROL_CAMERA = 13;
	public static int EVETN_GET_REAL_VIDEO_RSP = 216;
	
	public static int DATATYEP_KEEPALIVE = 0;
	public static int DATATYPE_VDIEO = 100;
	
	public static boolean third_party_call  = false;
	
	public static int videostream_socket_port = 6620;
	public static int login_socket_port = 6611;
	public static Bitmap bitmap;
	public static ConcurrentLinkedQueue<Bitmap> bmp_queue = new ConcurrentLinkedQueue<Bitmap>();
	
	public static int timeout = 20;
	public static boolean isGetResource = false;
	
	private static List<ResourceItemInfo> r_list = new ArrayList<ResourceItemInfo>();
	private static List<ResourceItemInfo> s_list = new ArrayList<ResourceItemInfo>();
	
	public static List<ResourceItemInfo> getAdapterList()
	{
		return s_list;
	}
	
	public static void insertAdapterElem(ResourceItemInfo elem)
	{
		s_list.add(elem);
	}
	
	public static void clearAdapterList()
	{
		s_list.clear();
	}
	
	public static ResourceItemInfo getAdapterElem(int pos)
	{
		return s_list.get(pos);
	}
	
	public static ResourceItemInfo removeAdapterElem(int pos)
	{
		return s_list.remove(pos);
	}
	
	
	public static int getAdapterListSize()
	{
		return s_list.size();
	}
	
	public static void setAdapterElem(int pos,ResourceItemInfo elem)
	{
		s_list.set(pos, elem);
	}
	
	public static List<ResourceItemInfo> getList()
	{
		return r_list;
	}
	
	public static void insertElem(ResourceItemInfo elem)
	{
		r_list.add(elem);
	}
	
	public static ResourceItemInfo getElem(int pos)
	{
		return r_list.get(pos);
	}
	
	public static int getListSize()
	{
		return r_list.size();
	}
	
	public static void clearList()
	{
		r_list.clear();
	}
	
	public static void setElem(int pos,ResourceItemInfo elem)
	{
		r_list.set(pos, elem);
	}
	
	public static boolean setElemState(String id,int state)
	{
		boolean find = false;
		
		for(int i = 0;i < r_list.size();i++)
		{

			ResourceItemInfo info = r_list.get(i);
			if(info.id.equals(id))
			{
				info.status = state;
			    setElem(i,info);
			    find = true;
				break;
			}
		}
		
		for(int i = 0;i < s_list.size();i++)
		{

			ResourceItemInfo info = s_list.get(i);
			if(info.id.equals(id))
			{
				info.status = state;
			    setAdapterElem(i,info);
			    find = true;
			}
		}
		
		if(find)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}