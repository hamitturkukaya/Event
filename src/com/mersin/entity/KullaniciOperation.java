package com.mersin.entity;

import java.net.SocketOptions;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.Parser;

import android.R.integer;
import android.util.Log;

public class KullaniciOperation {
	
	public KullaniciEntity giris(String kullaniciAdi, String sifre) {
		// Burada web servisi ile kontrol yapılacak 
		KullaniciEntity sonuc = null;
		
		String method_name = "giris";
		String namespace = "http://tempuri.org/";
		String soap_action = "http://tempuri.org/giris";

		String URL = "http://btkampus.net/etkinlik.asmx";

		// Soap request hazırlanması
		SoapObject request = new SoapObject(namespace, method_name);
		request.addProperty("kullaniciAdi", kullaniciAdi);
		request.addProperty("sifre", sifre);
		
		/*
		 * parametre alırsa
		 */

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);

		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		androidHttpTransport.debug = true;
		try {
			androidHttpTransport.call(soap_action, envelope);
			SoapObject response = (SoapObject) envelope.getResponse();

			if (response != null && response.getPropertyCount() > 0) 
			{
				int id = Integer.parseInt(response.getProperty("Id").toString());
				String ad = response.getProperty("KullaniciAdi").toString();
				String email = response.getProperty("Email").toString();
				String adSoyad = response.getProperty("AdSoyad").toString();

				sonuc = new KullaniciEntity();
				
				sonuc.setId(id);
				sonuc.setAdSoyad(adSoyad);
				sonuc.setEmail(email);
				sonuc.setKullaniciAdi(ad);
				
			}
		} catch (Exception e1) {
			e1.printStackTrace();

		}
		
		/*
		if(kullaniciAdi.equals("1") && sifre.equals("1")){
			sonuc = new KullaniciEntity();
			sonuc.setId(1);
			sonuc.setAdSoyad("H. Türkü Kaya");
			sonuc.setEmail("htkaya90@gmail.com");
			sonuc.setKullaniciAdi("1");
			sonuc.setSifre("1");
		}*/
		
		
		return sonuc;		
	}
	
	public Boolean kayit(KullaniciEntity kullanici) {
		
		boolean sonuc = false;
		int kullaniciId = 0;
		String adSoyad = kullanici.getAdSoyad();
		String kullaniciAdi = kullanici.getKullaniciAdi();
		String email = kullanici.getEmail();
		String sifre = kullanici.getSifre();
		
		String method_name = "InsertKullanici";
		String namespace = "http://tempuri.org/";
		String soap_action = "http://tempuri.org/InsertKullanici";

		String URL = "http://btkampus.net/etkinlik.asmx";

		// Soap request hazırlanması
		SoapObject request = new SoapObject(namespace, method_name);
		request.addProperty("kullaniciAdi", kullaniciAdi);
		request.addProperty("email", email);
		request.addProperty("sifre", sifre);
		request.addProperty("adSoyad", adSoyad);
		/*
		 * parametre alırsa
		 */

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);

		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		androidHttpTransport.debug = true;
		try {
			androidHttpTransport.call(soap_action, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			
			 kullaniciId = Integer.parseInt(response.toString());	
			kullanici.setId(kullaniciId);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		if (kullaniciId>0){
			sonuc = true;
		}
		
	
		
		return sonuc;
		
	}
	
	public ArrayList<KullaniciEntity> getArkadasList(int kullaniciId) {
		
		ArrayList<KullaniciEntity> arkadaslar = new ArrayList<KullaniciEntity>();
		
		// Burada web servisinden bu kişinin arkadaş listesini çekeceğiz.
		
		
		String method_name = "GetArkadasList";
		String namespace = "http://tempuri.org/";
		String soap_action = "http://tempuri.org/GetArkadasList";

		String URL = "http://btkampus.net/etkinlik.asmx";

		// Soap request hazırlanması
		SoapObject request = new SoapObject(namespace, method_name);
		request.addProperty("kullaniciId", kullaniciId);
		
		/*
		 * parametre alırsa
		 */

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);

		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		androidHttpTransport.debug = true;
		try {
			androidHttpTransport.call(soap_action, envelope);
			SoapObject response = (SoapObject) envelope.getResponse();

			if (response.hasProperty("KullaniciEntity")) 
			{
				int arkadasSayisi = response.getPropertyCount();
				for (int i = 0; i < arkadasSayisi; i++) {
					SoapObject soapSoz = (SoapObject) response.getProperty(i);

					int id = Integer.parseInt(soapSoz.getProperty("Id").toString());
					String kullaniciAdi = soapSoz.getProperty("KullaniciAdi").toString();
					String email = soapSoz.getProperty("Email").toString();
					String adSoyad = soapSoz.getProperty("AdSoyad").toString();

					KullaniciEntity kulEn = new KullaniciEntity();
					
					kulEn.setId(id);
					kulEn.setAdSoyad(adSoyad);
					kulEn.setEmail(email);
					kulEn.setKullaniciAdi(kullaniciAdi);
					arkadaslar.add(kulEn);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();

		}

		return arkadaslar;
		
	}
	
	public ArrayList<KullaniciEntity> arkadasAra(String arkadasAdSoyad){
		
		ArrayList<KullaniciEntity> arkadaslar = new ArrayList<KullaniciEntity>();
		
		// Burada web servisinden ad soyada gore kişiler listelenecek 
		String method_name = "ArkadasAra";
		String namespace = "http://tempuri.org/";
		String soap_action = "http://tempuri.org/ArkadasAra";

		String URL = "http://btkampus.net/etkinlik.asmx";

		// Soap request hazırlanması
		SoapObject request = new SoapObject(namespace, method_name);
		request.addProperty("adSoyad", arkadasAdSoyad);
		
		/*
		 * parametre alırsa
		 */

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);

		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		androidHttpTransport.debug = true;
		try {
			androidHttpTransport.call(soap_action, envelope);
			SoapObject response = (SoapObject) envelope.getResponse();

			if (response.hasProperty("KullaniciEntity")) 
			{
				int arkadasSayisi = response.getPropertyCount();
				for (int i = 0; i < arkadasSayisi; i++) {
					SoapObject soapSoz = (SoapObject) response.getProperty(i);

					int id = Integer.parseInt(soapSoz.getProperty("Id").toString());
					String kullaniciAdi = soapSoz.getProperty("KullaniciAdi").toString();
					String email = soapSoz.getProperty("Email").toString();
					String adSoyad = soapSoz.getProperty("AdSoyad").toString();

					KullaniciEntity kulEn = new KullaniciEntity();
					
					kulEn.setId(id);
					kulEn.setAdSoyad(adSoyad);
					kulEn.setEmail(email);
					kulEn.setKullaniciAdi(kullaniciAdi);
					arkadaslar.add(kulEn);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();

		}
		/*
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
		
		*/
		return arkadaslar;
		
	}
}