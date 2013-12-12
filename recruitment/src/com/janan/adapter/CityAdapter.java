package com.janan.adapter;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.janan.data.data.City;
import com.janan.recruit.BaseActivity;
import com.janan.util.DBHelper;

public class CityAdapter extends BaseAdapter{
	private ArrayList<City> mList;
	private BaseActivity mActivity;
	private boolean hasArea;
	private Bundle mBundle;
	public CityAdapter(BaseActivity activity,Bundle bundle){
		this.mActivity = activity;
		this.mBundle = bundle;
		this.hasArea = bundle.getBoolean(activity.TAG_HASAREA, false);
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
		final City _City = mList.get(position);
		if(hasArea){
			
		}
		convertView = _City.getImteView(mActivity, mBundle, hasArea);
		return convertView;
	}
	
	public ArrayList<City> getCities(){
		return mList;
	}
	public ArrayList<City> getmList() {
		return mList;
	}
	public void setmList(ArrayList<City> mList) {
		this.mList = mList;
	}
	
	
}
