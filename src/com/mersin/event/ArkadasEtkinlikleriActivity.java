package com.mersin.event;

import java.util.ArrayList;

import android.os.Bundle;

import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ArkadasEtkinlikleriActivity extends ListActivity {

	String kullaniciAdi;
	String arkadasAdi;
	TextView arkadasEtkinlikleriTvArkadasAdi;
	ArrayList<String> alArkadaslariminEtkinlikleri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arkadas_etkinlikleri);
		Bundle paket = getIntent().getExtras();
		kullaniciAdi = paket.getString("kullaniciAdi");
		arkadasAdi = paket.getString("arkadasAdi");
		
		arkadasEtkinlikleriTvArkadasAdi = (TextView) findViewById(R.id.arkadasEtkinlikleriTvArkadasAdi);
		arkadasEtkinlikleriTvArkadasAdi.setText(arkadasAdi);
		
		alArkadaslariminEtkinlikleri = getArkadasEtkinlikleriList();
		ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.satir_tasarimi, alArkadaslariminEtkinlikleri);
		setListAdapter(adp);
	}

	private ArrayList<String> getArkadasEtkinlikleriList() {
		ArrayList<String> arkadaslariminEtkinlikleri = new ArrayList<String>();
		arkadaslariminEtkinlikleri.add("Ab 2014");
		arkadaslariminEtkinlikleri.add("Rails Rumble");
		arkadaslariminEtkinlikleri.add("Heroku Hackaton");
		
		return arkadaslariminEtkinlikleri;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.arkadas_etkinlikleri, menu);
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		String etkinlik_adi = alArkadaslariminEtkinlikleri.get(position);
		Toast.makeText(this, etkinlik_adi, Toast.LENGTH_SHORT).show();
	}

}
