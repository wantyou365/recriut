package com.janan.data.data;

import android.R.integer;

import com.janan.data.bean.BaseBean;
import com.janan.data.bean.search.SearchCondition;

public class DataForSearchCondition {
	
	public static String defaultAllString = "不限";
	
	public static String blankString = "";
	
	public static String[] mJobtypeStrings = {
		"全职","兼职 ","实习"
	};
	public static Integer[] mJobtypeInt = {
		1,3,2
	};
	public static String[] mZhaopiinleibieStrings = {"储备","急聘职位"};
	
	public static String[] mZhaopiinleibieStrings1 = {"cunbei","bestflag"};
	
	public static String getmZhaopiinleibie4send(String str){
		for(int i=0;i<mZhaopiinleibieStrings.length;i++){
			if(mZhaopiinleibieStrings[i].equals(str)){
				return mZhaopiinleibieStrings1[i];
			}
		}
		return "";
	}
	
	
	public static int getJobtypeInt(String type){
		for(int i=0;i<mJobtypeStrings.length;i++){
			if(type.equals(mJobtypeStrings[i])){
				return mJobtypeInt[i];
			}
		}
		return -1;
	}
	public static String getJobtypeStr(int type){
		for(int i=0;i<mJobtypeInt.length;i++){
			if(type==mJobtypeInt[i]){
				return mJobtypeStrings[i];
			}
		}
		return defaultAllString;
	}
	public static String[] mBaochizhuStrings = {
		"包吃住","包住宿","包工作餐","不包吃住"
	};
	public static Integer[] mBaochizhuInt = {
		1,2,3,4
	};
	
	public static int getBaochizhuInt(String str){
		for(int i=0;i<mBaochizhuStrings.length;i++){
			if(str.equals(mBaochizhuStrings[i])){
				return mBaochizhuInt[i];
			}
		}
		return -1;
	}
	
	public static String[] mGongzuoshijianStrings = {"做一休一","做五休二","做二休一","做六休一","其他"};
	
	public static Integer[] mGongzuoshijianints = {1,2,3,4,5};
	
	public static int getGongzuoshijianInt(String str){
		for(int i=0;i<mGongzuoshijianStrings.length;i++){
			if(mGongzuoshijianStrings[i].equals(str)){
				return mGongzuoshijianints[i];
			}
		}
		return -1;
	}
	public static String getBaochizhuStr(int poistion){
		for(int i=0;i<mBaochizhuInt.length;i++){
			if(poistion==mBaochizhuInt[i]){
				return mBaochizhuStrings[i];
			}
		}
		return defaultAllString;
	}
	public static String[] mYixiangzhiweiStrings = { defaultAllString, "酒店企业管理", "餐饮企业管理",
			"娱乐企业管理", "会所/SPA/温泉", "高尔夫", "旅行社", "物业", "行政/法务", "人力资源",
			"市场/销售/公关", "前厅/宾客服务", "预定/总机/客服呼叫中心", "客房", "餐饮/餐厅/宴会/管事",
			"酒吧/大堂吧/咖啡厅" };

	public static String[] mFaburiqiStrings = { defaultAllString, "今日最新", "近三天", "近五天",
			"近一周", "近两周", "近一月", "近两月" };
	public static Integer[] mFaburiqiInts = { -1, 0, 3, 5, 7, 14, 30, 60 };

	public static int getFaburiqiInt(String str) {
		for (int i = 0; i < mFaburiqiStrings.length; i++) {
			if (mFaburiqiStrings[i].equals(str)) {
				return mFaburiqiInts[i];
			}
		}
		return -1;
	}
	public static String getFaburiqiString(int data) {
		for (int i = 0; i < mFaburiqiInts.length; i++) {
			if (mFaburiqiInts[i]==data) {
				return mFaburiqiStrings[i];
			}
		}
		return defaultAllString;
	}

