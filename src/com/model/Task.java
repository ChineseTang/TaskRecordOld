package com.model;

public class Task {
	private int tId;//����id
	private int uId;//�û�����id
	private String topic;//��������
	private String describe;//��������
	private long endtime;//�������ʱ��
	private long notetime;//����֪ͨʱ�䣬Ĭ��1��Сʱǰ
	private int finish;//�����Ƿ���ɣ�0δ��ɣ�1���
	private int importance;//�������Ҫ�ԣ�0 ��ɫ  ����Ҫ �� 1 ��ɫ ��Ҫ ��2 ��ɫ һ����Ҫ
	private int note;//ѡ���Ƿ���Ҫ֪ͨ��Ĭ����Ҫ
	private int noteway;//֪ͨ��ʽ��Ĭ��֪ͨ
	private int rate;//�����Ƶ�ʣ�Ĭ��Ϊһ�������� 0 �� 1 Ϊÿ������2 Ϊÿ������3 Ϊÿ������
	private long ttime;//�ύ����ʱ��
	
	//���캯��
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
