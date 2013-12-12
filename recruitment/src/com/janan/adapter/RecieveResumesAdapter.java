package com.janan.adapter;

import java.util.ArrayList;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.personal.Resume;
import com.janan.recruit.BaseActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class RecieveResumesAdapter extends BaseAdapter{
	
	private ArrayList<Resume> mList = new ArrayList<Resume>();
	private BaseActivity mActivity;
	private boolean canChecked;
	public RecieveResumesAdapter(BaseActivity activity,boolean cancheck){
		mActivity = activity;
		canChecked = cancheck;
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
		if(convertView == null){
//			convertView = mList.get(position).getRecieveResumeView(mActivity,canChecked);
			convertView = (LinearLayout) mActivity.getLayoutInflater()
					.inflate(R.layout.recieveresumeitemview, null);
		}
		convertView = mList.get(position).getRecieveResumeView(mActivity, canChecked, convertView);
		return convertView;
	}

	public ArrayList<Resume> getmList() {
		return mList;
	}

	public void setmList(ArrayList<Resume> mList) {
		this.mList = mList;
	}

	
}
