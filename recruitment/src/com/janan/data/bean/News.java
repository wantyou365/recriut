package com.janan.data.bean;

import com.gazecloud.dyteam.R;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class News {
	private String mId;
	private String mTitle;
	private String mSmallTitle;
	private String mContent;
	private String mUsername;
	private String fName;
	private String mLastView;
	private String mCopyFrom;
	private String mPostTime;
	private String mAuthor;
	private String mKeyWords;
	private String mHitString;
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
	public String getmSmallTitle() {
		return mSmallTitle;
	}
	public void setmSmallTitle(String mSmallTitle) {
		this.mSmallTitle = mSmallTitle;
	}
	public String getmContent() {
		return mContent;
	}
	public void setmContent(String mContent) {
		this.mContent = mContent;
	}
	public String getmUsername() {
		return mUsername;
	}
	public void setmUsername(String mUsername) {
		this.mUsername = mUsername;
	}
	public String getmCopyFrom() {
		return mCopyFrom;
	}
	public void setmCopyFrom(String mCopyFrom) {
		this.mCopyFrom = mCopyFrom;
	}
	public String getmPostTime() {
		return mPostTime;
	}
	public void setmPostTime(String mPostTime) {
		this.mPostTime = mPostTime;
	}
	public String getmAuthor() {
		return mAuthor;
	}
	public void setmAuthor(String mAuthor) {
		this.mAuthor = mAuthor;
	}
	public String getmKeyWords() {
		return mKeyWords;
	}
	public void setmKeyWords(String mKeyWords) {
		this.mKeyWords = mKeyWords;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getmLastView() {
		return mLastView;
	}
	public void setmLastView(String mLastView) {
		this.mLastView = mLastView;
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
		_FnameView.setText(getmUsername());
		TextView _LastTextView = (TextView)_Layout.findViewById(R.id.times);
		_LastTextView.setText(getmHitString());
		return _Layout;
	}
	
	
	
}
