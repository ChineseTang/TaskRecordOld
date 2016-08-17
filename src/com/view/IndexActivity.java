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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class IndexActivity extends BaseActivity {
	/*private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ArrayList<String> menuLists;
	private ArrayAdapter<String> adapter;
	private ActionBarDrawerToggle mDrawerToggle;
	private String mtitle;*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏上面的东西
		
		
		
		
		
		
		//getActionBar().setDisplayShowHomeEnabled(false); //取消actionbar左侧的快捷图标和文字
		//getActionBar().setDisplayShowTitleEnabled(false); 
		/*mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		mDrawerList = (ListView) findViewById(R.id.left_drawers);
		menuLists = new ArrayList<String>();
		mtitle = (String) getTitle();
		menuLists.add("首页");
		menuLists.add("设置");
		menuLists.add("记事本");
		menuLists.add("任务");
		menuLists.add("新任务");
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
		getActionBar().setHomeButtonEnabled(true);*/
		
		

	}

/*	@Override
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
		
		Bundle args = new Bundle();
		args.putString("text", menuLists.get(position));
		
		FragmentManager fm = getFragmentManager();
		
		switch (position) {
		case 0:
			Fragment taskFragment = new TaskFragment();
			taskFragment.setArguments(args);
			fm.beginTransaction().replace(R.id.content_frame, taskFragment).addToBackStack(null).commit();
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			Fragment showFragment = new ShowFragment();
			showFragment.setArguments(args);
			fm.beginTransaction().replace(R.id.content_frame, showFragment).addToBackStack(null).commit();
			break;
		case 4:
			Fragment taskfragment = new TaskFragment();
			taskfragment.setArguments(args);
			fm.beginTransaction().replace(R.id.content_frame, taskfragment).addToBackStack(null).commit();
			break;
		case 5:
			
			break;
		default:
			break;
		}
		
		
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
	}*/

}
