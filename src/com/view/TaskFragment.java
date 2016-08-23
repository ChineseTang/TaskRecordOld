package com.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.controller.AppApplication;
import com.controller.NewtaskController;
import com.model.Newtask;
import com.model.TUser;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class TaskFragment extends Fragment {
	private Button submitbutton;
	private EditText content;
	private String scontent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.simpletask_fragment, container,
				false);
		submitbutton = (Button) view.findViewById(R.id.submittask);
		content = (EditText) view.findViewById(R.id.desc);
		submitbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// �ж�����������Ƿ�Ϊ�գ����Ϊ�գ���ô����������ʾ
				scontent = content.getText().toString();
				if ("".equals(scontent) || scontent == null) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							getActivity());
					dialog.setTitle("��������ʧ��");
					dialog.setMessage("�������ݲ���Ϊ��");
					dialog.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {

								}
							});
					dialog.show();
				} else {
					// ��ǰ���û���������ݲ��� �� ���ݿ���
					// ��ע����Ϣ��װ��Newtask������
					DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
					Newtask newtask = new Newtask();
					TUser user = AppApplication.getUser();
					newtask.setuId(user.getuId());
					newtask.setNcontent(scontent);
					newtask.setNfinish(0);// 0��ʾû���������
					newtask.setaTime(format.format(new Date()));
					newtask.setNtasktime(new Date().getTime());

					boolean rs = new NewtaskController().addTask(newtask);

					if (rs) {
						// �������ɹ�����ת����¼����
						AlertDialog.Builder dialog = new AlertDialog.Builder(
								getActivity());
						dialog.setTitle("�ɹ�");
						dialog.setMessage("��������ɹ�");
						dialog.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {

									}
								});
						dialog.show();
						InputMethodManager imm = (InputMethodManager) getActivity()
								.getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(content.getWindowToken(), 0);
						content.setText(null);

					} else {
						AlertDialog.Builder dialog = new AlertDialog.Builder(
								getActivity());
						dialog.setTitle("ʧ��");
						dialog.setMessage("��������ʧ��");
						dialog.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {

									}
								});
						dialog.show();
					}
				}
			}
		});

		/*
		 * etEndTime = (EditText) view.findViewById(R.id.endtime); //չʾѡȡʱ��ѡ��
		 * etEndTime.setOnTouchListener(new OnTouchListener() {
		 * 
		 * @Override public boolean onTouch(View v, MotionEvent event) { if
		 * (event.getAction() == MotionEvent.ACTION_DOWN) {
		 * 
		 * AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 * View view = View.inflate(getActivity(), R.layout.date_time_dialog,
		 * null); final DatePicker datePicker = (DatePicker)
		 * view.findViewById(R.id.date_picker); final TimePicker timePicker =
		 * (android.widget.TimePicker) view.findViewById(R.id.time_picker);
		 * builder.setView(view);
		 * 
		 * Calendar cal = Calendar.getInstance();
		 * cal.setTimeInMillis(System.currentTimeMillis());
		 * datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
		 * cal.get(Calendar.DAY_OF_MONTH), null);
		 * 
		 * timePicker.setIs24HourView(true);
		 * timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
		 * timePicker.setCurrentMinute(Calendar.MINUTE);
		 * 
		 * 
		 * int inType = etEndTime.getInputType();
		 * etEndTime.setInputType(InputType.TYPE_NULL);
		 * etEndTime.onTouchEvent(event); etEndTime.setInputType(inType);
		 * etEndTime.setSelection(etEndTime.getText().length());
		 * 
		 * builder.setTitle("ѡȡ�������ʱ��"); builder.setPositiveButton("ȷ  ��", new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) {
		 * 
		 * StringBuffer sb = new StringBuffer();
		 * sb.append(String.format("%d-%02d-%02d", datePicker.getYear(),
		 * datePicker.getMonth() + 1, datePicker.getDayOfMonth()));
		 * sb.append("  "); sb.append(timePicker.getCurrentHour())
		 * .append(":").append(timePicker.getCurrentMinute());
		 * etEndTime.setText(sb);
		 * 
		 * dialog.cancel(); } });
		 * 
		 * 
		 * Dialog dialog = builder.create(); dialog.show(); }
		 * 
		 * return true; } });
		 */
		return view;
	}

}
