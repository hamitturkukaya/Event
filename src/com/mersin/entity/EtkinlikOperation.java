package com.mersin.entity;

import java.util.ArrayList;

public class EtkinlikOperation {
	public Boolean kayit(EtkinlikEntity etkinlik) {
		
		boolean sonuc = false;
		
		String ad = etkinlik.getAd();
		String aciklama = etkinlik.getAciklama();
		String yer = etkinlik.getYer();
		int kullaniciId = etkinlik.getKullaniciId();
			
		boolean webServisSonuc = true; // Burada web servisi ile etkinlik kaydedilecek
		
		if (webServisSonuc) {
			etkinlik.setId(2); // Buradaki Id web servisten dönecek
			sonuc = true;
		} 
		
		return sonuc;
		
	}
	
	public ArrayList<EtkinlikEntity> getEtkinlikList() {
		ArrayList<EtkinlikEntity> etkinlikler = new ArrayList<EtkinlikEntity>();
		
		EtkinlikEntity etk1 = new EtkinlikEntity();
		
		etk1.setId(3);
		etk1.setAd("AB 2014");
		etk1.setAciklama("x");
		etk1.setYer("Mersin");
		
		EtkinlikEntity etk2 = new EtkinlikEntity();
		
		etk2.setId(4);
		etk2.setAd("LKD 2014");
		etk2.setAciklama("x");
		etk2.setYer("Mersin");
		
		EtkinlikEntity etk3 = new EtkinlikEntity();
		
		etk3.setId(5);
		etk3.setAd("Doğum günü");
		etk3.setAciklama("x");
		etk3.setYer("Mersin");
		
		EtkinlikEntity etk4 = new EtkinlikEntity();
		
		etk4.setId(5);
		etk4.setAd("Yılbaşı");
		etk4.setAciklama("x");
		etk4.setYer("Mersin");
		
		etkinlikler.add(etk1);
		etkinlikler.add(etk2);
		etkinlikler.add(etk3);
		etkinlikler.add(etk4);
		return etkinlikler;
	}

	public ArrayList<EtkinlikEntity> getArkadasEtkinlik(int arkadasId) {
		
		ArrayList<EtkinlikEntity> etkinlikler = new ArrayList<EtkinlikEntity>();		
		EtkinlikEntity etk1 = new EtkinlikEntity();	
		etk1.setId(3);
		etk1.setAd("AB 2014");
		etk1.setAciklama("x");
		etk1.setYer("Mersin");
		
		EtkinlikEntity etk2 = new EtkinlikEntity();
		
		etk2.setId(4);
		etk2.setAd("LKD 2014");
		etk2.setAciklama("x");
		etk2.setYer("Mersin");
		
		EtkinlikEntity etk3 = new EtkinlikEntity();
		
		etk3.setId(5);
		etk3.setAd("Doğum günü");
		etk3.setAciklama("x");
		etk3.setYer("Mersin");
		
		EtkinlikEntity etk4 = new EtkinlikEntity();
		
		etk4.setId(5);
		etk4.setAd("Yılbaşı");
		etk4.setAciklama("x");
		etk4.setYer("Mersin");
		
		etkinlikler.add(etk1);
		etkinlikler.add(etk2);
		etkinlikler.add(etk3);
		etkinlikler.add(etk4);
		return etkinlikler;
		
	}

}
