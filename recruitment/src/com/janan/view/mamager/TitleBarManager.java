package com.janan.view.mamager;

import com.gazecloud.dyteam.R;

import android.app.Activity;

import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TitleBarManager {

	private LinearLayout mRootLayout;
	private ImageView mLeftButton;
	private ImageView mRightButton;
	private TextView mTitleTextView;
	private Activity mActivity;
	private boolean mLeftClick = false;
	private boolean mRightClick = false;

	public TitleBarManager(Activity activity) {
		this.mActivity = activity;
	}

	public LinearLayout createBar(String title, Drawable leftdraw,
			Drawable rightdraw) {
		mRootLayout = (LinearLayout) mActivity.getLayoutInflater().inflate(
				R.layout.titlebarview, null);
		mTitleTextView = (TextView) mRootLayout.findViewById(R.id.titletext);
		mTitleTextView.setText(title);
		if (!(leftdraw == null)) {
			addLeftButton(leftdraw);
		}
		if (rightdraw != null) {
			addRightButton(rightdraw);
		}
		return mRootLayout;
	}

	public void changeTitle(String title){
		if(mTitleTextView!=null){
			mTitleTextView.setText(title);
		}
	}
	private void addLeftButton(Drawable drawable) {
		mLeftButton = (ImageView) mRootLayout.findViewById(R.id.leftbutton);
		mLeftButton.setImageDrawable(drawable);
	}

	private void addRightButton(Drawable drawable) {
		mRightButton = (ImageView) mRootLayout.findViewById(R.id.rightbutton);
		mRightButton.setImageDrawable(drawable);

	}

	public boolean setLeftButtonListener(OnClickListener listener) {
		if (mLeftButton != null) {
			mLeftButton.setOnClickListener(listener);
			return true;
		} else {
			return false;
		}
	}

	public boolean setRightButtonListener(OnClickListener listener) {
		if (mRightButton != null) {
			mRightButton.setOnClickListener(listener);
			return true;
		} else {
			return false;
		}
	}

	public ImageView getmLeftButton() {
		return mLeftButton;
	}

	public ImageView getmRightButton() {
		return mRightButton;
	}

	public boolean ismLeftClick() {
		return mLeftClick;
	}

	public void setmLeftClick(boolean mLeftClick) {
		this.mLeftClick = mLeftClick;
	}

	public boolean ismRightClick() {
		return mRightClick;
	}

	public void setmRightClick(boolean mRightClick) {
		this.mRightClick = mRightClick;
	}
	
	public void setTitle(String text){
		mTitleTextView.setText(text);
	}
}
