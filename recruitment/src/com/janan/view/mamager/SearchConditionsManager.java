package com.janan.view.mamager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gazecloud.dyteam.R;
import com.janan.data.bean.search.NearSearch;
import com.janan.data.bean.search.SearchCondition;
import com.janan.recruit.BaseActivity;
import com.janan.recruit.companyactivity.SearchResumeListActivity;
import com.janan.recruit.searchactivity.SearchResultActivity;
import com.janan.util.Util;

public class SearchConditionsManager implements ViewManageStatus {
	private BaseActivity mActivity;
	private LinearLayout mLayout;
	private HashMap<Integer, SearchCondition> quickMap = null;// 快速搜索值
	private HashMap<Integer, SearchCondition> advancedMap = null;// 高级搜索值
	private HashMap<Integer, SearchCondition> searchResumeMap = null;// 职位搜索
	private Button mButton = null;// 搜索按钮
	private boolean hasButton = false;// 是否有搜索按钮
	private boolean isAdvanced = false;
	public static final int mSubscribe = 1;
	public static final int mSearch = 2;
	public static final int mSearchResume = 3;
	public static final int quickSize = 4;
	public static final int advancedSize = 12;
	public int mTag = 0;
	public int page = 1;

	private HashMap<Integer, String> mConditionValue = new HashMap<Integer, String>();

	public SearchConditionsManager(BaseActivity activity, boolean hasButton,
			boolean isAdvanced, int tag) {
		this(activity);
		this.hasButton = hasButton;
		this.isAdvanced = isAdvanced;
		this.mTag = tag;

	}

	public SearchConditionsManager(BaseActivity activity) {
		this.mActivity = activity;
	}

	private void initLayout() {
		if (mLayout == null) {
			mLayout = (LinearLayout) mActivity.getLayoutInflater().inflate(
					R.layout.searchkeysview, null);
		}

	}

	private void checkLayout() {
		initLayout();
		if (mLayout.getChildCount() > 0) {
			mLayout.removeAllViews();
		}
	}

	private void checkMap() {
		if (quickMap == null) {
			quickMap = new HashMap<Integer, SearchCondition>();
		}
		if (advancedMap == null) {
			advancedMap = new HashMap<Integer, SearchCondition>();
		}
		if (searchResumeMap == null) {
			searchResumeMap = new HashMap<Integer, SearchCondition>();
		}
	}

	public String getDate(int qiri) {
		Calendar mCalendar = Calendar.getInstance();
		int day = mCalendar.get(Calendar.DAY_OF_YEAR);
		mCalendar.set(Calendar.DAY_OF_YEAR, day - qiri);
		SimpleDateFormat _Format = new SimpleDateFormat("yyyy\\MM\\dd");
		return _Format.format(mCalendar.getTime());
	}

	NearSearch _Search;

