package com.mersin.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KayitActivity extends Activity implements OnClickListener{

	EditText kayitEtAdSoyad;
	EditText kayitEtKullaniciAdi;
	EditText kayitEtEmail;
	EditText kayitEtSifre;
	Button btnKayit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kayit);
		
		kayitEtAdSoyad = (EditText) findViewById(R.id.kayitEtAdSoyad);
		kayitEtKullaniciAdi = (EditText) findViewById(R.id.kayitEtKullaniciAdi);
		kayitEtEmail = (EditText) findViewById(R.id.kayitEtEmail);
		kayitEtSifre = (EditText) findViewById(R.id.kayitEtSifre);
		btnKayit = (Button) findViewById(R.id.kayitBtnKayit);
		
		btnKayit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v==btnKayit){
			String adSoyad = kayitEtAdSoyad.getText().toString();
			String kullaniciAdi = kayitEtKullaniciAdi.getText().toString();
			String email = kayitEtEmail.getText().toString();
			String sifre = kayitEtSifre.getText().toString();
			
			if (adSoyad.isEmpty() || kullaniciAdi.isEmpty() || email.isEmpty() || sifre.isEmpty()) {
				Genel.uyar(this);
			} else {
				String mesaj = adSoyad + " " + kullaniciAdi + " " + email + " " + sifre;
				boolean kayitBasarili = true;
				
				if (kayitBasarili) {
					Intent i = new Intent(this, AnaActivity.class);
					i.putExtra("kullaniciAdi", kullaniciAdi);
					startActivity(i);
				} else {
					Toast.makeText(this, "Kaydedilemedi", Toast.LENGTH_SHORT).show();
				}
			}
		}
		
	}	
	
}
