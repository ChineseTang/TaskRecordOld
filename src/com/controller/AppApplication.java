package com.controller;

import android.app.Application;
import android.content.Context;

import com.model.TUser;

public class AppApplication extends Application {
	private static Context context;
	private static TUser user;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		context = getApplicationContext();
	}
	public static Context getContext() {
		return context;
	}

	public static TUser getUser() {
		return user;
	}

	public static void setUser(TUser user) {
		AppApplication.user = user;
	}
	
	
}
