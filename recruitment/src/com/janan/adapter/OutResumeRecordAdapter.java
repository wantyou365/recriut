package com.janan.adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.OutResumeRecord;
import com.janan.data.bean.personal.CoverLetter;
import com.janan.recruit.BaseActivity;

public class OutResumeRecordAdapter extends BaseAdapter{
	private ArrayList<OutResumeRecord> mList = new ArrayList<OutResumeRecord>();
	private BaseActivity mActivity;
	
	public OutResumeRecordAdapter(BaseActivity activity){
		this.mActivity = activity;
	}
	
	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		
		return false;
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
		convertView = mActivity.getLayoutInflater().inflate(R.layout.sendresumeoutrecordsitemview, null);
		TextView titleView = (TextView) convertView.findViewById(R.id.title);
		titleView.setText("职位名称:"+mList.get(position).getmZhiweimc());
		TextView nameTextView = (TextView) convertView
				.findViewById(R.id.text1_coveritem);
		nameTextView.setText("公司名称:"+mList.get(position).getmQiyemc());
		TextView dateTextView = (TextView) convertView
				.findViewById(R.id.text2_coveritem);
		dateTextView.setText("发送时间:"+mList.get(position).getmPotime().substring(0, 10).replace(" ", "-"));
		TextView contentTextView = (TextView) convertView
				.findViewById(R.id.content);
		contentTextView.setText("公司邮箱:"+mList.get(position).getmQiyeyx());
		return convertView;
//		return mList.get(position).getItemView(mActivity);
	}

	public ArrayList<OutResumeRecord> getmList() {
		return mList;
	}

	public void setmList(ArrayList<OutResumeRecord> mList) {
		this.mList = mList;
	}

}
