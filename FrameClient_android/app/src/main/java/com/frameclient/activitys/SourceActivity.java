package com.frameclient.activitys;

import com.frameclient.adapter.ResourceAdapter;
import com.frameclient.utils.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class SourceActivity extends Activity{
	private String TAG = "SourceAcitvity";
	//private List<ResourceItemInfo> list = null;
	private Button view_menu = null;
	private Button view_back = null;
	public ListView view_listview = null;
	private ResourceAdapter listadapter = null;
	private Context mcontext = this;
	
	private LinearLayout view_layout_menu = null;
	private LinearLayout view_layout_menu_changepass = null;
	private LinearLayout view_layout_menu_web = null;
	private LinearLayout view_layout_menu_about = null;
	private LinearLayout view_layout_menu_quit = null;
	
	LayoutInflater layoutInflater = null;
	View popview = null;
    PopupWindow popWindow = null;
	
	private boolean isLogin;
	private boolean initMenu = false;
	private boolean isShow = false;
	private ProgressDialog proDialog;
	
	/** 用来操作SharePreferences的标识 */
	private final String SHARE_WEB_TAG = "SHARE_WEB_TAG";
	/** 如果登录成功后,用于保存用户名到SharedPreferences,以便下次不再输入 */
	private String SHARE_CONFIG_WEB_URL = "WEB_URL";
	
	
	Handler handler = new Handler() {
		public void handleMessage(Message msg) 
		{
			int type  = msg.getData().getInt("type");
			switch(type)
			{
			case -3:
				Toast.makeText(SourceActivity.this, "网页地址不能为空!",
						Toast.LENGTH_SHORT).show();	
				break;
			case -2:
				if (SoftResource.nextResource != null) {
					SoftResource.nextResource = null;
					Toast.makeText(SourceActivity.this, "资源更新失败!",
							Toast.LENGTH_SHORT).show();
				} else {
					SoftResource.isGetResource = false;
					Toast.makeText(SourceActivity.this, "请求资源失败!",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case 0:
				listadapter.notifyDataSetChanged();
				break;
			case 1:
				final String url = msg.getData().getString("url");

				AlertDialog.Builder builder = new AlertDialog.Builder(
						SourceActivity.this);// 新建一个Alertdialog的builder
				builder.setTitle("消息");
				builder.setMessage("收到新预案是否打开");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setPositiveButton("是",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								Intent intent = new Intent();
								intent.setAction("android.intent.action.VIEW");
								Uri content_url = Uri.parse("http://" + url);
								intent.setData(content_url);
								startActivity(intent);
							}
						});
				builder.setNegativeButton("否",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						});
				builder.create();
				builder.show();
				/*
				 * new AlertDialog.Builder(SourceActivity.this) .setTitle("消息")
				 * .setMessage("是否打开"+url) .setPositiveButton("是", null)
				 * .setNegativeButton("否", null) .show();
				 */
				
				break;
			case 2:
				if (SoftResource.nextResource != null) {
					for (int i = 0; i < SoftResource.getAdapterListSize(); i++) {
						ResourceItemInfo elem = (ResourceItemInfo) SoftResource
								.getAdapterElem(i);
						elem.isShow = false;
						SoftResource.setAdapterElem(i, elem);
						Log.v(TAG, "set id = " + elem.id + " name = "
								+ elem.name + " isShow false");
					}

					SoftResource.level++;
					// Log.v(TAG,
					// "=================> level  "+SoftResource.level+"  count = "+getCount());

					Log.v(TAG, "start time....count = "
							+ SoftResource.getList().size());
					for (int i = SoftResource.nextResource.indexInList + 1; i < SoftResource
							.getList().size(); i++) {
						ResourceItemInfo elem = (ResourceItemInfo) SoftResource
								.getElem(i);

						// Log.v(TAG, "the " +i+ " name = " + elem.name);

						if (elem.parent_index == SoftResource.nextResource.indexInList) {
							// Log.v(TAG,
							// "pos = "+i+" rii id = "+rii.id+" elem id "+elem.id+
							// " parentid  "+elem.parent_id+
							// " name = "+elem.name);
							// Log.v(TAG,"elem state  = "+elem.status);
							ResourceItemInfo info = ResourceItemInfo.copy(elem);
							info.isShow = true;
							info.level = SoftResource.level;
							SoftResource.insertAdapterElem(info);

							// Log.v(TAG,
							// "set "+i+" level "+info.level+" isShow true add into s_list size "+SoftResource.getAdapterListSize());
						}
					}

					SoftResource.nextResource = null;

					SoftResource.isGetResource = true;
					listadapter.notifyDataSetChanged();

					Toast.makeText(SourceActivity.this, "资源更新成功!",
							Toast.LENGTH_SHORT).show();
				} else {
					SoftResource.isGetResource = true;
					listadapter.notifyDataSetChanged();

					Toast.makeText(SourceActivity.this, "请求资源成功!",
							Toast.LENGTH_SHORT).show();
				}

				break;
			case 3:
				Toast.makeText(SourceActivity.this, "设备不在线", Toast.LENGTH_SHORT)
						.show();
				break;
			}
		}
	};

	private OnClickListener menu_listerner = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.layout_menu) {
				if (isShow) {
					popview.setVisibility(View.INVISIBLE);
					isShow = false;
				} else {
					popview.setVisibility(View.VISIBLE);
					isShow = true;
				}
			} else if (v.getId() == R.id.layout_change_password) {
				Intent intent = new Intent();
				intent.setClass(SourceActivity.this,
						ChangePasswordActivity.class);
				startActivity(intent);
			} else if (v.getId() == R.id.layout_web) {
				Intent intent = new Intent();
				intent.setClass(SourceActivity.this,
						WebConfigActivity.class);
				startActivity(intent);
			} else if (v.getId() == R.id.layout_about) {
				Intent intent = new Intent();
				intent.setClass(SourceActivity.this, AboutActivity.class);
				startActivity(intent);
			} else if (v.getId() == R.id.layout_quit) {
				Intent intent = new Intent("com.frameclient.networkserver.over");
				sendBroadcast(intent);
				if (VideoActivity.instance != null) {
					VideoActivity.instance.finish();
				}
				finish();
				System.exit(0);// 正常退出App
			}
		}
	};
	
	 private BroadcastReceiver broadcastRec = new BroadcastReceiver(){
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
			
			if(intent.getAction().equals("com.frameclient.notify"))
			{
				int type = intent.getIntExtra("type",-1);
				String url = intent.getStringExtra("url");
				
				
				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putInt("type", type);
				bundle.putString("url",url);
				message.setData(bundle);
				handler.sendMessage(message);
			}
			else if (intent.getAction().equals("com.frameclient.getresource.rsp")) 
			{
				
				Log.i(TAG,"get com.frameclient.getresource.rsp.........."); 
				if (proDialog != null) 
				{
					proDialog.dismiss();
					proDialog = null;
				}
				
				int result = intent.getIntExtra("result", -100);
				Message message = new Message();
				Bundle bundle = new Bundle();
				if (result < 0)
				{
					isLogin = false;
					bundle.putInt("type", -2);
				}
				else
				{
					isLogin = true;
					SoftResource.login = true;
					bundle.putInt("type", 2);
				}

				message.setData(bundle);
				handler.sendMessage(message);
			
			}
			else if (intent.getAction().equals("com.frameclient.camera.statechange"))
			{
				listadapter.notifyDataSetChanged();
			}
		
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
    	findViewsById();
		setListener();
				
		if(isLogin == true)
		{
			listadapter = new ResourceAdapter(mcontext, handler,
					SoftResource.getList(), SoftResource.getAdapterList());
			view_listview.setAdapter(listadapter);
			listadapter.notifyDataSetInvalidated();
			listadapter.notifyDataSetChanged();

			Log.i(TAG,
					"--->onCreate list.size() = " + SoftResource.getListSize());
		} 
		else
		{
			
			Intent intent = this.getIntent();
			isLogin = intent.getBooleanExtra("isLogin", false);
			
			if(isLogin)
			{
				
		        listadapter = new ResourceAdapter(mcontext,handler,SoftResource.getList(),SoftResource.getAdapterList());
		        view_listview.setAdapter(listadapter);
		
		        //setSelection(list.size() - 1);
				listadapter.notifyDataSetInvalidated();
				listadapter.notifyDataSetChanged();
				
		        IntentFilter intentFilter = new IntentFilter();
		        intentFilter.addAction("com.frameclient.notify");
		        intentFilter.addAction("com.frameclient.getresource.rsp");
		        Log.i(TAG, "registerReceiver.....");
		        registerReceiver(broadcastRec,intentFilter);
		        
		        SoftResource.login =true;
		       
			}
			else
			{
				Intent it = new Intent();
				it.setClass(SourceActivity.this, LoginActivity.class);
			    startActivity(it);
			    SoftResource.login = false;
			    finish();
			}
			
			Log.i(TAG,"--->onCreate list.size() = "+SoftResource.getListSize());  
		}

        
    }
    
    @Override  
    protected void onStart() {  
        // TODO Auto-generated method stub  
    	super.onStart();
    	if(SoftResource.isGetResource == false)
    	{
    		proDialog = ProgressDialog.show(SourceActivity.this, "请求资源",	"资源请求中..请稍后....", true, true);
    	}
    	

    	Log.i(TAG,"--->onStart list.size() = "+SoftResource.getListSize());  
    }  
    
    protected void onResume() {  
        // TODO Auto-generated method stub  
    	super.onResume();
    	
        Log.i(TAG,"--->onResume list.size() = "+SoftResource.getListSize());  
    }  
    
    
    protected void onPause() {  
        // TODO Auto-generated method stub  
    	super.onPause();
        Log.i(TAG,"--->onPause list.size() = "+SoftResource.getListSize());  
    }  
    
    @Override  
    protected void onRestart() {  
        // TODO Auto-generated method stub  
    	Log.i(TAG,"--->onRestart list.size() = "+SoftResource.getListSize());  
    	super.onRestart();  
    }  
    
    @Override  
    protected void onStop() {  
        // TODO Auto-generated method stub  
    	Log.i(TAG,"--->onStop list.size() = "+SoftResource.getListSize());  
    	super.onStop();  
    }  
 
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "--->onDestory list.size() = " + SoftResource.getListSize());
		if (popWindow != null) {
			popWindow.dismiss();
		}
		if (isLogin) {
			unregisterReceiver(broadcastRec);
		}
	}

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case 0:
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 如果是返回键,直接返回到桌面

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Log.v(TAG, "listadapter level = " + SoftResource.level
					+ " listadapter = " + listadapter);
			if (SoftResource.level == 1) {
				showExitGameAlert();

			} else {
				listadapter.back();
				listadapter.notifyDataSetChanged();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);

	}
	
	private void exitApp()
	{
		Intent intent = new Intent("com.frameclient.networkserver.over");
		sendBroadcast(intent);
		if (VideoActivity.instance != null)
		{
			VideoActivity.instance.finish();
		}
		finish();

		System.exit(0);// 正常退出App
	}

	private void showExitGameAlert() {
		new AlertDialog.Builder(this)
				.setTitle("系统提示")
				// 设置对话框标题

				.setMessage("将退出程序？")
				// 设置显示的内容

				.setPositiveButton("确定", new DialogInterface.OnClickListener() {// 添加确定按钮
							@Override
							public void onClick(DialogInterface dialog,
									int which) {// 确定按钮的响应事件
								// TODO Auto-generated method stub
								exitApp();
							}

						})
				.setNegativeButton("返回", new DialogInterface.OnClickListener() {// 添加返回按钮

							@Override
							public void onClick(DialogInterface dialog,
									int which) {// 响应事件
								// TODO Auto-generated method stub
								Log.i(TAG, "turn back");
							}

						}).show();// 在按键响应事件中显示此对话框
	}

	private void findViewsById() {
		view_menu = (Button) findViewById(R.id.btn_menu);
		view_back = (Button) findViewById(R.id.btn_back);
		view_listview = (ListView) findViewById(R.id.list);
	}
	
	private void setListener() {
		view_menu.setOnClickListener(menuListener);
		view_back.setOnClickListener(backListener);
	}
	
	/** 菜单Button Listener */
	private OnClickListener menuListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// Intent intent = new Intent();
			// intent.setClass(SourceActivity.this, MenuActivity.class);
			// startActivity(intent);
			// layoutInflater = LayoutInflater.from(context);
			if (initMenu == false) {
				layoutInflater = (LayoutInflater) (SourceActivity.this)
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				popview = layoutInflater.inflate(R.layout.activity_menu, null);

				DisplayMetrics dm = new DisplayMetrics();
				// 取得窗口属性
				getWindowManager().getDefaultDisplay().getMetrics(dm);
				// 窗口的宽度
				int screenWidth = dm.widthPixels;
				// 窗口高度
				int width = (int) ((int) screenWidth * 0.6);
				Log.d(TAG, "screen width = " + screenWidth + " menu width = "
						+ width);
				popWindow = new PopupWindow(popview, width,
						LayoutParams.MATCH_PARENT);
				popWindow.setContentView(popview);
				
				view_layout_menu = (LinearLayout)popview.findViewById(R.id.layout_menu);
				view_layout_menu.setOnClickListener(menu_listerner);
				
				view_layout_menu_changepass = (LinearLayout)popview.findViewById(R.id.layout_change_password);
				view_layout_menu_changepass.setOnClickListener(menu_listerner);

				view_layout_menu_web = (LinearLayout) popview
						.findViewById(R.id.layout_web);
				view_layout_menu_web.setOnClickListener(menu_listerner);

				view_layout_menu_about = (LinearLayout) popview
						.findViewById(R.id.layout_about);
				view_layout_menu_about.setOnClickListener(menu_listerner);
				
				view_layout_menu_quit = (LinearLayout)popview.findViewById(R.id.layout_quit);
				view_layout_menu_quit.setOnClickListener(menu_listerner);
				
				popWindow.showAtLocation(v, Gravity.LEFT |Gravity.TOP, 0, 0);
				initMenu = true;
				isShow = true;
			}
			
			if(isShow == false)
			{
				popview.setVisibility(View.VISIBLE);
				isShow = true;
			}
		
		}
	};
	
	/** 地图Button Listener */
	private OnClickListener backListener = new OnClickListener() 
	{
		@Override
		public void onClick(View v)
		{
			
			SharedPreferences share = getSharedPreferences(SHARE_WEB_TAG, 0);
			String web_url = share.getString(SHARE_CONFIG_WEB_URL, "");
			if("".equals(web_url))
			{
				Message message = new Message();
				Bundle bundle = new Bundle();
				
				bundle.putInt("type", -3);
				message.setData(bundle);
				handler.sendMessage(message);
				
				return;
			}
			
			Intent intent = new Intent();
			intent.setClass(SourceActivity.this,WebActivity.class);
			startActivity(intent);
			/*
    		if(SoftResource.level == 1)
    		{
    			showExitGameAlert();
    			
    		}
    		else
    		{
    			listadapter.back();
    			listadapter.notifyDataSetChanged();
    		}
    		*/
			
		}
	};

}