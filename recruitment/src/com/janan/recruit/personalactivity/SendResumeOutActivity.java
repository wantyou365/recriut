package com.janan.recruit.personalactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gazecloud.dyteam.R;
import com.janan.net.SendResumeOutEngine;
import com.janan.recruit.BaseActivity;
import com.janan.util.Util;
import com.janan.view.mamager.TitleBarManager;

public class SendResumeOutActivity extends BaseActivity{
	private LinearLayout mRootLayout;
	private EditText jobEdit;
	private EditText companyEdit;
	private EditText compantEmail;
	private TitleBarManager mBarUtil;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendresumeoutactivity);
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				
				if(msg.what == CallbackSuccess){
					String textString = getString(R.string.toastwaifasuccess);
					showToast(textString);
					finishSelf();
				}else if(msg.what == CallbackError){
					showToast(msg);
				}
			}
			
		};
		initView();
		showSelf();
	}
	SendResumeOutEngine mEngine;
	public void sendResume(View view){
//		finishSelf();
		
		if(Util.checkEditNull(jobEdit)&&Util.checkEditNull(companyEdit)&&Util.checkEditNull(compantEmail)){
			if(Util.checkEmail(compantEmail.getText().toString())){
				mEngine = new SendResumeOutEngine(this,true);
				mEngine.setComEmail(compantEmail.getText().toString());
				mEngine.setComName(companyEdit.getText().toString());
				mEngine.setJobName(jobEdit.getText().toString());
				mEngine.start();
				showProgress();
			}else{
				showToast(toastWrongEmail);
			}
		}else{
			showToast(toastNullString);
		}
	}

	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(this);

		mRootLayout.addView(
				mBarUtil.createBar(mTitle,
						getResources().getDrawable(R.drawable.homeback), null),
				0);
		mBarUtil.setLeftButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finishSelf();
			}
		});
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		addBar();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout)findViewById(R.id.rootlay_sendresumeoutactivity);
		jobEdit = (EditText)findViewById(R.id.jobnameedit);
		companyEdit = (EditText)findViewById(R.id.comnameedit);
		compantEmail = (EditText)findViewById(R.id.comemailedit);
	}
	
	
}
