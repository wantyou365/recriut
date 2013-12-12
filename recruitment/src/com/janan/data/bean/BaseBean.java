package com.janan.data.bean;

import android.R.integer;

import android.os.Bundle;

import com.gazecloud.dyteam.R;
import com.janan.recruit.BaseActivity;


public class BaseBean implements java.io.Serializable {
	// 搜索条件tag
	public static final int TAG_CONDITION_YIXIANGZHIWEI = 0;
	public static final int TAG_CONDITION_YIXIANGDIDIAN = 1;
	public static final int TAG_CONDITION_FABURIQI = 2;
	public static final int TAG_CONDITION_GUANJIANCI = 3;
	public static final int TAG_CONDITION_GONGZUONIANXIAN = 4;
	public static final int TAG_CONDITION_XINGBIEYAOQIU = 5;
	public static final int TAG_CONDITION_NIANLING = 6;
	public static final int TAG_CONDITION_DAOGANGSHIJIAN = 7;
	public static final int TAG_CONDITION_XUELIYAOQIU = 8;
	public static final int TAG_CONDITION_HANGYE = 9;
	public static final int TAG_CONDITION_XINGJI = 10;
	public static final int TAG_CONDITION_QIWANGYUEXIN = 11;
	public static final int TAG_CONDITION_JIUDIANLEIXING = 33;
	public static final int TAG_CONDITION_ZHAOPINDUIXIANG = 34;
	public static final int TAG_CONDITION_ZHAOPINLEIBIE = 35;
	public static final int TAG_CONDITION_BAOCHIZHU = 36;
	public static final int TAG_CONDITION_GONGZUOSHIJIAN = 37;
	// 个人信息tag
	public static final int TAG_LIULANQIYE = 12;
	public static final int TAG_QIYELAIXIN = 13;
	public static final int TAG_QIUZHIJILU = 14;
	public static final int TAG_ZHIWEISHOUCANG = 15;
	public static final int TAG_XIUGAIWEIJIANLI = 16;
	public static final int TAG_YULANJIANLI = 17;
	public static final int TAG_SHUAXINJIANLI = 18;
	public static final int TAG_WAIFAJIANLI = 19;
	public static final int TAG_WAIFAJIANLIJILU = 20;
	public static final int TAG_XIUGAIMIMA = 21;
	public static final int TAG_SHOUCANGGUANLI = 22;
	
	
	public static final int TAG_HUKOUSUOZAIDI = 23;
	public static final int TAG_JUZHUDI = 24;
	public static final int TAG_JOBCLASS = 25;
	public static final int TAG_JOBNAME = 26;
	public static final int TAG_SALARY = 27;
	public static final String SplitString = "：";

	public static final String Tag_Obj = "object";

	public BaseBean() {

	}

	// 获得tag的代表的key值
	public String getTagKey(int tag, BaseActivity mActivity) {
		String _Str = null;
		switch (tag) {
		case TAG_CONDITION_DAOGANGSHIJIAN:
			_Str = mActivity.getResources().getString(R.string.daogangshijian);
			break;
		case TAG_CONDITION_FABURIQI:
			_Str = mActivity.getResources().getString(R.string.faburiqi);
			break;
		case TAG_CONDITION_GONGZUONIANXIAN:
			_Str = mActivity.getResources().getString(R.string.gongzuonianxian);
			break;
		case TAG_CONDITION_GUANJIANCI:
			_Str = mActivity.getResources().getString(R.string.guanjianci);
			break;
		case TAG_CONDITION_HANGYE:
			_Str = mActivity.getResources().getString(R.string.hangye);
			break;
		case TAG_CONDITION_NIANLING:
			_Str = mActivity.getResources().getString(R.string.nianling);
			break;
		case TAG_CONDITION_QIWANGYUEXIN:
			_Str = mActivity.getResources().getString(R.string.qiwangyuexin);
			break;
		case TAG_CONDITION_XINGBIEYAOQIU:
			_Str = mActivity.getResources().getString(R.string.xingbieyaoqiu);
			break;
		case TAG_CONDITION_XINGJI:
			_Str = mActivity.getResources().getString(R.string.xingji);
			break;
		case TAG_CONDITION_XUELIYAOQIU:
			_Str = mActivity.getResources().getString(R.string.xueliyaoqiu);
			break;
		case TAG_CONDITION_YIXIANGDIDIAN:
			_Str = mActivity.getResources().getString(R.string.yixiangdidian);
			break;
		case TAG_CONDITION_YIXIANGZHIWEI:
			_Str = mActivity.getResources().getString(R.string.yixiangzhiwei);
			break;
		case TAG_LIULANQIYE:
			_Str = mActivity.getResources().getString(
					R.string.liulanwojianlideqiye);
			break;
		case TAG_QIUZHIJILU:
			_Str = mActivity.getResources().getString(R.string.qiuzhijilu);
			break;
		case TAG_QIYELAIXIN:
			_Str = mActivity.getResources().getString(R.string.qiyelaixin);
			break;
		case TAG_SHUAXINJIANLI:
			_Str = mActivity.getResources().getString(R.string.shuaxinjianli);
			break;
		case TAG_WAIFAJIANLI:
			_Str = mActivity.getResources().getString(R.string.waifajianli);
			break;
		case TAG_WAIFAJIANLIJILU:
			_Str = mActivity.getResources().getString(R.string.waifajianlijilu);
			break;
		case TAG_XIUGAIMIMA:
			_Str = mActivity.getResources().getString(R.string.xiugaimima);
			break;
		case TAG_XIUGAIWEIJIANLI:
			_Str = mActivity.getResources().getString(R.string.xiugaiweijianli);
			break;
		case TAG_YULANJIANLI:
			_Str = mActivity.getResources().getString(R.string.yulanjianli);
			break;
		case TAG_ZHIWEISHOUCANG:
			_Str = mActivity.getResources().getString(
					R.string.zhiweishoucangjia);
			break;
		default:
			break;
		}
		return _Str;
	}

	public void updateUI(Bundle bundle) {
		// TODO Auto-generated method stub

	}

	public String getStringTag(int tag) {
		return tag + "";
	}

	public String getDefoultValue(int tag, BaseActivity mActivity) {
		// TODO Auto-generated method stub
		String _Str = null;
		if (tag == TAG_CONDITION_YIXIANGZHIWEI
				|| tag == TAG_CONDITION_YIXIANGDIDIAN
				|| tag == TAG_CONDITION_DAOGANGSHIJIAN
				|| tag == TAG_CONDITION_GONGZUONIANXIAN
				|| tag == TAG_CONDITION_QIWANGYUEXIN
				|| tag == TAG_CONDITION_FABURIQI) {
//			_Str = mActivity.getResources().getString(R.string.buxian);
			_Str = mActivity.getString(R.string.select) + getTagKey(tag, mActivity);

		} else if (tag == TAG_CONDITION_DAOGANGSHIJIAN
				|| tag == TAG_CONDITION_XINGBIEYAOQIU
				|| tag == TAG_CONDITION_NIANLING
				|| tag == TAG_CONDITION_XUELIYAOQIU
				|| tag == TAG_CONDITION_HANGYE || tag == TAG_CONDITION_XINGJI) {
			_Str = mActivity.getString(R.string.select) + getTagKey(tag, mActivity);
		}
		return _Str;
	}
}
