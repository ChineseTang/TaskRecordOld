package com.model;

public class Newtask {
	private int ntId;//����id
	private int uId;//�û�����id
	private String ncontent;//��������
	private int nfinish;//�����Ƿ���ɣ�0δ��ɣ�1���
	private String aTime;//��ѯ���ܰ�ʱ������
	private long ntasktime;//�ύ����ʱ��
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
