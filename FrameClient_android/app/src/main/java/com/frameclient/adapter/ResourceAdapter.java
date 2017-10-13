package com.frameclient.adapter;

import java.util.List;

import com.frameclient.activitys.LoginActivity;
import com.frameclient.activitys.MenuActivity;
import com.frameclient.activitys.R;
import com.frameclient.activitys.SourceActivity;
import com.frameclient.activitys.VideoActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.frameclient.services.NetWorkService;
import com.frameclient.utils.*;

/**
 * 资源列表
 * 
 * @author zhoujj E-mail:25184150@qq.com
 * @version 创建时间：2013-10-16 类说明:
 */

public class ResourceAdapter extends BaseAdapter
{
	private LayoutInflater mInflater = null;
	private List<ResourceItemInfo> list = null;
	private List<ResourceItemInfo> showlist = null;
	private static Context context = null;
	static Handler handler;
	private ViewFlipper vf;
	private String TAG = "ResourceAdapter";
	private ViewGroup p;	
	private ImageView iv = null;
	private ImageView iv_camera = null;
	
	ViewHolder holder = null;
	
	public ResourceAdapter(Context ctx,Handler hdler,List<ResourceItemInfo> resource_list,List<ResourceItemInfo> show_list)
	{
		mInflater = LayoutInflater.from(ctx);
		list = resource_list;
		showlist = show_list;
		context = ctx;
		handler = hdler;
	}
	
	public void back()
	{
		ResourceItemInfo elem = null;;
		for(int i = 0;i < SoftResource.getAdapterListSize();)
		{
			elem = (ResourceItemInfo)SoftResource.getAdapterElem(i);
			if(elem.level == SoftResource.level)
			{
				//Log.i("SourceAcitvity","levle = "+SoftResource.level+" remove elem name  = "+elem.name+" size = "+SoftResource.getAdapterListSize());
				SoftResource.removeAdapterElem(i);
			}
			else
			{
				i++;
			}
		}
		
		for(int i = 0;i < SoftResource.getAdapterListSize();i++)
		{
			elem = (ResourceItemInfo)SoftResource.getAdapterElem(i);
			if(elem.level == SoftResource.level -1)
			{
				//Log.i("SourceAcitvity","level = "+SoftResource.level+" set elem name  = "+elem.name+" true size = "+SoftResource.getAdapterListSize());
				elem.isShow = true;
			}
		}
		
		if(SoftResource.level > 1)
		{
			SoftResource.level--;
		}
		
	}
	
	public void gotoNextLevelPage(ResourceItemInfo rii)
	{
		for(int i = 0;i < SoftResource.getAdapterListSize();i++)
		{
			ResourceItemInfo elem = (ResourceItemInfo)SoftResource.getAdapterElem(i);
			elem.isShow = false;
			SoftResource.setAdapterElem(i,elem);
			//Log.v(TAG, "set id = "+elem.id+" name = "+elem.name+" isShow false");
		}
		
		
		SoftResource.level++;
		//Log.v(TAG, "=================> level  "+SoftResource.level+"  count = "+getCount());
		
		//Log.v(TAG, "start time....");
		for(int i = rii.indexInList+1; i <list.size() ;i++)
		{
			ResourceItemInfo elem = (ResourceItemInfo)SoftResource.getElem(i);
			
			//Log.v(TAG, "the " +i+ " name = " + elem.name);

			if(elem.parent_index == rii.indexInList)
			{
				//Log.v(TAG, "pos = "+i+" rii id = "+rii.id+" elem id "+elem.id+ " parentid  "+elem.parent_id+ " name = "+elem.name);
				//Log.v(TAG,"elem state  = "+elem.status);
				ResourceItemInfo info = ResourceItemInfo.copy(elem);
				info.isShow = true;
				info.level = SoftResource.level;
				SoftResource.insertAdapterElem(info);
				//Log.v(TAG, "set "+i+" level "+info.level+" isShow true add into s_list size "+SoftResource.getAdapterListSize());
			}
		}
		
		//Log.v(TAG, "end time....");
		Message message = new Message();
		Bundle bundle = new Bundle();
		bundle.putInt("type",0);
		message.setData(bundle);
		handler.sendMessage(message);
	}
	
	@Override
	public int getCount() {
		if (null != showlist) {
			return showlist.size();

		} else {
			return 0;
		}
	}
	
