package com.janan.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class UncaughtExceptionHandler implements
		java.lang.Thread.UncaughtExceptionHandler {
	private Context con_;

	public UncaughtExceptionHandler(Context con) {
		this.con_ = con;
	}
  
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		StringWriter sw = new StringWriter();
		
		ex.printStackTrace(new PrintWriter(sw));
		String string = sw.toString();
		Intent data=new Intent(Intent.ACTION_SENDTO);  
		data.setData(Uri.parse("mailto:wantyou365@126.com"));  
		data.putExtra(Intent.EXTRA_SUBJECT, "ϵͳ�쳣");  
		data.putExtra(Intent.EXTRA_TEXT, string);  
		con_.startActivity(data);
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(10);// this is very important, we can got next activity
	}
   
}
