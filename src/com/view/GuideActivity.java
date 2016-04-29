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
	
	//�ײ�С��ͼƬ
	private ImageView[] dots;
	//��¼��ǰѡ��λ��
	private int currentIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		
		//��ʼ������
		initViews();
		//��ʼ���ײ�С��
		initDots();
	}
	private void initViews() {
		LayoutInflater inflater = LayoutInflater.from(this);
		
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.what_new_one, null));
		views.add(inflater.inflate(R.layout.what_new_two, null));
		views.add(inflater.inflate(R.layout.what_new_three, null));
		views.add(inflater.inflate(R.layout.what_new_four, null));
		
		//��ʼ��Adapter
		vpAdapter = new ViewPagerAdapter(views, this);
		
		vp = (ViewPager) findViewById(R.id.viewpager);
		vp.setAdapter(vpAdapter);
		vp.setOnPageChangeListener(this);
	}
	
	
	private void initDots() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
		
		dots = new ImageView[views.size()];
		
		//ѭ��ȡ��С��ͼƬ
		for(int i = 0 ; i < views.size(); i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);//ȫ��������Ϊ��ɫ
		}
		
		currentIndex = 0;
		dots[currentIndex].setEnabled(false);//����һ������Ϊ��ɫ��ѡ��״̬
	}
	//���õ�����С��״̬�仯��ʱ�򣬸ı�С���ѡ��״̬
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