	@Override
	public Object getItem(int position) {
		return showlist.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String id = "";
		String name = null;
		String stream_server = null;
		int type = -1;
		int status = 0;
		

		final int pos = position;	
		
		ResourceItemInfo rii = showlist.get(position);
		
		if(rii != null)
		{
			id = rii.id;
			name = rii.name;
			stream_server = rii.stream_server;
			type = rii.type;
			status = rii.status;
		}
		
		holder = new ViewHolder();
		
		if(type == SoftResource.type_resource)
		{

			convertView = mInflater.inflate(R.layout.item_enterprise, null);
			
		
			holder.name = (TextView)convertView.findViewById(R.id.tv_enterprise);
			holder.item = (LinearLayout)convertView.findViewById(R.id.linear_enterprise);
			
			
			if(rii.isShow == false)
			{
				iv = (ImageView) holder.item.findViewById(R.id.iv_unfold);
				iv.setBackgroundResource(R.drawable.unfold);
				holder.item.setVisibility(View.GONE);
			}
			else
			{
				iv = (ImageView) holder.item.findViewById(R.id.iv_unfold);
				iv.setBackgroundResource(R.drawable.fold);
				holder.item.setVisibility(View.VISIBLE);
			}

			convertView.setTag(holder);
		}
		else if(type == SoftResource.type_address)
		{

			convertView = mInflater.inflate(R.layout.item_enterprise, null);
			
			holder.name = (TextView)convertView.findViewById(R.id.tv_enterprise);
			holder.item = (LinearLayout)convertView.findViewById(R.id.linear_enterprise);
			
			if(rii.isShow == false)
			{
				iv = (ImageView) holder.item.findViewById(R.id.iv_unfold);
				iv.setBackgroundResource(R.drawable.unfold);
				holder.item.setVisibility(View.GONE);
			}
			else
			{
				iv = (ImageView) holder.item.findViewById(R.id.iv_unfold);
				iv.setBackgroundResource(R.drawable.fold);
				holder.item.setVisibility(View.VISIBLE);
			}
			
			
			convertView.setTag(holder);
			
		}
		else if(type == SoftResource.type_camera)
		{
	
			convertView = mInflater.inflate(R.layout.item_camera, null);

			
			holder.name = (TextView)convertView.findViewById(R.id.tv_camera);
			holder.item = (LinearLayout)convertView.findViewById(R.id.linear_camera);
			
			
			iv_camera = (ImageView)holder.item.findViewById(R.id.iv_camera);
			if(status != 2)
			{
				iv_camera.setBackgroundResource(R.drawable.cameraicon_offline);
			}
			
			convertView.setTag(holder);
			if(rii.isShow == true)
			{
				holder.item.setVisibility(View.VISIBLE);
			}
			else
			{
				holder.item.setVisibility(View.GONE);
			}
		}
		
		holder.item.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ResourceItemInfo rii = null;
				ResourceItemInfo elem = null;
				LinearLayout item = null;
				boolean isResourceExist = false;
	
				rii = (ResourceItemInfo)SoftResource.getAdapterElem(pos);
				//Log.i(TAG,"pos = "+pos+ " id = "+rii.id+ " name = "+rii.name+" parent_id= "+rii.parent_id);
				
				if(rii.type == SoftResource.type_camera)
				{	
					if(rii.status == 2)
					{
					
						Intent intent = new Intent();
						intent.putExtra("uid", SoftResource.uid);
						SoftResource.cameraid = rii.id;
						SoftResource.stream = rii.stream_server;
						SoftResource.camera_pos = pos;
						intent.setClass(context, VideoActivity.class);
						Log.i(TAG,"connect stream server = "+rii.stream_server+ " id =  "+SoftResource.cameraid+" name = "+rii.name);
						//intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
						context.startActivity(intent);
					}
					else
					{
						Message message = new Message();
	    				Bundle bundle = new Bundle();
	    				bundle.putInt("type",3);
	    				message.setData(bundle);
	    				handler.sendMessage(message);
					}
				}
				else if(rii.type == SoftResource.type_resource && rii.indexInList > 0)
				{
				
	
						for(int i = rii.indexInList+1; i <list.size() ;i++)
						{
							elem = (ResourceItemInfo)SoftResource.getElem(i);
							
							//Log.v(TAG, "the " +i+ " name = " + elem.name);

							if(elem.parent_index == rii.indexInList) //该下级资源已经存在
							{
								isResourceExist = true;
								break;
								//Log.v(TAG, "set "+i+" level "+info.level+" isShow true add into s_list size "+SoftResource.getAdapterListSize());
							}
						}
						
						if(isResourceExist == false)
						{
							/*
							Intent intent = new Intent("com.frameclient.getresource");
							intent.putExtra("from", 1);
							intent.putExtra("uid", SoftResource.uid);
							intent.putExtra("resource_id", rii.id);
							intent.putExtra("stream_server", rii.stream_server);
							intent.putExtra("type",0);
							SoftResource.nextResource = rii;
							
							context.sendBroadcast(intent);
							*/
							
							Intent it = new Intent(context, NetWorkService.class);
							
						    Bundle bundle = new Bundle();  
					        bundle.putInt("opera",2); 
					        bundle.putInt("from", 1);
					        bundle.putString("uid", SoftResource.uid);
					        bundle.putString("resource_id", rii.id);
					        bundle.putString("stream_server", rii.stream_server);
					        bundle.putInt("type",0);
					        it.putExtras(bundle); 
					        
					        context.startService(it);
					        SoftResource.nextResource = rii;	
						}
						else
						{
							
							Log.v(TAG, "resource is exist");
							gotoNextLevelPage(rii);
						}
					
				}
				else
				{
					gotoNextLevelPage(rii);
				}
	
			}
		});
		
		holder.name.setText(name);
		return convertView;
	}

	
	static class ViewHolder {
		public LinearLayout item = null;
		public TextView name = null;
	}

}


