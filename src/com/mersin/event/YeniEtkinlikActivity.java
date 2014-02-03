package com.mersin.event;

import com.mersin.entity.EtkinlikEntity;
import com.mersin.entity.EtkinlikOperation;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class YeniEtkinlikActivity extends Activity implements OnClickListener{

	EditText etkinlikEkleEtEtkinlikAdi;
	EditText etkinlikEkleEtEtkinlikAciklama;
	EditText etkinlikEkleEtEtkinlikYer;
	Button etkinlikEkleBtnKaydet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yeni_etkinlik);
		etkinlikEkleEtEtkinlikAdi = (EditText) findViewById(R.id.etkinlikEkleEtEtkinlikAdi);
		etkinlikEkleEtEtkinlikAciklama = (EditText) findViewById(R.id.etkinlikEkleEtEtkinlikAciklama);
		etkinlikEkleEtEtkinlikYer = (EditText) findViewById(R.id.etkinlikEkleEtEtkinlikYer);
		etkinlikEkleBtnKaydet = (Button) findViewById(R.id.etkinlikEkleBtnKaydet);
		etkinlikEkleBtnKaydet.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.yeni_etkinlik, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == etkinlikEkleBtnKaydet) {
			String etkinlik_adi = etkinlikEkleEtEtkinlikAdi.getText().toString();
			String etkinlik_aciklama = etkinlikEkleEtEtkinlikAciklama.getText().toString();
			String etkinlik_yer = etkinlikEkleEtEtkinlikYer.getText().toString();

			EtkinlikEntity etkinlik = new EtkinlikEntity();
			etkinlik.setAd(etkinlik_adi);
			etkinlik.setAciklama(etkinlik_aciklama);
			etkinlik.setYer(etkinlik_yer);
			etkinlik.setKullaniciId(Genel.kullaniciId);
			EtkinlikOperation operation = new EtkinlikOperation();
			boolean sonuc = operation.kayit(etkinlik);
			if (sonuc) {
				Toast.makeText(this, "Başarıyla Kaydedildi", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Kaydedilemedi", Toast.LENGTH_LONG).show();
			}
		}
		
	}

}
