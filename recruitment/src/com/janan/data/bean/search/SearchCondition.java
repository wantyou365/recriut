package com.janan.data.bean.search;

import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.BaseBean;
import com.janan.recruit.ActivityStatus;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.searchactivity.SearchCheckInfoActivity;
import com.janan.util.Util;
import com.janan.util.activity.JobDeptActivity;
import com.janan.util.activity.ProvinceActivity;

/**
 * 搜索条件
 * */
public class SearchCondition extends BaseBean {

	private String mKey;
	private String mValue;
	private TextView mValueTextView;
	private int mTag;
	private boolean isText;

	public SearchCondition(BaseActivity activity, int tag, String defaultValue,
			boolean istext) {
		this(activity, tag, istext);
		this.mValue = defaultValue;

	}

	public SearchCondition(BaseActivity activity, int tag, boolean istext) {

		this.mKey = getTagKey(tag, activity);
		this.mTag = tag;
		this.isText = istext;
	}

	public String getmKey() {
		return mKey;
	}

	public String getmValue() {
		return mValue;
	}

	public void setmValue(String mValue) {
		this.mValue = mValue;
	}

	public boolean isText() {
		return isText;
	}

	public LinearLayout getSearchItemView(BaseActivity mActivity) {
		if (isText) {
			return getSearchItemTextView(mActivity);
		} else {
			return getSearchItemEditView(mActivity);
		}
	}

	LinearLayout mTextLayout;

	// 两个text形式的搜索条目
	private LinearLayout getSearchItemTextView(BaseActivity mActivity) {
		if (mTextLayout != null) {
			return mTextLayout;
		}
		final BaseActivity _Activity = mActivity;
		mTextLayout = (LinearLayout) mActivity.getLayoutInflater().inflate(
				R.layout.searchkeyitem, null);
		TextView _KeyTextView = (TextView) mTextLayout.findViewById(R.id.key);
		_KeyTextView.setText(mKey);
		mValueTextView = (TextView) mTextLayout.findViewById(R.id.value);
		mValueTextView.setText(mValue);
		mValueTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				// _Intent.putExtra(_Activity.TAG_SEARCHKEY, mTag);
				_Intent.putExtra(_Activity.STRING_TITLE, mKey);
				Bundle _Bundle = new Bundle();
				_Bundle.putInt(_Activity.TAG_SEARCHKEY, mTag);
				if (mTag == TAG_CONDITION_YIXIANGZHIWEI) {
					_Bundle.putBoolean(_Activity.TAG_HASJOBNAME, true);
					_Intent.setClass(_Activity, JobDeptActivity.class);
				} else if (mTag == TAG_CONDITION_YIXIANGDIDIAN) {
					_Bundle.putBoolean(_Activity.TAG_HASAREA, true);
					_Bundle.putBoolean(_Activity.TAG_HASCITY, true);
					_Intent.setClass(_Activity, ProvinceActivity.class);
				} else if (mTag == TAG_CONDITION_HANGYE) {
					_Bundle.putBoolean(_Activity.TAG_HASJOBNAME, false);
					_Intent.setClass(_Activity, JobDeptActivity.class);
				} else {
					_Intent.setClass(_Activity, SearchCheckInfoActivity.class);
				}
				_Intent.putExtra(_Activity.TAG_BUNDLE, _Bundle);
				_Activity.startActivityForResult(_Intent,
						_Activity.REQUEST_SEARCHCHEK);
			}
		});
		return mTextLayout;
	}

	LinearLayout mEditLayout;

	// 带输入框的搜索条目
	private LinearLayout getSearchItemEditView(BaseActivity mActivity) {
		if (mEditLayout != null) {
			return mEditLayout;
		}
		mEditLayout = (LinearLayout) mActivity.getLayoutInflater().inflate(
				R.layout.searchkeyitem_edit, null);
		TextView keyTextView = (TextView) mEditLayout.findViewById(R.id.key);
		keyTextView.setText(mKey);
		EditText valueEditText = (EditText) mEditLayout
				.findViewById(R.id.value_edit);
		valueEditText.setText(mValue);
		valueEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				mValue = s.toString();
			}
		});
		return mEditLayout;
	}

	@Override
	public void updateUI(Bundle bundle) {
		// TODO Auto-generated method stub
		if (bundle != null) {
			if (mTag == TAG_CONDITION_YIXIANGDIDIAN) {
				setmValue(bundle.getString(BaseActivity.TAG_AREA));

			} else {
				setmValue(bundle.getString(ActivityStatus.TAG_VALUE));
			}

		} else {
			setmValue(null);
		}
		if (mValueTextView != null) {
			mValueTextView.setText(getmValue());
		}
	}

	public int getmTag() {
		return mTag;
	}

}
