package com.janan.recruit.personalactivity;

import java.util.ArrayList;


import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.adapter.SeeMyResumeAdapter;
import com.janan.data.bean.personal.CoverLetter;
import com.janan.data.bean.personal.User;
import com.janan.net.CompanyEmailEngine;
import com.janan.recruit.BaseActivity;

import com.janan.view.mamager.TitleBarManager;

public class SeeMyResumeCompanyActivity extends BaseActivity{
	private LinearLayout mRootLayout;
	private TitleBarManager mBarUtil;
	@Override
	public void addBar() {
		// TODO Auto-generated method stub
		mBarUtil = new TitleBarManager(this);
		mRootLayout.addView(mBarUtil.createBar(mTitle, null, null), 0);

	}

	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		super.updateUI();
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
	}

	@Override
	public void finishSelf() {
		// TODO Auto-generated method stub
		super.finishSelf();
	}

	@Override
	public void showSelf() {
		// TODO Auto-generated method stub
		addBar();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mRootLayout = (LinearLayout) findViewById(R.id.rootlay_companyemailactivity);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.companyemailactivity);
		mTitle = getIntent().getStringExtra(STRING_TITLE);
		initView();
		showSelf();
	}
	
}
