/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gzp;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author EGE
 */
public class Sehirler implements Comparable<Sehirler> {
       private int plakaKodu;
             private String isim;
             private List<Baglanti> komsulukListesi;
             private double lat;
             private double longitude;
             private Sehirler oncekisehir;
             private double enKucukUzaklik=Double.MAX_VALUE;
             int rakim;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getRakim() {
        return rakim;
    }

    public void setRakim(int rakim) {
        this.rakim = rakim;
    }




             public Sehirler(int plakakodu,String isim,double lat,double longitude,int rakim)
             {
                 this.isim=isim;
                 this.plakaKodu=plakakodu;
                 this.komsulukListesi=new ArrayList<>();
                 this.lat=lat;
                 this.longitude=longitude;
                 this.rakim=rakim;
             }

    public List<Baglanti> getKomsulukListesi() {
        return komsulukListesi;
    }

    public void setKomsulukListesi(List<Baglanti> komsulukListesi) {
        this.komsulukListesi = komsulukListesi;
    }



    public Sehirler getOncekisehirSehir() {
        return oncekisehir;
    }

    public void setoncekiSehir(Sehirler gelecekSehir) {
        this.oncekisehir = gelecekSehir;
    }

    public double getEnKucukUzaklik() {
        return enKucukUzaklik;
    }

    public void setEnKucukUzaklik(double enKucukUzaklik) {
        this.enKucukUzaklik = enKucukUzaklik;
    }

    public void komsuEkle(Baglanti baglanti)
             {
                this.komsulukListesi.add(baglanti);
             }

    public void komsuSil(Baglanti baglanti)
    {
        this.komsulukListesi.remove(baglanti);
    }


             public String toString()
    {
        return this.isim+" "+this.plakaKodu;
    }

    @Override
    public int compareTo(Sehirler otekiSehir) {
        return Double.compare(this.enKucukUzaklik,otekiSehir.enKucukUzaklik);
    }

    public int getPlakaKodu() {
        return plakaKodu;
    }

    public void setPlakaKodu(int plakaKodu) {
        this.plakaKodu = plakaKodu;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }


}


