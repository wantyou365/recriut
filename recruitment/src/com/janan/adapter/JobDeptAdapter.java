package com.janan.adapter;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.janan.data.data.JobDept;
import com.janan.data.data.Province;
import com.janan.recruit.BaseActivity;
import com.janan.util.DBHelper;
import com.janan.util.activity.CityActivity;

public class JobDeptAdapter extends BaseAdapter{
	private ArrayList<JobDept> mList = new ArrayList<JobDept>();
	private BaseActivity mActivity;
	private Bundle mBundle;
	public JobDeptAdapter(BaseActivity activity,Bundle bundle){
		this.mActivity = activity;
		this.mBundle = bundle;
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
		final JobDept _JobDept = mList.get(position);
		convertView = _JobDept.getItemView(mActivity, mBundle);
		return convertView;
	}	
	public ArrayList<JobDept> getmList() {
		return mList;
	}
	public void setmList(ArrayList<JobDept> mList) {
		this.mList = mList;
	}
	
}
