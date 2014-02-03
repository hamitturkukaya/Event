package com.mersin.event;

import java.util.ArrayList;
import java.util.List;

import com.mersin.entity.EtkinlikEntity;
import com.mersin.entity.EtkinlikOperation;

import android.os.Bundle;

import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ArkadasEtkinlikleriActivity extends ListActivity {
	String arkadasAdi;
	int arkadasId;
	TextView arkadasEtkinlikleriTvArkadasAdi;
	ArrayList<EtkinlikEntity> alArkadaslariminEtkinlikleri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arkadas_etkinlikleri);
		Bundle paket = getIntent().getExtras();
		arkadasAdi = paket.getString("arkadasAdi");
		arkadasId = paket.getInt("arkadasId");
		
		
		arkadasEtkinlikleriTvArkadasAdi = (TextView) findViewById(R.id.arkadasEtkinlikleriTvArkadasAdi);
		arkadasEtkinlikleriTvArkadasAdi.setText(arkadasAdi);
		
		EtkinlikOperation operation = new EtkinlikOperation();
		
		alArkadaslariminEtkinlikleri = operation.getArkadasEtkinlik(arkadasId);
		
		EtkinlikAdapter adp = new EtkinlikAdapter(this, R.layout.satir_tasarimi, alArkadaslariminEtkinlikleri);
		setListAdapter(adp);
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
		
		EtkinlikEntity etkinlik = alArkadaslariminEtkinlikleri.get(position);
		Toast.makeText(this, etkinlik.getAd(), Toast.LENGTH_SHORT).show();
	}

	public class EtkinlikAdapter extends ArrayAdapter<EtkinlikEntity> {

		ArrayList<EtkinlikEntity> etkinlikler;
		
		public EtkinlikAdapter(Context context, int resource, List<EtkinlikEntity> objects) {
			super(context, resource, objects);
			
			etkinlikler = (ArrayList<EtkinlikEntity>) objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			EtkinlikEntity etkinlik = etkinlikler.get(position);
			
			LayoutInflater inf = getLayoutInflater();
			
			View satir = inf.inflate(R.layout.satir_tasarimi, parent, false);
			TextView tvAdSoyad = (TextView) satir.findViewById(R.id.satirtasarimiTvAdSoyad);
			TextView tvKullaniciAdi = (TextView) satir.findViewById(R.id.satirtasarimikullaniciAdi);
			tvAdSoyad.setText(etkinlik.getAd());
			tvKullaniciAdi.setText(etkinlik.getAciklama());
			return satir;
		}
		
		
	}
}
