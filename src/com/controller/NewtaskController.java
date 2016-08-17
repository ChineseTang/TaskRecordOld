package com.controller;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.db.TaskRecordOpenHelper;
import com.model.Newtask;

public class NewtaskController {
	public boolean addTask(Newtask newtask) {
		TaskRecordOpenHelper to = new TaskRecordOpenHelper();
		SQLiteDatabase db = to.getConnection();
		String sql = "insert into NewTask(uid,ncontent,nfinish,nTime,ntasktime) values('"
				+ newtask.getuId()
				+ "','"
				+ newtask.getNcontent()
				+ "','"
				+ newtask.getNfinish()
				+ "','"
				+ newtask.getaTime()
				+ "','"
				+ newtask.getNtasktime() + "')";
		try {
			db.execSQL(sql);
			db.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//��ѯ���е�����
		public ArrayList<Newtask> searchAllTasks(int uid) {
			
			TaskRecordOpenHelper tdb = new TaskRecordOpenHelper();
			SQLiteDatabase db = tdb.getConnection();
			ArrayList<Newtask> tasks = new ArrayList<Newtask>();
			//��ѯ���
			String sql = "select * from Newtask where uid=" + uid + " order by ntasktime desc";
			Cursor cs = db.rawQuery(sql, null);
			try {
				if (cs.moveToFirst()) {
					do {
						int ntid = cs.getInt(cs.getColumnIndex("ntId"));
						int utid = cs.getInt(cs.getColumnIndex("uId"));
						String ncontent = cs.getString(cs.getColumnIndex("ncontent"));
						int nfinish = cs.getInt(cs.getColumnIndex("nfinish"));
						String nTime = cs.getString(cs.getColumnIndex("nTime"));
						long ntasktime = cs.getLong(cs.getColumnIndex("ntasktime"));
						//����һ������
						Newtask task = new Newtask();
						
						task.setNtId(ntid);
						task.setuId(utid);
						task.setNcontent(ncontent);
						task.setNfinish(nfinish);
						task.setaTime(nTime);
						task.setNtasktime(ntasktime);
						//��ӵ��������
						tasks.add(task);
					} while (cs.moveToNext());
				}
				cs.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//��������
			return tasks;
			
		}
public ArrayList<Newtask> searchFinishTasks(int uid) {
			
			TaskRecordOpenHelper tdb = new TaskRecordOpenHelper();
			SQLiteDatabase db = tdb.getConnection();
			ArrayList<Newtask> tasks = new ArrayList<Newtask>();
			//��ѯ���
			String sql = "select * from Newtask where nfinish=1 and uid=" + uid + " order by ntasktime desc";
			Cursor cs = db.rawQuery(sql, null);
			try {
				if (cs.moveToFirst()) {
					do {
						int ntid = cs.getInt(cs.getColumnIndex("ntId"));
						int utid = cs.getInt(cs.getColumnIndex("uId"));
						String ncontent = cs.getString(cs.getColumnIndex("ncontent"));
						int nfinish = cs.getInt(cs.getColumnIndex("nfinish"));
						String nTime = cs.getString(cs.getColumnIndex("nTime"));
						long ntasktime = cs.getLong(cs.getColumnIndex("ntasktime"));
						//����һ������
						Newtask task = new Newtask();
						
						task.setNtId(ntid);
						task.setuId(utid);
						task.setNcontent(ncontent);
						task.setNfinish(nfinish);
						task.setaTime(nTime);
						task.setNtasktime(ntasktime);
						//��ӵ��������
						tasks.add(task);
					} while (cs.moveToNext());
				}
				cs.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//��������
			return tasks;
			
		}
public ArrayList<Newtask> searchNotFinishTasks(int uid) {
	
	TaskRecordOpenHelper tdb = new TaskRecordOpenHelper();
	SQLiteDatabase db = tdb.getConnection();
	ArrayList<Newtask> tasks = new ArrayList<Newtask>();
	//��ѯ���
	String sql = "select * from Newtask where nfinish=0 and uid=" + uid + " order by ntasktime desc";
	Cursor cs = db.rawQuery(sql, null);
	try {
		if (cs.moveToFirst()) {
			do {
				int ntid = cs.getInt(cs.getColumnIndex("ntId"));
				int utid = cs.getInt(cs.getColumnIndex("uId"));
				String ncontent = cs.getString(cs.getColumnIndex("ncontent"));
				int nfinish = cs.getInt(cs.getColumnIndex("nfinish"));
				String nTime = cs.getString(cs.getColumnIndex("nTime"));
				long ntasktime = cs.getLong(cs.getColumnIndex("ntasktime"));
				//����һ������
				Newtask task = new Newtask();
				
				task.setNtId(ntid);
				task.setuId(utid);
				task.setNcontent(ncontent);
				task.setNfinish(nfinish);
				task.setaTime(nTime);
				task.setNtasktime(ntasktime);
				//��ӵ��������
				tasks.add(task);
			} while (cs.moveToNext());
		}
		cs.close();
		db.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	//��������
	return tasks;
	
}
//���������״̬����δ��ɵ��������Ϊ�Ѿ���ɵ�����
public boolean changeToFinish(int ntid) {
	TaskRecordOpenHelper to = new TaskRecordOpenHelper();
	SQLiteDatabase db = to.getConnection();
	String sql = "update Newtask set nfinish=1 where ntid=" + ntid;
	db.execSQL(sql);
	return true;
}
//���������״̬����δ��ɵ��������Ϊ�Ѿ���ɵ�����
public boolean changeToNotFinish(int ntid) {
	TaskRecordOpenHelper to = new TaskRecordOpenHelper();
	SQLiteDatabase db = to.getConnection();
	String sql = "update Newtask set nfinish=0 where ntid=" + ntid;
	db.execSQL(sql);
	return true;
}
}
