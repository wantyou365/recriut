package com.janan.data.bean;





import com.gazecloud.dyteam.R;

import android.R.integer;
import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabData {
	/**
	 * tab
	 * 标签信息
	 * */
	private Class mClass;
	private String mTagId;
	private String mStringId;
	private Activity mActivity;
	public TabData(Class lClass,int lTagId,int lStringId,Activity lActivity){
		this.mClass = lClass;
		this.mTagId = lActivity.getResources().getString(lTagId);
		this.mStringId = lActivity.getResources().getString(lStringId);
		this.mActivity = lActivity;
	}
	/**
	 * 获取标签view
	 * */
	public View getDislayView(){		 
         TextView _text = (TextView)mActivity.getLayoutInflater().inflate(R.layout.tabsview, null);
         _text.setText(mStringId);
         if(mTagId.equals(mActivity.getResources().getString(R.string.tab_tag_subscribe))){
        	
        	 _text.setCompoundDrawablesWithIntrinsicBounds(null, mActivity.getResources()
        			 .getDrawable(R.drawable.tab_subscribe), null, null);
//        	 _text.setBackgroundResource(R.drawable.tab_subscribe);
         }else if(mTagId.equals(mActivity.getResources().getString(R.string.tab_tag_search))){
        	 _text.setCompoundDrawablesWithIntrinsicBounds(null, mActivity.getResources()
        			 .getDrawable(R.drawable.tab_search), null, null);
//        	 _text.setBackgroundResource(R.drawable.tab_search);
         }else if(mTagId.equals(mActivity.getResources().getString(R.string.tab_tag_personal))){
        	 _text.setCompoundDrawablesWithIntrinsicBounds(null, mActivity.getResources()
        			 .getDrawable(R.drawable.tab_person), null, null);
//        	 _text.setBackgroundResource(R.drawable.tab_person);
         }else if(mTagId.equals(mActivity.getResources().getString(R.string.tab_tag_business))){
        	 _text.setCompoundDrawablesWithIntrinsicBounds(null, mActivity.getResources()
        			 .getDrawable(R.drawable.tab_business), null, null);
//        	 _text.setBackgroundResource(R.drawable.tab_business);
         }else if(mTagId.equals(mActivity.getResources().getString(R.string.tab_tag_dynamics))){
        	 _text.setCompoundDrawablesWithIntrinsicBounds(null, mActivity.getResources()
        			 .getDrawable(R.drawable.tab_dynamics), null, null);
//        	 _text.setBackgroundResource(R.drawable.tab_dynamics);
         }
         return _text;
	}
	public Class getmClass() {
		return mClass;
	}
	public String getmStringId() {
		return mStringId;
	}
	public Activity getmActivity() {
		return mActivity;
	}
	public String getmTagId() {
		return mTagId;
	}
	
	
}
