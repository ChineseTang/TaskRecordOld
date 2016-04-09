package com.view;

import android.app.Activity;
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
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        register = (TextView) findViewById(R.id.register);
        String regtxt = "Ã»ÓÐÕËºÅ µã»÷×¢²á";
        SpannableString span = new SpannableString(register.getText().toString());
        span.setSpan(new ClickableSpan() {
			
			@Override
			public void updateDrawState(TextPaint ds) {
				// TODO Auto-generated method stub
				super.updateDrawState(ds);
				ds.setColor(Color.WHITE);
				ds.setUnderlineText(false);
			}

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent regintent = new Intent(MainActivity.this,RegisterActivity.class);
				startActivity(regintent);
			}
		}, 0, regtxt.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        register.setText(span);
        register.setMovementMethod(LinkMovementMethod.getInstance());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
