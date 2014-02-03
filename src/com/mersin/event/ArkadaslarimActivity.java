package com.mersin.event;

import java.util.ArrayList;
import java.util.List;

import com.mersin.entity.KullaniciEntity;
import com.mersin.entity.KullaniciOperation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ArkadaslarimActivity extends ListActivity {

	ArrayList<KullaniciEntity> alArkadaslarim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arkadaslarim);

		AsenkronArkadaslarim asenkArk = new AsenkronArkadaslarim();
		asenkArk.execute();
	}

	private ArrayList<KullaniciEntity> getArkadasList() {
		KullaniciOperation operation = new KullaniciOperation();
			
		ArrayList<KullaniciEntity> sonuc = operation.getArkadasList(Genel.kullaniciId);
		
		return sonuc;
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
			startActivity(i);
			
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {	
		super.onListItemClick(l, v, position, id);
		
		KullaniciEntity arkadas = alArkadaslarim.get(position);
		//Toast.makeText(this, arkadasAdi, Toast.LENGTH_SHORT).show();
		
		Intent i = new Intent(this, ArkadasEtkinlikleriActivity.class);
		i.putExtra("arkadasAdi", arkadas.getAdSoyad());
		i.putExtra("arkadasId", arkadas.getId());
		startActivity(i);
	}
	
	public class KullaniciAdapter extends ArrayAdapter<KullaniciEntity> {

		ArrayList<KullaniciEntity> kullanicilar;
		
		public KullaniciAdapter(Context context, int resource, List<KullaniciEntity> objects) {
			super(context, resource, objects);
			
			kullanicilar = (ArrayList<KullaniciEntity>) objects;
		}
		
		@Override			
		public View getView(int position, View convertView, ViewGroup parent) {
			
			KullaniciEntity kullanici = kullanicilar.get(position);
			LayoutInflater layoutInf = getLayoutInflater();
			
			
			View satir = layoutInf.inflate(R.layout.satir_tasarimi, parent, false);
			
			TextView tvAdsoyad = (TextView) satir.findViewById(R.id.satirtasarimiTvAdSoyad);
			TextView tvKullaniciAdi = (TextView) satir.findViewById(R.id.satirtasarimikullaniciAdi);
			
			tvAdsoyad.setText(kullanici.getAdSoyad());
			tvKullaniciAdi.setText(kullanici.getKullaniciAdi());
			
			return satir;
			
		}
		
	}
	
	public class AsenkronArkadaslarim extends AsyncTask<Void, Void, ArrayList<KullaniciEntity>>{

		ProgressDialog pDialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pDialog = new ProgressDialog(ArkadaslarimActivity.this);
			pDialog.setTitle("Bekleyiniz");
			pDialog.setMessage("Arkadaş listesi alınıyor");
			pDialog.show();
		}
		
		@Override
		protected ArrayList<KullaniciEntity> doInBackground(Void... params) {
			// dialoğu görmek için sleep koyuyoruz
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return getArkadasList();
		}
		
		@Override
		protected void onPostExecute(ArrayList<KullaniciEntity> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			alArkadaslarim = result;
			
			KullaniciAdapter adp = new KullaniciAdapter(ArkadaslarimActivity.this, R.layout.satir_tasarimi, result);
			setListAdapter(adp);
			
			pDialog.dismiss();
		}
		
	}
}
