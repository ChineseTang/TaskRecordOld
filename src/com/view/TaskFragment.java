package com.view;

import java.util.Calendar;

import com.controller.AppApplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class TaskFragment extends Fragment{
	private EditText etEndTime;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.task_fragment, container,false);
		etEndTime = (EditText) view.findViewById(R.id.endtime);
		//展示选取时间选项
		etEndTime.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 if (event.getAction() == MotionEvent.ACTION_DOWN) {  
				      
		             AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  
		             View view = View.inflate(getActivity(), R.layout.date_time_dialog, null);  
		             final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);  
		             final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.time_picker);  
		             builder.setView(view);  
		   
		             Calendar cal = Calendar.getInstance();  
		             cal.setTimeInMillis(System.currentTimeMillis());  
		             datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);  
		   
		             timePicker.setIs24HourView(true);  
		             timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));  
		             timePicker.setCurrentMinute(Calendar.MINUTE);  
		   
		         
		                 int inType = etEndTime.getInputType();  
		                 etEndTime.setInputType(InputType.TYPE_NULL);      
		                 etEndTime.onTouchEvent(event);  
		                 etEndTime.setInputType(inType);  
		                 etEndTime.setSelection(etEndTime.getText().length());  
		   
		                 builder.setTitle("选取事务结束时间");  
		                 builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {  
		   
		                     @Override  
		                     public void onClick(DialogInterface dialog, int which) {  
		   
		                         StringBuffer sb = new StringBuffer();  
		                         sb.append(String.format("%d-%02d-%02d",   
		                                 datePicker.getYear(),   
		                                 datePicker.getMonth() + 1,   
		                                 datePicker.getDayOfMonth()));  
		                         sb.append("  ");  
		                         sb.append(timePicker.getCurrentHour())  
		                         .append(":").append(timePicker.getCurrentMinute());  
		                         etEndTime.setText(sb);  
		                           
		                         dialog.cancel();  
		                     }  
		                 });  
		             
		               
		             Dialog dialog = builder.create();  
		             dialog.show();  
		         }  
		   
				return true;
			}
		});
		return view;
	}

	
}
