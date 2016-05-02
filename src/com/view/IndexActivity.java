package com.view;

import java.util.ArrayList;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class IndexActivity extends BaseActivity implements OnItemClickListener {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ArrayList<String> menuLists;
	private ArrayAdapter<String> adapter;
	private ActionBarDrawerToggle mDrawerToggle;
	private String mtitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
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
						getActionBar().setTitle("请选择");
						invalidateOptionsMenu();
					}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		//begin the app icon 
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
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
