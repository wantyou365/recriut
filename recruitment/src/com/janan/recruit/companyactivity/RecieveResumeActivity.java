package com.janan.recruit.companyactivity;

import java.util.ArrayList;

import java.util.Calendar;

import android.R.integer;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

import com.gazecloud.dyteam.R;
import com.janan.adapter.RecieveResumesAdapter;
import com.janan.data.bean.personal.Resume;
import com.janan.net.RecieveResumesEngine;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.personalactivity.ResumeChangeActivity;
import com.janan.recruit.personalactivity.ResumePreviewActivity;

public class RecieveResumeActivity extends BaseActivity implements
		DatePickerDialog.OnDateSetListener,OnTimeSetListener {

	private ListView mListView;
	private ArrayList<Resume> mList;
	private ArrayList<Resume> mCheckedList;
	private RecieveResumesAdapter mAdapter;
	private boolean canCheck;
	private Button sendButton;
	private int mYear = 0;
	private int mMonth = 0;
	private int mDay = 0;
	private int mHour = 0;
	private int mMin = 0;
	boolean isSended = false;
	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		super.showSelf();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout) findViewById(R.id.rootlay_recieveresumeactivity);
		mListView = (ListView) findViewById(R.id.resumeslist);
		sendButton = (Button) findViewById(R.id.button);
		
	}

	int sendCount = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recieveresumeslistactivity);
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		canCheck = getIntent().getBooleanExtra("cancheck", false);
		initView();
		showSelf();
		mEngine = new RecieveResumesEngine(mActivity, false);
		mEngine.setSend(false);
		startRequest();
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				
				if (msg.what == CallbackError) {
					closeProgress();
					showToast(msg);
					isSended = false;
				} else if (msg.what == CallbackSuccess) {
					closeProgress();
					if (mAdapter == null) {
						mAdapter = new RecieveResumesAdapter(mActivity,
								canCheck);
					}
					if (canCheck) {
						sendButton.setVisibility(View.VISIBLE);
					}
					ArrayList<Resume> _List = mEngine.getmList();
					mList = mAdapter.getmList();
					for (int i = 0; i < _List.size(); i++) {
						mList.add(_List.get(i));
					}
					mListView.setAdapter(mAdapter);
//					
					mAdapter.notifyDataSetChanged();
				}else if(msg.what == CallbackSendSuccess){
					sendCount++;
					if(sendCount<mCheckedList.size()){
						Resume _Resume = mCheckedList.get(sendCount);
						mEngine.setJobIdString(_Resume.getmJobid());
						mEngine.setPersonIdString(_Resume.getmUserId());
						mEngine.setDateString(mYear+"-"+mMonth+"-"+mDay+" "+mHour+":"+mMin);
						mEngine.setSend(true);
						startRequest();
					}else{
						closeProgress();
						String textString = getString(R.string.notifysuccess);
						showToast(textString);
						sendCount = 0;
						isSended = false;
					}
					
				}else if(msg.what == 9999){
//					for(int i=0;i<mCheckedList.size();i++){
						
							Resume _Resume = mCheckedList.get(sendCount);
							mEngine.setJobIdString(_Resume.getmJobid());
							mEngine.setPersonIdString(_Resume.getmUserId());
							mEngine.setDateString(mYear+"-"+mMonth+"-"+mDay+" "+mHour+":"+mMin);
							mEngine.setSend(true);
							startRequest();
						
//					}
				}
			}

		};
	}

	RecieveResumesEngine mEngine;

	@Override
	public void startRequest() {
		// TODO Auto-generated method stub
		showProgress();
		if(mEngine == null){
			mEngine = new RecieveResumesEngine(mActivity, false);
		}
		
		mEngine.start();
	}

	public void sendNotify(View view) {
		mCheckedList = new ArrayList<Resume>();
		for (int i = 0; i < mList.size(); i++) {
			if (mList.get(i).isSelected()) {
				mCheckedList.add(mList.get(i));
			}
		}
		if (mCheckedList.size() == 0) {
			showToast(getString(R.string.toastChangeNotiPerson));
		} else {
			Calendar _Calendar = Calendar.getInstance();
			mYear = _Calendar.get(Calendar.YEAR);
			mMonth = _Calendar.get(Calendar.MONTH);
			mDay = _Calendar.get(Calendar.DAY_OF_MONTH);
			DatePickerDialog dlg = new DatePickerDialog(
					RecieveResumeActivity.this, RecieveResumeActivity.this,
					mYear, mMonth, mDay);
			dlg.setTitle(getString(R.string.timedialogtitle));
			dlg.show();
		}

	}
	TimePickerDialog tlg;
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		mYear = year;
		mMonth = monthOfYear;
		mDay = dayOfMonth;
		Calendar _Calendar = Calendar.getInstance();
		mHour = _Calendar.get(Calendar.HOUR_OF_DAY);
		mMin = _Calendar.get(Calendar.MINUTE);
		if(tlg == null){
			tlg	 = new TimePickerDialog(RecieveResumeActivity.this, RecieveResumeActivity.this,mHour,mMin,true);
		}
		if(!tlg.isShowing()){
			tlg.setTitle(getString(R.string.timedialogtitle1));
			tlg.show();
			
		}
		
	}
	
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		
		mHour = hourOfDay;
		mMin = minute;
		if(!isSended){
			Message msg = mHandler.obtainMessage();
			msg.what = 9999;
			mHandler.sendMessageDelayed(msg, 200);
			isSended = true;
		}
		
		
	}

	
}
