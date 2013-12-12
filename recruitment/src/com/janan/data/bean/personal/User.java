package com.janan.data.bean.personal;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.BaseBean;
import com.janan.net.RefreshResumeEngine;
import com.janan.recruit.BaseActivity;

import com.janan.recruit.personalactivity.ChangPassWordActivity;
import com.janan.recruit.personalactivity.CompanyEmailActivity;
import com.janan.recruit.personalactivity.JobsCollectionActivity;
import com.janan.recruit.personalactivity.ResumeChangeActivity;
import com.janan.recruit.personalactivity.ResumePreviewActivity;
import com.janan.recruit.personalactivity.SeeMyResumeCompanyActivity;
import com.janan.recruit.personalactivity.SendResumeOutActivity;
import com.janan.recruit.personalactivity.SendResumeOutRecordsActivity;

public class User extends BaseBean{
	
	public User() {
	
		// TODO Auto-generated constructor stub
	}
	private String mUserID;
	private String mUserName;
	private String mPassWord;
	private String mComId;
	public String getmComId() {
		return mComId;
	}

	public void setmComId(String mComId) {
		this.mComId = mComId;
	}
	private String mUserEmail;
	private String mUserType;
	private String mQiyelaixin;//企业来信
	private String mQiuzhijilu;//求职记录
	private String mZhiweishoucang;//职位收藏
	private String mWaifajianli;//外发简历记录
	private String mSeeTimes;//简历浏览次数
	private Resume mResume;//简历
	private boolean hasChineseResume;//有中文简历
	private boolean hasEnglishResume;//有英文简历
	
	public LinearLayout getPersonalActivityItemView(String left,String right,final int tag,BaseActivity mActivity){
		LinearLayout _Layout = 
				(LinearLayout)mActivity.getLayoutInflater().inflate(R.layout.personitemview, null);
		if(left!=null){
			TextView _TextView1 =
					(TextView)_Layout.findViewById(R.id.text1_personitem);
			_TextView1.setText(left);
		}
		if(right!=null){
			TextView _TextView2 = 
					(TextView)_Layout.findViewById(R.id.text2_personitem);
			_TextView2.setText(right);
		}
		LinearLayout _Line = (LinearLayout)_Layout.findViewById(R.id.line);
		if(tag==TAG_ZHIWEISHOUCANG||tag==TAG_SHUAXINJIANLI
				||tag==TAG_WAIFAJIANLIJILU||tag == TAG_LIULANQIYE||tag == TAG_SHOUCANGGUANLI ||tag == TAG_QIYELAIXIN){
			_Line.setVisibility(View.GONE);
		}
		final BaseActivity _Activity = mActivity;
		_Layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				itemOnClick(tag,_Activity);
			}
		});
		return _Layout;
		
		
	}
	
	private void itemOnClick(int tag,BaseActivity mActivity){
		Log.i("janan", "点击了person！");
		Intent _Intent = new Intent();
		switch (tag) {
		case TAG_LIULANQIYE:
			
			break;
		case TAG_QIUZHIJILU:
			_Intent.setClass(mActivity, CompanyEmailActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.qiuzhijilu));
			_Intent.putExtra("user", this);
			_Intent.putExtra("tag", 2);
			mActivity.startActivity(_Intent);
			break;
		case TAG_QIYELAIXIN:
			_Intent.setClass(mActivity, CompanyEmailActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.qiyelaixin));
			_Intent.putExtra("user", this);
			_Intent.putExtra("tag", 1);
			mActivity.startActivity(_Intent);
			
			break; 
		case TAG_SHUAXINJIANLI:
			RefreshResumeEngine _Engine = new RefreshResumeEngine(mActivity,true);
			mActivity.showProgress();
			_Engine.start();
			break;
		case TAG_WAIFAJIANLI:
			_Intent.setClass(mActivity, SendResumeOutActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.waifajianli));
			mActivity.startActivity(_Intent);
			break;
		case TAG_WAIFAJIANLIJILU:
			_Intent.setClass(mActivity, SendResumeOutRecordsActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.waifajianlijilu));
			mActivity.startActivity(_Intent);
			break;
		case TAG_XIUGAIMIMA:
			
			_Intent.setClass(mActivity, ChangPassWordActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.xiugaimima));
			_Intent.putExtra("isperson", true);
			mActivity.startActivity(_Intent);
			break;
		case TAG_XIUGAIWEIJIANLI:
			_Intent.setClass(mActivity, ResumeChangeActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.xiugaiweijianli));
			mActivity.startActivity(_Intent);
			break;
		case TAG_YULANJIANLI:
			_Intent.setClass(mActivity, ResumePreviewActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.yulanjianli));
			mActivity.startActivity(_Intent);
			break;
		case TAG_ZHIWEISHOUCANG:
			_Intent.setClass(mActivity, JobsCollectionActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.zhiweishoucangjia));
			mActivity.startActivity(_Intent);
			break;
		case TAG_SHOUCANGGUANLI:
			_Intent.setClass(mActivity, JobsCollectionActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.shoucangkuanli));
			_Intent.putExtra("candelete", true);
			mActivity.startActivity(_Intent);
			break;
		default:
			break;
		}
	}
	
	public String getmUserID() {
		return mUserID;
	}

	public void setmUserID(String mUserID) {
		this.mUserID = mUserID;
	}

	public String getmUserName() {
		return mUserName;
	}

	public void setmUserName(String mUserName) {
		this.mUserName = mUserName;
	}

	public String getmQiyelaixin() {
		return mQiyelaixin;
	}

	public void setmQiyelaixin(String mQiyelaixin) {
		this.mQiyelaixin = mQiyelaixin;
	}

	public String getmQiuzhijilu() {
		return mQiuzhijilu;
	}

	public void setmQiuzhijilu(String mQiuzhijilu) {
		this.mQiuzhijilu = mQiuzhijilu;
	}

	public String getmZhiweishoucang() {
		return mZhiweishoucang;
	}

	public void setmZhiweishoucang(String mZhiweishoucang) {
		this.mZhiweishoucang = mZhiweishoucang;
	}

	public String getmWaifajianli() {
		return mWaifajianli;
	}

	public void setmWaifajianli(String mWaifajianli) {
		this.mWaifajianli = mWaifajianli;
	}

	public Resume getmResume() {
		return mResume;
	}

	public void setmResume(Resume mResume) {
		this.mResume = mResume;
	}

	public boolean isHasChineseResume() {
		return hasChineseResume;
	}

	public void setHasChineseResume(boolean hasChineseResume) {
		this.hasChineseResume = hasChineseResume;
	}

	public boolean isHasEnglishResume() {
		return hasEnglishResume;
	}

	public void setHasEnglishResume(boolean hasEnglishResume) {
		this.hasEnglishResume = hasEnglishResume;
	}

	public String getmSeeTimes() {
		return mSeeTimes;
	}

	public void setmSeeTimes(String mSeeTimes) {
		this.mSeeTimes = mSeeTimes;
	}
	public String getmPassWord() {
		return mPassWord;
	}

	public void setmPassWord(String mPassWord) {
		this.mPassWord = mPassWord;
	}

	public String getmUserEmail() {
		return mUserEmail;
	}

	public void setmUserEmail(String mUserEmail) {
		this.mUserEmail = mUserEmail;
	}

	public String getmUserType() {
		return mUserType;
	}

	public void setmUserType(String mUserType) {
		this.mUserType = mUserType;
	}
	
}
