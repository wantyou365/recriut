package com.janan.recruit.personalactivity;

import android.R.string;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.personal.User;
import com.janan.net.BaseEngine;
import com.janan.net.LoginEngine;
import com.janan.net.LogoutEngine;
import com.janan.recruit.BaseActivity;
import com.janan.util.DBHelper;
import com.janan.util.Util;
import com.janan.view.mamager.TitleBarManager;

/**
 * 个人中心acitivty
 */
public class PersonalAcitivty extends BaseActivity {
	private LinearLayout mRootLayout;
	private LinearLayout mLayout_Line1;
	private LinearLayout mLayout_Line2;
	private LinearLayout mLayout_Line3;
	private LinearLayout mLayout_Line4;
	private LinearLayout mLayout_Line5;

	private LinearLayout mUnLogView;
	private ScrollView mLoggedView;
	private EditText mUserNameEditText;
	private EditText mPassWordEditText;
	private TextView mWelComeTitle;
	private TitleBarManager mBarUtil;
	private boolean isLogin = false;
	private CheckBox mCheckBox;
	private boolean isAuto;
	private boolean startFromApply = false;
	
	
	
	public void showChoiceDialog(){
		AlertDialog.Builder _Builder = new AlertDialog.Builder(mActivity);
		_Builder.setMessage(mActivity.getString(R.string.editresume));
		_Builder.setPositiveButton(mActivity.getString(R.string.submit), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				_Intent.setClass(mActivity, ResumeChangeActivity.class);
				_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.bianjijianli));
				mActivity.startActivity(_Intent);
			}

		});
		_Builder.setNegativeButton(mActivity.getString(R.string.cancle), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}

		});
		_Builder.create().show();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalactivity);
		DBHelper _helper = new DBHelper(mActivity);
		mUser = _helper.getUser();
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if (msg.what == handle_Login) {
					isLogin = true;
					DBHelper _Helper = new DBHelper(mActivity);
					_Helper.clearUser();
					_Helper.setUser(mUser);
					showSelf();
					mUserNameEditText.setText("");
					mPassWordEditText.setText("");
					if(startFromApply){
						Intent _Intent = new Intent();
						_Intent.putExtra(TAG_STARTFROMAPPLY, true);
						setResult(RESULT_OK, _Intent);
						finishSelf();
					}else if(isRegister){
						showChoiceDialog();
						isRegister = false;
					}
					
						
					
					
				} else if (msg.what == CallbackError) {
					showToast(msg);					
					showSelf();
				} else if (msg.what == CallbacknetError) {
					showToast(msg);
				} else if(msg.what == CallbackRefreshSuccess){
					showToast(mActivity.getString(R.string.toastrefreshsuccess));
				}
			}

		};
		if (BaseEngine.mPersonCookie != null) {
			isLogin = true;
		}
		if (mUser != null) {
			if (get(mActivity).getBoolean(TAG_PERSONAUTO, false)) {
				startLoginbyUser(mUser);
			}

		}
		startFromApply = getIntent().getBooleanExtra(TAG_STARTFROMAPPLY, false);
		initView();
		showSelf();
	}

	private void showLogoutDialog() {
		AlertDialog.Builder _Builder = new AlertDialog.Builder(mActivity);
		_Builder.setMessage(mActivity.getString(R.string.logouttitle));
		_Builder.setPositiveButton(mActivity.getString(R.string.submit), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				DBHelper _helper = new DBHelper(mActivity);
				_helper.clearUser();
				mUser = null;
				isLogin = false;
				BaseEngine.mPersonCookie = null;
				showSelf();
			}

		});
		_Builder.setNegativeButton(mActivity.getString(R.string.cancle), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}

		});
		_Builder.create().show();

	}

	public void login(View view) {
		mUserNameString = mUserNameEditText.getText().toString();
		mPasswordString = mPassWordEditText.getText().toString();
		if (!Util.checkEditNull(mUserNameEditText)
				|| !Util.checkEditNull(mPassWordEditText)) {
			showToast(toastLoginError);
		} else {
//			if(Util.checkEmail(mUserNameString)){				
				startLogin();				
//			}else{
//				showToast(getString(R.string.pleaseeditrightemail));
//			}
			
		}

	}

	LoginEngine mEngine;
	private String mUserNameString;
	private String mPasswordString;
	public void startLogin() {
		showProgress();
		Editor _Editor = get(mActivity).edit();
		_Editor.putBoolean(TAG_PERSONAUTO, isAuto);
		_Editor.commit();
		mEngine = new LoginEngine(this, true);
		mEngine.setmEmail(mUserNameString);
		mEngine.setmPassWord(mPasswordString);
		mEngine.start();
	}

	public void startLoginbyUser(User user) {
		showProgress();
		mEngine = new LoginEngine(this, true);
		mEngine.setmEmail(user.getmUserEmail());
		mEngine.setmPassWord(user.getmPassWord());
		mEngine.start();

	}

	public void register(View view) {
		Intent _Intent = new Intent(mActivity, RegisterActivity.class);
		startActivityForResult(_Intent, RegisterRequest);
//		startActivity(_Intent);
	}

	View mBar;
	LogoutEngine mLogoutEngine;

	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(this);
		try {
			mRootLayout.removeView(mBar);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (isLogin) {
			mBar = mBarUtil
					.createBar(
							getResources().getString(
									R.string.title_gerenzhongxin),
							getResources().getDrawable(
									R.drawable.login_out_down), null);
			mBarUtil.setLeftButtonListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					showLogoutDialog();
				}
			});
		} else {
			mBar = mBarUtil.createBar(
					getResources().getString(R.string.title_gerenzhongxin),
					null, null);
		}

		mRootLayout.addView(mBar, 0);

	}

	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		super.updateUI();
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
	}

	@Override
	public void finishSelf() {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void showSelf() {
		addBar();
		// TODO Auto-generated method stub
		if (isLogin) {
			mLoggedView.setVisibility(View.VISIBLE);
			mUnLogView.setVisibility(View.GONE);
			mWelComeTitle.setText(mActivity.getString(R.string.welcomeback) + mUser.getmUserName());
			if (mLayout_Line1.getChildCount() > 0) {
				mLayout_Line1.removeAllViews();
			}
			mLayout_Line1.addView(mUser.getPersonalActivityItemView(
					getResources().getString(R.string.qiyelaixin), null,
					mUser.TAG_QIYELAIXIN, this));
//			mLayout_Line1.addView(mUser.getPersonalActivityItemView(
//					getResources().getString(R.string.liulanwojianlideqiye),
//					null, mUser.TAG_LIULANQIYE, this));
			if (mLayout_Line2.getChildCount() > 0) {
				mLayout_Line2.removeAllViews();
			}
			mLayout_Line2.addView(mUser.getPersonalActivityItemView(
					getResources().getString(R.string.qiuzhijilu), null,
					mUser.TAG_QIUZHIJILU, this));
			mLayout_Line2.addView(mUser.getPersonalActivityItemView(
					getResources().getString(R.string.zhiweishoucangjia), null,
					mUser.TAG_ZHIWEISHOUCANG, this));
			if (mLayout_Line3.getChildCount() > 0) {
				mLayout_Line3.removeAllViews();
			}
			mLayout_Line3.addView(mUser.getPersonalActivityItemView(
					getResources().getString(R.string.xiugaiweijianli), null,
					mUser.TAG_XIUGAIWEIJIANLI, this));
			mLayout_Line3.addView(mUser.getPersonalActivityItemView(
					getResources().getString(R.string.yulanjianli), null,
					mUser.TAG_YULANJIANLI, this));
			mLayout_Line3.addView(mUser.getPersonalActivityItemView(
					getResources().getString(R.string.shuaxinjianli), null,
					mUser.TAG_SHUAXINJIANLI, this));
			if (mLayout_Line4.getChildCount() > 0) {
				mLayout_Line4.removeAllViews();
			}
			mLayout_Line4.addView(mUser.getPersonalActivityItemView(
					getResources().getString(R.string.waifajianli), null,
					mUser.TAG_WAIFAJIANLI, this));
			mLayout_Line4.addView(mUser.getPersonalActivityItemView(
					getResources().getString(R.string.waifajianlijilu), null,
					mUser.TAG_WAIFAJIANLIJILU, this));
			if (mLayout_Line5.getChildCount() > 0) {
				mLayout_Line5.removeAllViews();
			}
			mLayout_Line5.addView(mUser.getPersonalActivityItemView(
					getResources().getString(R.string.xiugaimima), null,
					mUser.TAG_XIUGAIMIMA, this));
			mLayout_Line5.addView(mUser.getPersonalActivityItemView(
					getResources().getString(R.string.shoucangkuanli), null,
					mUser.TAG_SHOUCANGGUANLI, this));
		} else {
			mLoggedView.setVisibility(View.GONE);
			mUnLogView.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		mRootLayout = (LinearLayout) findViewById(R.id.rootlayout_personalactivity);
		mUnLogView = (LinearLayout) findViewById(R.id.unlogview);
		mLoggedView = (ScrollView) findViewById(R.id.loggedview);
		mUserNameEditText = (EditText) findViewById(R.id.usernameedit);
		mPassWordEditText = (EditText) findViewById(R.id.passwordedit);
		mLayout_Line1 = (LinearLayout) findViewById(R.id.layone);
		mLayout_Line2 = (LinearLayout) findViewById(R.id.laytwo);
		mLayout_Line3 = (LinearLayout) findViewById(R.id.laythree);
		mLayout_Line4 = (LinearLayout) findViewById(R.id.layfour);
		mLayout_Line5 = (LinearLayout) findViewById(R.id.layfive);
		mWelComeTitle = (TextView) findViewById(R.id.welcometitle);
		mCheckBox = (CheckBox) findViewById(R.id.checkbox);
		mCheckBox.setChecked(true);
		isAuto = mCheckBox.isChecked();
		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				isAuto = isChecked;
			}
		});
	}
	boolean isRegister = false;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == RegisterRequest){
			if(resultCode == RESULT_OK){
				if(data!=null){
					mUserNameString = data.getStringExtra("username");
					mPasswordString = data.getStringExtra("password");
					startLogin();
					isRegister = true;
				}
				
			}
		}
	}

	
}
