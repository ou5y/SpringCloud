package com.frameclient.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class WebConfigActivity extends Activity
{
	private String TAG = "MeetingConfigActivity";
	private Button view_back = null;
	private Button view_summit = null;
	private EditText view_web_url = null;
	
	
	/** 用来操作SharePreferences的标识 */
	private final String SHARE_WEB_TAG = "SHARE_WEB_TAG";
	/** 如果登录成功后,用于保存用户名到SharedPreferences,以便下次不再输入 */
	private String SHARE_CONFIG_WEB_URL = "WEB_URL";
	
	private ProgressDialog proDialog = null;
	
	private void findViewsById() {
		view_back = (Button) findViewById(R.id.back);
		view_summit = (Button)findViewById(R.id.btn_summit);
		view_web_url = (EditText)findViewById(R.id.web_url);
	}
	
	private void init()
	{
		SharedPreferences share = getSharedPreferences(SHARE_WEB_TAG, 0);
		String web_url = share.getString(SHARE_CONFIG_WEB_URL, "");
		
		view_web_url.setText(web_url);;
	}
	
	private void setListener() {
		view_back.setOnClickListener(btnListener);
		view_summit.setOnClickListener(btnListener);
	}
	
	private OnClickListener btnListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.back)
			{
				if(proDialog != null)
				{
					proDialog.dismiss();
				}
				finish();	
			}
			else if(v.getId() == R.id.btn_summit)
			{
				String web_url = "";
				web_url = view_web_url.getText().toString();	
				
				if("".equals(web_url))
				{
					Message message = new Message();
    				Bundle bundle = new Bundle();
    				bundle.putInt("message",0);
    				message.setData(bundle);
    				handler.sendMessage(message);
    				
				}
				else
				{
					Message message = new Message();
    				Bundle bundle = new Bundle();
    				bundle.putInt("message",1);
    				message.setData(bundle);
    				handler.sendMessage(message);
    				
    				saveSharePreferences(web_url);			
				}
				
				
				return;
							
			}
		
		}
	};
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		@SuppressLint("NewApi")
		public void handleMessage(Message msg) 
		{
			int message = msg.getData().getInt("message");
			
			switch(message)
			{
			case 0:
				Toast.makeText(WebConfigActivity.this, "url不能为空",Toast.LENGTH_SHORT).show();
              break;
			case 1:
				Toast.makeText(WebConfigActivity.this, "保存成功",Toast.LENGTH_SHORT).show();
              break;
			default:
				break;
				
			}
		}
	};
	
	
	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);	    
        setContentView(R.layout.activity_web_config);
        
        findViewsById();
        setListener();
        
        init();
	}
	
	@Override
	protected void onDestroy() {
		
		Log.i(TAG,"--->onDestroy ");  
		super.onDestroy();
	}
	
	private void saveSharePreferences(String url) {
		SharedPreferences share = getSharedPreferences(SHARE_WEB_TAG, 0);
		share.edit().putString(SHARE_CONFIG_WEB_URL,url).commit();
		share = null;
	}
	
}