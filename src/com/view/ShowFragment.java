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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.controller.AppApplication;
import com.controller.NewtaskController;
import com.model.Newtask;
import com.myview.MonthDateView;
import com.myview.MonthDateView.DateClick;

public class ShowFragment extends Fragment {
	private ImageView iv_left;
	private ImageView iv_right;
	private TextView tv_date;
	private TextView tv_week;
	private TextView tv_today;
	private MonthDateView monthDateView;
	private ListView tasklist;
	private TaskAdapter taskadapter;
	private ArrayList<Newtask> tasks = new ArrayList<Newtask>();
	private ArrayList<Newtask> drawtasks = new ArrayList<Newtask>();
	private List<Integer> list = new ArrayList<Integer>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_show, container, false);
		
		// 初始化控件
		init(view);
		monthDateView.setTextView(tv_date, tv_week);
		monthDateView.setDateClick(new DateClick() {
			@Override
			public void onClickOnDate() {
				// 显示当天的任务
				/*
				 * Toast.makeText(AppApplication.getContext(), "点击了：" +
				 * monthDateView.getmSelYear() +
				 * (monthDateView.getmSelMonth()+1) +
				 * monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
				 */
				int omonth = monthDateView.getmSelMonth() + 1;
				int oday = monthDateView.getmSelDay();
				String smonth;
				String sday;
				if (omonth >= 0 && omonth <= 9) {
					smonth = '0' + String.valueOf(omonth);
				} else {
					smonth = String.valueOf(omonth);
				}
				if (oday >= 0 && oday <= 9) {
					sday = '0' + String.valueOf(oday);
				} else {
					sday = String.valueOf(oday);
				}
				String gettime = monthDateView.getmSelYear() + "." + smonth
						+ "." + sday;
				// 然后根据日期去数据库查找对应的当日任务
				tasks = new NewtaskController().searchByTime(AppApplication
						.getUser().getuId(), gettime);
				taskadapter = new TaskAdapter(AppApplication
						.getContext(), R.layout.task_item, tasks);
				tasklist.setAdapter(taskadapter);
			}
		});

		tasklist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final Newtask task = tasks.get(position);
				final int positionin = position;
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						getActivity(),AlertDialog.THEME_HOLO_LIGHT);
				dialog.setTitle("任务内容");
				dialog.setMessage(task.getNcontent());
				dialog.setPositiveButton("完成",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// 更新这个任务的状态，改变其为完成状态
								new NewtaskController().changeToFinish(task
										.getNtId());
								String datetime = task.getaTime();
								tasks = new NewtaskController().searchByTime(
										AppApplication.getUser().getuId(),
										datetime);
								taskadapter = new TaskAdapter(
										AppApplication.getContext(),
										R.layout.task_item, tasks);
								tasklist.setAdapter(taskadapter);
								taskadapter.notifyDataSetChanged();
							}
						});
				
				dialog.setNegativeButton("未完",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								new NewtaskController().changeToNotFinish(task
										.getNtId());
								String datetime = task.getaTime();
								tasks = new NewtaskController().searchByTime(
										AppApplication.getUser().getuId(),
										datetime);
								taskadapter = new TaskAdapter(
										AppApplication.getContext(),
										R.layout.task_item, tasks);
								tasklist.setAdapter(taskadapter);
								taskadapter.notifyDataSetChanged();
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
						taskadapter = new TaskAdapter(
								AppApplication.getContext(),
								R.layout.task_item, tasks);
						tasklist.setAdapter(taskadapter);*/
						tasks.remove(positionin);
						taskadapter.notifyDataSetChanged();
					}
					
				});
				dialog.show();
			}
		});
		//2016.01.05
		String datetime = monthDateView.getTodayToView();
		//String newdatetime = datetime.substring(0, datetime.length()-2);
		tasks = new NewtaskController().searchByTime(AppApplication.getUser()
				.getuId(), datetime);
		taskadapter = new TaskAdapter(AppApplication.getContext(),
				R.layout.task_item, tasks);
		tasklist.setAdapter(taskadapter);
		drawMonthColors(datetime);
		/*drawtasks = new NewtaskController().searchDrawByTime(AppApplication.getUser()
				.getuId(), newdatetime);
		//为任务当天画上小圆点
		if (drawtasks != null) {
			list = getDrawColors(drawtasks);
			monthDateView.setDaysHasThingList(list);
		}*/
		setOnlistener();
		return view;
	}

	/**
	 * 初始化空间
	 */
	private void init(View view) {
		iv_left = (ImageView) view.findViewById(R.id.iv_left);
		iv_right = (ImageView) view.findViewById(R.id.iv_right);
		monthDateView = (MonthDateView) view.findViewById(R.id.monthDateView);
		tv_date = (TextView) view.findViewById(R.id.date_text);
		tv_week = (TextView) view.findViewById(R.id.week_text);
		tv_today = (TextView) view.findViewById(R.id.tv_today);
		tasklist = (ListView) view.findViewById(R.id.tasklist);
	}

	/**
	 * 根据tasks画出任务的那天，有红色的小圆点
	 * 
	 * @param tasks
	 * @return
	 */
	private ArrayList<Integer> getDrawColors(ArrayList<Newtask> tasks) {
		ArrayList<Integer> drawlists = new ArrayList<Integer>();
		int day;
		String sday;
		for (Newtask newtask : tasks) {
			sday = newtask.getaTime();
			sday = sday.substring(sday.length() - 2, sday.length());
			day = Integer.valueOf(sday);
			drawlists.add(Integer.valueOf(day));
		}
		return drawlists;
	}

	private void setOnlistener() {
		iv_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				monthDateView.onLeftClick();
				String gettime = monthDateView.onLeftGetTime();
				drawMonthColors(gettime);
				/*
				 * Toast.makeText(AppApplication.getContext(), "点击了：" + gettime,
				 * Toast.LENGTH_SHORT).show();
				 */
				tasks = new NewtaskController().searchByTime(AppApplication
						.getUser().getuId(), gettime);
				taskadapter = new TaskAdapter(AppApplication
						.getContext(), R.layout.task_item, tasks);
				tasklist.setAdapter(taskadapter);
			}
		});

		iv_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				monthDateView.onRightClick();
				String gettime = monthDateView.onRightGetTime();
				drawMonthColors(gettime);
				/*
				 * Toast.makeText(AppApplication.getContext(), "点击了：" + gettime,
				 * Toast.LENGTH_SHORT).show();
				 */
				tasks = new NewtaskController().searchByTime(AppApplication
						.getUser().getuId(), gettime);
				taskadapter = new TaskAdapter(AppApplication
						.getContext(), R.layout.task_item, tasks);
				tasklist.setAdapter(taskadapter);
			}
		});

		tv_today.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				monthDateView.setTodayToView();
				String datetime = monthDateView.getTodayToView();
				drawMonthColors(datetime);
				tasks = new NewtaskController().searchByTime(AppApplication
						.getUser().getuId(), datetime);
				taskadapter = new TaskAdapter(AppApplication
						.getContext(), R.layout.task_item, tasks);
				tasklist.setAdapter(taskadapter);

			}
		});
	}
	private void drawMonthColors(String gettime) {
		String newgettime = gettime.substring(0, gettime.length()-2);
		drawtasks = new NewtaskController().searchDrawByTime(AppApplication.getUser()
				.getuId(), newgettime);
		//为任务当月画上小圆点
		if (drawtasks != null) {
			list = getDrawColors(drawtasks);
			monthDateView.setDaysHasThingList(list);
		}
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
			if (convertView == null) {
				view = LayoutInflater.from(getContext()).inflate(resourceId,
						null);
				viewHolder = new ViewHolder();
				// match
				viewHolder.tasknumber = (TextView) view
						.findViewById(R.id.tasknumber);
				viewHolder.taskcontent = (TextView) view
						.findViewById(R.id.taskcontent);
				viewHolder.taskfinish = (TextView) view
						.findViewById(R.id.taskfinish);
				viewHolder.tasktime = (TextView) view
						.findViewById(R.id.tasktime);
				view.setTag(viewHolder);
			} else {
				view = convertView;
				viewHolder = (ViewHolder) view.getTag();
			}

			// viewHolder.tasknumber.setText(String.valueOf(anewtask.getNtId())+"、");
			viewHolder.tasknumber.setText(String.valueOf(position + 1) + "、");
			viewHolder.taskcontent.setText(anewtask.getNcontent());
			if (anewtask.getNfinish() == 1) {
				viewHolder.taskfinish.setText("已完成");
				viewHolder.taskfinish.setTextColor(Color.RED);
			} else {
				viewHolder.taskfinish.setText("未完成");
				viewHolder.taskfinish.setTextColor(Color.BLACK);
			}
			viewHolder.tasktime.setText(anewtask.getaTime());

			return view;
		}

		class ViewHolder {
			TextView tasknumber;
			TextView taskcontent;
			TextView taskfinish;
			TextView tasktime;
		}
	}
}
