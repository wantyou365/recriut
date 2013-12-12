package com.janan.recruit.personalactivity;

import com.gazecloud.dyteam.R;
import com.janan.net.RegisterEngine;

import com.janan.recruit.BaseActivity;
import com.janan.util.DBHelper;
import com.janan.util.Util;
import com.janan.view.mamager.TitleBarManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class RegisterActivity extends BaseActivity {

	private TitleBarManager mBarUtil;
	private LinearLayout mRootLayout;
	private EditText mUserNameEditText;
	private EditText mPassWordEditText;
	private EditText mConfirmPassEditText;
	private EditText mEmailEditText;
	private EditText mQQEditText;
	private String mUserName;
	private String mpassWord;
	private String mConfirmPass;
	private String mEmail;
	private String mQQ;
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
		mRootLayout = (LinearLayout) findViewById(R.id.rootlay_registeractivity);
		mUserNameEditText = (EditText) findViewById(R.id.register_username);
		mPassWordEditText = (EditText) findViewById(R.id.register_password);
		mConfirmPassEditText = (EditText) findViewById(R.id.confirm_password);

		mEmailEditText = (EditText) findViewById(R.id.register_email);
		mQQEditText = (EditText)findViewById(R.id.register_qq);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mTitle = "ÕÊºÅ×¢²á";
		setContentView(R.layout.registeractivity);
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(msg.what == CallbackSuccess){
					closeProgress();
					
//					showToast(toastRegisterSuccess);
					Intent _Intent = new Intent();
					_Intent.putExtra("username", mEmail);
					_Intent.putExtra("password", mpassWord);
//					_Intent.setClass(mActivity, ResumeChangeActivity.class);
//					_Intent.putExtra(mActivity.STRING_TITLE, "´´½¨¼òÀú");
//					_Intent.putExtra("isregister", true);
//					mActivity.startActivity(_Intent);
					setResult(RESULT_OK,_Intent);
					finishSelf();
				}else if(msg.what ==CallbackError){
					
					closeProgress();
					showToast(msg);
				}
			}
		};
		initView();
		showSelf();
	}
	public void registerOnclick(View view) {
		
		if(Util.checkEditNull(mUserNameEditText)&&Util.checkEditNull(mPassWordEditText)&&Util.checkEditNull(mConfirmPassEditText)
				&&Util.checkEditNull(mEmailEditText)&&Util.checkEditNull(mQQEditText)){
			mUserName = mUserNameEditText.getText().toString().trim();
			mpassWord = mPassWordEditText.getText().toString().trim();
			mConfirmPass = mConfirmPassEditText.getText().toString().trim();
			mEmail = mEmailEditText.getText().toString().trim();
			mQQ = mQQEditText.getText().toString().trim();
			if(!mpassWord.equals(mConfirmPass)){
				showToast(toastPassErrorString);
			}else if(!Util.checkEmail(mEmail)){
				showToast(toastWrongEmail);
			}			
			else{
				startRegister();
			}
		}else{
			showToast(toastNullString);
		}
		
//		if(mUserNameEditText.getText() == null ||"".equals(mUserNameEditText.getText().toString())){
//			showToast(toastNullString);
//		}else if(mPassWordEditText.getText() == null ||"".equals(mPassWordEditText.getText().toString())){
//			showToast(toastNullString);
//		}else if(mConfirmPassEditText.getText() == null ||"".equals(mConfirmPassEditText.getText().toString())){
//			showToast(toastNullString);
//		}else if(mEmailEditText.getText() == null ||"".equals(mEmailEditText.getText().toString())){
//			showToast(toastNullString);
//		}else if(!Util.checkEditNull(mQQEditText)){
//			showToast(toastNullString);
//		}
//		else{
//			mUserName = mUserNameEditText.getText().toString();
//			mpassWord = mPassWordEditText.getText().toString();
//			mConfirmPass = mConfirmPassEditText.getText().toString();
//			mEmail = mEmailEditText.getText().toString();
//			if(!mpassWord.equals(mConfirmPass)){
//				showToast(toastPassErrorString);
//			}else if(!Util.checkEmail(mEmail)){
//				showToast(toastWrongEmail);
//			}			
//			else{
//				startRegister();
//			}
//		}
	}
	RegisterEngine mEngine;
	private void startRegister(){
		showProgress();
		mEngine = new RegisterEngine(mActivity,true);
		mEngine.setmUserName(mUserName);
		mEngine.setmPassWord(mpassWord);
		mEngine.setmEmail(mEmail);
		mEngine.setmQQ(mQQ);
		mEngine.setmUserTypeString("2");
		mEngine.start();
	}
}
