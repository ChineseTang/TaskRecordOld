package com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class TaskRecordOpenHelper extends SQLiteOpenHelper {

	private final static int DATABASE_VERSION = 1;// 数据库版本号
	private final static String DATABASE_NAME = "taskrecord.db";// 数据库名
	private static Context context;// 内容上下文

	private final String tuser = "create table TUser("
			+ "uId INTEGER PRIMARY KEY autoincrement,"
			+ "uName   varchar(64) not null," + "uEmail	 varchar(64) not null,"
			+ "uPwd	 varchar(64) not null," + "uGender  varchar(4) not null,"
			+ "uImage   varchar(128) not null," + "uTime    long  not null)";
	private final String task = "create table Task("
			+ "tId INTEGER PRIMARY KEY autoincrement,"
			+ "uId integer not null," + "topic varchar(256) not null,"
			+ "descripe	text,endtime long," + "notetime long," + "finish int,"
			+ "importance int," + "ttime long," + "note int," + "noteway int,"
			+ "rate int)";
	private final String newtask = "create table Newtask("
			+ "ntId INTEGER PRIMARY KEY autoincrement,"
			+ "uId integer not null," + "ncontent	text," + "nfinish int,"
			+ "nTime varchar(64)," + "ntasktime long)";

	public static void setContext(Context context) {
		TaskRecordOpenHelper.context = context;
	}

	public TaskRecordOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public TaskRecordOpenHelper() {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);// 创建数据库
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 第一次执行时，创建表格
		db.execSQL(tuser);
		db.execSQL(newtask);
	}

	public SQLiteDatabase getConnection() {
		SQLiteDatabase db = getWritableDatabase();
		return db;
	}

	public void close(SQLiteDatabase db) {
		db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
