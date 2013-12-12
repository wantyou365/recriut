package com.janan.recruit.personalactivity;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gazecloud.dyteam.R;
import com.janan.net.ChangePassWordEngine;
import com.janan.recruit.BaseActivity;
import com.janan.view.mamager.TitleBarManager;

public class ChangPassWordActivity extends BaseActivity {
	private LinearLayout mRootLayout;
	private EditText mOldPassEdit;
	private EditText mNewPassEdit;
	private EditText mConfirmPassEdit;
	private String oldPass;
	private String newPass;
	private String confirmPass;
	private TitleBarManager mBarUtil;
	private Button confirmButton;
	private ChangePassWordEngine  mEnging;
	private boolean isPerson;
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(this);
		mRootLayout.addView(mBarUtil.createBar(mTitle, getResources().getDrawable(R.drawable.homeback), null), 0);
		mBarUtil.setLeftButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finishSelf();
			}
		});
	}

	@Override
	public void finishSelf() {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		addBar();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout) findViewById(R.id.rootlay_changpasswordactivity);
		mOldPassEdit = (EditText)findViewById(R.id.oldpass);
		mNewPassEdit = (EditText)findViewById(R.id.newpass);
		mConfirmPassEdit = (EditText)findViewById(R.id.confirmpass);
		confirmButton = (Button) findViewById(R.id.confirmbutton);
		confirmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String errorString = getString(R.string.toastErrorAll);
				if(mOldPassEdit.getText()==null||"".equals(mOldPassEdit.getText().toString())){
					showToast(errorString);
				}else if(mNewPassEdit.getText()==null||"".equals(mNewPassEdit.getText().toString())){
					showToast(errorString);
				}else if(mConfirmPassEdit.getText() == null||"".equals(mConfirmPassEdit.getText().toString())){
					showToast(errorString);
				}else{
					oldPass = mOldPassEdit.getText().toString();
					newPass = mNewPassEdit.getText().toString();
					confirmPass = mConfirmPassEdit.getText().toString();
					if(!newPass.equals(confirmPass)){
						showToast(getString(R.string.toastconfirmpassword));
					}else{
						startChange();
					}
				}
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepasswordactivity);
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		isPerson = getIntent().getBooleanExtra(TAG_PERSON,true);
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if(msg.what == CallbackSuccess){
					showToast(msg);
					finishSelf();
				}else if(msg.what == CallbackError){
					showToast(msg);
				}
			}
			
		};
		initView();
		showSelf();
	}
	
	private void startChange(){
		showProgress();
		if(mEnging == null){
			mEnging = new ChangePassWordEngine(mActivity,isPerson);
		}
		mEnging.setmNewPass(newPass);
		mEnging.setmOldpass(oldPass);
		mEnging.setPerson(isPerson);
		mEnging.start();
	}

}
