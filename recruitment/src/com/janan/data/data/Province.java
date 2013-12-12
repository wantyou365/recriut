package com.janan.data.data;

import java.io.Serializable;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.recruit.ActivityStatus;
import com.janan.recruit.BaseActivity;
import com.janan.util.activity.CityActivity;

public class Province implements Serializable {
	private String mName;
	private String mId;

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	// public LinearLayout getItemViewNoMore(BaseActivity activity,Bundle
	// bundle){
	// LinearLayout _Lay = (LinearLayout) activity.getLayoutInflater()
	// .inflate(R.layout.provincenocityitem, null);
	// TextView mTextView = (TextView) _Lay.findViewById(R.id.textinfo);
	// final BaseActivity _Activity = activity;
	// final Bundle _Bundle = bundle;
	// mTextView.setText(mName);
	// _Lay.setOnClickListener(new OnClickListener() {
	// //
	// @Override
	// public void onClick(View v) {
	//
	// Intent _Intent = new Intent();
	// _Bundle.putInt(_Activity.TAG_SEARCHKEY, _Activity.mTag);
	// _Bundle.putString(_Activity.TAG_VALUE, mName);
	// _Bundle.putString(_Activity.TAG_PROVINCE, mName);
	// _Intent.putExtra(_Activity.TAG_BUNDLE, _Bundle);
	// _Activity.setResult(_Activity.VALUE_RESULT_OK, _Intent);
	// _Activity.finishSelf();
	// }
	// });
	// return _Lay;
	// }
	public LinearLayout getItemView(BaseActivity activity, Bundle bundle,
			boolean hasCity) {
		LinearLayout _Lay;
		if (hasCity) {
			_Lay = (LinearLayout) activity.getLayoutInflater().inflate(
					R.layout.provinceitem, null);
		} else {
			_Lay = (LinearLayout) activity.getLayoutInflater().inflate(
					R.layout.provincenocityitem, null);
		}

		TextView mTextView = (TextView) _Lay.findViewById(R.id.textinfo);
		final BaseActivity _Activity = activity;
		final Bundle _Bundle = bundle;
		mTextView.setText(getmName());
		_Lay.setOnClickListener(new OnClickListener() {
			//
			@Override
			public void onClick(View v) {

				Intent _Intent = new Intent();
				_Bundle.putString(_Activity.TAG_PROVINCE, getmName());
				_Bundle.putString(_Activity.TAG_PROVINCEID, getmId());
				_Intent.putExtra(_Activity.TAG_BUNDLE, _Bundle);
				if (_Bundle.getBoolean(_Activity.TAG_HASCITY, false)) {
					_Intent.setClass(_Activity, CityActivity.class);
					_Activity.startActivityForResult(_Intent,
							_Activity.REQUEST_SEARCHCHEK);
				} else {
					_Bundle.putString(_Activity.TAG_VALUE, getmName());
					_Activity.setResult(_Activity.VALUE_RESULT_OK, _Intent);
					_Activity.finishSelf();
				}

			}
		});
		return _Lay;
	}
}
