package com.janan.util;

import android.content.SharedPreferences;

import com.janan.recruit.BaseActivity;

public class PreferenceUtil {
	private static SharedPreferences mPreference;
	
	public static SharedPreferences getPrefrence(BaseActivity activity){
		mPreference = (SharedPreferences) activity.getPreferences(activity.MODE_PRIVATE);
		return mPreference;
	}
}
