package com.model;

public class Newtask {
	private int ntId;//事务id
	private int uId;//用户主键id
	private String ncontent;//具体描述
	private int nfinish;//任务是否完成，0未完成，1完成
	private String aTime;//查询功能按时间排序
	private long ntasktime;//提交任务时间
	public Newtask() {
		
	}
	public int getNtId() {
		return ntId;
	}
	public void setNtId(int ntId) {
		this.ntId = ntId;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public int getNfinish() {
		return nfinish;
	}
	public void setNfinish(int nfinish) {
		this.nfinish = nfinish;
	}
	public String getaTime() {
		return aTime;
	}
	public void setaTime(String aTime) {
		this.aTime = aTime;
	}
	public long getNtasktime() {
		return ntasktime;
	}
	public void setNtasktime(long ntasktime) {
		this.ntasktime = ntasktime;
	}
	public Newtask(int ntId, int uId, String ncontent, int nfinish,
			String aTime, long ntasktime) {
		super();
		this.ntId = ntId;
		this.uId = uId;
		this.ncontent = ncontent;
		this.nfinish = nfinish;
		this.aTime = aTime;
		this.ntasktime = ntasktime;
	}
}