	public static String[] mGongzuonianxianStrings = { defaultAllString, "一年以上", "两年以上",
			"三年以上", "五年以上", "八年以上", "十年以上" };
	public static Integer[] mGongzuonianxianInts = { -1, 1, 2, 3, 5, 8, 10 };

	public static int getWorkingYear(String str) {
		for (int i = 0; i < mGongzuonianxianStrings.length; i++) {
			if (mGongzuonianxianStrings[i].equals(str)) {
				return mGongzuonianxianInts[i];
			}
		}
		return -1;
	}
	
	public static String getWorkingYearString(int data) {
		for (int i = 0; i < mGongzuonianxianInts.length; i++) {
			if (mGongzuonianxianInts[i] == data) {
				return mGongzuonianxianStrings[i];
			}
		}
		return defaultAllString;
	}

	public static String[] mXingbieyaoqiuStrings = { defaultAllString, "男", "女" };
	public static Integer[] mXingbieyaoqiuInts1 = { 0, 1, 2 };
	public static Integer[] mXingbieyaoqiuInts2 = { -1, 0, 1 };
	public static int getXingbie4Search(String str){
		for (int i = 0; i < mXingbieyaoqiuStrings.length; i++) {
			if (mXingbieyaoqiuStrings[i].equals(str)) {
				return mXingbieyaoqiuInts1[i];
			}
		}
		return -1;
	}
	public static String getXingbie4SearchString(int data){
		for (int i = 0; i < mXingbieyaoqiuInts1.length; i++) {
			if (mXingbieyaoqiuInts1[i] == data) {
				return mXingbieyaoqiuStrings[i];
			}
		}
		return defaultAllString;
	}
	public static int getXingbie4Search2(String str){
		for (int i = 0; i < mXingbieyaoqiuStrings.length; i++) {
			if (mXingbieyaoqiuStrings[i].equals(str)) {
				return mXingbieyaoqiuInts2[i];
			}
		}
		return -1;
	}
	public static String getXingbie4SearchString2(int data){
		for (int i = 0; i < mXingbieyaoqiuInts2.length; i++) {
			if (mXingbieyaoqiuInts2[i] == data) {
				return mXingbieyaoqiuStrings[i];
			}
		}
		return defaultAllString;
	}
	public static String[] mDaogangshijianStrings = { defaultAllString, "一周内", "半月内",
			"一月内", "两月内" };

	public static String[] mXueliyaoqiuStrings = { defaultAllString, "高中", "大专", "本科",
			"硕士", "博士" ,"博士后"};
	public static Integer[] mXueliyaoqiuInts = { -1, 20, 30, 40, 50, 60,70 };
	
	public static int getXueliyaoqiu(String str){
		for (int i = 0; i < mXueliyaoqiuStrings.length; i++) {
			if (mXueliyaoqiuStrings[i].equals(str)) {
				return mXueliyaoqiuInts[i];
			}
		}
		return -1;
	}
	
	public static String getXueliyaoqiuString(int data){
		for (int i = 0; i < mXueliyaoqiuInts.length; i++) {
			if (mXueliyaoqiuInts[i] == data) {
				return mXueliyaoqiuStrings[i];
			}
		}
		return defaultAllString;
	}

	public static String[] mHangyeStrings = { "酒店业", "餐饮/娱乐业", "其他行业" };
	
	public static Integer[] mHangyeInts = {1,2,6};
	
	public static int getHangye(String str){
		for(int i=0;i<mHangyeStrings.length;i++){
			if(mHangyeStrings[i].equals(str)){
				return mHangyeInts[i];
			}
		}
		return -1;
	}
	
	public static String[] mJiudianleixingStrings = {"国际品牌酒店","国内品牌酒店","单体酒店","经济型连锁酒店","餐饮/娱乐","未定"};
	public static Integer[] mJiudianleixingints = {1,2,3,4,5,6};
	