	private void setSearchJobButtonLis() {
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				// if(_Search == null){
				_Search = new NearSearch();
				// }

				_Search.setmInentJob(quickMap.get(
						SearchCondition.TAG_CONDITION_YIXIANGZHIWEI)
						.getmValue());
				_Search.setmIntentPlace(quickMap.get(
						SearchCondition.TAG_CONDITION_YIXIANGDIDIAN)
						.getmValue());
				_Search.setmPostDate(quickMap.get(
						SearchCondition.TAG_CONDITION_FABURIQI).getmValue());

				_Search.setmKeyWord(quickMap.get(
						SearchCondition.TAG_CONDITION_GUANJIANCI).getmValue());

				if (Util.checkString(_Search.getmInentJob())
						&& Util.checkString(_Search.getmIntentPlace())
				) {
					if (advancedMap.size() > 4) {
						_Search.setmWorkingYears(advancedMap.get(
								SearchCondition.TAG_CONDITION_GONGZUONIANXIAN)
								.getmValue());

						_Search.setmSex(advancedMap.get(
								SearchCondition.TAG_CONDITION_XINGBIEYAOQIU)
								.getmValue());

						_Search.setmAge(advancedMap.get(
								SearchCondition.TAG_CONDITION_NIANLING)
								.getmValue());
						_Search.setmDutyTime(null);

						_Search.setmEducation(advancedMap.get(
								SearchCondition.TAG_CONDITION_XUELIYAOQIU)
								.getmValue());

						_Search.setmBusiness(advancedMap.get(
								SearchCondition.TAG_CONDITION_HANGYE)
								.getmValue());

						_Search.setmStarLevel(advancedMap.get(
								SearchCondition.TAG_CONDITION_XINGJI)
								.getmValue());

						_Search.setmSalary(advancedMap.get(
								SearchCondition.TAG_CONDITION_QIWANGYUEXIN)
								.getmValue());
					}
					if (mTag == mSearch) {

						_Intent.putExtra(mActivity.TAG_SEARCHCONDITIONKEY,
								_Search);
						_Intent.putExtra(mActivity.STRING_TITLE,
								_Search.getmInentJob());
						_Intent.setClass(mActivity, SearchResultActivity.class);
						mActivity.startActivity(_Intent);
					} else if (mTag == mSubscribe) {

						_Intent.putExtra(mActivity.TAG_SEARCHCONDITIONKEY,
								_Search);
						mActivity.setResult(mActivity.RESULT_OK, _Intent);
						mActivity.finishSelf();
					}

				} else {
					String _TextString = mActivity.getString(R.string.select)
							+ getConditionById(
									SearchCondition.TAG_CONDITION_YIXIANGZHIWEI)
									.getmKey()
							+ ","
							+ getConditionById(
									SearchCondition.TAG_CONDITION_YIXIANGDIDIAN)
									.getmKey()
							+ mActivity.getString(R.string.aftertosearch);
					Toast.makeText(mActivity, _TextString, Toast.LENGTH_SHORT)
							.show();

				}

			}
		});
	}

	private void setSearchResumeButtonLis() {
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent();
				NearSearch _Search = new NearSearch();
				_Search.setmInentJob(searchResumeMap.get(
						SearchCondition.TAG_CONDITION_YIXIANGZHIWEI)
						.getmValue());
				_Search.setmIntentPlace(searchResumeMap.get(
						SearchCondition.TAG_CONDITION_YIXIANGDIDIAN)
						.getmValue());
				_Search.setmWorkingYears(searchResumeMap.get(
						SearchCondition.TAG_CONDITION_GONGZUONIANXIAN)
						.getmValue());
				_Search.setmSex(searchResumeMap.get(
						SearchCondition.TAG_CONDITION_XINGBIEYAOQIU)
						.getmValue());
				_Search.setmAge(searchResumeMap.get(
						SearchCondition.TAG_CONDITION_NIANLING).getmValue());
				_Search.setmBusiness(searchResumeMap.get(
						SearchCondition.TAG_CONDITION_HANGYE).getmValue());
				_Intent.putExtra(mActivity.TAG_SEARCHCONDITIONKEY, _Search);
				_Intent.setClass(mActivity, SearchResumeListActivity.class);
				mActivity.startActivity(_Intent);
				mActivity.finishSelf();
			}
		});

	}

	private void addSearchButton() {
		if (mButton == null) {
			mButton = new Button(mActivity);
			if (mTag == mSearch) {
				mButton.setBackgroundDrawable(mActivity.getResources()
						.getDrawable(R.drawable.home_btn_search_2));
				setSearchJobButtonLis();
			} else if (mTag == mSubscribe) {
				mButton.setBackgroundDrawable(mActivity.getResources()
						.getDrawable(R.drawable.ic_dy_1));
				setSearchJobButtonLis();
			} else if (mTag == mSearchResume) {
				mButton.setBackgroundDrawable(mActivity.getResources()
						.getDrawable(R.drawable.home_btn_search));
				setSearchResumeButtonLis();
			}
		}

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		mLayout.addView(mButton, lp);
	}

	private SearchCondition addSearchConditionToMap(
			HashMap<Integer, SearchCondition> map, int tag, boolean isText) {
		SearchCondition _Condetion = new SearchCondition(mActivity, tag, isText);
		if (isText) {
			_Condetion.setmValue(_Condetion.getDefoultValue(tag, mActivity));
		}
		map.put(tag, _Condetion);
		return _Condetion;
	}

	public void createResumeSearch() {
		checkLayout();
		checkMap();
		if (!searchResumeMap
				.containsKey(SearchCondition.TAG_CONDITION_YIXIANGZHIWEI)) {
			SearchCondition _Condition = addSearchConditionToMap(
					searchResumeMap,
					SearchCondition.TAG_CONDITION_YIXIANGZHIWEI, true);
		}
		if (!searchResumeMap
				.containsKey(SearchCondition.TAG_CONDITION_YIXIANGDIDIAN)) {
			SearchCondition _Condition = addSearchConditionToMap(
					searchResumeMap,
					SearchCondition.TAG_CONDITION_YIXIANGDIDIAN, true);
		}
		if (!searchResumeMap
				.containsKey(SearchCondition.TAG_CONDITION_GONGZUONIANXIAN)) {
			SearchCondition _Condition = addSearchConditionToMap(
					searchResumeMap,
					SearchCondition.TAG_CONDITION_GONGZUONIANXIAN, true);
		}
		if (!searchResumeMap
				.containsKey(SearchCondition.TAG_CONDITION_XINGBIEYAOQIU)) {
			SearchCondition _Condition = addSearchConditionToMap(
					searchResumeMap,
					SearchCondition.TAG_CONDITION_XINGBIEYAOQIU, true);
		}
		if (!searchResumeMap
				.containsKey(SearchCondition.TAG_CONDITION_NIANLING)) {
			SearchCondition _Condition = addSearchConditionToMap(
					searchResumeMap, SearchCondition.TAG_CONDITION_NIANLING,
					true);
		}
		if (!searchResumeMap.containsKey(SearchCondition.TAG_CONDITION_HANGYE)) {
			SearchCondition _Condition = addSearchConditionToMap(
					searchResumeMap, SearchCondition.TAG_CONDITION_HANGYE, true);
		}
		addView(searchResumeMap);

	}

	/**
	 * 创建快速搜索窗口
	 * */
	public void createQuickSearch() {
		checkLayout();
		checkMap();
		advancedMap.clear();
		if (!quickMap.containsKey(SearchCondition.TAG_CONDITION_YIXIANGZHIWEI)) {
			SearchCondition _Condition = addSearchConditionToMap(quickMap,
					SearchCondition.TAG_CONDITION_YIXIANGZHIWEI, true);
		}
		advancedMap.put(SearchCondition.TAG_CONDITION_YIXIANGZHIWEI,
				quickMap.get(SearchCondition.TAG_CONDITION_YIXIANGZHIWEI));
		if (!quickMap.containsKey(SearchCondition.TAG_CONDITION_YIXIANGDIDIAN)) {
			SearchCondition _Condition = addSearchConditionToMap(quickMap,
					SearchCondition.TAG_CONDITION_YIXIANGDIDIAN, true);
		}
		advancedMap.put(SearchCondition.TAG_CONDITION_YIXIANGDIDIAN,
				quickMap.get(SearchCondition.TAG_CONDITION_YIXIANGDIDIAN));
		if (!quickMap.containsKey(SearchCondition.TAG_CONDITION_FABURIQI)) {
			SearchCondition _Condition = addSearchConditionToMap(quickMap,
					SearchCondition.TAG_CONDITION_FABURIQI, true);
		}
		advancedMap.put(SearchCondition.TAG_CONDITION_FABURIQI,
				quickMap.get(SearchCondition.TAG_CONDITION_FABURIQI));
		if (!quickMap.containsKey(SearchCondition.TAG_CONDITION_GUANJIANCI)) {
			SearchCondition _Condition = addSearchConditionToMap(quickMap,
					SearchCondition.TAG_CONDITION_GUANJIANCI, false);
		}
		advancedMap.put(SearchCondition.TAG_CONDITION_GUANJIANCI,
				quickMap.get(SearchCondition.TAG_CONDITION_GUANJIANCI));
		if (!isAdvanced) {
			addView(quickMap);
		}

	}

	private void addView(HashMap<Integer, SearchCondition> map) {

		if (map.containsKey(SearchCondition.TAG_CONDITION_YIXIANGZHIWEI)) {
			mLayout.addView(map
					.get(SearchCondition.TAG_CONDITION_YIXIANGZHIWEI)
					.getSearchItemView(mActivity));
		}
		if (map.containsKey(SearchCondition.TAG_CONDITION_YIXIANGDIDIAN)) {
			mLayout.addView(map
					.get(SearchCondition.TAG_CONDITION_YIXIANGDIDIAN)
					.getSearchItemView(mActivity));
		}
		if (map.containsKey(SearchCondition.TAG_CONDITION_FABURIQI)) {
			mLayout.addView(map.get(SearchCondition.TAG_CONDITION_FABURIQI)
					.getSearchItemView(mActivity));
		}
		if (map.containsKey(SearchCondition.TAG_CONDITION_GUANJIANCI)) {
			mLayout.addView(map.get(SearchCondition.TAG_CONDITION_GUANJIANCI)
					.getSearchItemView(mActivity));
		}
		if (map.containsKey(SearchCondition.TAG_CONDITION_GONGZUONIANXIAN)) {
			mLayout.addView(map.get(
					SearchCondition.TAG_CONDITION_GONGZUONIANXIAN)
					.getSearchItemView(mActivity));
		}
		if (map.containsKey(SearchCondition.TAG_CONDITION_XINGBIEYAOQIU)) {
			mLayout.addView(map
					.get(SearchCondition.TAG_CONDITION_XINGBIEYAOQIU)
					.getSearchItemView(mActivity));
		}
		if (map.containsKey(SearchCondition.TAG_CONDITION_NIANLING)) {
			mLayout.addView(map.get(SearchCondition.TAG_CONDITION_NIANLING)
					.getSearchItemView(mActivity));
		}
		// if (map.containsKey(SearchCondition.TAG_CONDITION_DAOGANGSHIJIAN)) {
		// mLayout.addView(map.get(
		// SearchCondition.TAG_CONDITION_DAOGANGSHIJIAN)
		// .getSearchItemView(mActivity));
		// }
		if (map.containsKey(SearchCondition.TAG_CONDITION_XUELIYAOQIU)) {
			mLayout.addView(map.get(SearchCondition.TAG_CONDITION_XUELIYAOQIU)
					.getSearchItemView(mActivity));
		}
		if (map.containsKey(SearchCondition.TAG_CONDITION_HANGYE)) {
			mLayout.addView(map.get(SearchCondition.TAG_CONDITION_HANGYE)
					.getSearchItemView(mActivity));
		}
		if (map.containsKey(SearchCondition.TAG_CONDITION_XINGJI)) {
			mLayout.addView(map.get(SearchCondition.TAG_CONDITION_XINGJI)
					.getSearchItemView(mActivity));
		}
		if (map.containsKey(SearchCondition.TAG_CONDITION_QIWANGYUEXIN)) {
			mLayout.addView(map.get(SearchCondition.TAG_CONDITION_QIWANGYUEXIN)
					.getSearchItemView(mActivity));
		}

	}

	/**
	 * 创建高级搜索窗口
	 * */
	public void createAdvancedSearch() {
		checkLayout();
		checkMap();
		if (quickMap.size() < quickSize) {
			createQuickSearch();
		}
		if (!advancedMap
				.containsKey(SearchCondition.TAG_CONDITION_GONGZUONIANXIAN)) {
			addSearchConditionToMap(advancedMap,
					SearchCondition.TAG_CONDITION_GONGZUONIANXIAN, true);
		}
		if (!advancedMap
				.containsKey(SearchCondition.TAG_CONDITION_XINGBIEYAOQIU)) {
			addSearchConditionToMap(advancedMap,
					SearchCondition.TAG_CONDITION_XINGBIEYAOQIU, true);
		}
		if (!advancedMap.containsKey(SearchCondition.TAG_CONDITION_NIANLING)) {
			addSearchConditionToMap(advancedMap,
					SearchCondition.TAG_CONDITION_NIANLING, true);
		}
		// if (!advancedMap
		// .containsKey(SearchCondition.TAG_CONDITION_DAOGANGSHIJIAN)) {
		// addSearchConditionToMap(advancedMap,
		// SearchCondition.TAG_CONDITION_DAOGANGSHIJIAN, true);
		// }
		if (!advancedMap.containsKey(SearchCondition.TAG_CONDITION_XUELIYAOQIU)) {
			addSearchConditionToMap(advancedMap,
					SearchCondition.TAG_CONDITION_XUELIYAOQIU, true);
		}
		if (!advancedMap.containsKey(SearchCondition.TAG_CONDITION_HANGYE)) {
			addSearchConditionToMap(advancedMap,
					SearchCondition.TAG_CONDITION_HANGYE, true);
		}
		if (!advancedMap.containsKey(SearchCondition.TAG_CONDITION_XINGJI)) {
			addSearchConditionToMap(advancedMap,
					SearchCondition.TAG_CONDITION_XINGJI, true);
		}
		if (!advancedMap
				.containsKey(SearchCondition.TAG_CONDITION_QIWANGYUEXIN)) {
			addSearchConditionToMap(advancedMap,
					SearchCondition.TAG_CONDITION_QIWANGYUEXIN, true);
		}

		addView(advancedMap);
	}

	public LinearLayout getmLayout() {
		return mLayout;
	}

	@Override
	public void updateUI(Bundle bundle) {
		// TODO Auto-generated method stub
		int tag = bundle.getInt(mActivity.TAG_SEARCHKEY);
		getConditionById(tag).updateUI(bundle);
	}

	/**
	 * 初始化显示的搜索框
	 * */
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		checkLayout();
		if (isAdvanced) {
			createAdvancedSearch();
		} else {
			createQuickSearch();
		}
		if (hasButton) {
			addSearchButton();
		}
	}

	public void initSearchResumeView() {
		checkLayout();
		createResumeSearch();
		if (hasButton) {
			addSearchButton();
		}
	}

	public BaseActivity getmActivity() {
		return mActivity;
	}

	public void setmActivity(BaseActivity mActivity) {
		this.mActivity = mActivity;
	}

	public boolean isAdvanced() {
		return isAdvanced;
	}

	public void setAdvanced(boolean isAdvanced) {
		this.isAdvanced = isAdvanced;
	}

	public void setHasButton(boolean hasButton) {
		this.hasButton = hasButton;
	}

	public HashMap<Integer, SearchCondition> getQuickMap() {
		return quickMap;
	}

	public void setQuickMap(HashMap<Integer, SearchCondition> quickMap) {
		this.quickMap = quickMap;
	}

	public HashMap<Integer, SearchCondition> getAdvancedMap() {
		return advancedMap;
	}

	public void setAdvancedMap(HashMap<Integer, SearchCondition> advancedMap) {
		this.advancedMap = advancedMap;
	}

	public boolean isHasButton() {
		return hasButton;
	}

	public SearchCondition getConditionById(int tag) {
		if (quickMap.containsKey(tag)) {
			return quickMap.get(tag);
		} else if (advancedMap.containsKey(tag)) {
			return advancedMap.get(tag);
		} else {
			return searchResumeMap.get(tag);
		}
	}

	public void changeValueById(int tag, String value) {
		if (quickMap.containsKey(tag)) {
			quickMap.get(tag).setmValue(value);

		}
	}

	public int getmTag() {
		return mTag;
	}

	public void setmTag(int mTag) {
		this.mTag = mTag;
	}

}
