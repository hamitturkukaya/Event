package com.mersin.entity;

import java.util.ArrayList;

import android.R.integer;

public class KullaniciOperation {
	
	public KullaniciEntity giris(String kullaniciAdi, String sifre) {
		// Burada web servisi ile kontrol yapılacak 
		KullaniciEntity sonuc = null;
		
		if(kullaniciAdi.equals("1") && sifre.equals("1")){
			sonuc = new KullaniciEntity();
			sonuc.setId(1);
			sonuc.setAdSoyad("H. Türkü Kaya");
			sonuc.setEmail("htkaya90@gmail.com");
			sonuc.setKullaniciAdi("1");
			sonuc.setSifre("1");
		}
		
		return sonuc;		
	}
	
	public Boolean kayit(KullaniciEntity kullanici) {
		
		boolean sonuc = false;
		
		String adSoyad = kullanici.getAdSoyad();
		String kullaniciAdi = kullanici.getKullaniciAdi();
		String email = kullanici.getEmail();
		String sifre = kullanici.getSifre();
			
		boolean webServisSonuc = true; // Burada web servisi ile kullanıcı kaydedilecek
		
		if (webServisSonuc) {
			kullanici.setId(2); // Buradaki Id web servisten dönecek
			sonuc = true;
		} 
		
		return sonuc;
		
	}
	
	public ArrayList<KullaniciEntity> getArkadasList(int kullaniciId) {
		
		ArrayList<KullaniciEntity> arkadaslar = new ArrayList<KullaniciEntity>();
		
		// Burada web servisinden bu kişinin arkadaş listesini çekeceğiz.
		
		KullaniciEntity kul1= new KullaniciEntity();
		
		kul1.setId(3);
		kul1.setAdSoyad("Ali");
		kul1.setKullaniciAdi("ali");
		kul1.setEmail("ali@ab.org");
	

		KullaniciEntity kul2= new KullaniciEntity();
		
		kul2.setId(4);
		kul2.setAdSoyad("Veli");
		kul2.setKullaniciAdi("veli");
		kul2.setEmail("veli@ab.org");
		
		KullaniciEntity kul3= new KullaniciEntity();
		
		kul3.setId(4);
		kul3.setAdSoyad("Ahmet");
		kul3.setKullaniciAdi("ahmet");
		kul3.setEmail("ahmet@ab.org");
		
		KullaniciEntity kul4= new KullaniciEntity();
		
		kul4.setId(4);
		kul4.setAdSoyad("Mehmet");
		kul4.setKullaniciAdi("mehmet");
		kul4.setEmail("mehmet@ab.org");
		
		
		arkadaslar.add(kul1);	
		arkadaslar.add(kul2);
		arkadaslar.add(kul3);
		arkadaslar.add(kul4);
		return arkadaslar;
		
	}
}