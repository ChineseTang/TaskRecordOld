package com.view;

import java.util.ArrayList;
import java.util.List;

import com.myview.MonthDateView;
import com.myview.MonthDateView.DateClick;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class IndexActivity extends BaseActivity implements OnItemClickListener {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ArrayList<String> menuLists;
	private ArrayAdapter<String> adapter;
	private ActionBarDrawerToggle mDrawerToggle;
	private String mtitle;
	private ImageView iv_left;
	private ImageView iv_right;
	private TextView tv_date;
	private TextView tv_week;
	private TextView tv_today;
	private MonthDateView monthDateView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		getActionBar().setDisplayShowHomeEnabled(false); //取消actionbar左侧的快捷图标和文字
		//getActionBar().setDisplayShowTitleEnabled(false); 
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		mDrawerList = (ListView) findViewById(R.id.left_drawers);
		menuLists = new ArrayList<String>();
		mtitle = (String) getTitle();
		menuLists.add("首页");
		menuLists.add("设置");
		menuLists.add("记事本");
		menuLists.add("任务");
		menuLists.add("设置");
		menuLists.add("注销");
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,menuLists);
		
		mDrawerList.setAdapter(adapter);
		
		mDrawerList.setOnItemClickListener(this);
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, 
				R.string.drawer_open, R.string.drawer_close){

					@Override
					public void onDrawerClosed(View drawerView) {
						super.onDrawerClosed(drawerView);
						getActionBar().setTitle(mtitle);
						invalidateOptionsMenu();//call OnprepareOptionValue
					}

					@Override
					public void onDrawerOpened(View drawerView) {
						super.onDrawerOpened(drawerView);
						getActionBar().setTitle("事务记录");
						invalidateOptionsMenu();
					}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		//begin the app icon 
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(12);
		list.add(15);
		list.add(16);
		iv_left = (ImageView) findViewById(R.id.iv_left);
		iv_right = (ImageView) findViewById(R.id.iv_right);
		monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
		tv_date = (TextView) findViewById(R.id.date_text);
		tv_week  =(TextView) findViewById(R.id.week_text);
		tv_today = (TextView) findViewById(R.id.tv_today);
		monthDateView.setTextView(tv_date,tv_week);
		monthDateView.setDaysHasThingList(list);
		monthDateView.setDateClick(new DateClick() {
			
			@Override
			public void onClickOnDate() {
				Toast.makeText(getApplication(), "点击了：" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
			}
		});
		setOnlistener();
	}
	private void setOnlistener(){
		iv_left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				monthDateView.onLeftClick();
			}
		});
		
		iv_right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				monthDateView.onRightClick();
			}
		});
		
		tv_today.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				monthDateView.setTodayToView();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index, menu);
		return true;
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean isDrawOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_websearch).setVisible(!isDrawOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if(mDrawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_websearch:
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			Uri uri = Uri.parse("http://www.baidu.com");
			intent.setData(uri);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//当点击不同的选项时对应不同的Fragement
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// insert into a fragment into the FrameLayout
		Fragment taskFragment = new TaskFragment();
		Bundle args = new Bundle();
		args.putString("text", menuLists.get(position));
		taskFragment.setArguments(args);
		
		FragmentManager fm = getFragmentManager();
		fm.beginTransaction().replace(R.id.content_frame, taskFragment).commit();
		
		mDrawerLayout.closeDrawer(mDrawerList);
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
		
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
