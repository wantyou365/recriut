package com.janan.recruit.companyactivity;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.janan.data.bean.company.CompanyInfo;
import com.janan.net.BaseEngine;
import com.janan.net.LoginEngine;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.personalactivity.ChangPassWordActivity;
import com.janan.util.DBHelper;
import com.janan.util.Util;
import com.janan.view.mamager.TitleBarManager;

/**
 * 企业中心activity
 */
public class CompanyActivity extends BaseActivity implements OnClickListener {
	private TitleBarManager mBarUtil;
	private LinearLayout mRootLayout;
	private LinearLayout unLogLayout;
	
	private ScrollView logedScrollView;
	private TextView mQiyexinxiTextView;
	private TextView mXinxifabuTextView;
	private TextView mShoudaojianliTextView;
	private TextView mMianshitongzhiTextView;
	private TextView mMimaxiugaiTextView;
	private TextView mSousuojianliTextView;
	private TextView welcomeText;
	private EditText mUserNameEditText;
	private EditText mPassWordEditText;
	private CheckBox mCheckBox;
	private boolean isAuto;
	private boolean isLogin = false;
	View mBar;
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
							mTitle,
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
					mTitle,
					null, null);
		}

		mRootLayout.addView(mBar, 0);

	}
	private void showLogoutDialog() {
		AlertDialog.Builder _Builder = new AlertDialog.Builder(mActivity);
		_Builder.setMessage(mActivity.getString(R.string.logouttitle));
		_Builder.setPositiveButton(mActivity.getString(R.string.submit), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				DBHelper _helper = new DBHelper(mActivity);
				_helper.clearCompany();
				mCompany = null;
				isLogin = false;
				BaseEngine.mCompantCookie = null;
				showSelf();
				logedScrollView.setVisibility(View.GONE);
				unLogLayout.setVisibility(View.VISIBLE);
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
	public void showSelf() {
		// TODO Auto-generated method stub
		addBar();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout) findViewById(R.id.rootlayout_companyactivity);
		unLogLayout = (LinearLayout) findViewById(R.id.unlogview);
		
		
		logedScrollView = (ScrollView) findViewById(R.id.loggedview);
		welcomeText = (TextView)findViewById(R.id.welcometitle);
//		welcomeText.setVisibility(View.GONE);
		mQiyexinxiTextView = (TextView) findViewById(R.id.qiyexinxi);
		mXinxifabuTextView = (TextView) findViewById(R.id.xinxifabu);
		mShoudaojianliTextView = (TextView) findViewById(R.id.shoudaojianli);
		mMimaxiugaiTextView = (TextView) findViewById(R.id.mimaxiugai);
		mSousuojianliTextView = (TextView) findViewById(R.id.jianlisousuo);
		mMianshitongzhiTextView = (TextView) findViewById(R.id.mianshitongzhi);
		mUserNameEditText = (EditText) findViewById(R.id.usernameedit);
		mPassWordEditText = (EditText) findViewById(R.id.passwordedit);
		mCheckBox = (CheckBox) findViewById(R.id.checkbox);
		mCheckBox.setChecked(true);
		isAuto = mCheckBox.isChecked();
		setListener();
	}

	private void setListener() {
		mQiyexinxiTextView.setOnClickListener(this);
		mXinxifabuTextView.setOnClickListener(this);
		mShoudaojianliTextView.setOnClickListener(this);
		mMimaxiugaiTextView.setOnClickListener(this);
		mMianshitongzhiTextView.setOnClickListener(this);
		mSousuojianliTextView.setOnClickListener(this);
		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				isAuto = isChecked;
			}
		});

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.companyactivity);
		mTitle = getString(R.string.tab_business);
		DBHelper _Helper = new DBHelper(mActivity);
		mCompany = _Helper.getCompany();
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				closeProgress();
				if (msg.what == handle_Login) {

					DBHelper _Helper = new DBHelper(mActivity);
					_Helper.clearCompany();
					_Helper.setCompany(mCompany);
					isLogin = true;
					logedScrollView.setVisibility(View.VISIBLE);
					unLogLayout.setVisibility(View.GONE);
					mUserNameEditText.setText("");
					mPassWordEditText.setText("");
					if(mCompany.getmCompanyName()!=null){
						welcomeText.setText(mActivity.getString(R.string.welcomeback) + mCompany.getmCompanyName());
					}else{
						welcomeText.setText(mActivity.getString(R.string.welcomeback));
					}
					
				
					showSelf();
				} else if (msg.what == CallbackError) {
					isLogin = false;
					showToast(msg);
					logedScrollView.setVisibility(View.GONE);
					unLogLayout.setVisibility(View.VISIBLE);
					showSelf();
				} else if (msg.what == CallbacknetError) {
					showToast(msg);
				}
			}

		};
		initView();
		unLogLayout.setVisibility(View.VISIBLE);
		logedScrollView.setVisibility(View.GONE);
		if (mCompany != null) {
			if(get(mActivity).getBoolean(TAG_COMPANYAUTO, false)){
				startLogin();
			}
			
		}

		showSelf();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		Intent _Intent = new Intent();
		if (id == mQiyexinxiTextView.getId()) {
			_Intent.setClass(mActivity, CompanyInfoActivity.class);
			_Intent.putExtra("isregister", false);
			_Intent.putExtra(mActivity.STRING_TITLE,
					mQiyexinxiTextView.getText());
			mActivity.startActivity(_Intent);
		} else if (id == mXinxifabuTextView.getId()) {
			_Intent.setClass(mActivity, SendJobsInfoActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE,
					mXinxifabuTextView.getText());
			mActivity.startActivity(_Intent);
		} else if (id == mShoudaojianliTextView.getId()) {
			_Intent.setClass(mActivity, RecieveResumeActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE,
					mShoudaojianliTextView.getText());
			_Intent.putExtra("cancheck", false);
			mActivity.startActivity(_Intent);
		} else if (id == mMimaxiugaiTextView.getId()) {
			_Intent.setClass(mActivity, ChangPassWordActivity.class);
			_Intent.putExtra(TAG_PERSON, false);
			_Intent.putExtra(mActivity.STRING_TITLE,
					mMimaxiugaiTextView.getText());
			mActivity.startActivity(_Intent);
		} else if (id == mMianshitongzhiTextView.getId()) {
			_Intent.setClass(mActivity, RecieveResumeActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE,
					mMianshitongzhiTextView.getText());
			_Intent.putExtra("cancheck", true);
			mActivity.startActivity(_Intent);
		} else if(id == mSousuojianliTextView.getId()){
			_Intent.setClass(mActivity, SearchResumeActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE,
					mSousuojianliTextView.getText());
			mActivity.startActivity(_Intent);
		}

	}

	String _PassWordString;
	String _UserNameString;

	public void login(View view) {

		if (!Util.checkEditNull(mUserNameEditText)
				|| !Util.checkEditNull(mPassWordEditText)) {
			showToast(toastNullString);
		} else {
			Editor _Editor = get(mActivity).edit();
			_Editor.putBoolean(TAG_COMPANYAUTO, isAuto);
			_Editor.commit();
			_UserNameString = mUserNameEditText.getText().toString();
			_PassWordString = mPassWordEditText.getText().toString();
//			if(Util.checkEmail(_UserNameString)){
				mCompany = new CompanyInfo();
				mCompany.setmEmail(_UserNameString);
				mCompany.setmPassWord(_PassWordString);
				startLogin();
//			}else{
//				showToast(getString(R.string.pleaseeditrightemail));
//			}
			
		}

	}

	LoginEngine mEngine;

	public void startLogin() {
		showProgress();
		mEngine = new LoginEngine(this, false);
		mEngine.setmEmail(mCompany.getmEmail());
		mEngine.setmPassWord(mCompany.getmPassWord());
		mEngine.start();
		// mEngine.startLogin(_UserNameString, _PassWordString);
	}

	public void register(View view) {
		Intent _Intent = new Intent();
		_Intent.setClass(mActivity, CompanyInfoActivity.class);
		_Intent.putExtra(STRING_TITLE, getString(R.string.company_register));
		_Intent.putExtra("isregister", true);
		startActivityForResult(_Intent, 999);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==999){
			if(resultCode == RESULT_OK){
				startLogin();
			}
		}
	}

	
}
