package com.frameclient.activitys;

import android.app.*;
import com.frameclient.utils.CrashHanlderExcetpion;


public class FrameClientApp extends Application
{
	private static FrameClientApp myInstance;
	
	public static FrameClientApp getInstance()
	{
		return myInstance;
	}
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		myInstance = this;
		CrashHanlderExcetpion crashHandler = CrashHanlderExcetpion.getInstance();
		crashHandler.init(this);
	}
}