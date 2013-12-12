package com.janan.util;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.janan.data.bean.JobInfo;
import com.janan.data.bean.company.CompanyInfo;
import com.janan.data.bean.personal.User;
import com.janan.data.bean.search.NearSearch;
import com.janan.data.data.City;
import com.janan.data.data.District;
import com.janan.data.data.Province;
import com.janan.recruit.BaseActivity;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "mydata.db"; // 数据库名称
	public static final String ID = "id";
	public static final String PID = "pid";
	public static final String CID = "cid";
	public static final String NAME = "name";
	public static final String TABLE_CITYS = "citys";
	public static final String TABLE_DISTRICTS = "districts";
	public static final String TABLE_PROVINCES = "provinces";

	public static final String TABLE_USER = "user";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String USEREMAIL = "email";
	public static final String USERTYPE = "usertype";

	public static final String TABLE_COMPANY = "company";
	public static final String TABLE_NEARSEARCH = "nearsearch";
	
	
	public static final String TABLE_JOB = "job";	
	public static final String JOB_ID = "id";
	public static final String JOB_NAME = "name";
	public static final String JOB_CONPANYNAME = "companyname";
	public static final String JOB_CITY = "city";
	public static final String JOB_AREA = "area";
	public static final String JOB_VIEW_CLICKS = "viewclicks";
	public static final String JOB_LASTUPDATE = "lastupdate";
	
	
	// public static final String INTENTJOB = "inentjob";//意向职位
	// public static final String INTENTPLACE = "intentplace";//意向地点
	// public static final String POSTDATE = "postdate";//发布日期
	// public static final String KEYWORD = "keyword";//关键词
	// public static final String WORKINGYEARS = "workingyears";//工作年限
	// public static final String SEX = "sex";//性别要求
	// public static final String AGE = "age";//年龄要求
	// public static final String DUTYTIME = "dutytime";//到岗时间
	// public static final String EDUCATION = "education";//学历要求
	// public static final String STARLEVEL = "starlevel";//星级
	// public static final String SALARY = "salary";//期望月薪
	
	
	
	SQLiteDatabase dbDatabase;
	BaseActivity activity;

	public static final String SQL_CREATECITYS = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_CITYS + " (" + ID + " INTEGER PRIMARY KEY, VARCHAR," + PID
			+ " VARCHAR," + NAME + " VARCHAR);";
	public static final String SQL_CREATEDISTRICTS = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_DISTRICTS
			+ " ("
			+ ID
			+ " INTEGER PRIMARY KEY, VARCHAR,"
			+ CID + " VARCHAR," + NAME + " VARCHAR);";
	public static final String SQL_CREATEPROVINCES = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_PROVINCES
			+ " ("
			+ ID
			+ " INTEGER PRIMARY KEY, VARCHAR,"
			+ NAME + " VARCHAR);";

	public static final String SQL_CREATEUSER = "create table if not exists "
			+ TABLE_USER + " (" + USERNAME + " varchar PRIMARY KEY,"
			+ USEREMAIL + " varchar," + USERTYPE + " varchar," + PASSWORD
			+ " varchar);";
	public static final String SQL_CREATECOMPANY = "create table if not exists "
			+ TABLE_COMPANY
			+ " ("
			+ USERNAME
			+ " varchar PRIMARY KEY,"
			+ USEREMAIL
			+ " varchar,"
			+ USERTYPE
			+ " varchar,"
			+ PASSWORD
			+ " varchar);";
	public static final String SQL_CREATEJOB = "create table if not exists "
			+TABLE_JOB
			+" ("
			+JOB_ID
			+ " varchar PRIMARY KEY,"
			+JOB_CONPANYNAME
			+ " varchar,"
			+JOB_LASTUPDATE
			+ " varchar,"
			+JOB_NAME
			+ " varchar,"
			+JOB_VIEW_CLICKS
			+ " varchar,"
			+JOB_CITY
			+ " varchar,"
			+JOB_AREA
			+ " varchar);";

	// public static final String SQL_CREATENEARSEARCH =
	// "craete table if not exists"
	// + TABLE_NEARSEARCH+" ("+INTENTJOB+" VARCHAR," + INTENTPLACE+" VARCHAR," +
	// POSTDATE+" VARCHAR,"
	// + KEYWORD
	// +" VARCHAR,"+WORKINGYEARS+" VARCHAR,"+SEX+" VARCHAR,"+AGE+" VARCHAR,"
	// +DUTYTIME+" VARCHAR,"+STARLEVEL+" VARCHAR,"+SALARY+" VARCHAR);";
	private static final int version = 1; // 数据库版本

	public DBHelper(Context context) {

		super(context, DB_NAME, null, version);
		activity = (BaseActivity) context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(SQL_CREATECITYS);
		db.execSQL(SQL_CREATEDISTRICTS);
		db.execSQL(SQL_CREATEPROVINCES);
		db.execSQL(SQL_CREATEUSER);
		db.execSQL(SQL_CREATECOMPANY);
		db.execSQL(SQL_CREATEJOB);
		// db.execSQL(SQL_CREATENEARSEARCH);
	}

	// public void setNearSearch(NearSearch nearch){
	// dbDatabase = this.getReadableDatabase();
	// ContentValues mValue = new ContentValues();
	// mValue.put(INTENTJOB, nearch.getmInentJob());
	// mValue.put(INTENTPLACE, nearch.getmIntentPlace());
	// // mValue.put(POSTDATE, user.getmUserEmail());
	// // mValue.put(USERTYPE, user.getmUserType());
	// dbDatabase.insert(TABLE_USER, null, mValue);
	// dbDatabase.close();
	// }
	public void setJob(JobInfo job){
		dbDatabase = this.getReadableDatabase();
		ContentValues mValue = new ContentValues();
		mValue.put(JOB_ID, job.getmJobId());
		mValue.put(JOB_CONPANYNAME, job.getmGongsimingchen());
		mValue.put(JOB_LASTUPDATE, job.getmFaburiqi());
		mValue.put(JOB_NAME, job.getmZhiweimingcheng());
		mValue.put(JOB_VIEW_CLICKS, job.getmViewClicks());
		mValue.put(JOB_CITY, job.getmCityString());
		mValue.put(JOB_AREA, job.getmAreaString());
		dbDatabase.insert(TABLE_JOB, null, mValue);
		dbDatabase.close();	
	}
	
	public boolean checkJob(JobInfo job){
		dbDatabase = this.getReadableDatabase();
		String[] colums = { JOB_ID};
		Cursor _Cursor = dbDatabase.query(TABLE_JOB, colums, JOB_ID+"="+job.getmJobId(), null, null,
				null, null);
		if(_Cursor.getCount()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public void deleteJob(JobInfo job){
		dbDatabase = this.getReadableDatabase();
		dbDatabase.delete(TABLE_JOB, JOB_ID+"="+job.getmJobId(), null);
		dbDatabase.close();
	}
	public ArrayList<JobInfo> getJobs() {
		ArrayList<JobInfo> _JobInfoList = new ArrayList<JobInfo>();
		String colums[] = { JOB_ID, JOB_CONPANYNAME,JOB_LASTUPDATE,JOB_NAME,JOB_VIEW_CLICKS ,JOB_CITY,JOB_AREA};
		SQLiteDatabase dbDatabase = this.getReadableDatabase();
		Cursor _Cursor = dbDatabase.query(TABLE_JOB, colums, null, null,
				null, null, null);
		if(_Cursor.getCount()>0){
			int _IDIndex = _Cursor.getColumnIndex(JOB_ID);
			int _ComNameIndex = _Cursor.getColumnIndex(JOB_CONPANYNAME);
			int _DateIndex = _Cursor.getColumnIndex(JOB_LASTUPDATE);
			int _JobNameIndex = _Cursor.getColumnIndex(JOB_NAME);
			int _JobViewIndex = _Cursor.getColumnIndex(JOB_VIEW_CLICKS);
			int _JobCityIndex = _Cursor.getColumnIndex(JOB_CITY);
			int _JobAreaIndex = _Cursor.getColumnIndex(JOB_AREA);
			while (_Cursor.moveToNext()) {
				JobInfo _Info = new JobInfo();
				_Info.setmJobId(_Cursor.getString(_IDIndex));
				_Info.setmGongsimingchen(_Cursor.getString(_ComNameIndex));
				_Info.setmZhiweimingcheng(_Cursor.getString(_JobNameIndex));
				_Info.setmViewClicks(_Cursor.getString(_JobViewIndex));
				_Info.setmCityString(_Cursor.getString(_JobCityIndex));
				_Info.setmAreaString(_Cursor.getString(_JobAreaIndex));
				_Info.setmFaburiqi(_Cursor.getString(_DateIndex));
				_JobInfoList.add(_Info);
			}
			return _JobInfoList;
		}else{
			return null;
		}

	}
	
	public void setUser(User user) {
		dbDatabase = this.getReadableDatabase();
		ContentValues mValue = new ContentValues();
		mValue.put(USERNAME, user.getmUserName());
		mValue.put(PASSWORD, user.getmPassWord());
		mValue.put(USEREMAIL, user.getmUserEmail());
		mValue.put(USERTYPE, user.getmUserType());
		dbDatabase.insert(TABLE_USER, null, mValue);
		dbDatabase.close();
	}

	public void clearUser() {
		dbDatabase = this.getReadableDatabase();
		dbDatabase.delete(TABLE_USER, null, null);
		dbDatabase.close();
	}

	public User getUser() {
		dbDatabase = this.getReadableDatabase();
		String[] colums = { USERNAME, PASSWORD, USEREMAIL, USERTYPE };
		Cursor _Cursor = dbDatabase.query(TABLE_USER, colums, null, null, null,
				null, null);
		int _NameIndex = _Cursor.getColumnIndex(USERNAME);
		int _IdIndex = _Cursor.getColumnIndex(PASSWORD);
		int _EmailIndex = _Cursor.getColumnIndex(USEREMAIL);
		int _TypeIndex = _Cursor.getColumnIndex(USERTYPE);
		User _User = null;
		if (_Cursor.getCount() > 0) {
			_User = new User();
			while (_Cursor.moveToNext()) {
				_User.setmUserName(_Cursor.getString(_NameIndex));
				_User.setmPassWord(_Cursor.getString(_IdIndex));
				_User.setmUserEmail(_Cursor.getString(_EmailIndex));
				_User.setmUserType(_Cursor.getString(_TypeIndex));
			}
		}
		dbDatabase.close();
		return _User;
	}

	public void setCompany(CompanyInfo user) {
		dbDatabase = this.getReadableDatabase();
		ContentValues mValue = new ContentValues();
		mValue.put(USERNAME, user.getmCompanyName());
		mValue.put(PASSWORD, user.getmPassWord());
		mValue.put(USEREMAIL, user.getmEmail());

		dbDatabase.insert(TABLE_COMPANY, null, mValue);
		dbDatabase.close();
	}

	public void clearCompany() {
		dbDatabase = this.getReadableDatabase();
		dbDatabase.delete(TABLE_COMPANY, null, null);
		dbDatabase.close();
	}

	public CompanyInfo getCompany() {
		dbDatabase = this.getReadableDatabase();
		String[] colums = { USERNAME, PASSWORD, USEREMAIL, USERTYPE };
		Cursor _Cursor = dbDatabase.query(TABLE_COMPANY, colums, null, null,
				null, null, null);
		int _NameIndex = _Cursor.getColumnIndex(USERNAME);
		int _IdIndex = _Cursor.getColumnIndex(PASSWORD);
		int _EmailIndex = _Cursor.getColumnIndex(USEREMAIL);
		int _TypeIndex = _Cursor.getColumnIndex(USERTYPE);
		CompanyInfo _User = null;
		if (_Cursor.getCount() > 0) {
			_User = new CompanyInfo();
			while (_Cursor.moveToNext()) {
				_User.setmCompanyName(_Cursor.getString(_NameIndex));
				_User.setmPassWord(_Cursor.getString(_IdIndex));
				_User.setmEmail(_Cursor.getString(_EmailIndex));

			}
		}
		dbDatabase.close();
		return _User;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public int checkSize(String tablename) {
		dbDatabase = this.getReadableDatabase();
		Cursor _CoCursor = dbDatabase.rawQuery("select count(*) from "
				+ tablename, null);
		String culumnString = _CoCursor.getColumnNames()[0];
		int index = _CoCursor.getColumnIndex(culumnString);
		_CoCursor.moveToNext();
		int count = _CoCursor.getInt(index);
		_CoCursor.close();
		dbDatabase.close();
		return count;
	}

	public void setCity(City city) {
		Log.i("janan", "city" + city.getmId());
		dbDatabase = this.getReadableDatabase();
		ContentValues mValue = new ContentValues();
		mValue.put(ID, city.getmId());
		mValue.put(PID, city.getmPid());
		mValue.put(NAME, city.getmName());
		dbDatabase.insert(TABLE_CITYS, null, mValue);
	}

	public void setProvince(Province province) {
		Log.i("janan", "province" + province.getmId());
		dbDatabase = this.getReadableDatabase();
		ContentValues mValue = new ContentValues();
		mValue.put(ID, province.getmId());

		mValue.put(NAME, province.getmName());
		dbDatabase.insert(TABLE_PROVINCES, null, mValue);
	}

	public void setDistrict(District district) {
		Log.i("janan", "district" + district.getmID());
		dbDatabase = this.getReadableDatabase();
		ContentValues mValue = new ContentValues();
		mValue.put(ID, district.getmID());
		mValue.put(CID, district.getmCID());
		mValue.put(NAME, district.getmName());
		dbDatabase.insert(TABLE_DISTRICTS, null, mValue);
	}

	public void closeDB() {
		dbDatabase.close();
	}

	public ArrayList<City> getCitiesByPid(String pid) {
		String colums[] = { NAME, ID, PID };
		ArrayList<City> _CityList = new ArrayList<City>();
		SQLiteDatabase dbDatabase = this.getReadableDatabase();
		Cursor _Cursor = dbDatabase.query(TABLE_CITYS, colums, PID + "=" + pid,
				null, null, null, null);
		int _NameIndex = _Cursor.getColumnIndex(NAME);
		int _IdIndex = _Cursor.getColumnIndex(ID);
		int _PidIndex = _Cursor.getColumnIndex(PID);
		if (_Cursor.getCount() > 0) {
			while (_Cursor.moveToNext()) {
				City _City = new City();
				_City.setmId(_Cursor.getString(_IdIndex));
				_City.setmPid(_Cursor.getString(_PidIndex));
				_City.setmName(_Cursor.getString(_NameIndex));
				_CityList.add(_City);
			}

		}
		_Cursor.close();
		dbDatabase.close();
		return _CityList;
	}

	public ArrayList<City> getAllCities() {
		ArrayList<City> _CityList = new ArrayList<City>();
		String colums[] = { ID, PID, NAME };
		SQLiteDatabase dbDatabase = this.getReadableDatabase();
		Cursor _Cursor = dbDatabase.query(TABLE_CITYS, colums, null, null,
				null, null, null);
		int _NameIndex = _Cursor.getColumnIndex(NAME);
		int _IdIndex = _Cursor.getColumnIndex(ID);
		int _PidIndex = _Cursor.getColumnIndex(PID);
		if (_Cursor.getCount() > 0) {
			while (_Cursor.moveToNext()) {
				City _City = new City();
				_City.setmId(_Cursor.getString(_IdIndex));
				_City.setmPid(_Cursor.getString(_PidIndex));
				_City.setmName(_Cursor.getString(_NameIndex));
				_CityList.add(_City);
			}

		}
		_Cursor.close();
		dbDatabase.close();
		return _CityList;
	}

	public ArrayList<Province> getAllProvinces() {
		ArrayList<Province> _ProvincesList = new ArrayList<Province>();
		String colums[] = { ID, NAME };
		SQLiteDatabase dbDatabase = this.getReadableDatabase();
		Cursor _Cursor = dbDatabase.query(TABLE_PROVINCES, colums, null, null,
				null, null, null);
		int _NameIndex = _Cursor.getColumnIndex(NAME);
		int _IdIndex = _Cursor.getColumnIndex(ID);
		if (_Cursor.getCount() > 0) {
			while (_Cursor.moveToNext()) {
				Province _Province = new Province();
				_Province.setmId(_Cursor.getString(_IdIndex));
				_Province.setmName(_Cursor.getString(_NameIndex));
				_ProvincesList.add(_Province);
			}

		}
		_Cursor.close();
		dbDatabase.close();
		return _ProvincesList;

	}

	public ArrayList<District> getAllDistricts() {
		ArrayList<District> _DistrictsList = new ArrayList<District>();
		String colums[] = { ID, CID, NAME };
		SQLiteDatabase dbDatabase = this.getReadableDatabase();
		Cursor _Cursor = dbDatabase.query(TABLE_DISTRICTS, colums, null, null,
				null, null, null);
		int _NameIndex = _Cursor.getColumnIndex(NAME);
		int _IdIndex = _Cursor.getColumnIndex(ID);
		int _CidIndex = _Cursor.getColumnIndex(CID);
		if (_Cursor.getCount() > 0) {
			while (_Cursor.moveToNext()) {
				District _District = new District();

				_District.setmID(_Cursor.getString(_IdIndex));
				_District.setmName(_Cursor.getString(_NameIndex));
				_District.setmCID(_Cursor.getString(_CidIndex));
				_DistrictsList.add(_District);
			}

		}
		_Cursor.close();
		dbDatabase.close();
		return _DistrictsList;
	}

	public ArrayList<District> getDistrictsByCid(String cid) {
		ArrayList<District> _DistrictsList = new ArrayList<District>();
		String colums[] = { ID, CID, NAME };
		SQLiteDatabase dbDatabase = this.getReadableDatabase();
		Cursor _Cursor = dbDatabase.query(TABLE_DISTRICTS, colums, CID + "="
				+ cid, null, null, null, null);
		int _NameIndex = _Cursor.getColumnIndex(NAME);
		int _IdIndex = _Cursor.getColumnIndex(ID);
		int _CidIndex = _Cursor.getColumnIndex(CID);
		if (_Cursor.getCount() > 0) {
			while (_Cursor.moveToNext()) {
				District _District = new District();

				_District.setmID(_Cursor.getString(_IdIndex));
				_District.setmName(_Cursor.getString(_NameIndex));
				_District.setmCID(_Cursor.getString(_CidIndex));
				_DistrictsList.add(_District);
			}

		}
		_Cursor.close();
		dbDatabase.close();
		return _DistrictsList;
	}
}
