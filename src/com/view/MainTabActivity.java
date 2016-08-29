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
	// ����FragmentTabHost����
	private FragmentTabHost mTabHost;
	// ����һ������
	private LayoutInflater layoutInflater;
	private static WindowManager windowManager = null;
	private static WindowManager.LayoutParams windowManagerParams = null;
	private FloatView floatView = null;
	// �������������Fragment���� 4��
	private Class fragmentArray[] = { ShowFragment.class, TaskFragment.class,SearchFragment.class, MyFragment.class};

	// ������������Ű�ťͼƬ
	private int mImageViewArray[] =

	{ R.drawable.tab_home_btn, R.drawable.tab_message_btn,
			R.drawable.tab_square_btn,R.drawable.tab_selfinfo_btn };

	// Tabѡ�������
	private String mTextviewArray[] = { "չʾ", "����", "��ѯ", "�ҵ�" };

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
			  		dialog.setTitle("��������");
			  		final EditText et = new EditText(MainTabActivity.this);et.setMinHeight(300);
					et.setBackground(null);
					et.setGravity(Gravity.TOP | Gravity.LEFT); 
					dialog.setView(et);
			  		dialog.setPositiveButton("����",new DialogInterface.OnClickListener() {
			  			@Override
			  			public void onClick(DialogInterface arg0, int arg1) {
			  		
			  			}
			  		});
			  		dialog.setNegativeButton("ȡ��",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
						}
			  		
						});
			  		dialog.show();
			}
		});
		floatView.setImageResource(R.drawable.addtask); // ����򵥵����Դ���icon������ʾ
		// ��ȡWindowManager
		windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		// ����LayoutParams(ȫ�ֱ�������ز���
		windowManagerParams = ((AppApplication) getApplication()).getWindowParams();
		windowManagerParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;  // ����window type
		windowManagerParams.format = PixelFormat.RGBA_8888; // ����ͼƬ��ʽ��Ч��Ϊ����͸��
		// ����Window flag
		windowManagerParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
				| LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
				| WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
		windowManagerParams.alpha = 80f;
		/*
		 * ע�⣬flag��ֵ����Ϊ�� LayoutParams.FLAG_NOT_TOUCH_MODAL ��Ӱ�������¼�
		 * LayoutParams.FLAG_NOT_FOCUSABLE ���ɾ۽� LayoutParams.FLAG_NOT_TOUCHABLE
		 * ���ɴ���
		 */
		// �����������������Ͻǣ����ڵ�������
		windowManagerParams.gravity = Gravity.LEFT|Gravity.TOP;   //�����������������Ͻ�
		//windowManagerParams.gravity = Gravity.CENTER_VERTICAL;
		// ����Ļ���Ͻ�Ϊԭ�㣬����x��y��ʼֵ
		windowManagerParams.x = 600;
		windowManagerParams.y = 600;
		// �����������ڳ�������
		windowManagerParams.width = LayoutParams.WRAP_CONTENT;
		windowManagerParams.height = LayoutParams.WRAP_CONTENT;
		// ��ʾmyFloatViewͼ��
		windowManager.addView(floatView, windowManagerParams);
	}

	// ��ʼ�����
	private void initView() {
		// ʵ�������ֶ���
		layoutInflater = LayoutInflater.from(this);

		// ʵ����TabHost���󣬵õ�TabHost
		// mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		// �õ�fragment�ĸ���
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// Ϊÿһ��Tab��ť����ͼ�ꡢ���ֺ�����
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));

			// ��Tab��ť��ӽ�Tabѡ���
			mTabHost.addTab(tabSpec, fragmentArray[i], null);

			// ����Tab��ť�ı���
			mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
		}
	}

	/**
	 * ��Tab��ť����ͼ�������
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
