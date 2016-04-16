package com.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.controller.TUserController;
import com.model.TUser;

public class RegisterActivity extends BaseActivity {
	
	private ImageButton iback;
	private ImageView iphoto;
	private EditText eusername;
	private EditText email;
	private EditText epwd;
	private EditText erepwd;
	private RadioGroup rg;
	private RadioButton rmale;
	private RadioButton rfemale;
	private Button regbtn;
	
	private String susername;
	private String semail;
	private String spwd;
	private String srpwd;
	private String sgender = "��";//�Ա�
	
	private Uri imageUri;
	public static final int TAKE_PHOTO = 1;
	public static final int CROP_PHOTO = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//�����ޱ���
		setContentView(R.layout.activity_register);
		//get values from view
		iback = (ImageButton) findViewById(R.id.array);
		iphoto = (ImageView) findViewById(R.id.photo);
		eusername = (EditText) findViewById(R.id.userName);
		email = (EditText) findViewById(R.id.emailaddr);
		epwd = (EditText) findViewById(R.id.pwd);
		erepwd = (EditText) findViewById(R.id.repwd);
		rg = (RadioGroup) findViewById(R.id.gender);
		rmale = (RadioButton) findViewById(R.id.male);
		rfemale = (RadioButton) findViewById(R.id.female);
		regbtn = (Button) findViewById(R.id.registerbtn);
		//����Ա�
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkId) {
				if(checkId == rmale.getId()) {
					sgender = rmale.getText().toString();
				}else if(checkId == rfemale.getId()) {
					sgender = rfemale.getText().toString();
				}
			}
		});
		//Ϊiback��ťע������¼�
		iback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		//Ϊiphoto ��ťע������¼��������������Ȼ����һ����Ƭ
		iphoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//����File�������ڴ洢���պ����Ƭ
				File outputImage = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
				try {
					if(outputImage.exists()) {
						outputImage.delete();
					}
					outputImage.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				imageUri = Uri.fromFile(outputImage);
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
				startActivityForResult(intent,TAKE_PHOTO);//�����������
				
			}
		});
		//ע�ᰴť���������¼�
		regbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//1 ���ע����Ϣ�е�ֵ
				susername = eusername.getText().toString();
				semail = email.getText().toString();
				spwd = epwd.getText().toString();
				srpwd = erepwd.getText().toString();
				//2 ��֤ע����Ϣ��ֵ�Ƿ����
				if(susername == null || susername.equals("")|| semail == null || semail.equals("") || spwd == null || spwd.equals("")|| srpwd == null || srpwd.equals("")) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
					dialog.setTitle("ע��ʧ��");
					dialog.setMessage("�û���Ϣ����Ϊ��");
					dialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
						}
					});
					dialog.show();
				}else if(!spwd.equals(srpwd)) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
					dialog.setTitle("ע��ʧ��");
					dialog.setMessage("��������Ӧ����ͬ");
					dialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
						}
					});
					dialog.show();
				}else{
					//��ע����Ϣ��װ��TUser������
					DateFormat format= new SimpleDateFormat("yyyy.MM.dd");  
					TUser user = new TUser();
					user.setuName(susername);
					user.setuEmail(semail);
					user.setuPwd(spwd);
					user.setuImage(Environment.getExternalStorageDirectory() + "output_image.jpg");//�洢�������SD��Ŀ¼�µ�λ��
					user.setuGender(sgender);
					user.setuTime(format.format(new Date()));
					//��Ϣû�������ˣ���ô�͵���Controller��������ݰ�
					boolean result = new TUserController().registerUser(user);
					
					 if(result) {
					    	//�������ɹ�����ת����¼����
					    	AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
							dialog.setTitle("ע��ɹ�");
							dialog.setMessage("��������¼ҳ��");
							dialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
							    	startActivity(intent);
							    	finish();
								}
							});
							dialog.show();
							
					    	
					    }else {
					    	AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
							dialog.setTitle("ע��ʧ��");
							dialog.setMessage("ע����Ϣ�д���");
							dialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									
								}
							});
							dialog.show();
					    }
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAKE_PHOTO:
			if(resultCode == RESULT_OK) {
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(imageUri, "image/*");
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
				startActivityForResult(intent, CROP_PHOTO);//�����ü�����
			}
			break;
		case CROP_PHOTO:
			if(resultCode == RESULT_OK) {
				try {
					Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
					iphoto.setImageBitmap(bitmap);//���ü������Ƭ��ʾ����
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			}
			break;
		default:
			break;
		}
	}
	
	

}
