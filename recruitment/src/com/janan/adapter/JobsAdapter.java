package com.janan.adapter;

import java.util.ArrayList;

import com.janan.data.bean.JobInfo;
import com.janan.recruit.BaseActivity;
import com.janan.view.mamager.SubScribeJobManager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class JobsAdapter extends BaseAdapter{
	private ArrayList<JobInfo> mList;
	private BaseActivity mActivity;
	
	public JobsAdapter(BaseActivity activity){
		this.mActivity = activity;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = mList.get(position).getJobsItemView(mActivity);
		return convertView;
	}
	public ArrayList<JobInfo> getmList() {
		return mList;
	}
	public void setmList(ArrayList<JobInfo> mList) {
		this.mList = mList;
	}
	public BaseActivity getmActivity() {
		return mActivity;
	}
	public void setmActivity(BaseActivity mActivity) {
		this.mActivity = mActivity;
	}

}
