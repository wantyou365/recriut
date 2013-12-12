package com.janan.adapter;

import java.util.ArrayList;

import com.janan.data.bean.JobInfo;
import com.janan.recruit.BaseActivity;
import com.janan.view.mamager.SubScribeJobManager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PositionSubscribeAdapter extends BaseAdapter{
	private ArrayList<JobInfo> mList = new ArrayList<JobInfo>();
	private BaseActivity mActivity;
	private boolean canDelete = false;
	public PositionSubscribeAdapter(BaseActivity activity,boolean canDelete){
		this.mActivity = activity;
		this.canDelete = canDelete;
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
		if(canDelete){
			convertView = mList.get(position).getCollectionItemViewWithDelete(mActivity, this);
		}else{
			convertView = mList.get(position).getSubscribeItemView(mActivity);
		}
		
		return convertView;
	}

	public ArrayList<JobInfo> getmList() {
		return mList;
	}

	public void setmList(ArrayList<JobInfo> mList) {
		this.mList = mList;
	}
	
}
