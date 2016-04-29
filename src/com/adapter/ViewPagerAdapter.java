package com.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.view.IndexActivity;
import com.view.R;

//������������������
public class ViewPagerAdapter extends PagerAdapter {
	//�����б�
	private List<View> views;
	private Activity activity;
	
	public ViewPagerAdapter(List<View> views, Activity activity) {
		this.views = views;
		this.activity = activity;
	}
	
	// ����arg1λ�õĽ���
		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(views.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}
		// ��õ�ǰ������
		@Override
		public int getCount() {
			if (views != null) {
				return views.size();
			}
			return 0;
		}
		private void goHome() {
			// ��ת
			Intent intent = new Intent(activity, IndexActivity.class);
			activity.startActivity(intent);
			activity.finish();
		}
		
		// �ж��Ƿ��ɶ������ɽ���
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return (arg0 == arg1);
		}
		// ��ʼ��arg1λ�õĽ���
		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(views.get(arg1), 0);
			if (arg1 == views.size() - 1) {
				
				Button mstart = (Button) arg0.findViewById(R.id.iv_start_taskRecord);
				mstart.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// �����Ѿ�����
						goHome();
					}
				});
			}
			return views.get(arg1);
		}

}
