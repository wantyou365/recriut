//package com.janan.util;
//
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.protocol.HTTP;
//import org.apache.http.util.EntityUtils;
//
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//
//import com.janan.data.bean.personal.CoverLetter;
//import com.janan.data.bean.personal.Resume;
//import com.janan.recruit.BaseActivity;
//import com.janan.recruit.R;
//
//public class ResumeManager {
//	
//	private Resume mResume;
//	private LinearLayout mLayout;
//	private BaseActivity mActivity;
//	
//	public ResumeManager(BaseActivity activity){
//		mActivity = activity;
//		checkResume();
//	}
//	
//	private void checkResume(){
//		if(mResume == null){
//			mResume = new Resume(mActivity);
//			mResume.setmBirthday("1985-5-5");
//			mResume.setmEducationString("学士学位");
//			mResume.setmHeightString("179");
//			mResume.setmIntentionJob("运营经理");
//			mResume.setmIntentionPlaceString("上海");
//			mResume.setmNameString("刘佳");
//			mResume.setmPage(1);
//			mResume.setmPhoneNum("13876499876");
//			mResume.setmQiwangyuexin("10000-15000");
//			mResume.setmResidence("上海");
//			mResume.setmSexString("男");
//			mResume.setmUserId("123");
//			CoverLetter _Letter = new CoverLetter();
//			_Letter.setmTitleString("我的简历");
//			_Letter.setmContentString("lelelelelelelele");
//			_Letter.setmUerId(mResume.getmUserId());
//			mResume.setmCoverLetter(_Letter);
//		}
//	}
//	public LinearLayout getFirstPageView(){
//		checkResume();
//		LinearLayout _Layout = null;
//		if(mResume.getmPage()==1){
//			_Layout = 
//					(LinearLayout)mActivity.getLayoutInflater().inflate(R.layout.firstresumeview, null);
//			EditText _NameEditText = (EditText)_Layout.findViewById(R.id.nameedit);
//			_NameEditText.setText(mResume.getmNameString());
//			RadioGroup _Group = (RadioGroup)_Layout.findViewById(R.id.ataawGroup);
//			_Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {    
//	            @Override 
//	            public void onCheckedChanged(RadioGroup group, int checkedId) { 
//	                //doing actions    
//	            	
//	            	if(checkedId == R.id.radioButton1){
//	            		group.getChildAt(0).setClickable(true);
//	            		mResume.setmSexString("男");
//	            	}
//	            	if(checkedId == R.id.radioButton2){
//	            		group.getChildAt(1).setClickable(true);
//	            		mResume.setmSexString("女");
//	            	}
//	            } 
//			});
//			
//			RadioButton _MaleCheckBox = (RadioButton)_Layout.findViewById(R.id.radioButton1);
//			RadioButton _FemaleCheckBox = (RadioButton)_Layout.findViewById(R.id.radioButton2);
//			if(mResume.getmSexString().equals("男")){
//				
//				_MaleCheckBox.setChecked(true);
//			}else{
//				_FemaleCheckBox.setChecked(true);
//			}
//			
//			EditText _PhoneEditText = (EditText)_Layout.findViewById(R.id.phoneedit);
//			_PhoneEditText.setText(mResume.getmPhoneNum());
//			TextView _BirthTextView = (TextView)_Layout.findViewById(R.id.birthdayedit);
//			_BirthTextView.setText(mResume.getmBirthday());
//			TextView _ResidenceTextView = (TextView)_Layout.findViewById(R.id.residenceedit);
//			_ResidenceTextView.setText(mResume.getmResidence());
//			TextView _IntentionPlaceTextView = (TextView)_Layout.findViewById(R.id.intentionplaceedit);
//			_IntentionPlaceTextView.setText(mResume.getmIntentionPlaceString());
//			TextView _IntentionJobView = (TextView)_Layout.findViewById(R.id.intentionjobedit);
//			_IntentionJobView.setText(mResume.getmIntentionJob());
//			Button _SaveButton = (Button)_Layout.findViewById(R.id.save);
//			_SaveButton.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					HttpPost httpRequest =new HttpPost("http://www.dyteam.com/api/index.php?module=user&method=register");  
//				    //Post运作传送变数必须用NameValuePair[]阵列储存  
//				    //传参数 服务端获取的方法为request.getParameter("name")  
//				    List <NameValuePair> params=new ArrayList<NameValuePair>();  
//				    params.add(new BasicNameValuePair("username","wantyou365")); 
//				    params.add(new BasicNameValuePair("email","wantyou365@163.com"));
//				    params.add(new BasicNameValuePair("password","shangdongf"));
//				    params.add(new BasicNameValuePair("user_type","2"));
//				    try{  
//				       
//				       
//				     //发出HTTP request  
//				     httpRequest.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));  
//				     //取得HTTP response  
//				     HttpResponse httpResponse=new DefaultHttpClient().execute(httpRequest);  
//				       
//				     //若状态码为200 ok   
//				     if(httpResponse.getStatusLine().getStatusCode()==200){  
//				      //取出回应字串  
//				      String strResult=EntityUtils.toString(httpResponse.getEntity());  
//				      Log.i("janan", strResult) ;
//				     }else{  
//				    	 String strResult=EntityUtils.toString(httpResponse.getEntity());
//				    	 Log.i("janan", "错误"+strResult) ;
//				     }  
//				    }catch(Exception e){  
//				     e.printStackTrace();
//				    }  
//				}
//			});
//		}
//		
//				
//		return _Layout;
//	}
//	
//}
