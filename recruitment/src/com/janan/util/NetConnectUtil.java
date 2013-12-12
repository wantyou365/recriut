package com.janan.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

public class NetConnectUtil {
	private Context mContext;
	public NetConnectUtil(Context context){
		this.mContext = context;
	}
	
	public boolean isWifiConnected(){
		ConnectivityManager netManager = (ConnectivityManager)mContext
				.getSystemService(mContext.CONNECTIVITY_SERVICE);
		if(netManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()!=null){
			State _State = netManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
			if(State.CONNECTED == _State){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
		
	}
	
	public boolean isMobileConnected(){
		ConnectivityManager netManager = (ConnectivityManager)mContext
				.getSystemService(mContext.CONNECTIVITY_SERVICE);
		if(netManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!=null){
			State _State = netManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
			if(State.CONNECTED == _State){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}
}
