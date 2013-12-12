package com.janan.recruit.companyactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.company.CompanyInfo;
import com.janan.data.bean.search.SearchCondition;
import com.janan.net.CompanyRegisterEngine;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.searchactivity.SearchCheckInfoActivity;
import com.janan.util.Util;
import com.janan.util.activity.ProvinceActivity;
import com.janan.view.mamager.TitleBarManager;

public class CompanyInfoActivity extends BaseActivity implements OnClickListener{

	private String mTitle;
	private TitleBarManager mBarUtil;
	private BaseActivity mActivity;
	private boolean isRegister;
	private LinearLayout mRootLayout;
	// 注册
	private EditText mUserNameEditText;
	private EditText mPassWordEditText;
	private EditText mConfirmPassEditText;
	private EditText mEmailEditText;
	private EditText mCompanynameEditText;
	private EditText mContactNameEditText;
	private TextView mArreaText;
	private EditText mAddressEditText;
	private EditText mPhoneEditText;
	private TextView mSuoshuhangyeTextView;
	private TextView mHangyeleixingTextView;
	private TextView mXingjibiaozhunTextView;
	private LinearLayout mJianjieLayout;
	private EditText mJianjieEdit;
	private Button mButton;

	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(mActivity);
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
		super.updateUI();
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		addBar();
	}

	private void initLayout() {
		mRootLayout = (LinearLayout)findViewById(R.id.layout_conpanyinfoactivity);
		mRootLayout.setVisibility(View.VISIBLE);
		if (isRegister) {
			(findViewById(R.id.usernamelayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.emaillayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.passwordlayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.confirmlayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.companynamelayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.contactlayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.arealayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.addresslayout)).setVisibility(View.GONE);
			(findViewById(R.id.phonelayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.hangye)).setVisibility(View.VISIBLE);
			(findViewById(R.id.leixing)).setVisibility(View.VISIBLE);
			(findViewById(R.id.xingji)).setVisibility(View.VISIBLE);
			(findViewById(R.id.jianjie)).setVisibility(View.GONE);
		} else {
			(findViewById(R.id.usernamelayout)).setVisibility(View.GONE);
			(findViewById(R.id.emaillayout)).setVisibility(View.GONE);
			(findViewById(R.id.passwordlayout)).setVisibility(View.GONE);
			(findViewById(R.id.confirmlayout)).setVisibility(View.GONE);
			
			(findViewById(R.id.addresslayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.phonelayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.hangye)).setVisibility(View.GONE);
			(findViewById(R.id.leixing)).setVisibility(View.GONE);
			(findViewById(R.id.xingji)).setVisibility(View.GONE);
			(findViewById(R.id.companynamelayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.contactlayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.arealayout)).setVisibility(View.VISIBLE);
			(findViewById(R.id.jianjie)).setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void initView() {

		initLayout();
		mJianjieLayout = (LinearLayout) findViewById(R.id.jianjie);
		mJianjieEdit = (EditText)findViewById(R.id.jianjieedit);
		mUserNameEditText = (EditText) findViewById(R.id.username);
		mPassWordEditText = (EditText) findViewById(R.id.password);
		mConfirmPassEditText = (EditText) findViewById(R.id.confirmpass);
		mEmailEditText = (EditText) findViewById(R.id.email);
		mCompanynameEditText = (EditText) findViewById(R.id.companyname);
		mContactNameEditText = (EditText) findViewById(R.id.contactname);
		mArreaText = (TextView) findViewById(R.id.area);
		mArreaText.setOnClickListener(this);
		mAddressEditText = (EditText) findViewById(R.id.address);
		mPhoneEditText = (EditText) findViewById(R.id.phone);
		mSuoshuhangyeTextView = (TextView) findViewById(R.id.suoshuhangye);
		mSuoshuhangyeTextView.setOnClickListener(this);
		mHangyeleixingTextView = (TextView) findViewById(R.id.jiudianleixing);
		mHangyeleixingTextView.setOnClickListener(this);
		mXingjibiaozhunTextView = (TextView) findViewById(R.id.xingjibiaozhun);
		mXingjibiaozhunTextView.setOnClickListener(this);
		mButton = (Button) findViewById(R.id.button);
		if (isRegister) {
			mButton.setText(getString(R.string.agree));
		} else {
			mButton.setText(getString(R.string.comfirmchange));
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.companyinfoactivity);
		mActivity = this;
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		isRegister = getIntent().getBooleanExtra("isregister", false);
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub

				closeProgress();
				
				if (msg.what == CallbackSuccess) {
					if(isRegister){
						
						setResult(RESULT_OK);
					}else{
						showToast(toastChangSuccess);					
					}
					finishSelf();
				}
				else if(msg.what == CallbackError){
				
					showToast(msg);
				}else if(msg.what == CallbackSearchSuccess){
					mCompanynameEditText.setText(mCompany.getmCompanyName());
					mContactNameEditText.setText(mCompany.getmContactInfo());
					if(mCompany.getmCity()!=null&&!"".equals(mCompany.getmCity())){
						mArreaText.setText(mCompany.getmCity()+","+mCompany.getmArea());
					}
					
					mAddressEditText.setText(mCompany.getmAddress());
					mPhoneEditText.setText(mCompany.getmPhone());
//					mJobsCountEditText.setText(mCompany.getmJobsCount());
					mJianjieEdit.setText(mCompany.getmMoMoContent());
				}
			}

		};
		initView();
		showSelf();
		if(!isRegister){
			startSearch();
		}
		
	}

	CompanyRegisterEngine mRegisEngine;
	
	public void registerOnclick(View view) {
		
		if (isRegister&&checkEditRegister()) {
			showProgress();
			mRegisEngine = new CompanyRegisterEngine(mActivity, false);
			mRegisEngine.setChange(false);
			mRegisEngine.setSearch(false);
			mRegisEngine.setmCompany(mCompany);
			mRegisEngine.start();
		}else if(!isRegister){
			showProgress();
			if(mCompany == null){
				mCompany = new CompanyInfo();
			}
			
			if(Util.checkEditNull(mCompanynameEditText)){
				mCompany.setmCompanyName(mCompanynameEditText.getText().toString());
			}
			if(Util.checkEditNull(mContactNameEditText)){
				mCompany.setmContactInfo(mContactNameEditText.getText().toString());
			}
			if(Util.checkEditNull(mAddressEditText)){
				mCompany.setmAddress(mAddressEditText.getText().toString());
			}
			if(Util.checkEditNull(mPhoneEditText)){
				mCompany.setmPhone(mPhoneEditText.getText().toString());
			}
//			if(Util.checkEditNull(mJobsCountEditText)){
//				mCompany.setmJobsCount(mJobsCountEditText.getText().toString());
//			}
			
			if(Util.checkEditNull(mJianjieEdit)){
				mCompany.setmMoMoContent(mJianjieEdit.getText().toString());
			}
			mRegisEngine = new CompanyRegisterEngine(mActivity, false);
			mRegisEngine.setmCompany(mCompany);
			mRegisEngine.setChange(true);
			mRegisEngine.setSearch(false);
			mRegisEngine.start();
		}
	}

	private void startSearch(){
		if(mCompany == null){
			mCompany = new CompanyInfo();
		}
		mRegisEngine = new CompanyRegisterEngine(mActivity, false);
		mRegisEngine.setChange(false);
		mRegisEngine.setSearch(true);
		mRegisEngine.setmCompany(mCompany);
		showProgress();
		mRegisEngine.start();
	}
	private boolean checkEditRegister() {
		if (isRegister) {
			if(!Util.checkEditNull(mUserNameEditText)
					||!Util.checkEditNull(mPassWordEditText)
					||!Util.checkEditNull(mConfirmPassEditText)
					||!Util.checkEditNull(mEmailEditText)
					||!Util.checkEditNull(mCompanynameEditText)
					||!Util.checkEditNull(mContactNameEditText)
					||!Util.checkEditNull(mPhoneEditText)
					||"".equals(mArreaText.getText().toString())
					){
				showToast(toastNullString);
				return false;
			}else if(!Util.checkPassword(mPassWordEditText.getText().toString(), mConfirmPassEditText.getText().toString())){
				showToast(toastPassErrorString);
				return false;
			}else if(!Util.checkEmail(mEmailEditText.getText().toString())){
				showToast(toastWrongEmail);
				return false;
			}
			else{
				if(mCompany == null){
					mCompany = new CompanyInfo();
				}				
				mCompany.setmUserName(mUserNameEditText.getText().toString());
				mCompany.setmPassWord(mPassWordEditText.getText().toString());
				mCompany.setmEmail(mEmailEditText.getText().toString());
				mCompany.setmCompanyName(mCompanynameEditText.getText().toString());
				mCompany.setmContactInfo(mContactNameEditText.getText().toString());
				mCompany.setmPhone(mPhoneEditText.getText().toString());
				return true;
			}

		}else{
			return true;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		Intent _Intent = new Intent();
		Bundle _Bundle = new Bundle();
		if(mArreaText.getId() == id){
			
			_Intent.setClass(mActivity, ProvinceActivity.class);
			
			_Bundle.putBoolean(TAG_HASAREA, true);
			_Bundle.putBoolean(TAG_HASCITY, true);
			_Intent.putExtra(TAG_BUNDLE, _Bundle);
			startActivityForResult(_Intent,
					REQUEST_SEARCHCHEK);                   
		}else if(mXingjibiaozhunTextView.getId() == id){
			_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
			_Intent.putExtra(STRING_TITLE, "星级标准");
			_Bundle.putInt(TAG_SEARCHKEY, SearchCondition.TAG_CONDITION_XINGJI);
			_Intent.putExtra(TAG_BUNDLE, _Bundle);
			startActivityForResult(_Intent, 99);
		}else if(mSuoshuhangyeTextView.getId() == id){
			_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
			_Intent.putExtra(STRING_TITLE, "所属行业");
			_Bundle.putInt(TAG_SEARCHKEY, SearchCondition.TAG_CONDITION_HANGYE);
			_Intent.putExtra(TAG_BUNDLE, _Bundle);
			startActivityForResult(_Intent, 88);
		}else if(mHangyeleixingTextView.getId() == id){
			_Intent.setClass(mActivity, SearchCheckInfoActivity.class);
			_Intent.putExtra(STRING_TITLE, "酒店类型");
			_Bundle.putInt(TAG_SEARCHKEY, SearchCondition.TAG_CONDITION_JIUDIANLEIXING);
			_Intent.putExtra(TAG_BUNDLE, _Bundle);
			startActivityForResult(_Intent, 77);
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(mCompany == null){
			mCompany = new CompanyInfo();
		}
		if(data != null){
			if(requestCode == REQUEST_SEARCHCHEK){
				Bundle _Bundle = data.getBundleExtra(TAG_BUNDLE);
				
				mCompany.setmProvice(_Bundle.getString(TAG_PROVINCE));
				mCompany.setmCity(_Bundle.getString(TAG_CITY));
				mCompany.setmArea(_Bundle.getString(TAG_AREA));
				if(Util.checkZhixiashi(mCompany.getmProvice())){
					mArreaText.setText(mCompany.getmProvice()+","+mCompany.getmArea());
				}else{
					mArreaText.setText(mCompany.getmCity()+","+mCompany.getmArea());
				}
			}else if(requestCode == 99){
				if(resultCode == VALUE_RESULT_OK){
					Bundle _Bundle = data.getBundleExtra(TAG_BUNDLE);
					String _valueString = _Bundle.getString(TAG_VALUE);
					mXingjibiaozhunTextView.setText(_valueString);
					mCompany.setmXingJiString(_valueString);
				}
			}else if(requestCode == 88){
				if(resultCode == VALUE_RESULT_OK){
					Bundle _Bundle = data.getBundleExtra(TAG_BUNDLE);
					String _valueString = _Bundle.getString(TAG_VALUE);
					mSuoshuhangyeTextView.setText(_valueString);
					mCompany.setmIndustry(_valueString);
				}
			}else if(requestCode == 77){
				if(resultCode == VALUE_RESULT_OK){
					Bundle _Bundle = data.getBundleExtra(TAG_BUNDLE);
					String _valueString = _Bundle.getString(TAG_VALUE);
					mHangyeleixingTextView.setText(_valueString);
					mCompany.setmProperity(_valueString);
				}
			}
			
			
			
		}
	}
	
	
}
