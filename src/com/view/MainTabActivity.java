package com.view;


import com.controller.AppApplication;
import com.myview.FloatView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class MainTabActivity extends FragmentActivity {
	// 定义FragmentTabHost对象
	private FragmentTabHost mTabHost;
	// 定义一个布局
	private LayoutInflater layoutInflater;
	private static WindowManager windowManager = null;
	private static WindowManager.LayoutParams windowManagerParams = null;
	private FloatView floatView = null;
	// 定义数组来存放Fragment界面 4个
	private Class fragmentArray[] = { ShowFragment.class, TaskFragment.class,SearchFragment.class, MyFragment.class};

	// 定义数组来存放按钮图片
	private int mImageViewArray[] =

	{ R.drawable.tab_home_btn, R.drawable.tab_message_btn,
			R.drawable.tab_square_btn,R.drawable.tab_selfinfo_btn };

	// Tab选项卡的文字
	private String mTextviewArray[] = { "展示", "任务", "查询", "我的" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_fragments);
		createView();
		initView();
		if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {  
            finish();  
            return;  
        }  
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		windowManager.removeView(floatView);
	}
	private void createView() {
		floatView = new FloatView(MainTabActivity.this);
		floatView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 AlertDialog.Builder dialog = new AlertDialog.Builder(MainTabActivity.this,AlertDialog.THEME_HOLO_LIGHT);
			  		dialog.setTitle("创建任务");
			  		final EditText et = new EditText(MainTabActivity.this);et.setMinHeight(300);
					et.setBackground(null);
					et.setGravity(Gravity.TOP | Gravity.LEFT); 
					dialog.setView(et);
			  		dialog.setPositiveButton("创建",new DialogInterface.OnClickListener() {
			  			@Override
			  			public void onClick(DialogInterface arg0, int arg1) {
			  		
			  			}
			  		});
			  		dialog.setNegativeButton("取消",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
						}
			  		
						});
			  		dialog.show();
			}
		});
		floatView.setImageResource(R.drawable.addtask); // 这里简单的用自带的icon来做演示
		// 获取WindowManager
		windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		// 设置LayoutParams(全局变量）相关参数
		windowManagerParams = ((AppApplication) getApplication()).getWindowParams();
		windowManagerParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;  // 设置window type
		windowManagerParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明
		// 设置Window flag
		windowManagerParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
				| LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
				| WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
		windowManagerParams.alpha = 80f;
		/*
		 * 注意，flag的值可以为： LayoutParams.FLAG_NOT_TOUCH_MODAL 不影响后面的事件
		 * LayoutParams.FLAG_NOT_FOCUSABLE 不可聚焦 LayoutParams.FLAG_NOT_TOUCHABLE
		 * 不可触摸
		 */
		// 调整悬浮窗口至左上角，便于调整坐标
		windowManagerParams.gravity = Gravity.LEFT|Gravity.TOP;   //调整悬浮窗口至左上角
		//windowManagerParams.gravity = Gravity.CENTER_VERTICAL;
		// 以屏幕左上角为原点，设置x、y初始值
		windowManagerParams.x = 600;
		windowManagerParams.y = 600;
		// 设置悬浮窗口长宽数据
		windowManagerParams.width = LayoutParams.WRAP_CONTENT;
		windowManagerParams.height = LayoutParams.WRAP_CONTENT;
		// 显示myFloatView图像
		windowManager.addView(floatView, windowManagerParams);
	}

	// 初始化组件
	private void initView() {
		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		// mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		// 得到fragment的个数
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));

			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);

			// 设置Tab按钮的背景
			mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
		}
	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.textview);

		textView.setText(mTextviewArray[index]);

		return view;
	}
}
