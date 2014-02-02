package com.mersin.event;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ArkadasAraActivity extends Activity implements OnClickListener{

	String kullaniciAdi;
	EditText arkadasAraEtAdSoyad;
	Button arkadasAraBtnAra;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arkadas_ara);
		
		kullaniciAdi = getIntent().getExtras().getString("kullaniciAdi");
		arkadasAraEtAdSoyad = (EditText) findViewById(R.id.arkadasAraEtAdSoyad);
		arkadasAraBtnAra = (Button) findViewById(R.id.arkadasAraBtnAra);
		
		arkadasAraBtnAra.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.arkadas_ara, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		if (v == arkadasAraBtnAra){
			
			ara();
			
		}
		
	}

	private void ara() {
		String ad = arkadasAraEtAdSoyad.getText().toString();
		
		if (ad.isEmpty()){
			Genel.uyar(this);
		} else {
			Toast.makeText(this, ad, Toast.LENGTH_LONG).show();
		}
		
	}

}
