package com.myview;

import com.controller.AppApplication;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class FloatView extends ImageView {
	private float mTouchStartX;
	private float mTouchStartY;
	private float x;
	private float y;
	private float mStartX;
	private float mStartY;
	private Context context;
	private boolean ismove;// ��ָ�Ƿ��ƶ��ı��λ
	private WindowManager windowManager = (WindowManager) getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
	// ��windowManagerParams����Ϊ��ȡ��ȫ�ֱ��������Ա����������ڵ�����
	private WindowManager.LayoutParams windowManagerParams = ((AppApplication) getContext().getApplicationContext()).getWindowParams();

	public FloatView(Context context) {
		super(context);
		this.context = context;
		ismove = false;
		this.setOnTouchListener(ontl);
	}

	OnTouchListener ontl = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			// ��ȡ��״̬���ĸ߶�
			x = event.getRawX();
			y = event.getRawY(); // 25��ϵͳ״̬���ĸ߶�
			// Log.i("currP", "currX"+x+"====currY"+y);
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// ��ȡ���View�����꣬���Դ�View���Ͻ�Ϊԭ��
				ismove = false;
				mTouchStartX = event.getX();
				mTouchStartY = event.getY();

				// Log.i("startP",
				// "startX"+mTouchStartX+"====startY"+mTouchStartY);

				break;
			case MotionEvent.ACTION_MOVE:
				ismove = true;
				updateViewPosition();
				break;

			case MotionEvent.ACTION_UP:
				updateViewPosition();
				mTouchStartX = mTouchStartY = 0;
				if ((x - mStartX) < 5 && (y - mStartY) < 5) {
					
				}
				break;
			}
			return ismove;
		}
	};

	private void updateViewPosition() {
		// ���¸�������λ�ò���
		windowManagerParams.x = (int) (x - mTouchStartX);
		windowManagerParams.y = (int) (y - mTouchStartY);
		windowManager.updateViewLayout(this, windowManagerParams); // ˢ����ʾ
	}
}
