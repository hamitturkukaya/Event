package com.mersin.event;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener{

	EditText loginEtKullaniciAdi;
	EditText loginEtSifre;
	Button loginBtnGiris;
	Button loginBtnUyeOl;
	TextView loginTvSonuc;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		loginEtKullaniciAdi = (EditText) findViewById(R.id.loginEtKullaniciAdi);
		loginEtSifre = (EditText) findViewById(R.id.loginEtSifre);
		loginBtnGiris = (Button) findViewById(R.id.loginBtnGiris);
		loginBtnUyeOl = (Button) findViewById(R.id.loginBtnUyeOl);
		loginTvSonuc = (TextView) findViewById(R.id.loginTvSonuc);
		loginBtnGiris.setOnClickListener(this);
		loginBtnUyeOl.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == loginBtnGiris) {
			giris();
		} else if (v == loginBtnUyeOl) {
			uyeOl();
		}
	}

	private void uyeOl() {
		Intent i = new Intent(this, KayitActivity.class);
		startActivity(i);
	}

	private void giris() {
		String kullaniciAdi = loginEtKullaniciAdi.getText().toString();
		String sifre  = loginEtSifre.getText().toString();
		
		if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
			Genel.uyar(this);
		} else {	
			if (kullaniciAdi.equals("turku") && sifre.equals("kaya")) {
				loginTvSonuc.setText(R.string.basarili);
			} else {
				loginTvSonuc.setText(R.string.başarısız);
			}
		}
	}

}
