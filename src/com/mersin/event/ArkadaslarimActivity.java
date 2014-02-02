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

public class ArkadaslarimActivity extends ListActivity {

	String kullaniciAdi;
	ArrayList<String> alArkadaslarim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arkadaslarim);
		
		kullaniciAdi = getIntent().getExtras().getString("kullaniciAdi");
		alArkadaslarim = getArkadasList();
		ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.satir_tasarimi, alArkadaslarim);
		setListAdapter(adp);
	}

	private ArrayList<String> getArkadasList() {
		ArrayList<String> arkadaslarim = new ArrayList<String>();
		arkadaslarim.add("Ali");
		arkadaslarim.add("Veli");
		arkadaslarim.add("Ahmet");
		arkadaslarim.add("Mehmet");
		arkadaslarim.add("Murat");
		arkadaslarim.add("Umut");
		arkadaslarim.add("Caner");
		arkadaslarim.add("İsmail");
		arkadaslarim.add("Baran");
		return arkadaslarim;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		getMenuInflater().inflate(R.menu.arkadaslarim, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int menu_item = item.getItemId();
		
		if (menu_item == R.id.arkadaslarimMenuItemArkadasAra){
			
			Intent i = new Intent(this, ArkadasAraActivity.class);
			i.putExtra("kullaniciAdi", kullaniciAdi);
			startActivity(i);
			
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {	
		super.onListItemClick(l, v, position, id);
		String arkadasAdi = alArkadaslarim.get(position);
		//Toast.makeText(this, arkadasAdi, Toast.LENGTH_SHORT).show();
		
		Intent i = new Intent(this, ArkadasEtkinlikleriActivity.class);
		i.putExtra("kullaniciAdi", kullaniciAdi);
		i.putExtra("arkadasAdi", arkadasAdi);
		startActivity(i);
	}

}
