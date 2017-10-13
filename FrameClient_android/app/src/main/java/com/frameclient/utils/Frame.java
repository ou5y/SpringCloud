package com.frameclient.utils;

public class Frame
{
	public FrameInfo info;
	public byte data[];
	
	public Frame(int len,byte[] h264)
	{
		info = new FrameInfo();
		
		data = new byte[len];
		System.arraycopy(h264,0,data ,0,len);
	}
	
}