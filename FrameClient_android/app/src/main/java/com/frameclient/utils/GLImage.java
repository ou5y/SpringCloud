package com.frameclient.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.frameclient.activitys.R;

public  class GLImage  
{  
    public static Bitmap iBitmap;  
     
    public static void load(Resources resources)  
    {  
        iBitmap = BitmapFactory.decodeResource(resources, R.drawable.test1);  
    }
    
}  
	