package com.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TaskFragment extends Fragment {
	private TextView textView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.task_fragment, container,false);
		textView = (TextView) view.findViewById(R.id.taskview);
		
		String text = getArguments().getString("text");
		textView.setText(text);
		return view;
	}
}
