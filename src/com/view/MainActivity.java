package com.view;


import com.controller.AppApplication;
import com.controller.TUserController;
import com.db.TaskRecordOpenHelper;
import com.model.TUser;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
	private TextView register;
	private EditText eusername;
	private EditText epwd;
	private Button loginbtn;
	private String username;
	private String pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置无标题
        setContentView(R.layout.activity_main);
        TaskRecordOpenHelper.setContext(getApplicationContext());
        register = (TextView) findViewById(R.id.register);
        eusername = (EditText) findViewById(R.id.userName);
        epwd = (EditText) findViewById(R.id.pwd);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        
        String regtxt = "没有账号 点击注册";
        SpannableString span = new SpannableString(register.getText().toString());
        span.setSpan(new ClickableSpan() {
			
			@Override
			public void updateDrawState(TextPaint ds) {
				super.updateDrawState(ds);
				ds.setColor(Color.WHITE);
				ds.setUnderlineText(false);
			}

			@Override
			public void onClick(View arg0) {
				Intent regintent = new Intent(MainActivity.this,RegisterActivity.class);
				startActivity(regintent);
			}
		}, 0, regtxt.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        register.setText(span);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        
        loginbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//获取用户登录信息
				username = eusername.getText().toString();
				pwd = epwd.getText().toString();
				if(username == null || username.equals("") || pwd == null || pwd.equals("")) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
					dialog.setTitle("登录失败");
					dialog.setMessage("用户名或密码不能为空");
					dialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							
						}
					});
					dialog.show();
				}else{
					TUser t = new TUser();
					t.setuName(username);
					t.setuPwd(pwd);
					TUser rs = new TUserController().loginUser(t);
					//如果rs 结果不为nul，则表示登录成功
					if(rs != null) {
						//将用户信息保存到全局中
						AppApplication.setUser(rs);
						Intent aintent = new Intent(MainActivity.this,IndexActivity.class);
						startActivity(aintent);
					}else {
						AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
						dialog.setTitle("登录失败");
						dialog.setMessage("用户名或密码错误");
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
