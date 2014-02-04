package com.mersin.event;

import com.mersin.entity.KullaniciEntity;
import com.mersin.entity.KullaniciOperation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KayitActivity extends Activity implements OnClickListener{

	EditText kayitEtAdSoyad;
	EditText kayitEtKullaniciAdi;
	EditText kayitEtEmail;
	EditText kayitEtSifre;
	Button btnKayit;
	KullaniciEntity kullanici;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kayit);
		
		kayitEtAdSoyad = (EditText) findViewById(R.id.kayitEtAdSoyad);
		kayitEtKullaniciAdi = (EditText) findViewById(R.id.kayitEtKullaniciAdi);
		kayitEtEmail = (EditText) findViewById(R.id.kayitEtEmail);
		kayitEtSifre = (EditText) findViewById(R.id.kayitEtSifre);
		btnKayit = (Button) findViewById(R.id.kayitBtnKayit);
		
		btnKayit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v==btnKayit){
			kaydet();
		}
		
	}	
	
	class AsenkronArkadasEkle extends AsyncTask<KullaniciEntity, Void, Boolean>{

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(KayitActivity.this);
			dialog.setTitle("Kaydediliyor");
			dialog.setMessage("Kullanıcı oluşturuluyor");
			dialog.show();
		}
		
		@Override
		protected Boolean doInBackground(KullaniciEntity... params) {
			KullaniciOperation operation = new KullaniciOperation();
			boolean kayitBasarili = operation.kayit(params[0]);
			return kayitBasarili;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			
			super.onPostExecute(result);
			if (result) {
				Intent i = new Intent(KayitActivity.this, LoginActivity.class);
				startActivity(i);
				Toast.makeText(KayitActivity.this, "Başarıyla kaydedildi", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(KayitActivity.this, "Kaydedilemedi", Toast.LENGTH_SHORT).show();
			}
			dialog.dismiss();
		}
		
	}
	
	private void kaydet() {
		String adSoyad = kayitEtAdSoyad.getText().toString();
		String kullaniciAdi = kayitEtKullaniciAdi.getText().toString();
		String email = kayitEtEmail.getText().toString();
		String sifre = kayitEtSifre.getText().toString();
		
		if (adSoyad.isEmpty() || kullaniciAdi.isEmpty() || email.isEmpty() || sifre.isEmpty()) {
			Genel.uyar(this);
		} else {
			//String mesaj = adSoyad + " " + kullaniciAdi + " " + email + " " + sifre;
			
			kullanici = new KullaniciEntity();
			
			kullanici.setAdSoyad(adSoyad);
			kullanici.setKullaniciAdi(kullaniciAdi);
			kullanici.setEmail(email);
			kullanici.setSifre(sifre);						
			
			AsenkronArkadasEkle async = new AsenkronArkadasEkle();
			async.execute(kullanici);		
		}
		
	}

		
}
