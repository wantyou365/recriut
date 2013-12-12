package com.janan.data.bean;

import java.io.Serializable;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;

public class ShiXi implements Serializable{
	private String newsid;
	private String parentid;
	private String title;
	private String content;
	private String clickcount;
	private String poster;
	private String published;
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getClickcount() {
		return clickcount;
	}
	public void setClickcount(String clickcount) {
		this.clickcount = clickcount;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public View getTitleView(Activity activity){
		LinearLayout _Layout = (LinearLayout)activity.getLayoutInflater().inflate(R.layout.newstitleitem, null);
		TextView _TitleView = (TextView)_Layout.findViewById(R.id.title);
		_TitleView.setText(getTitle());
		TextView _FnameView = (TextView)_Layout.findViewById(R.id.fname);
		_FnameView.setText(getPoster());
		TextView _LastTextView = (TextView)_Layout.findViewById(R.id.times);
		_LastTextView.setText(getClickcount());
		return _Layout;
	}
	
	
}
