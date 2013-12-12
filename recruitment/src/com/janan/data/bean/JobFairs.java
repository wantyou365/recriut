package com.janan.data.bean;

import java.io.Serializable;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;

public class JobFairs implements Serializable{
	private String mId;
	private String mTitle;
	private String mContent;
	private String mTypeName;
	private String mDateString;
	private String mCity;
	private String mCopyFrom;
	private String mHitString;
	public String getmCopyFrom() {
		return mCopyFrom;
	}
	public void setmCopyFrom(String mCopyFrom) {
		this.mCopyFrom = mCopyFrom;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public String getmContent() {
		return mContent;
	}
	public void setmContent(String mContent) {
		this.mContent = mContent;
	}
	public String getmTypeName() {
		return mTypeName;
	}
	public void setmTypeName(String mTypeName) {
		this.mTypeName = mTypeName;
	}
	public String getmDateString() {
		return mDateString;
	}
	public void setmDateString(String mDateString) {
		this.mDateString = mDateString;
	}
	public String getmCity() {
		return mCity;
	}
	public void setmCity(String mCity) {
		this.mCity = mCity;
	}
	
	public String getmHitString() {
		return mHitString;
	}
	public void setmHitString(String mHitString) {
		this.mHitString = mHitString;
	}
	
	public View getTitleView(Activity activity){
		LinearLayout _Layout = (LinearLayout)activity.getLayoutInflater().inflate(R.layout.newstitleitem, null);
		TextView _TitleView = (TextView)_Layout.findViewById(R.id.title);
		_TitleView.setText(getmTitle());
		TextView _FnameView = (TextView)_Layout.findViewById(R.id.fname);
		_FnameView.setText(getmDateString().subSequence(0, 10));
		TextView _LastTextView = (TextView)_Layout.findViewById(R.id.times);
		_LastTextView.setText(getmHitString());
		return _Layout;
	}
	
}
