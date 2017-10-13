package com.frameclient.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class AboutActivity extends Activity
{
	private Button view_back = null;
	
	private void findViewsById() {
		view_back = (Button) findViewById(R.id.btn_aboutback);
	}
	
	
	private void setListener() {
		
		view_back.setOnClickListener(btn_Listener);
	}
	
	private OnClickListener btn_Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.btn_aboutback)
			{
				finish();
			}
		}
	};

	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
        setContentView(R.layout.activity_about);
    	findViewsById();
		setListener();
        
	}
	
}