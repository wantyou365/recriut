package com.janan.view.mamager;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.search.NearSearch;
import com.janan.data.data.DataForSearchCondition;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.JobFairsListActivity;
import com.janan.recruit.searchactivity.SearchResultActivity;
import com.janan.recruit.searchactivity.ShiXiActivity;
import com.janan.resume.workdynactivity.WorkDynamicsActivity;

public class FastNavigationViewUtil implements OnClickListener {

	private BaseActivity mActivity;
	TextView _Zhaopinhui;
	TextView _Jipinzhiwei;
	TextView _Xingjizhaopin_5;
	TextView _Xingjizhaopin_4;
	TextView _Shixishengpaiqian;
	TextView _Mingquexinzi;
	TextView _Baochibaozhu;
	TextView _Jiudianzhiwei;
	TextView _Canyinzhiwei;
	TextView _Wenzhizhiwei;
	TextView _Shixishengzhiwei;
	TextView _Zixundongtai;

	public FastNavigationViewUtil(BaseActivity activity) {
		this.mActivity = activity;
	}

	public LinearLayout getFastNavigationView() {

		LinearLayout layout = (LinearLayout) mActivity.getLayoutInflater()
				.inflate(R.layout.fastnavigation, null);
		_Zhaopinhui = (TextView) layout.findViewById(R.id.zhaopinhui);
		_Zhaopinhui.setOnClickListener(this);
		_Jipinzhiwei = (TextView) layout.findViewById(R.id.jipinzhiwei);
		_Jipinzhiwei.setOnClickListener(this);
		_Xingjizhaopin_5 = (TextView) layout.findViewById(R.id.xingjizhaopin_5);
		_Xingjizhaopin_5.setOnClickListener(this);
		_Xingjizhaopin_4 = (TextView) layout.findViewById(R.id.xingjizhaopin_4);
		_Xingjizhaopin_4.setOnClickListener(this);
		_Shixishengpaiqian = (TextView) layout
				.findViewById(R.id.shixishengpaiqian);
		_Shixishengpaiqian.setOnClickListener(this);
		_Mingquexinzi = (TextView) layout.findViewById(R.id.mingquexinzi);
		_Mingquexinzi.setOnClickListener(this);
		_Baochibaozhu = (TextView) layout.findViewById(R.id.baochibaozhu);
		_Baochibaozhu.setOnClickListener(this);
		_Jiudianzhiwei = (TextView) layout.findViewById(R.id.jiudianzhiwei);
		_Jiudianzhiwei.setOnClickListener(this);
		_Canyinzhiwei = (TextView) layout.findViewById(R.id.canyinzhiwei);
		_Canyinzhiwei.setOnClickListener(this);
		_Wenzhizhiwei = (TextView) layout.findViewById(R.id.wenzhizhiwei);
		_Wenzhizhiwei.setOnClickListener(this);
		_Shixishengzhiwei = (TextView) layout
				.findViewById(R.id.shixishengzhiwei);
		_Shixishengzhiwei.setOnClickListener(this);
		_Zixundongtai = (TextView) layout.findViewById(R.id.zixundongtai);
		_Zixundongtai.setOnClickListener(this);

		return layout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		if (id == _Zhaopinhui.getId()) {
			Intent _Intent = new Intent();
			_Intent.setClass(mActivity, JobFairsListActivity.class);
			mActivity.startActivity(_Intent);
		} else if (id == _Jipinzhiwei.getId()) {
			Intent _Intent = new Intent();
			NearSearch _Search = new NearSearch();
			_Search.setmJipin("1");
			_Intent.putExtra("searchcondition", _Search);
			_Intent.setClass(mActivity, SearchResultActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.jipinzhiwei));
			mActivity.startActivity(_Intent);
		} else if (id == _Xingjizhaopin_5.getId()) {
			Intent _Intent = new Intent();
			NearSearch _Search = new NearSearch();
			_Search.setmStarLevel(DataForSearchCondition.mXingjiStrings[0]);
			_Intent.setClass(mActivity, SearchResultActivity.class);
			_Intent.putExtra("searchcondition", _Search);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.wuxingjizhaopin));
			mActivity.startActivity(_Intent);
		} else if (id == _Xingjizhaopin_4.getId()) {
			Intent _Intent = new Intent();
			NearSearch _Search = new NearSearch();
			_Search.setmStarLevel(DataForSearchCondition.mXingjiStrings[2]);
			_Intent.putExtra("searchcondition", _Search);
			_Intent.setClass(mActivity, SearchResultActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.sixingjizhaopin));
			mActivity.startActivity(_Intent);  
		} else if (id == _Shixishengpaiqian.getId()) {
			Intent _Intent = new Intent();
//			NearSearch _Search = new NearSearch();
//			_Search.setmJobType("2");
//			_Intent.putExtra("searchcondition", _Search);
			_Intent.setClass(mActivity, ShiXiActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.shixishengpaiqian));
			mActivity.startActivity(_Intent);
		} else if (id == _Mingquexinzi.getId()) {
			Intent _Intent = new Intent();
			NearSearch _Search = new NearSearch();
			_Search.setmDealString("0");
			_Intent.putExtra("searchcondition", _Search);
			_Intent.setClass(mActivity, SearchResultActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.mingquexinzi));
			mActivity.startActivity(_Intent);
		} else if (id == _Baochibaozhu.getId()) {
			Intent _Intent = new Intent();
			NearSearch _Search = new NearSearch();
			_Search.setmCzqk("1");
			_Intent.putExtra("searchcondition", _Search);
			_Intent.setClass(mActivity, SearchResultActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.baochibaozhu));
			mActivity.startActivity(_Intent);
		} else if (id == _Jiudianzhiwei.getId()) {
			Intent _Intent = new Intent();
			NearSearch _Search = new NearSearch();
			_Search.setmIndustry("1");
			_Intent.putExtra("searchcondition", _Search);
			_Intent.setClass(mActivity, SearchResultActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.jiudianzhiwei));
			mActivity.startActivity(_Intent);
		} else if (id == _Canyinzhiwei.getId()) {
			Intent _Intent = new Intent();
			NearSearch _Search = new NearSearch();
			_Search.setmIndustry("2");
			_Intent.putExtra("searchcondition", _Search);
			_Intent.setClass(mActivity, SearchResultActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.canyinzhiwei));
			mActivity.startActivity(_Intent);
		} else if (id == _Wenzhizhiwei.getId()) {
			Intent _Intent = new Intent();
			NearSearch _Search = new NearSearch();
			_Search.setmInentJob(mActivity.getString(R.string.wenzhi));
			_Intent.putExtra("searchcondition", _Search);
			_Intent.setClass(mActivity, SearchResultActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.wenzhizhiwei));
			mActivity.startActivity(_Intent);
		} else if (id == _Shixishengzhiwei.getId()) {
			Intent _Intent = new Intent();
			NearSearch _Search = new NearSearch();
			_Search.setmInentJob(mActivity.getString(R.string.shixisheng));
			_Intent.putExtra("searchcondition", _Search);
			_Intent.setClass(mActivity, SearchResultActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.shixishengzhiwei));
			mActivity.startActivity(_Intent);
		} else if (id == _Zixundongtai.getId()) {

			Intent _Intent = new Intent();
			_Intent.setClass(mActivity, WorkDynamicsActivity.class);
			_Intent.putExtra(mActivity.STRING_TITLE, mActivity.getString(R.string.zixuandongtai));
			_Intent.putExtra(mActivity.TAG_FROMFAST, true);
			mActivity.startActivity(_Intent);
		}
	}
}
