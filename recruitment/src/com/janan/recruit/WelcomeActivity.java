package com.janan.recruit;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.widget.TextView;

import com.gazecloud.dyteam.R;

import com.janan.util.LocationUtil;

public class WelcomeActivity extends BaseActivity {
	public static final int DB_OK = 1;
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LocationUtil.setAddress(mActivity);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == DB_OK) {
					Intent _Intent = new Intent();
					_Intent.setClass(mActivity, MainTabActivity.class);
					startActivity(_Intent);
					finishSelf();
				} else {
					// showToast("请打开位置信息");
				}

			}
		};
		TextView _TextView = new TextView(this);
		_TextView.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.welcome));
		setContentView(_TextView);
		mActivity = this;
		MyThread myThread = new MyThread();
		myThread.start();

	}

	class MyThread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Message _Message = mHandler.obtainMessage();
				_Message.what = DB_OK;
				mHandler.sendMessageDelayed(_Message, 2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.run();
		}

	}

}
