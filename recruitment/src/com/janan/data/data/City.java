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
import com.janan.util.activity.DistrictActivity;

public class City implements Serializable {
	private String mId;
	private String mPid;
	private String mName;
	private boolean hasMore = false;

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPid() {
		return mPid;
	}

	public void setmPid(String mPid) {
		this.mPid = mPid;
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

	public LinearLayout getImteView(BaseActivity activity,Bundle bundle,boolean hasArea) {
		LinearLayout _Lay;
		if(hasArea){
			_Lay = (LinearLayout) activity.getLayoutInflater()
					.inflate(R.layout.provinceitem, null);
		}else{
			_Lay = (LinearLayout) activity.getLayoutInflater()
					.inflate(R.layout.provincenocityitem, null);
		}
		
		TextView mTextView = (TextView) _Lay.findViewById(R.id.textinfo);
		final BaseActivity _Activity = activity;
		final Bundle _Bundle = bundle;
		mTextView.setText(getmName());
		_Lay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				_Bundle.putString(_Activity.TAG_CITY, getmName());
				_Bundle.putString(_Activity.TAG_CITYID, getmId());
				_Intent.putExtra(_Activity.TAG_BUNDLE, _Bundle);
				if(_Bundle.getBoolean(_Activity.TAG_HASAREA, false)){
					_Intent.setClass(_Activity, DistrictActivity.class);
					_Activity.startActivityForResult(_Intent,
							_Activity.REQUEST_SEARCHCHEK);
				}else{
					_Bundle.putString(_Activity.TAG_VALUE, getmName());
					_Activity.setResult(_Activity.VALUE_RESULT_OK, _Intent);
					_Activity.finishSelf();
				}		
			}
		});
		return _Lay;
	}
//	public LinearLayout getItemViewNoMore(BaseActivity activity){
//		LinearLayout _Lay = (LinearLayout) activity.getLayoutInflater()
//				.inflate(R.layout.provincenocityitem, null);
//		TextView mTextView = (TextView) _Lay.findViewById(R.id.textinfo);
//		final BaseActivity _Activity = activity;
//		mTextView.setText(mName);
//		_Lay.setOnClickListener(new OnClickListener() {
//			//
//			@Override
//			public void onClick(View v) {
//				Intent _Intent = new Intent();
//				_Intent.putExtra(_Activity.TAG_VALUE, mName);
//				_Activity.setResult(_Activity.VALUE_RESULT_OK, _Intent);
//				_Activity.finishSelf();
//			}
//		});
//		return _Lay;
//	}
}
