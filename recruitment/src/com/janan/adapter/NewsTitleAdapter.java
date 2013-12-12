package com.janan.adapter;

import java.util.ArrayList;

import com.janan.data.bean.News;
import com.janan.recruit.BaseActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class NewsTitleAdapter extends BaseAdapter{
	private ArrayList<News> mList = new ArrayList<News>();
	private BaseActivity mActivity;
	public NewsTitleAdapter(BaseActivity activity){
		mActivity = activity;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return mList.get(position).getTitleView(mActivity);
	}
	public ArrayList<News> getmList() {
		return mList;
	}
	public void setmList(ArrayList<News> mList) {
		this.mList = mList;
	}
	public BaseActivity getmActivity() {
		return mActivity;
	}
	public void setmActivity(BaseActivity mActivity) {
		this.mActivity = mActivity;
	}

	
}
