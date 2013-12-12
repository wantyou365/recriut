package com.janan.recruit.personalactivity;

import android.os.Bundle;

import com.janan.data.bean.personal.CoverLetter;
import com.janan.recruit.BaseActivity;
import com.janan.view.mamager.TitleBarManager;

public class ConverLetterActivity extends BaseActivity{

	private CoverLetter mLetter;
	private TitleBarManager mBarUtil;
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		super.addBar();
	}

	@Override
	public void finishSelf() {
		// TODO Auto-generated method stub
		super.finishSelf();
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		super.showSelf();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mLetter = (CoverLetter)getIntent().getSerializableExtra("letter");
	}

	
}