	public static int getLeixing(String str){
		for(int i=0;i<mJiudianleixingStrings.length;i++){
			if(mJiudianleixingStrings[i].equals(str)){
				return mJiudianleixingints[i];
			}
		}
		return -1;
	}
	public static String[] mXingjiStrings = {  "五星","准五星","四星","准四星", "三星","三星以下","待评" };
	
	
	public static Integer[] mXingjiInts = {-1,5,4,3,2,1};
	public static int getXingji(String str){
		for (int i = 0; i < mXingjiStrings.length; i++) {
			if (mXingjiStrings[i].equals(str)) {
				return mXingjiInts[i];
			}
		}
		return -1;
	}
	public static String getXingjiString(int data){
		for (int i = 0; i < mXingjiInts.length; i++) {
			if (mXingjiInts[i] == data) {
				return mXingjiStrings[i];
			}
		}
		return defaultAllString;
	}

	public static String[] mQiwangyuexinStrings = { defaultAllString, "0-1000",
			"1001-2000", "2001-3000", "3001-5000", "4500-6000", "6001-8000",
			"8001-10000", "10000以上" };
	public static String[] mQiwangxinziString_Resume = {
		defaultAllString,"1000","2000","3000","4000","5000","6000","7000","8000","9000","10000及以上"
	};
	
	public static String[] mNianlingStrings = { "20-25", "26-30", "31-40",
			"41-50" };

	public static String[] getDataByTag(int tag) {
		switch (tag) {
		case SearchCondition.TAG_CONDITION_YIXIANGZHIWEI:
			return mYixiangzhiweiStrings;
		case SearchCondition.TAG_CONDITION_FABURIQI:
			return mFaburiqiStrings;
		case SearchCondition.TAG_CONDITION_GONGZUONIANXIAN:
			return mGongzuonianxianStrings;
		case SearchCondition.TAG_CONDITION_XINGBIEYAOQIU:
			return mXingbieyaoqiuStrings;
		case SearchCondition.TAG_CONDITION_DAOGANGSHIJIAN:
			return mDaogangshijianStrings;
		case SearchCondition.TAG_CONDITION_XUELIYAOQIU:
			return mXueliyaoqiuStrings;
		case SearchCondition.TAG_CONDITION_HANGYE:
			return mHangyeStrings;
		case SearchCondition.TAG_CONDITION_XINGJI:
			return mXingjiStrings;
		case SearchCondition.TAG_CONDITION_QIWANGYUEXIN:
			return mQiwangyuexinStrings;
		case SearchCondition.TAG_CONDITION_NIANLING:
			return mNianlingStrings;
		case BaseBean.TAG_SALARY:
			return mQiwangxinziString_Resume;
		case SearchCondition.TAG_CONDITION_JIUDIANLEIXING:
			return mJiudianleixingStrings;
		case SearchCondition.TAG_CONDITION_ZHAOPINDUIXIANG:
			return mJobtypeStrings;
		case SearchCondition.TAG_CONDITION_ZHAOPINLEIBIE:
			return mZhaopiinleibieStrings;
		case SearchCondition.TAG_CONDITION_BAOCHIZHU:
			return mBaochizhuStrings;
		case SearchCondition.TAG_CONDITION_GONGZUOSHIJIAN:
			return mGongzuoshijianStrings;
		default:
			return null;
		}
	}

