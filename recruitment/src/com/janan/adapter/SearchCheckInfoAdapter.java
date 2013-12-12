package com.janan.adapter;

import java.util.ArrayList;




import com.gazecloud.dyteam.R;
import com.janan.recruit.BaseActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchCheckInfoAdapter extends BaseAdapter{

	private ArrayList<String> mList;
	private BaseActivity mActivity;
	private int tag;
	public SearchCheckInfoAdapter(BaseActivity activity){
		this.mActivity = activity;
	}
	
	public ArrayList<String> getmList() {
		return mList;
	}

	public void setmList(ArrayList<String> mList) {
		this.mList = mList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		arg1 = mActivity.getLayoutInflater().inflate(R.layout.cityitem, null);
		TextView mTextView = (TextView)arg1.findViewById(R.id.textinfo);
		mTextView.setText(mList.get(arg0));
//		mTextView.setTextColor(Color.BLUE);
		return arg1;
	}

}
