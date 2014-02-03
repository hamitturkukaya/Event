package com.mersin.event;

import java.util.ArrayList;
import java.util.List;

import com.mersin.entity.EtkinlikEntity;
import com.mersin.entity.EtkinlikOperation;
import com.mersin.entity.KullaniciEntity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EtkinliklerimActivity extends ListActivity {

	ArrayList<EtkinlikEntity> alEtkinliklerim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_etkinliklerim);
		AsenkronEtkinlik async = new AsenkronEtkinlik();
		async.execute();
		
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
		startActivity(i);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		EtkinlikEntity etkinlik = alEtkinliklerim.get(position);
		Toast.makeText(this, etkinlik.getAd(), Toast.LENGTH_SHORT).show();
	}

	public class EtkinlikAdapter extends ArrayAdapter<EtkinlikEntity>{

		ArrayList<EtkinlikEntity> etkinlikler;
		
		public EtkinlikAdapter(Context context, int resource, List<EtkinlikEntity> objects) {
			super(context, resource, objects);
			etkinlikler = (ArrayList<EtkinlikEntity>) objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			EtkinlikEntity etkinlik = etkinlikler.get(position);
			
			LayoutInflater inf = getLayoutInflater();
			
			View satir = inf.inflate(R.layout.satir_tasarimi, parent, false);
			
			TextView ad = (TextView) satir.findViewById(R.id.satirtasarimiTvAdSoyad);
			TextView aciklama = (TextView) satir.findViewById(R.id.satirtasarimikullaniciAdi);
			ad.setText(etkinlik.getAd());
			aciklama.setText(etkinlik.getAciklama());
			
			return satir;
			
		}
		
	}
	
	public class AsenkronEtkinlik extends AsyncTask<Void, Void, ArrayList<EtkinlikEntity>>{

		ProgressDialog prg;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			prg = new ProgressDialog(EtkinliklerimActivity.this);
			prg.setTitle("Yükleiyor");
			prg.setMessage("Etkinlikler Alınıyor");
			prg.show();
		}
		
		@Override
		protected ArrayList<EtkinlikEntity> doInBackground(Void... params) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EtkinlikOperation operation = new EtkinlikOperation();
			alEtkinliklerim = operation.getEtkinlikList();
			return alEtkinliklerim;
		}
		
		@Override
		protected void onPostExecute(ArrayList<EtkinlikEntity> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			EtkinlikAdapter adp = new EtkinlikAdapter(EtkinliklerimActivity.this, R.layout.satir_tasarimi2, alEtkinliklerim);
			setListAdapter(adp);
			prg.dismiss();
		}
	}

}