	public static String jobString = "{\"jobs\":[{\"JobId\":\"69416\",\"Comid\":\"VRexg3igfvec5210\",\"UserType\":\"\",\"PublishName\":\"\",\"JobType\":\"1\",\"JobClass\":\"\u8d22\u52a1\u90e8\",\"JobName\":\"\u8d22\u52a1\u4e13\u5458\uff08\u9152\u5e97\u7ba1\u7406\u90e8\uff09\",\"BestFlag\":\"0\",\"HotFlag\":\"0\",\"Deal\":\"0\",\"DeptId\":\"6685\",\"Number\":\"1\",\"End_Date\":\"2013-04-23 00:00:00\",\"Work_Area\":\"\u4e0a\u6d77\",\"Work_City\":\"\u6d66\u4e1c\u65b0\u533a\",\"Require\":\"\u804c\u52a1\u63cf\u8ff0\uff1a"
			+ "\n1.\u4e13\u4e1a\u4eba\u5458\u804c\u4f4d\uff0c\u80fd\u5728\u4e0a\u7ea7\u7684\u76d1\u7763\u548c\u9886\u5bfc\u4e0b\u5b9a\u671f\u5b8c\u6210\u91cf\u5316\u7684\u5de5\u4f5c\u8981\u6c42\uff0c\u5e76\u80fd\u72ec\u7acb\u5904\u7406\u548c\u89e3\u51b3\u6240\u8d1f\u8d23\u7684\u4efb\u52a1\u3002"
			+ "\n2.\u6309\u7167\u516c\u53f8\u53ca\u653f\u5e9c\u89c4\u5b9a\u7684\u8981\u6c42\u7f16\u5236\u5404\u79cd\u8d22\u52a1\u62a5\u8868\uff0c\u5e76\u534f\u52a9\u4e3b\u7ba1\u8fdb\u884c\u5206\u6790\u3002"
			+ "\n"
			+ "\n\u5c97\u4f4d\u8981\u6c42\uff1a"
			+ "\n1.\u8d22\u4f1a\u4e13\u4e1a\u672c\u79d1\u4ee5\u4e0a\u5b66\u5386\uff0c\u6301\u6709\u4f1a\u8ba1\u4e0a\u5c97\u8bc1"
			+ "\n2.2\u5e74\u4ee5\u4e0a\u5de5\u4f5c\u7ecf\u9a8c\u3002\uff081\u5e74\u4ee5\u4e0a\u4f1a\u8ba1\u5e08\u4e8b\u52a1\u6240\u5ba1\u8ba1\u7ecf\u9a8c\u8005\u4f18\u5148\uff09"
			+ "\n3.\u82f1\u6587\u56db\u7ea7\u4ee5\u4e0a\uff0c\u826f\u597d\u7684\u82f1\u6587\u4e66\u5199\u548c\u9605\u8bfb\u80fd\u529b\u3002\",\"Chat_Addr\":\" \",\"Edus\":\"60\",\"Sex\":\"2\",\"Works\":\"2\",\"LastUpdate_Time\":\"2012-10-23 13:47:00\",\"ViewClicks\":\"218\",\"SendClicks\":\"2\",\"JobFlag\":\"1\",\"Area\":\"\u4e0a\u6d77\",\"City\":\" \",\"Min_Age\":\"28\",\"Max_Age\":\"0\",\"TopAll\":\"0\",\"TopDate\":\"\",\"TopEndDate\":\"\",\"ContactPerson\":\"\u94b1\u5c0f\u59d0\",\"Phone\":\"021-61601111\",\"CompanyFax\":\" \",\"PhoneKeep\":\"0\",\"Email\":\"hr@onehome-yalong.com\",\"EmailKeep\":\"0\",\"Address\":\"\u4e0a\u6d77\u6d66\u4e1c\u65b0\u533a\u5d2e\u5c71\u8def688\u53f7\",\"ZipCode\":\"200135 \",\"gongsmc\":\"\",\"gsjs\":\"\",\"lxqq\":\"\",\"yuezd\":\"\",\"tianzd\":\"\",\"xiashizd\":\"\",\"mianshizd\":\"\",\"addtime\":\"2012-08-31 14:59:13\",\"czqk\":\" \",\"gzsj\":\" \",\"hukou_Area\":\"\",\"hukou_City\":\"\",\"language\":\"\",\"lug_zwcd\":\"\",\"mt\":\"\",\"mtkeep\":\"0\",\"busload\":\"\"}],\"result\":1,\"result_text\":\"\",\"total_count\":1}";
}
