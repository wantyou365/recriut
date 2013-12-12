package com.janan.net;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

import com.janan.data.bean.company.CompanyInfo;
import com.janan.data.bean.personal.User;
import com.janan.data.data.DataForSearchCondition;
import com.janan.recruit.BaseActivity;

public class CompanyRegisterEngine extends BaseEngine {

	private CompanyInfo mCompany;
	private boolean isChange = false;
	private boolean isSearch = false;
	public CompanyRegisterEngine(BaseActivity activity, boolean isperson) {
		super(activity, isperson);
		// TODO Auto-generated constructor stub
	}

	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> _Params = new ArrayList<NameValuePair>();
				if(isSearch){
					addEncodePara(mCompany.getmCompanyId(), "company_id", _Params);
					requestToNet(mCompanyDataSearch, _Params);
				}else if (isChange) {
					addEncodePara(mCompany.getmCompanyName(), "company_name", _Params);
					addEncodePara(mCompany.getmContactInfo(), "contact_person", _Params);
					addEncodePara(mCompany.getmCity(), "locus_city", _Params);
					addEncodePara(mCompany.getmArea(), "locus_area", _Params);
					addEncodePara(mCompany.getmPhone(), "phone", _Params);
					addEncodePara(mCompany.getmAddress(), "address", _Params);
					addEncodePara(mCompany.getmJobsCount(), "workers", _Params);
					addEncodePara(mCompany.getmMoMoContent(), "company_memo", _Params);
					requestToNet(mCompanyChangeData, _Params);
				} else {
					addEncodePara(mCompany.getmUserName(), "username", _Params);
					addEncodePara(mCompany.getmEmail(), "email", _Params);
					addEncodePara(mCompany.getmPassWord(), "password", _Params);
					addEncodePara(mCompany.getmCompanyName(), "company_name", _Params);
					addEncodePara(mCompany.getmContactInfo(), "contact_person", _Params);
					addEncodePara(mCompany.getmCity(), "locus_city", _Params);
					addEncodePara(mCompany.getmArea(), "locus_area", _Params);
					addEncodePara(mCompany.getmPhone(), "phone", _Params);
					addEncodePara(mCompany.getmXingJiString(), "xingji", _Params);
					addEncodePara(DataForSearchCondition.getLeixing(mCompany.getmProperity())+"", "Properity", _Params);;
					addEncodePara(DataForSearchCondition.getHangye(mCompany.getmIndustry())+"", "Industry", _Params);
					requestToNet(mCompanyRegister, _Params);
				}
				
			}
		}).start();
	}

	@Override
	public void requestToNet(String url, ArrayList<NameValuePair> params) {
		// TODO Auto-generated method stub

		super.requestToNet(url, params);

	}

	@Override
	public void parseResponse(String result) {
		// TODO Auto-generated method stub
		try {
			JSONTokener jsonParser = new JSONTokener(result);
			JSONObject _ResultObject = (JSONObject) jsonParser.nextValue();
			int _ResultId = _ResultObject.optInt("result");
			if (_ResultId == 0) {
				String _TextString = _ResultObject.optString("result_text");
				mActivity.callBack(mActivity.CallbackError, _TextString);
			} else if (_ResultId == 1) {
				if(isSearch()){
					if(mActivity.mCompany == null){
						mActivity.mCompany = new CompanyInfo();
					}
					JSONObject _UserInfoJsonObject = _ResultObject
							.optJSONObject("company_info");
					mActivity.mCompany.setmCompanyName(_UserInfoJsonObject
							.optString("CompanyName"));
					mActivity.mCompany.setmContactInfo(_UserInfoJsonObject
							.optString("ContactPerson"));
					mActivity.mCompany.setmCity(_UserInfoJsonObject
							.optString("Locus_City"));
					mActivity.mCompany.setmArea(_UserInfoJsonObject
							.optString("Locus_Area"));
					mActivity.mCompany.setmPhone(_UserInfoJsonObject
							.optString("Phone"));
					mActivity.mCompany.setmAddress(_UserInfoJsonObject
							.optString("Address"));
					mActivity.mCompany.setmJobsCount(_UserInfoJsonObject
							.optString("Workers"));
					mActivity.mCompany.setmMoMoContent(_UserInfoJsonObject.optString("Company_Memo"));
					mActivity.callBack(mActivity.CallbackSearchSuccess, null);
					return;
				}
				mActivity.callBack(mActivity.CallbackSuccess, null);
			}
		} catch (Exception ex) {
			// 异常处理代码

			mActivity.callBack(mActivity.CallbackError,
					MSGHANDLERESULTERROR_STRING);
			
			ex.printStackTrace();
		}
	}

	public CompanyInfo getmmCompany() {
		return mCompany;
	}

	public void setmCompany(CompanyInfo mCompany) {
		this.mCompany = mCompany;
	}

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}

	public boolean isSearch() {
		return isSearch;
	}

	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

}
