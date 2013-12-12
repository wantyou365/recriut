package com.janan.view.mamager;

import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.companyactivity.JobsActivity;

public class NearSearchManager {
	private LinearLayout mRootLayLayout = null;
	private LinearLayout mNearsLayout = null;
	private BaseActivity mActivity = null;

	public NearSearchManager(BaseActivity activity) {
		this.mActivity = activity;
		initLay();
	}

	private void initLay() {
		mRootLayLayout = (LinearLayout) mActivity.getLayoutInflater().inflate(
				R.layout.nearsearchview, null);
		mNearsLayout = (LinearLayout) mRootLayLayout
				.findViewById(R.id.nearsearchLay);
		TextView _TextView1 = (TextView) mNearsLayout.findViewById(R.id.text1);
		TextView _TextView2 = (TextView) mNearsLayout.findViewById(R.id.text2);
		_TextView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				_Intent.setClass(mActivity, JobsActivity.class);
				_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getResources()
						.getString(R.string.jobinfotitle));
				mActivity.startActivity(_Intent);
			}
		});
		_TextView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				_Intent.setClass(mActivity, JobsActivity.class);
				_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getResources()
						.getString(R.string.jobinfotitle));
				mActivity.startActivity(_Intent);
			}
		});
	}

	private void createNearSearchLay() {

	}

	public LinearLayout getNearSearchLay() {
		return mRootLayLayout;
	}
}
