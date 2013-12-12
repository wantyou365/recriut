package com.janan.data.bean.personal;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.JobInfo;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.searchactivity.JobInfoActivity;

/**
 * 求职信
 * */
public class CoverLetter {
	private String mTitleString;//标题
	private String mContentString;//内容
	private String mUerId;
	private String JobId;
	private String mDate;//邀请时间
	private String mJobName;
	public String getmTitleString() {
		return mTitleString;
	}
	public void setmTitleString(String mTitleString) {
		this.mTitleString = mTitleString;
	}
	public String getmContentString() {
		return mContentString;
	}
	public void setmContentString(String mContentString) {
		this.mContentString = mContentString;
	}
	public String getmUerId() {
		return mUerId;
	}
	public void setmUerId(String mUerId) {
		this.mUerId = mUerId;
	}
	public String getJobId() {
		return JobId;
	}
	public void setJobId(String jobId) {
		JobId = jobId;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	
	public String getmJobName() {
		return mJobName;
	}
	public void setmJobName(String mJobName) {
		this.mJobName = mJobName;
	}
	public View getItemView(final BaseActivity activity){
		LinearLayout _ItemLayout = (LinearLayout)activity.getLayoutInflater()
				.inflate(R.layout.coveritemview, null);
		_ItemLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				_Intent.setClass(activity, JobInfoActivity.class);
				_Intent.putExtra(activity.STRING_TITLE, activity.getResources()
						.getString(R.string.jobinfotitle));
				JobInfo _Info = new JobInfo();
//				JobInfo _JobInfo = mList.get(arg2);
				_Info.setmJobId(getJobId());
				_Intent.putExtra("job", _Info);
				activity.startActivity(_Intent);
			}
		});
		TextView _TitleText = (TextView)_ItemLayout.findViewById(R.id.title);
		TextView _jobNameText = (TextView)_ItemLayout.findViewById(R.id.text1_coveritem);
		TextView _DateTextView = (TextView)_ItemLayout.findViewById(R.id.text2_coveritem);
		TextView _ContentTextView = (TextView)_ItemLayout.findViewById(R.id.content);
		
		_TitleText.setText(getmTitleString());
		_jobNameText.setText(getmJobName());
		_DateTextView.setText(getmDate().substring(0,10));
		if(getmContentString()!=null){
			_ContentTextView.setText(getmContentString());
		}else{
			_ContentTextView.setVisibility(View.GONE);
		}
		
		return _ItemLayout;
	}
	
	
	
}
