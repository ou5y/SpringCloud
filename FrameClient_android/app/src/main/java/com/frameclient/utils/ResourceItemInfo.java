package com.frameclient.utils;

public class ResourceItemInfo
{
	public String id;
	public String name;
	public String stream_server;
	public int parent_index;
	public int error;
	public int status;
	public int channel;
	public int indexInList;
	public int type;   // 0 title //1 address //2 camera
	public int level;
	public boolean isShow; // for carema type
	
	public static ResourceItemInfo copy(ResourceItemInfo info)
	{
		ResourceItemInfo elem = new ResourceItemInfo();
		elem.id = info.id;
		elem.name = info.name;
		elem.stream_server = info.stream_server;
		elem.parent_index = info.parent_index;
		elem.error = info.error;
		elem.status = info.status;
		elem.channel = info.channel;
		elem.indexInList = info.indexInList;
		elem.type = info.type;
		elem.level = info.level;
		elem.isShow = info.isShow;
		return elem;
	}
}