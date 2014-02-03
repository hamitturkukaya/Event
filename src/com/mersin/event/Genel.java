package com.mersin.event;

import com.mersin.event.R.string;

import android.app.AlertDialog;
import android.content.Context;

public class Genel {
	
	public static String kullaniciAdi;
	public static int kullaniciId;
	
	public static void uyar(Context context) {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
		alertBuilder.setTitle(R.string.alert_title);
		alertBuilder.setMessage(R.string.alert_message);
		alertBuilder.setPositiveButton(R.string.alert_positive_button, null);
		
		AlertDialog alert = alertBuilder.create();
		alert.show();		
	}
}
