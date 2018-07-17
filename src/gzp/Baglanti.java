/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gzp;

/**
 *
 * @author EGE
 */
public class Baglanti {
    private double maaliyet;
private Sehirler gidilecekSehir;
private Sehirler nerden;

    public Sehirler getNerden() {
        return nerden;
    }

    public void setNerden(Sehirler nerden) {
        this.nerden = nerden;
    }

    public Baglanti(double maaliyet, Sehirler nerden, Sehirler gidilecekSehir) {
        this.maaliyet = maaliyet;
        this.gidilecekSehir = gidilecekSehir;
        this.nerden=nerden;

    }

    public double getMaaliyet() {
        return maaliyet;
    }

    public void setMaaliyet(double maaliyet) {
        this.maaliyet = maaliyet;
    }



    public Sehirler getGidilecekSehir() {
        return gidilecekSehir;
    }

    public void setGidilecekSehir(Sehirler gidilecekSehir) {
        this.gidilecekSehir = gidilecekSehir;
    }

    @Override
    public String toString() {
        return String.valueOf(gidilecekSehir);
    }

    Sehirler getGidilecekSehir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


