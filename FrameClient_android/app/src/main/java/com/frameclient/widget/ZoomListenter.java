package com.frameclient.widget;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.FloatMath;  
import android.util.Log;
import android.view.MotionEvent;  
import android.view.SurfaceView;
import android.view.View;  
import android.view.View.OnTouchListener;  
import android.widget.Toast;
  
public class ZoomListenter implements OnTouchListener {  
   
     private int mode = 0;  
     float oldDist;  
     float newDist;

     
     float start_x = 0;
     float start_y = 0;
     float end_x = 0;
     float end_y = 0;
     
     Context context;
     Handler handler = null;
     String TAG = "ZoomListenter";
   
     SurfaceView sfView = null;  
     
     public ZoomListenter(Context ctx,Handler hdl)
     {
    	 context = ctx;
    	 handler = hdl;
     }
     
   
     @Override  
     public boolean onTouch(View v, MotionEvent event) {  
    	 sfView = (SurfaceView) v;   
         switch (event.getAction() & MotionEvent.ACTION_MASK) {  
         case MotionEvent.ACTION_DOWN:  
             mode = 1;  
             start_x = event.getX();
             start_y = event.getY();
             Log.i(TAG,"ACTION_DOWN x = "+event.getX()+" y = "+event.getY());
          
             break;  
         case MotionEvent.ACTION_UP:  
        	   
             end_x = event.getX();
             end_y = event.getY();
             
             if(mode == 1)
             {
            	 direction(start_x,start_y,end_x,end_y);
             }
             mode = 0;  
             break;  
         case MotionEvent.ACTION_POINTER_UP:
        	 newDist = spacing(event); 
        	 mode = 0;
             Log.i(TAG,"ACTION_POINTER_UP x = "+event.getX()+" y = "+event.getY());
             break;  
         case MotionEvent.ACTION_POINTER_DOWN:  

             oldDist = spacing(event);  
             mode -= 1;
             Log.i(TAG,"ACTION_POINTER_DOWN x = "+event.getX()+" y = "+event.getY());
         case MotionEvent.ACTION_MOVE:  
        	 
             if (mode >= 2) {  
                 float newDist = spacing(event);  
                 Log.i(TAG,"ACTION_MOVE newDist= " + newDist+"oldDist = "+oldDist);
                 if (newDist > oldDist + 1) {  
                     zoom(newDist / oldDist); 
                     
                     oldDist = newDist;  
                 }  
                 if (newDist < oldDist - 1) {  
                     zoom(newDist / oldDist);  
                     oldDist = newDist;  
                 }  
             }  
             Log.i(TAG,"ACTION_MOVE");
             break;  
         }  
         return true;  
     }  
   
     private void zoom(float f) {  
         //textView.setTextSize(textSize *= f);
    	 if(f > 1.0)
    	 {
    		 Log.i(TAG,"turn right");
    	 }
     }  
   
     private float spacing(MotionEvent event) {  
         float x = event.getX(0) - event.getX(1);  
         float y = event.getY(0) - event.getY(1);  
         return FloatMath.sqrt(x * x + y * y);  
     }  
     
     private void direction(float start_x,float start_y,float end_x,float end_y)
     {
    	 float x_displacement = 0;
    	 float y_displacement = 0;
    	 
    	 Log.i(TAG,"start_x = "+start_x+" start_y = "+start_y+ " end_x = "+end_x+" end_y = "+end_y);
    	 
    	 x_displacement = Math.abs(end_x - start_x);
    	 y_displacement = Math.abs(end_y - start_y);
    	 
    	 if(x_displacement < 10 && y_displacement < 10)
    	 {
    			Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putInt("message",10000);
				message.setData(bundle);
				handler.sendMessage(message);
				return;
    	 }
    	 
    	 Log.i(TAG,"x_displacement = "+x_displacement+" y_displacement = "+y_displacement);
    	 
    	 if(x_displacement - y_displacement > 0)  //水平位移
    	 {
    		 if(end_x - start_x > 0)  //向右
    		 {
    			 Log.i(TAG,"turn right");
				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putInt("message",10001);
				message.setData(bundle);
				handler.sendMessage(message);
    		 }
    		 else
    		 {
				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putInt("message",10002);
				message.setData(bundle);
				handler.sendMessage(message);
    			 Log.i(TAG,"turn left");
    		 }
    	 }
    	 else
    	 {
    		 if(end_y - start_y > 0)
    		 {
    			 Log.i(TAG,"turn down");
    		 }
    		 else
    		 {
    			 Log.i(TAG,"turn up");
    		 }
    	 }
    	 
     }
   
 }  