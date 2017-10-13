package com.frameclient.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebActivity extends Activity
{
	private Button view_back = null;
	private WebView view_web = null;
	
	/** 用来操作SharePreferences的标识 */
	private final String SHARE_WEB_TAG = "SHARE_WEB_TAG";
	/** 如果登录成功后,用于保存用户名到SharedPreferences,以便下次不再输入 */
	private String SHARE_CONFIG_WEB_URL = "WEB_URL";
	
	
	private void findViewsById() 
	{
		view_back = (Button) findViewById(R.id.back);
		view_web = (WebView) findViewById(R.id.webView);
	}
	
	
	private void setListener()
	{
		view_back.setOnClickListener(btn_Listener);
	}
	
	private OnClickListener btn_Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.back)
			{			
				finish();
			}
		}
	};
	
	@SuppressLint("SetJavaScriptEnabled")
	private void openWeb()
	{
		SharedPreferences share = getSharedPreferences(SHARE_WEB_TAG, 0);
		String web_url = share.getString(SHARE_CONFIG_WEB_URL, "");
		share = null;
		
		if(!web_url.contains("http"))
		{
			web_url = String.format("http://%s",web_url);
		}
		
		view_web.loadUrl(web_url);
		WebSettings settings = view_web.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setSupportZoom(true); 
		// 设置出现缩放工具 
		settings.setBuiltInZoomControls(true);
		//扩大比例的缩放
		settings.setUseWideViewPort(true);
		//自适应屏幕
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		settings.setLoadWithOverviewMode(true);
		view_web.setWebViewClient(new WebViewClient()
		{
	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url)
	        {
	            // TODO Auto-generated method stub
	            //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
	            view.loadUrl(url);
	            return true;
	        }
	   });
		
	}
	
	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
        setContentView(R.layout.activity_web);
    	findViewsById();
		setListener();
		
		openWeb();
	}
	
	@Override 
    public boolean onKeyDown(int keyCode, KeyEvent event)
	{  
        if ((keyCode == KeyEvent.KEYCODE_BACK) )
        {  
        	if(view_web.canGoBack())
        	{
        		view_web.goBack(); //goBack()表示返回WebView的上一页面  
        		return true;
        	}
        	else
        	{
        		finish();
        	}
        }  
        return false;  
    }  
}