package com.model;

public class TUser {
	private int uId;//�û�id
	private String uName;//�û���
	private String uEmail;//����
	private String uPwd;//����
	private String uGender;//�Ա�
	private String uImage;//ͼƬ �� ���û����� ��ΪĬ��ͼƬ
	private String uTime;//ע��ʱ��
	
	public TUser() {
		
	}
	//���캯��
	public TUser(String uName, String uEmail, String uPwd, String uGender,
			String uImage, String uTime) {
		super();
		this.uName = uName;
		this.uEmail = uEmail;
		this.uPwd = uPwd;
		this.uGender = uGender;
		this.uImage = uImage;
		this.uTime = uTime;
	}
	
	
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	public String getuGender() {
		return uGender;
	}
	public void setuGender(String uGender) {
		this.uGender = uGender;
	}
	public String getuImage() {
		return uImage;
	}
	public void setuImage(String uImage) {
		this.uImage = uImage;
	}
	public String getuTime() {
		return uTime;
	}
	public void setuTime(String uTime) {
		this.uTime = uTime;
	}
	
	
}
