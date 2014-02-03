package com.mersin.event;

import java.util.ArrayList;

import com.mersin.entity.KullaniciEntity;
import com.mersin.entity.KullaniciOperation;

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
	int kullaniciId;
	ArrayList<String> alArkadaslarim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arkadaslarim);
		
		kullaniciAdi = getIntent().getExtras().getString("kullaniciAdi");
		kullaniciId = getIntent().getExtras().getInt("kullaniciId");
		
		alArkadaslarim = getArkadasList();
		ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.satir_tasarimi, alArkadaslarim);
		setListAdapter(adp);
	}

	private ArrayList<String> getArkadasList() {
		KullaniciOperation operation = new KullaniciOperation();
		
		
		ArrayList<String> arkadaslarim = new ArrayList<String>();
		
		ArrayList<KullaniciEntity> alArkadasEntity = operation.getArkadasList(kullaniciId);
		
		for (KullaniciEntity entity : alArkadasEntity) {
			arkadaslarim.add(entity.getAdSoyad());
		}

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
		String arkadas = alArkadaslarim.get(position);
		//Toast.makeText(this, arkadasAdi, Toast.LENGTH_SHORT).show();
		
		Intent i = new Intent(this, ArkadasEtkinlikleriActivity.class);
		i.putExtra("kullaniciAdi", kullaniciAdi);
		i.putExtra("arkadasAdi", arkadas);
		startActivity(i);
	}

}
