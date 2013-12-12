package com.janan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.StaticLayout;
import android.util.Log;

public class LocationUtil {
	public static Double mLongitude = 0.0;
	public static Double mLatitude = 0.0;

	public static void setAddress(final Context context) {
		getLocation(context);
		if (!(mLongitude == 0.0 && mLatitude == 0.0)) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					String City;
					String Province;

					String url = "http://maps.google.com/maps/api/geocode/json?latlng="
							+ mLatitude
							+ ","
							+ mLongitude
							+ "&language=zh_CN&sensor=false";
					HttpClient client = new DefaultHttpClient();
					StringBuilder sb = new StringBuilder();
					try {
						HttpResponse resp = client.execute(new HttpGet(url));
						HttpEntity he = resp.getEntity();
						BufferedReader br = new BufferedReader(
								new InputStreamReader(he.getContent()));
						String str = "";
						while ((str = br.readLine()) != null) {
							sb.append(str);
						}

					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					try {
						JSONObject jo1 = new JSONObject(sb.toString());
						String str1 = jo1.getString("results");
						JSONArray arr1 = new JSONArray(str1);
						String str2 = arr1.get(0).toString();
						JSONObject jo2 = new JSONObject(str2);
						String str3 = jo2.getString("formatted_address");
						JSONArray arr2 = jo2.getJSONArray("address_components");
						for (int i = 0; i < arr2.length(); i++) {
							JSONObject ob = arr2.optJSONObject(i);
							if (ob != null) {
								JSONArray child_ob = ob.getJSONArray("types");
								String[] strs = new String[child_ob.length()];
								for (int j = 0; j < child_ob.length(); j++) {
									strs[j] = child_ob.optString(j);
								}
								if (strs.length > 1) {
									Util.ifArrayContain("locality", strs);
									if (Util.ifArrayContain("locality", strs)
											&& Util.ifArrayContain("political",
													strs)) {
										City = ob.optString("long_name");
										SharedPreferences _pre = PreferenceManager
												.getDefaultSharedPreferences(context);
										Editor _editEditor = _pre.edit();
										_editEditor.putString("city", City);
										_editEditor.commit();
									}
									if (Util.ifArrayContain(
											"administrative_area_level_1", strs)
											&& Util.ifArrayContain("political",
													strs)) {
										Province = ob.optString("long_name");
										SharedPreferences _pre = PreferenceManager
												.getDefaultSharedPreferences(context);
										Editor _editEditor = _pre.edit();
										_editEditor.putString("province",
												Province);
										_editEditor.commit();
									}

								}

							}
						}

						// Toast.makeText(LocationService.this, str3,
						// Toast.LENGTH_LONG).show();
					} catch (JSONException e) {

					}
				}
			}).start();
		}

	}

	private static TelephonyManager telephonyManager;
	private static String IMSI;

	private static void getLocation(Context context) {
		final LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		final LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) { // 当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
				// log it when the location changes
				if (location != null) {
					Log.i("SuperMap",
							"Location changed : Lat: " + location.getLatitude()
									+ " Lng: " + location.getLongitude());
				}
			}

			public void onProviderDisabled(String provider) {
				// Provider被disable时触发此函数，比如GPS被关闭
			}

			public void onProviderEnabled(String provider) {
				// Provider被enable时触发此函数，比如GPS被打开
				Location location = locationManager
						.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				mLatitude = location.getLatitude(); // 经度
				mLongitude = location.getLongitude(); // 纬度
				double altitude = location.getAltitude(); // 海拔
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// Provider的转态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
			}
		};
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 0, locationListener);
		
		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		
		if (location != null) {
			mLatitude = location.getLatitude(); // 经度
			mLongitude = location.getLongitude(); // 纬度
		} else {
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
					1000, 0, locationListener);
			location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

			if (location != null) {
				mLatitude = location.getLatitude(); // 经度
				mLongitude = location.getLongitude(); // 纬度
			} else {
				location = locationManager
						.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

				if (location != null) {
					mLatitude = location.getLatitude(); // 经度
					mLongitude = location.getLongitude(); // 纬度
				}
			}
		}
		
//		if(location == null){
//			String ipString = GetHostIp();
//			String url = "http://www.ip138.com/";
//			HttpClient client = new DefaultHttpClient();
//			StringBuilder sb = new StringBuilder();
//			try {
//				HttpResponse resp = client.execute(new HttpGet(url));
//				HttpEntity he = resp.getEntity();
//				BufferedReader br = new BufferedReader(
//						new InputStreamReader(he.getContent(),"gb2312"));
//				String str = "";
//				while ((str = br.readLine()) != null) {
//					sb.append(str);
//				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
		// String ProvidersName = null;
		// telephonyManager = (TelephonyManager) context
		// .getSystemService(Context.TELEPHONY_SERVICE);
		// String phoneNumString = telephonyManager.getLine1Number();
		// IMSI = telephonyManager.getSubscriberId();
		// telephonyManager.get
		// // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
		//
		// System.out.println(IMSI);
		//
		// if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
		//
		// ProvidersName = "中国移动";
		//
		// } else if (IMSI.startsWith("46001")) {
		//
		// ProvidersName = "中国联通";
		//
		// } else if (IMSI.startsWith("46003")) {
		//
		// ProvidersName = "中国电信";
		//
		// }

		// if(location == null){
		// if(!locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)){
		// android.provider.Settings.Secure.setLocationProviderEnabled(context.getContentResolver(),
		// LocationManager.GPS_PROVIDER, false);
		// }
		// }
	}

	public static String GetHostIp() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> ipAddr = intf.getInetAddresses(); ipAddr
						.hasMoreElements();) {
					InetAddress inetAddress = ipAddr.nextElement();
					System.out.println("");
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException ex) {
		} catch (Exception e) {
		}
		return null;
	}
}
