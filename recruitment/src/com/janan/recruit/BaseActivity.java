package com.janan.recruit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.company.CompanyInfo;
import com.janan.data.bean.personal.User;
import com.janan.net.BaseEngine;
import com.janan.util.LocationUtil;
import com.janan.util.UncaughtExceptionHandler;
import com.janan.util.Util;
import com.janan.view.mamager.TitleBarManager;

public class BaseActivity extends Activity implements ActivityStatus {

	public String mTitle;

	public BaseActivity mActivity;
	public static User mUser;
	public BaseEngine mEngine;
	public static CompanyInfo mCompany;
	public static final int CallbackSuccess = 1;
	public static final int CallbackApplySuccess = 2;
	public static final int CallbackError = 8;
	public static final int CallbacknetError = 10;
	public static final int CallbackSendSuccess = 11;
	public static final int CallbackSubscribeSearch = 12;
	public static final int CallbackSearchSuccess = 9;
	public static final int CallbackRefreshSuccess = 13;
	public static final int handle_MSG = 1;
	public static final int handle_Login = 2;
	public static final int handle_Logout = 3;
	public static final int handle_Apply = 4;// …Í«Î
	public static final int handle_Subscribe = 5;
	public static final int handle_Collection = 6;//  ’≤ÿ
	public static final int handle_Share = 7;// ∑÷œÌ
	public static final int applyStartLoginRequestCode = 15;
	public TextView mFooter;
	public static final String TAG_PERSON = "isperson";
	public static String toastNullString = null;
	public static String toastPassErrorString = null;
	public static String toastRegisterSuccess = null;
	public static String toastWrongEmail = null;
	public static String toastLoginError = null;
	public static String toastRegister = null;
	public static String toastChangSuccess = null;
	public static String toastFabuSuccess = null;
	public static String toastNoResumes = null;
	public static String toastSureLogin = null;

	public static final String TAG_COMPANYAUTO = "company";
	public static final String TAG_PERSONAUTO = "person";
	public static final String TAG_STARTFROMAPPLY = "startfromapply";
	public static final String TAG_FROMFAST = "fast";
	public static final int RegisterRequest = 199;
	public TitleBarManager mBarUtil;
	public LinearLayout mRootLayout;
	public Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub

			closeProgress();
			showToast(msg);

		}

	};

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
	public void updateUI() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishSelf() {
		// TODO Auto-generated method stub
		mActivity.finish();
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub

		addBar();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mActivity = this;
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(
				mActivity));
		if (toastNullString == null) {
			toastNullString = mActivity.getString(R.string.toastNullString);
			toastPassErrorString = mActivity
					.getString(R.string.toastPassErrorString);
			toastRegisterSuccess = mActivity
					.getString(R.string.toastRegisterSuccess);
			toastWrongEmail = mActivity.getString(R.string.toastWrongEmail);
			toastLoginError = mActivity.getString(R.string.toastLoginError);
			toastRegister = mActivity.getString(R.string.toastRegister);
			toastChangSuccess = mActivity.getString(R.string.toastChangSuccess);
			toastFabuSuccess = mActivity.getString(R.string.toastFabuSuccess);
			toastNoResumes = mActivity.getString(R.string.toastNoResumes);
			toastSureLogin = mActivity.getString(R.string.toastSureLogin);
		}
		createFooter();
		if (!Util.checkLocationExists(mActivity)) {
			LocationUtil.setAddress(mActivity);
		}
	}

	@Override
	public void callBack(int callbackstatus, String info) {
		// TODO Auto-generated method stub
		Message _Message = mHandler.obtainMessage();
		_Message.what = callbackstatus;
		_Message.obj = info;
		mHandler.sendMessage(_Message);
	}

	@Override
	public void showToast(Message message) {
		// TODO Auto-generated method stub
		if (message.obj != null && !"".equals(message.obj.toString())) {
			Toast.makeText(mActivity, message.obj.toString(),
					Toast.LENGTH_SHORT).show();
		}

	}

	public void showToast(String message) {
		// TODO Auto-generated method stub
		if (message != null && !"".equals(message)) {
			Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
		}

	}

	public Handler getmHandler() {
		return mHandler;
	}

	public ProgressDialog mypDialog;

	public void showProgress() {

		if (mypDialog == null) {
			mypDialog = new ProgressDialog(this);
		}
		if (!mypDialog.isShowing()) {
			mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mypDialog.setMessage(mActivity.getString(R.string.waiting));
			mypDialog.setIndeterminate(false);
			mypDialog.setCanceledOnTouchOutside(false);
			mypDialog.show();
		}

	}

	public void closeProgress() {
		if (mypDialog != null) {
			if (mypDialog.isShowing()) {
				mypDialog.dismiss();
			}

		}
	}

	public void createFooter() {
		mFooter = (TextView) getLayoutInflater().inflate(R.layout.moretext,
				null);
	}

	public void startRequest() {

	}

	public void setListenter() {

	}

	public static SharedPreferences get(BaseActivity activity) {
		return PreferenceManager.getDefaultSharedPreferences(activity);
	}
}
