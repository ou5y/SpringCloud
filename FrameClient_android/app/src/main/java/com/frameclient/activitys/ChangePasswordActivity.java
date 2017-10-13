package com.frameclient.activitys;

import java.io.InputStream;
import java.net.Socket;

import com.frameclient.services.NetWorkService;
import com.frameclient.utils.SoftResource;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;

public class ChangePasswordActivity extends Activity
{
	private String TAG = "ChangePasswordActivity";
	private Button view_back = null;
	private Button view_summit = null;
	private EditText view_current_pass = null;
	private EditText view_new_pass = null;
	private EditText view_sure_new_pass = null;
	
	private ProgressDialog proDialog = null;
	
	private void findViewsById() {
		view_back = (Button) findViewById(R.id.back);
		view_summit = (Button)findViewById(R.id.btn_summit);
		
		view_current_pass = (EditText)findViewById(R.id.cur_pass);
		view_new_pass = (EditText)findViewById(R.id.new_pass);
		view_sure_new_pass = (EditText)findViewById(R.id.sure_new_pass);
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
				String cur_pass = "";
				String new_pass = "";
				String sure_new_pass = "";
				
				cur_pass = view_current_pass.getText().toString();	
				if("".equals(cur_pass))
				{
					Message message = new Message();
    				Bundle bundle = new Bundle();
    				bundle.putInt("message",-5);
    				message.setData(bundle);
    				handler.sendMessage(message);
    				return;
				}
				
				new_pass = view_new_pass.getText().toString();
				if("".equals(new_pass))
				{
					Message message = new Message();
    				Bundle bundle = new Bundle();
    				bundle.putInt("message",-2);
    				message.setData(bundle);
    				handler.sendMessage(message);
    				return;
				}
				
				sure_new_pass = view_sure_new_pass.getText().toString();
				if("".equals(sure_new_pass))
				{
					Message message = new Message();
    				Bundle bundle = new Bundle();
    				bundle.putInt("message",-3);
    				message.setData(bundle);
    				handler.sendMessage(message);
    				return;
				}
				
				if(new_pass.equals(sure_new_pass) == false)
				{
					Message message = new Message();
    				Bundle bundle = new Bundle();
    				bundle.putInt("message",-4);
    				message.setData(bundle);
    				handler.sendMessage(message);
    				return;
				}
				
				/*
				Intent intent = new Intent("com.frameclient.changepassword");
				intent.putExtra("curr_pass", cur_pass);
				intent.putExtra("new_pass",new_pass);
				sendBroadcast(intent);	
				*/
				Intent it = new Intent(ChangePasswordActivity.this, NetWorkService.class);
				
			    Bundle bundle = new Bundle();  
		        bundle.putInt("opera",3); 
		        bundle.putString("curr_pass", cur_pass);
		        bundle.putString("new_pass", new_pass);
		        it.putExtras(bundle); 
		        
		        startService(it);	
				proDialog = ProgressDialog.show(ChangePasswordActivity.this, "请稍后..","正在提交中....", true, true);
			}
		
		}
	};
	
	Handler handler = new Handler() {
		@SuppressLint("NewApi")
		public void handleMessage(Message msg) 
		{
			int message = msg.getData().getInt("message");
			
			switch(message)
			{
			case 0:
				Toast.makeText(ChangePasswordActivity.this, "修改成功",Toast.LENGTH_SHORT).show();
              break;
			case -1:
				Toast.makeText(ChangePasswordActivity.this, "当前密码错误",Toast.LENGTH_SHORT).show();
              break;
			case -5:				
				Toast.makeText(ChangePasswordActivity.this, "请输入当前密码",Toast.LENGTH_SHORT).show();
				break;
			case -2:
				Toast.makeText(ChangePasswordActivity.this, "请输入新密码",Toast.LENGTH_SHORT).show();
				break;
			case -3:
				Toast.makeText(ChangePasswordActivity.this, "请确认新密码",Toast.LENGTH_SHORT).show();
				break;
			case -4:
				Toast.makeText(ChangePasswordActivity.this, "新密码不一致",Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
				
			}
		}
	};
	
	
	private BroadcastReceiver broadcastRec = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
     		if(intent.getAction().equals("com.frameclient.changepassword.rsp"))
     		{
     			int error = intent.getIntExtra("rsp", -10);
     			Log.v(TAG,"changeword rsp error = "+error);
     			Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putInt("message",error);
				message.setData(bundle);
				handler.sendMessage(message);
				
				if(proDialog != null)
				{
					proDialog.dismiss();
				}
     		}

	 		
		}
    };
	
	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);	    
        setContentView(R.layout.activity_changepassword);
        
        findViewsById();
        setListener();
        
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.frameclient.changepassword.rsp");
        registerReceiver(broadcastRec,intentFilter);
	}
	
	@Override
	protected void onDestroy() {
		
		Log.i(TAG,"--->onDestroy ");  
		try {
			unregisterReceiver(broadcastRec);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.onDestroy();
	}
	
}