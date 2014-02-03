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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ArkadasAraActivity extends ListActivity implements OnClickListener{

	EditText arkadasAraEtAdSoyad;
	Button arkadasAraBtnAra;
	
	ArrayList<KullaniciEntity> alKullaniciEntity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arkadas_ara);
		
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
		String arkadasAdSoyad = arkadasAraEtAdSoyad.getText().toString();
		
		
	
		if (arkadasAdSoyad.isEmpty()){
			Genel.uyar(this);
		} else {
			
			//Toast.makeText(this, arkadasAdSoyad, Toast.LENGTH_LONG).show();
			AsenkronArkadasAra async = new AsenkronArkadasAra(arkadasAdSoyad);
			async.execute();
			
		}
		
	}
	
	public class AsenkronArkadasAra extends AsyncTask<Void, Void, ArrayList<KullaniciEntity>> {
		
		String arkadasAdSoyad;
		ProgressDialog prg;
		public AsenkronArkadasAra(String arkadasAd) {
			arkadasAdSoyad = arkadasAd;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			prg = new ProgressDialog(ArkadasAraActivity.this);
			prg.setTitle("Aranıyor");
			prg.setMessage("Arkadaş litesi aranıyor");
			prg.show();
		}
		
		@Override
		protected ArrayList<KullaniciEntity> doInBackground(Void... params) {
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			KullaniciOperation operation = new KullaniciOperation();
			alKullaniciEntity = operation.arkadasAra(arkadasAdSoyad);
			
			return alKullaniciEntity;
		}
		
		@Override
		protected void onPostExecute(ArrayList<KullaniciEntity> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			ArkadasAdapter adp = new ArkadasAdapter(ArkadasAraActivity.this, R.layout.satir_tasarimi, alKullaniciEntity);
			setListAdapter(adp);
			prg.dismiss();
		}
		
	}
	
	public class ArkadasAdapter extends ArrayAdapter<KullaniciEntity>{

		ArrayList<KullaniciEntity> arkadaslar;
		
		public ArkadasAdapter(Context context, int resource, List<KullaniciEntity> objects) {
			super(context, resource, objects);
			
			arkadaslar = (ArrayList<KullaniciEntity>) objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			KullaniciEntity kullanici = arkadaslar.get(position);
			
			LayoutInflater inf = getLayoutInflater();
			View satir = inf.inflate(R.layout.satir_tasarimi, parent, false);
			
			TextView tvAdSoyad = (TextView) satir.findViewById(R.id.satirtasarimiTvAdSoyad);
			TextView tvKullaniciAdi = (TextView) satir.findViewById(R.id.satirtasarimikullaniciAdi);
			
			tvAdSoyad.setText(kullanici.getAdSoyad());
			tvKullaniciAdi.setText(kullanici.getKullaniciAdi());
			
			return satir;
			
		}

		
		
	}
	
	

}
