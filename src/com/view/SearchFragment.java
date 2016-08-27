package com.view;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.controller.AppApplication;
import com.controller.NewtaskController;
import com.model.Newtask;

public class SearchFragment extends Fragment {
	private ArrayList<Newtask> tasks = new ArrayList<Newtask>();
	private Button allbtn;
	private Button notfinishbtn;
	private Button finishbtn;
	private ListView lv;
	private int tag = 0;//���ڱ��Ŀǰ���ĸ����棬�����У�δ�꣬�������꣬������ɾ��ʱ�ж���ת���ĸ����棬Ĭ����0
	//��ʾ���н��棬1����ɣ�-1��δ���
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.activity_search, container,false);
		
		 
		tasks = new NewtaskController().searchAllTasks(AppApplication.getUser().getuId());
		
		 lv = (ListView) view.findViewById(R.id.alltask);
		allbtn = (Button) view.findViewById(R.id.searchalltask);
		notfinishbtn = (Button) view.findViewById(R.id.searchnotfinish);
		finishbtn = (Button) view.findViewById(R.id.searchfinish);
		//ѡ�����е�����
		
		
		TaskAdapter taskadapter = new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks);
		lv.setAdapter(taskadapter);
		//��ÿһ����һ�������¼��������������½�һ��AlertBuilder��ʾһ��������������ݡ�
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final Newtask task = tasks.get(position);
				AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_LIGHT);
				dialog.setTitle("��������");
				dialog.setMessage(task.getNcontent());
				dialog.setPositiveButton("ɾ������",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						//ɾ������
						new NewtaskController().deleteTaskById(task.getNtId());
						
						//���ݵ�ǰ״̬
						if(tag == 0) {
							tasks = new NewtaskController().searchAllTasks(AppApplication.getUser().getuId());
						}else if(tag == 1) {
							tasks = new NewtaskController().searchFinishTasks(AppApplication.getUser().getuId());
						}else if(tag == -1) {
							tasks = new NewtaskController().searchNotFinishTasks(AppApplication.getUser().getuId());
						}
						lv.setAdapter(new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks));
					}
				});
				
				
				
				
				//���������ɣ���ô�����޸�Ϊδ���
				if(task.getNfinish() == 1 ) {
					dialog.setNegativeButton("δ�������",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									new NewtaskController().changeToNotFinish(task
											.getNtId());
									if(tag == 0) {
										tasks = new NewtaskController().searchAllTasks(AppApplication.getUser().getuId());
									}else if(tag == 1) {
										tasks = new NewtaskController().searchFinishTasks(AppApplication.getUser().getuId());
									}else if(tag == -1) {
										tasks = new NewtaskController().searchNotFinishTasks(AppApplication.getUser().getuId());
									}
									lv.setAdapter(new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks));
								/*	taskadapter = new TaskAdapter(
											AppApplication.getContext(),
											R.layout.task_item, tasks);
									tasklist.setAdapter(taskadapter);
									taskadapter.notifyDataSetChanged();*/
								}
							});
					//�������δ��ɣ������޸�Ϊ���
				}else if(task.getNfinish() == 0){
					dialog.setNegativeButton("�������",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									
									new NewtaskController().changeToFinish(task.getNtId());
									//tasks = new NewtaskController().searchFinishTasks(AppApplication.getUser().getuId());
									if(tag == 0) {
										tasks = new NewtaskController().searchAllTasks(AppApplication.getUser().getuId());
									}else if(tag == 1) {
										tasks = new NewtaskController().searchFinishTasks(AppApplication.getUser().getuId());
									}else if(tag == -1) {
										tasks = new NewtaskController().searchNotFinishTasks(AppApplication.getUser().getuId());
									}
									lv.setAdapter(new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks));
									
									
									/*new NewtaskController().changeToFinish(task
											.getNtId());
									String datetime = task.getaTime();
									tasks = new NewtaskController().searchByTime(
											AppApplication.getUser().getuId(),
											datetime);
									lv.setAdapter(new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks));*/
								/*	taskadapter = new TaskAdapter(
											AppApplication.getContext(),
											R.layout.task_item, tasks);
									tasklist.setAdapter(taskadapter);
									taskadapter.notifyDataSetChanged();*/
								}
							});
				}
				
				
				
				
				/*dialog.setNegativeButton("δ�������", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						new NewtaskController().changeToNotFinish(task.getNtId());
						//tasks = new NewtaskController().searchNotFinishTasks(AppApplication.getUser().getuId());
						if(tag == 0) {
							tasks = new NewtaskController().searchAllTasks(AppApplication.getUser().getuId());
						}else if(tag == 1) {
							tasks = new NewtaskController().searchFinishTasks(AppApplication.getUser().getuId());
						}else if(tag == -1) {
							tasks = new NewtaskController().searchNotFinishTasks(AppApplication.getUser().getuId());
						}
						lv.setAdapter(new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks));
					}
				});*/
				//�����ɸ�������
				dialog.setNeutralButton("�޸�����", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
						AlertDialog.Builder newdialog = new AlertDialog.Builder(
								getActivity(),AlertDialog.THEME_HOLO_LIGHT);
						final EditText et = new EditText(getActivity());  
						et.setText(task.getNcontent());
						et.setSelection(et.getText().length());
						et.setMinHeight(300);
						et.setBackground(null);
						et.setGravity(Gravity.TOP | Gravity.LEFT);
						newdialog.setView(et);
						
						newdialog.setTitle("��������");
						//newdialog.setMessage(task.getNcontent());
						newdialog.setPositiveButton("ȡ���޸�����",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface arg0, int arg1) {
										/*new NewtaskController().deleteTaskById(task.getNtId());
										tasks.remove(positionin);
										taskadapter.notifyDataSetChanged();*/
									}
								});
						newdialog.setNegativeButton("�޸�����",new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								//1���½�һ��Task
								Newtask newtask = new Newtask(task.getNtId(),task.getuId(),et.getText().toString(),
										0,task.getaTime(),task.getNtasktime());
								//�޸�����
								boolean rs = new NewtaskController().updtateTask(newtask);
								if(rs) {
									
									if(tag == 0) {
										tasks = new NewtaskController().searchAllTasks(AppApplication.getUser().getuId());
									}else if(tag == 1) {
										tasks = new NewtaskController().searchFinishTasks(AppApplication.getUser().getuId());
									}else if(tag == -1) {
										tasks = new NewtaskController().searchNotFinishTasks(AppApplication.getUser().getuId());
									}
									lv.setAdapter(new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks));
								}
								/*new NewtaskController().deleteTaskById(task.getNtId());
								tasks.remove(positionin);
								taskadapter.notifyDataSetChanged();*/
							}
						});
						newdialog.show();
					}
					
				});
				dialog.show();
			}
		});
		allbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tag = 0;
				tasks = new NewtaskController().searchAllTasks(AppApplication.getUser().getuId());
				lv.setAdapter(new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks));
			}
		});
		notfinishbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tag = -1;
				tasks = new NewtaskController().searchNotFinishTasks(AppApplication.getUser().getuId());
				lv.setAdapter(new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks));
			}
		});
		finishbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tag = 1;
				tasks = new NewtaskController().searchFinishTasks(AppApplication.getUser().getuId());
				lv.setAdapter(new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks));
			}
		});
		return view;
	}
	class TaskAdapter extends ArrayAdapter<Newtask> {
		private int resourceId;
		
		public TaskAdapter(Context context, int resource, List<Newtask> objects) {
			super(context, resource, objects);
			resourceId = resource;
		}
		

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Newtask anewtask = getItem(position);
			View view;
			ViewHolder viewHolder;
			if(convertView == null) {
				view = LayoutInflater.from(getContext()).inflate(resourceId, null);
				viewHolder = new ViewHolder();
				//match
				viewHolder.tasknumber = (TextView) view.findViewById(R.id.tasknumber);
				viewHolder.taskcontent = (TextView) view.findViewById(R.id.taskcontent);
				viewHolder.taskfinish = (TextView) view.findViewById(R.id.taskfinish);
				viewHolder.tasktime = (TextView) view.findViewById(R.id.tasktime);
				view.setTag(viewHolder);
			}else{
				view = convertView;
				viewHolder = (ViewHolder) view.getTag();
			}
			
			//viewHolder.tasknumber.setText(String.valueOf(anewtask.getNtId())+"��");
			viewHolder.tasknumber.setText(String.valueOf(position+1)+"��");
			viewHolder.taskcontent.setText(anewtask.getNcontent());
			if(anewtask.getNfinish() == 1) {
				viewHolder.taskfinish.setText("�����");
				viewHolder.taskfinish.setTextColor(Color.RED);
			}else{
				viewHolder.taskfinish.setText("δ���");
				viewHolder.taskfinish.setTextColor(Color.WHITE);
			}
			viewHolder.tasktime.setText(anewtask.getaTime());
			if(position%2 == 0 ) {
				view.setBackgroundResource(R.drawable.listitembgcolor1);
			}else if(position%2 == 1 ) {
				view.setBackgroundResource(R.drawable.listitembgcolor2);
			}
			
			return view;
		}
		
		class ViewHolder{
			TextView tasknumber;
			TextView taskcontent;
			TextView taskfinish;
			TextView tasktime;
		}
	}
}

