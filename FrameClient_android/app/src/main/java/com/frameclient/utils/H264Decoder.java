package com.frameclient.utils;

import android.util.Log;

public class H264Decoder
{
	private static boolean sLoaded = false;
	private String TAG = "H264Decoder";
	
	public H264Decoder()
	{
	     if(!loadLibs())
	     {
	    	 Log.d(TAG, "Couldn't load lib H264Decoder");
	     }
	     else
	     {
	    	 Log.d(TAG, "load libs ffmpeg & H264Decoder ");
	     }
	}
	
    private static boolean loadLibs() {
    	if(sLoaded) {
    		return true;
    	}
    	boolean err = false;
   
		try {			
			System.loadLibrary("ffmpeg");
			System.loadLibrary("H264Decoder");				
		} catch(UnsatisfiedLinkError e) {
			// fatal error, we can't load some our libs
			Log.d("H264Decoder", "Couldn't load lib H264Decoder: " + e.getMessage());
			err = true;
		}
	
    	if(!err) {
    		sLoaded = true;
    	}
    	return sLoaded;
    }

	public native void init(int codec_id,int srcW ,int srcH,int fps,int bit_rate,int gop_size);
	public native int decode(byte[] in,int len,byte[] out,int scalerW,int scalerH,int needScaler);
	public native void uninit();
}