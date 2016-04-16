package com.view;

import com.controller.ActivityCollector;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity {
	//the BaseActivity is used for debugging , we can know what current activity now
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//…Ë÷√app º÷’ ˙∆¡
		Log.d("BaseActivity", getClass().getSimpleName());//print the BaseActivity
		ActivityCollector.addActivity(this);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
}
