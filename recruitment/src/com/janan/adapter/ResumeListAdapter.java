package com.janan.adapter;

import java.util.ArrayList;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.JobInfo;
import com.janan.data.bean.personal.Resume;
import com.janan.recruit.BaseActivity;
import com.janan.view.mamager.SubScribeJobManager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ResumeListAdapter extends BaseAdapter {
	private ArrayList<Resume> mList = new ArrayList<Resume>();
	private BaseActivity mActivity;

	public ResumeListAdapter(BaseActivity activity) {
		this.mActivity = activity;
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
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = mActivity.getLayoutInflater()
					.inflate(R.layout.searchresumeitemview, null);					
		}
		mList.get(position).getSearchResumeView(mActivity,convertView);
		return convertView;
	}

	public ArrayList<Resume> getmList() {
		return mList;
	}

	public void setmList(ArrayList<Resume> mList) {
		this.mList = mList;
	}

}
