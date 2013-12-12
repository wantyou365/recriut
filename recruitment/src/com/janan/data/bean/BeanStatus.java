package com.janan.data.bean;

import android.R.integer;
import android.os.Bundle;

public interface BeanStatus {
	/**
	 * 刷新bean的值
	 * */
	public void updateUI(Bundle bundle);
	
	/**
	 * 提取默认值
	 * */
	public String getDefoultValue(int tag);
}
