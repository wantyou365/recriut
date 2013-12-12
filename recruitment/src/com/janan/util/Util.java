package com.janan.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.janan.data.bean.JobInfo;
import com.janan.data.data.DataForSearchCondition;
import com.janan.recruit.BaseActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;

public class Util
{
  private static String username_expression11 = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
  public static boolean checkEmail(String email)
  {
    if (email != null) {
      Pattern pattern = Pattern.compile(username_expression11);
      Matcher mc = pattern.matcher(email);
      return mc.matches();
    }
    return false;
  }

  public static boolean checkPassword(String pass, String confirm)
  {
    if ((pass != null) && (confirm != null))
    {
      return pass.equals(confirm);
    }

    return false;
  }
  
  public static boolean checkEditNull(EditText edit){
	  if(edit.getText()!=null){
		  return true;
	  }
	  return false;
  }
  
  public static boolean isConditionNull(String value){
	  if(value==null||value.equals(DataForSearchCondition.defaultAllString)||value.startsWith("请")){
		  return true;
	  }else{
		  return false;
	  }
  }
  
  public static String getPushDate(int plus){
	  Calendar mCalendar  = Calendar .getInstance();
		int day = mCalendar.get(Calendar.DAY_OF_YEAR);
		mCalendar.set(Calendar.DAY_OF_YEAR, day -plus);
		SimpleDateFormat _Format = new SimpleDateFormat("yyyy/MM/dd");
		return _Format.format(mCalendar.getTime());
  }
  
  public static String[] splitDate(String date){
	  if(date!=null){
		  return date.split("-");
	  }else{
		  return null;
	  }
  }
  
  public static String[] splitPlace(String place){
	  if(place!=null){
		  return place.split(",");
	  }else {
		  return null;
	  }
  }
  
  public static boolean checkZhixiashi(String city){
	  if(city!=null){
		  if("上海市".equals(city)||"北京市".equals(city)||"天津市".equals(city)||"重庆市".equals(city)){
			  return true;
		  }else{
			  return false;
		  }
	  }else{
		  return false;
	  }
  }
  
  public static boolean ifArrayContain(String value,String[] values){
	  boolean contains = false;
	  for(int i=0;i<values.length;i++){
		  if(values[i].equals(value)){
			  contains = true;
		  }
	  }
	  return contains;
  }
  
  public static String handleString(String str){
	  if(checkString(str)){
		  return str.trim();
	  }else{
		  return "";
	  }
  }
  
  public static boolean checkString(String str){
	  if(str == null|| "".equals(str)||str.contains("请选择")){
		  return false;
	  }else{
		  return true;
	  }
  }
  
  public static void shareWork(String url,JobInfo job,BaseActivity activity){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		intent.putExtra(Intent.EXTRA_TEXT, url + "\r\n" + job.getmZhiweimingcheng()+"\r\n"+job.getmGongsimingchen());
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, job.getmZhiweimingcheng());
		activity.startActivity(Intent.createChooser(intent, "分享"));
  }
  
  public static boolean checkLocationExists(Context context){
	  SharedPreferences _pre = PreferenceManager
				.getDefaultSharedPreferences(context);
	  String cityString = _pre.getString("city", "");
	  String proString = _pre.getString("province", "");
	  return (checkString(cityString)&&checkString(proString));
	 
  }
  
  public static String replaceString(String str){
	  if(checkString(str)){
		  str = str.replace("&nbsp;", " ");
		  str = str.replace("&#8226;", "·");
		  str = str.replace("&#183;", "·");
		  str = str.replace("&lt;p&gt;", " ");
	  }else{
		  str = "";
	  }
	 
	  return str;
  }
}