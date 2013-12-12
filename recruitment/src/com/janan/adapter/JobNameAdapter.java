package com.janan.adapter;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.janan.data.data.City;
import com.janan.data.data.JobName;
import com.janan.recruit.BaseActivity;
import com.janan.util.DBHelper;

public class JobNameAdapter extends BaseAdapter{
	private ArrayList<JobName> mList;
	private BaseActivity mActivity;
	private Bundle mBundle;
	public JobNameAdapter(BaseActivity activity,Bundle bundle){
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
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final JobName _JobName = mList.get(position);
		convertView = _JobName.getImteView(mActivity,mBundle);
		return convertView;
	}
	
	public ArrayList<JobName> getmList() {
		return mList;
	}
	public void setmList(ArrayList<JobName> mList) {
		this.mList = mList;
	}
	
	
}
