package com.janan.adapter;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.janan.data.data.District;
import com.janan.recruit.BaseActivity;

public class DistrictAdapter extends BaseAdapter{
	private ArrayList<District> mList;
	private BaseActivity mActivity;
	private Bundle mBundle ;
	public DistrictAdapter(BaseActivity activity,Bundle bundle){
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
		final District _District = mList.get(position);
		convertView = _District.getImteView(mActivity,mBundle);

		return convertView;
	}
	
	
	public ArrayList<District> getCities(){
		return mList;
	}
	public ArrayList<District> getmList() {
		return mList;
	}
	public void setmList(ArrayList<District> mList) {
		this.mList = mList;
	}
	
	
}
