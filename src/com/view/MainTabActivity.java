package com.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class MainTabActivity extends FragmentActivity {
	// ����FragmentTabHost����
	private FragmentTabHost mTabHost;
	// ����һ������
	private LayoutInflater layoutInflater;
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
		initView();
		if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {  
            finish();  
            return;  
        }  
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
