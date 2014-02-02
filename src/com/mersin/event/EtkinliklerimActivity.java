package com.mersin.event;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class EtkinliklerimActivity extends ListActivity {

	String kullaniciAdi;
	ArrayList<String> alEtkinliklerim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_etkinliklerim);
		
		kullaniciAdi = getIntent().getExtras().getString("kullaniciAdi");
		alEtkinliklerim = getEtkinlikList();
		ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.satir_tasarimi, alEtkinliklerim);
		setListAdapter(adp);
	}

	private ArrayList<String> getEtkinlikList() {
		ArrayList<String> etkinliklerim = new ArrayList<String>();
		etkinliklerim.add("AB 2014");
		etkinliklerim.add("LKD 2014");
		etkinliklerim.add("Doğum günü");
		etkinliklerim.add("Yılbaşı");
		
		return etkinliklerim;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.etkinliklerim, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		int tiklanan_id = item.getItemId();
		
		if (tiklanan_id == R.id.etkinliklerimMenuItemYeniEtkinlik) {
			yeniEtkinlikEke();
		}
		
		return super.onOptionsItemSelected(item);
	}

	private void yeniEtkinlikEke() {
		
		Intent i = new Intent(this, YeniEtkinlikActivity.class);
		i.putExtra("kullaniciAdi", kullaniciAdi);
		startActivity(i);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		String etkinlikAdi = alEtkinliklerim.get(position);
		Toast.makeText(this, etkinlikAdi, Toast.LENGTH_SHORT).show();
	}

}
