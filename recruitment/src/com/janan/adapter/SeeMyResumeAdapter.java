package com.janan.adapter;

import java.util.ArrayList;

import com.janan.data.bean.personal.CoverLetter;
import com.janan.recruit.BaseActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SeeMyResumeAdapter extends BaseAdapter{
	private ArrayList<CoverLetter> mList = new ArrayList<CoverLetter>();
	private BaseActivity mActivity;
	
	public SeeMyResumeAdapter(BaseActivity activity){
		this.mActivity = activity;
	}
	
//	@Override
//	public boolean isEnabled(int position) {
//		// TODO Auto-generated method stub
//		if(position<getCount()){
//			return false;
//		}
//		return true;
//	}

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
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		return mList.get(position).getItemView(mActivity);
	}

	public ArrayList<CoverLetter> getmList() {
		return mList;
	}

	public void setmList(ArrayList<CoverLetter> mList) {
		this.mList = mList;
	}

}
