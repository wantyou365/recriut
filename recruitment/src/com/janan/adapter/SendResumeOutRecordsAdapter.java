package com.janan.adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.janan.data.bean.personal.Resume;

public class SendResumeOutRecordsAdapter extends BaseAdapter{

	public ArrayList<Resume> mList = new ArrayList<Resume>();
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
		return null;
	}

	public ArrayList<Resume> getmList() {
		return mList;
	}

	public void setmList(ArrayList<Resume> mList) {
		this.mList = mList;
	}

	
}
