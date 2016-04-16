package com.model;

public class Task {
	private int tId;//事务id
	private int uId;//用户主键id
	private String topic;//事务主题
	private String describe;//具体描述
	private long endtime;//任务结束时间
	private long notetime;//任务通知时间，默认1个小时前
	private int finish;//任务是否完成，0未完成，1完成
	private int importance;//任务的重要性，0 红色  很重要 ； 1 蓝色 重要 ；2 白色 一般重要
	private int note;//选择是否需要通知，默认需要
	private int noteway;//通知方式，默认通知
	private int rate;//任务的频率，默认为一次性任务 0 ， 1 为每日任务，2 为每周任务，3 为每月任务
	private long ttime;//提交任务时间
	
	//构造函数
	public Task(int uId, String topic, String describe, long endtime,
			long notetime, int finish, int importance, int note, int noteway,
			int rate, long ttime) {
		super();
		this.uId = uId;
		this.topic = topic;
		this.describe = describe;
		this.endtime = endtime;
		this.notetime = notetime;
		this.finish = finish;
		this.importance = importance;
		this.note = note;
		this.noteway = noteway;
		this.rate = rate;
		this.ttime = ttime;
	}
	
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	public long getNotetime() {
		return notetime;
	}
	public void setNotetime(long notetime) {
		this.notetime = notetime;
	}
	public int getFinish() {
		return finish;
	}
	public void setFinish(int finish) {
		this.finish = finish;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	public int getNoteway() {
		return noteway;
	}
	public void setNoteway(int noteway) {
		this.noteway = noteway;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public long getTtime() {
		return ttime;
	}
	public void setTtime(long ttime) {
		this.ttime = ttime;
	}
	
	
	
}
