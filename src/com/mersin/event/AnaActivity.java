package com.mersin.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AnaActivity extends Activity implements OnClickListener{
	
	TextView anaTvKullaniciAdi;
	String kullaniciAdi;
	int kullaniciId;
	Button anaBtnEtkinliklerim;
	Button anaBtnArkadaşlar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ana);
		
		anaTvKullaniciAdi = (TextView) findViewById(R.id.anaTvKullaniciAdi);
		kullaniciAdi = getIntent().getExtras().getString("kullaniciAdi");
		kullaniciId = getIntent().getExtras().getInt("kullaniciId");
		anaTvKullaniciAdi.setText(kullaniciAdi);
		anaBtnEtkinliklerim = (Button) findViewById(R.id.anaBtnEtkinliklerim);
		anaBtnArkadaşlar = (Button) findViewById(R.id.anaBtnArkadaslar);
		anaBtnEtkinliklerim.setOnClickListener(this);
		anaBtnArkadaşlar.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		if (v==anaBtnEtkinliklerim){
			
			etkinliklerim();
			
		} else if (v == anaBtnArkadaşlar){
			
			arkadaslarim();
			
		}
		
	}
	
	private void etkinliklerim() {
		
		Intent i = new Intent(this, EtkinliklerimActivity.class);
		i.putExtra("kullaniciAdi", kullaniciAdi);
		i.putExtra("kullaniciId", kullaniciId);
		startActivity(i);
		
	}
	
	private void arkadaslarim() {
		Intent i = new Intent(this, ArkadaslarimActivity.class);
		i.putExtra("kullaniciAdi", kullaniciAdi);
		i.putExtra("kullaniciId", kullaniciId);
		startActivity(i);
	}

}
