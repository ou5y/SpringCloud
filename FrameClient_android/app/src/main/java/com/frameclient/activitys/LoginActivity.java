package com.frameclient.activitys;

import com.frameclient.services.NetWorkService;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private String username;
	private String password;
	private String ipaddr;

	private EditText view_username;
	private EditText view_password;
	private EditText view_ipaddr;
	private Button view_login;
	private CheckBox view_remember;
	private CheckBox view_autologin;

	
	private static final String TAG = "FrmaeDebug";
	private static final int MENU_EXIT = Menu.FIRST - 1;
	private static final int MENU_ABOUT = Menu.FIRST;

	/** 用来操作SharePreferences的标识 */
	private final String SHARE_LOGIN_TAG = "SHARE_LOGIN_TAG";
	/** 如果登录成功后,用于保存用户名到SharedPreferences,以便下次不再输入 */
	private String SHARE_LOGIN_USERNAME = "LOGIN_USERNAME";
	/** 如果登录成功后,用于保存PASSWORD到SharedPreferences,以便下次不再输入 */
	private String SHARE_LOGIN_PASSWORD = "LOGIN_PASSWORD";
	/** 如果登录成功后,用于保存PASSWORD到SharedPreferences,以便下次不再输入 */
	private String SHARE_LOGIN_IP_ADDR = "LOGIN_IPADDR";

	/** 如果登陆失败,这个可以给用户确切的消息显示,true是网络连接失败,false是用户名和密码错误 */

	/** 登录loading提示框 */
	private ProgressDialog proDialog;

	/** 登录后台通知更新UI线程,主要用于登录失败,通知UI线程更新界面 */
	Handler loginHandler = new Handler() {
		public void handleMessage(Message msg) {
			int error = msg.getData().getInt("error");
	
			switch (error) {
			case 1:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "没登录!", Toast.LENGTH_SHORT)
						.show();
				break;
			case 2:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "网络错误!", Toast.LENGTH_SHORT)
						.show();
				break;
			case 10:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "没初始化!", Toast.LENGTH_SHORT)
						.show();
				break;
			case 11:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "参数错误!", Toast.LENGTH_SHORT)
						.show();
				break;
			case 12:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "数据错误!", Toast.LENGTH_SHORT)
						.show();
				break;
			case 1001:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "数据库访问错误!",
						Toast.LENGTH_SHORT).show();
				break;
			case 1101:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "摄像机不存在!",
						Toast.LENGTH_SHORT).show();
				break;
			case 1102:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "没找到转码服务器!",
						Toast.LENGTH_SHORT).show();
				break;
			case 1103:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "设备未登录!", Toast.LENGTH_SHORT)
						.show();
				break;
			case 1104:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "设备流打开错误!",
						Toast.LENGTH_SHORT).show();
				break;
			case 1105:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "连接流媒体或转码服务器失败!",
						Toast.LENGTH_SHORT).show();
				break;
			case 0:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT)
						.show();
				Intent intent = new Intent();
				intent.putExtra("isLogin", true);
				intent.setClass(LoginActivity.this, SourceActivity.class);
				startActivity(intent);
				finish();
				try {
					unregisterReceiver(broadcastRec);
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case -1:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "用户不存在!", Toast.LENGTH_SHORT)
						.show();
				view_username.setText("");
				view_password.setText("");
				clearShareUserInfo(false, true, true);
				break;
			case -2:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "密码错误!", Toast.LENGTH_SHORT)
						.show();
				view_password.setText("");
				clearShareUserInfo(false, false, true);
				break;
			case -3:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "服务器无法连接!",
						Toast.LENGTH_SHORT).show();
				break;
			case -4:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "获取资源失败!",
						Toast.LENGTH_SHORT).show();
				break;
			case -5:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "IP地址为空!",
						Toast.LENGTH_SHORT).show();
				break;
			case -6:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "用户名为空!", Toast.LENGTH_SHORT)
						.show();
				break;
			case -7:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "密码为空!", Toast.LENGTH_SHORT)
						.show();
				break;
			case -8:
				if (proDialog != null) 
				{
					proDialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, "登陆超时!", Toast.LENGTH_SHORT)
						.show();
				break;
			default:
				break;

			}
		}
	};

	private BroadcastReceiver broadcastRec = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub

			if (intent.getAction().equals("com.frameclient.login.rsp")) {
				int err = intent.getIntExtra("error", -100);

				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putInt("error", err);
				message.setData(bundle);
				loginHandler.sendMessage(message);
			} 
			else if (intent.getAction().equals("com.frameclient.connection.rsp"))
			{
				int res = intent.getIntExtra("result", -100);
				if (res < 0) {
					Message message = new Message();
					Bundle bundle = new Bundle();
					bundle.putInt("error", -3);
					message.setData(bundle);
					loginHandler.sendMessage(message);
				}
				

			}
			else if (intent.getAction().equals("com.frameclient.logout.rsp"))
			{

				String uid = intent.getStringExtra("uid");
			} 
			else if (intent.getAction().equals("com.frameclient.login.timeout"))
			{
				int result = intent.getIntExtra("result", -100);
				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putInt("error", -8);
				message.setData(bundle);
				loginHandler.sendMessage(message);
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		findViewsById();
		initView(false);
		setListener();

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("com.frameclient.connection.rsp");
		intentFilter.addAction("com.frameclient.login.rsp");
		intentFilter.addAction("com.frameclient.logout.rsp");
		intentFilter.addAction("com.frameclient.login.timeout");
		Log.i(TAG, "registerReceiver.....");
		registerReceiver(broadcastRec, intentFilter);

		//Log.i(TAG, "starting newtworkservice.....");
		// Intent it = new Intent("com.frameclient.services.networkservice");
		//Intent it = new Intent(LoginActivity.this, NetWorkService.class);
		//startService(it);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		switch (item.getItemId()) {
		case MENU_EXIT:
			finish();
			break;
		case MENU_ABOUT:
			alertAbout();
			break;
		}
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "-->onDestory");
		try {
			unregisterReceiver(broadcastRec);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0 && resultCode == RESULT_OK) {
			Log.v(TAG, "onActivityResult : loginacitvity finish");
			finish();
		}
	}

	private void findViewsById() {
		view_username = (EditText) findViewById(R.id.username);
		view_password = (EditText) findViewById(R.id.password);
		view_ipaddr = (EditText) findViewById(R.id.ipaddr);
		view_login = (Button) findViewById(R.id.btn_login);
		view_remember = (CheckBox) findViewById(R.id.remember);
		view_autologin = (CheckBox) findViewById(R.id.autologin);
	}

	/**
	 * 初始化界面
	 * 
	 * @param isRememberMe
	 * 
	 * */
	private void initView(boolean isRememberMe) {
		SharedPreferences share = getSharedPreferences(SHARE_LOGIN_TAG, 0);
		String username = share.getString(SHARE_LOGIN_USERNAME, "");
		String password = share.getString(SHARE_LOGIN_PASSWORD, "");
		String ipaddr = share.getString(SHARE_LOGIN_IP_ADDR, "");
		Log.d(this.toString(), "userName=" + username + " password=" + password
				+ " IPAddr = " + ipaddr);
		if (!"".equals(username)) {
			view_username.setText(username);
		}
		if (!"".equals(password)) {
			view_password.setText(password);
		}
		if (!"".equals(ipaddr)) {
			view_ipaddr.setText(ipaddr);
		}
		share = null;
	}

	private void saveSharePreferences(boolean saveIpaddr, boolean saveUserName,
			boolean savePassword) {
		SharedPreferences share = getSharedPreferences(SHARE_LOGIN_TAG, 0);
		if (saveUserName) {
			Log.d(this.toString(), "saveUserName="
					+ view_username.getText().toString());
			share.edit()
					.putString(SHARE_LOGIN_USERNAME,
							view_username.getText().toString()).commit();
		}
		if (savePassword) {
			share.edit()
					.putString(SHARE_LOGIN_PASSWORD,
							view_password.getText().toString()).commit();
		}
		if (saveIpaddr) {
			share.edit()
					.putString(SHARE_LOGIN_IP_ADDR,
							view_ipaddr.getText().toString()).commit();
		}
		share = null;
	}

	private void setListener() {
		view_login.setOnClickListener(submitListener);
		view_remember.setOnClickListener(rememberPasswordListener);
		view_autologin.setOnClickListener(autologinListener);
	}

	/** 登录Button Listener */
	private OnClickListener submitListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			proDialog = ProgressDialog.show(LoginActivity.this, "连接中..",
					"连接中..请稍后....", true, true);
			Thread loginThread = new Thread(new LoginFailureHandler());
			loginThread.start();
		}
	};

	/** 记住密码 checkbox Listener */
	private OnClickListener rememberPasswordListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (view_remember.isChecked()) {
				saveSharePreferences(true, true, true);
			}
		}
	};

	/** 自动登录 checkbox Listener */
	private OnClickListener autologinListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (view_autologin.isChecked() && !view_remember.isChecked()) {
				saveSharePreferences(true, true, true);
			}
		}
	};

	/** 弹出关于对话框 */
	private void alertAbout() {
		new AlertDialog.Builder(LoginActivity.this)
				.setTitle(R.string.MENU_ABOUT)
				.setMessage(R.string.aboutInfo)
				.setPositiveButton(R.string.ok_label,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
							}
						}).show();
	}

	/** 清除密码 */
	private void clearShareUserInfo(boolean ip, boolean username,
			boolean password) {
		SharedPreferences share = getSharedPreferences(SHARE_LOGIN_TAG, 0);
		if (ip) {
			share.edit().putString(SHARE_LOGIN_IP_ADDR, "").commit();
		}

		if (username) {
			share.edit().putString(SHARE_LOGIN_USERNAME, "").commit();
		}

		if (password) {
			share.edit().putString(SHARE_LOGIN_PASSWORD, "").commit();
		}

		share = null;
	}

	class LoginFailureHandler implements Runnable {
		@Override
		public void run() {
			username = view_username.getText().toString();
			password = view_password.getText().toString();
			ipaddr = view_ipaddr.getText().toString();

			if ("".equals(ipaddr)) {
				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putInt("error", -5);
				message.setData(bundle);
				loginHandler.sendMessage(message);
			} else if ("".equals(username)) {
				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putInt("error", -6);
				message.setData(bundle);
				loginHandler.sendMessage(message);
			} else if ("".equals(password)) {

				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putInt("error", -7);
				message.setData(bundle);
				loginHandler.sendMessage(message);

			} else {
				/*
				Intent conn = new Intent("com.frameclient.connection");
				conn.putExtra("ipaddr", ipaddr);
				sendBroadcast(conn);
				*/
				Intent it = new Intent(LoginActivity.this, NetWorkService.class);
				
			    Bundle bundle = new Bundle();  
		        bundle.putInt("opera",0); 
		        bundle.putString("ipaddr", ipaddr);
		        bundle.putString("username", username);
		        bundle.putString("password", password);
		        it.putExtras(bundle); 
		        
		        startService(it);	

			}

		}

	}

}
