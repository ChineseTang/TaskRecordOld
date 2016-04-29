package com.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adapter.ViewPagerAdapter;

public class GuideActivity extends Activity implements OnPageChangeListener{
	
	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	
	//底部小点图片
	private ImageView[] dots;
	//记录当前选中位置
	private int currentIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		
		//初始化界面
		initViews();
		//初始化底部小点
		initDots();
	}
	private void initViews() {
		LayoutInflater inflater = LayoutInflater.from(this);
		
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.what_new_one, null));
		views.add(inflater.inflate(R.layout.what_new_two, null));
		views.add(inflater.inflate(R.layout.what_new_three, null));
		views.add(inflater.inflate(R.layout.what_new_four, null));
		
		//初始化Adapter
		vpAdapter = new ViewPagerAdapter(views, this);
		
		vp = (ViewPager) findViewById(R.id.viewpager);
		vp.setAdapter(vpAdapter);
		vp.setOnPageChangeListener(this);
	}
	
	
	private void initDots() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
		
		dots = new ImageView[views.size()];
		
		//循环取得小点图片
		for(int i = 0 ; i < views.size(); i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);//全部都设置为灰色
		}
		
		currentIndex = 0;
		dots[currentIndex].setEnabled(false);//将第一个设置为白色，选中状态
	}
	//设置当下面小点状态变化的时候，改变小点的选中状态
	private void setCurrentDot(int position) {
		if(position < 0 || position > views.size() -1 || currentIndex == position) {
			return;
		}
		dots[position].setEnabled(false);
		dots[currentIndex].setEnabled(true);
		
		currentIndex = position;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guide, menu);
		return true;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		setCurrentDot(arg0);
	}

}
