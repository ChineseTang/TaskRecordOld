package com.view;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.controller.AppApplication;
import com.controller.NewtaskController;
import com.model.Newtask;
import com.view.ShowFragment.TaskAdapter;

public class SearchFragment extends Fragment {
	private ArrayList<Newtask> tasks = new ArrayList<Newtask>();
	private Button allbtn;
	private Button notfinishbtn;
	private Button finishbtn;
	private ListView lv;
	private int tag = 0;//用于标记目前是哪个界面，是所有，未完，还是已完，用于在删除时判断跳转到哪个界面，默认是0
	//表示所有界面，1是完成，-1是未完成
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.activity_search, container,false);
		
		 
		tasks = new NewtaskController().searchAllTasks(AppApplication.getUser().getuId());
		
		 lv = (ListView) view.findViewById(R.id.alltask);
		allbtn = (Button) view.findViewById(R.id.searchalltask);
		notfinishbtn = (Button) view.findViewById(R.id.searchnotfinish);
		finishbtn = (Button) view.findViewById(R.id.searchfinish);
		//选择所有的任务
		
		
		TaskAdapter taskadapter = new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks);
		lv.setAdapter(taskadapter);
		//对每一项作一个任务事件处理，点击则可以新建一个AlertBuilder显示一个任务的所有内容。
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final Newtask task = tasks.get(position);
				AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_LIGHT);
				dialog.setTitle("任务内容");
				dialog.setMessage(task.getNcontent());
				dialog.setPositiveButton("完成",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						//更新这个任务的状态，改变其为完成状态
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
					}
				});
				dialog.setNegativeButton("未完", new DialogInterface.OnClickListener() {
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
				});
				//点击删除该条任务
				dialog.setNeutralButton("删除", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						new NewtaskController().deleteTaskById(task.getNtId());
						
						/*String datetime = task.getaTime();
						tasks = new NewtaskController().searchByTime(
								AppApplication.getUser().getuId(),
								datetime);
						TaskAdapter taskadapter = new TaskAdapter(
								AppApplication.getContext(),
								R.layout.task_item, tasks);
						lv.setAdapter(taskadapter);*/
						//根据当前状态
						if(tag == 0) {
							tasks = new NewtaskController().searchAllTasks(AppApplication.getUser().getuId());
						}else if(tag == 1) {
							tasks = new NewtaskController().searchFinishTasks(AppApplication.getUser().getuId());
						}else if(tag == -1) {
							tasks = new NewtaskController().searchNotFinishTasks(AppApplication.getUser().getuId());
						}
						lv.setAdapter(new TaskAdapter(AppApplication.getContext(), R.layout.task_item, tasks));
						//taskadapter.notifyDataSetChanged();
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
			
			//viewHolder.tasknumber.setText(String.valueOf(anewtask.getNtId())+"、");
			viewHolder.tasknumber.setText(String.valueOf(position+1)+"、");
			viewHolder.taskcontent.setText(anewtask.getNcontent());
			if(anewtask.getNfinish() == 1) {
				viewHolder.taskfinish.setText("已完成");
				viewHolder.taskfinish.setTextColor(Color.RED);
			}else{
				viewHolder.taskfinish.setText("未完成");
				viewHolder.taskfinish.setTextColor(Color.BLACK);
			}
			viewHolder.tasktime.setText(anewtask.getaTime());
			
			
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

