package com.janan.data.data;

import java.io.Serializable;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.recruit.BaseActivity;

public class JobName implements Serializable {
	private String mId;
	private String mJPid;
	private String mName;
	private boolean hasMore = false;

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPid() {
		return mJPid;
	}

	public void setmPid(String mJPid) {
		this.mJPid = mJPid;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}

	public LinearLayout getImteView(BaseActivity activity,Bundle bundle) {
		LinearLayout _Lay = (LinearLayout) activity.getLayoutInflater()
				.inflate(R.layout.cityitem, null);
		TextView mTextView = (TextView) _Lay.findViewById(R.id.textinfo);
		final BaseActivity _Activity = activity;
		final Bundle _Bundle = bundle;
		mTextView.setText(getmName());
		_Lay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				_Bundle.putString(_Activity.TAG_JOBNAME, getmName());
				_Bundle.putString(_Activity.TAG_JOBNAMEID, getmId());
				_Bundle.putString(_Activity.TAG_VALUE, getmName());
				_Intent.putExtra(_Activity.TAG_BUNDLE, _Bundle);
				_Activity.setResult(_Activity.VALUE_RESULT_OK, _Intent);
				_Activity.finishSelf();
			}
		});
		return _Lay;
	}

}
