package com.mersin.event;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class YeniEtkinlikActivity extends Activity implements OnClickListener{

	String kullaniciAdi;
	EditText etkinlikEkleEtEtkinlikAdi;
	EditText etkinlikEkleEtEtkinlikAciklama;
	EditText etkinlikEkleEtEtkinlikYer;
	Button etkinlikEkleBtnKaydet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yeni_etkinlik);
		
		kullaniciAdi = getIntent().getExtras().getString("kullaniciAdi");
		
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
			
			String mesaj = etkinlik_adi + " " + etkinlik_aciklama + " " + etkinlik_yer;
			
			Toast.makeText(this, mesaj, Toast.LENGTH_LONG).show();
		}
		
	}

}
