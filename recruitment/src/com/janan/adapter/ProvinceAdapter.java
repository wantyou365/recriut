package com.janan.adapter;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.janan.data.data.Province;
import com.janan.recruit.BaseActivity;
import com.janan.util.DBHelper;
import com.janan.util.activity.CityActivity;

public class ProvinceAdapter extends BaseAdapter {
	private ArrayList<Province> mList = new ArrayList<Province>();
	private BaseActivity mActivity;
	private boolean hasCity;
	private Bundle mBundle;

	public ProvinceAdapter(BaseActivity activity, Bundle bundle) {
		this.mActivity = activity;
		this.mBundle = bundle;
		this.hasCity = mBundle.getBoolean(mActivity.TAG_HASCITY, false);
		// getProvinceFromDB();
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
		final Province _Province = mList.get(position);
		convertView = _Province.getItemView(mActivity, mBundle, hasCity);
		return convertView;
	}

	public ArrayList<Province> getProvinces() {
		return mList;
	}

	public ArrayList<Province> getmList() {
		return mList;
	}

	public void setmList(ArrayList<Province> mList) {
		this.mList = mList;
	}

}
