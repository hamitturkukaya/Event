package com.mersin.event;

import android.app.AlertDialog;
import android.content.Context;

public class Genel {
	
	public static void uyar(Context context) {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
		alertBuilder.setTitle(R.string.alert_title);
		alertBuilder.setMessage(R.string.alert_message);
		alertBuilder.setPositiveButton(R.string.alert_positive_button, null);
		
		AlertDialog alert = alertBuilder.create();
		alert.show();		
	}
}
