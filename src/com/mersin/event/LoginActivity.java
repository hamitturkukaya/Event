package com.mersin.event;

import com.mersin.entity.KullaniciEntity;
import com.mersin.entity.KullaniciOperation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{

	EditText loginEtKullaniciAdi;
	EditText loginEtSifre;
	Button loginBtnGiris;
	Button loginBtnUyeOl;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		loginEtKullaniciAdi = (EditText) findViewById(R.id.loginEtKullaniciAdi);
		loginEtSifre = (EditText) findViewById(R.id.loginEtSifre);
		loginBtnGiris = (Button) findViewById(R.id.loginBtnGiris);
		loginBtnUyeOl = (Button) findViewById(R.id.loginBtnUyeOl);
		loginBtnGiris.setOnClickListener(this);
		loginBtnUyeOl.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == loginBtnGiris) {
			giris();
		} else if (v == loginBtnUyeOl) {
			uyeOl();
		}
	}

	private void uyeOl() {
		Intent i = new Intent(this, KayitActivity.class);
		startActivity(i);
	}
	
	class AsenkronGiris extends AsyncTask<String, Void, KullaniciEntity>{

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(LoginActivity.this);
			dialog.setTitle("Lütfen Bekleyin");
			dialog.setMessage("Giriş Yapılıyor");
			dialog.show();
		}
		
		@Override
		protected KullaniciEntity doInBackground(String... params) {
			KullaniciOperation operation = new KullaniciOperation();
			KullaniciEntity kullanici = operation.giris(params[0], params[1]);
			
			return kullanici;
		}
		
		@Override
		protected void onPostExecute(KullaniciEntity result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null) {
				Genel.kullaniciId = result.getId();
				Genel.kullaniciAdi = result.getKullaniciAdi();
				Intent i = new Intent(LoginActivity.this, AnaActivity.class);
				startActivity(i);
			} else {
				Toast.makeText(LoginActivity.this, "Başarısız", Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();
		}

		
	}

	private void giris() {
		
		String kullaniciAdi = loginEtKullaniciAdi.getText().toString();
		String sifre  = loginEtSifre.getText().toString();
		if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
			Genel.uyar(this);
		} else {
			

			AsenkronGiris async = new AsenkronGiris();
			async.execute(kullaniciAdi, sifre);
			

		}
	}

}
