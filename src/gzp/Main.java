/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gzp;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author EGE
 */
public class Main {
    public static String satir;
    public static double bilgiler[][] = new double[81][4];
    public static ArrayList<Sehirler> sehirArray = new ArrayList<>();
    public static ArrayList<Baglanti> yols = new ArrayList<>();
    public static int yukseklik = 50;

    public static void main(String[] args) {
        dosyaOkuma();
        graphOlusturma();
        Zeplin zeplin = new Zeplin(zeplinOlusturma());
        ZeplinHavalandi(sehirArray.get(33), sehirArray.get(0), zeplin);




    }

    public static void dosyaOkuma() {

        //ilk satırları sildim
        try (Scanner scanner = new Scanner(new FileReader("latlong.txt"))) {
            while (scanner.hasNextLine()) {
                satir = scanner.nextLine();
                String satirs[] = null;
                satirs = satir.split(",");
                //System.out.println(satirs[0]);

                double lat = Double.valueOf(satirs[0]);
                double longutidue = Double.valueOf(satirs[1]);
                int plakaKodu = Integer.valueOf(satirs[2]);
                int rakim = Integer.valueOf(satirs[3]);

                Scanner scanner2 = new Scanner(new FileReader("sehirler.txt"));

                for (int i = 0; i < plakaKodu; i++)
                    satir = scanner2.nextLine();
                satirs = satir.split(",");
                String sehirIsmi = satirs[1];
                sehirArray.add(new Sehirler(plakaKodu, sehirIsmi, lat, longutidue, rakim));

            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya Bulunamadı");
        } catch (IOException e) {
            System.out.println("dosya açılırken bir hata oluştu");
        }


    }


    public static void graphOlusturma() {

        try (Scanner scanner = new Scanner(new FileReader("güncel_komsular.txt"))) {
            int j = 0;
            while (scanner.hasNextLine()) {
                String satir1 = scanner.nextLine();
                //System.out.println(satir1);
                String[] buffer = satir1.split("\n");
                //System.out.println(buffer.length);

                String[] buffer1=satir1.split(",");
                int buffer3=Integer.parseInt(buffer1[0]);
             //   System.out.println(buffer3+"------");
                for(int i=1;i<buffer1.length;i++)
                {
                   // System.out.println(buffer1[0]);
                    int buffer2=Integer.parseInt(buffer1[i]);
                   // System.out.println(buffer2);
                    double weight = getDistanceFromLatLonInKm(sehirArray.get(j).getLat(), sehirArray.get(j).getLongitude(), sehirArray.get(buffer2 - 1).getLat(), sehirArray.get(buffer2 - 1).getLongitude());
                    sehirArray.get(j).komsuEkle(new Baglanti(weight,sehirArray.get(j) ,sehirArray.get(buffer2 - 1)));
                    yols.add(new Baglanti(weight,sehirArray.get(j),sehirArray.get(buffer2 - 1)));

                }

                       j++;


            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya Bulunamadı");
        } catch (IOException e) {
            System.out.println("dosya açılırken bir hata oluştu");
        }

    }


    public static double getDistanceFromLatLonInKm(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2 - lat1);  // deg2rad below
        double dLon = deg2rad(lon2 - lon1);
        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(deg2rad(lat1)) *
                        Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c; // Distance in km
        return d;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static int zeplinOlusturma() {
        System.out.println("Lütfen Yolcu Sayısını giriniz");
        Scanner scan = new Scanner(System.in);
        int flag = 0;
        int yolcusayisi;

        yolcusayisi = scan.nextInt();

        while (yolcusayisi > 50 || yolcusayisi < 5) {
            System.out.println("Yanlış Girdiniz");
            yolcusayisi = scan.nextInt();
        }

        return yolcusayisi;


    }

    public static void ZeplinHavalandi(Sehirler nerden, Sehirler nereye, Zeplin zeplin) {
        Dijkstra alg = new Dijkstra();
        alg.yolHesapla(nerden);
        List<Sehirler> yollar = new ArrayList<>();
        int size = yollar.size();
        yollar = alg.enkisayolugetir(nereye);
        System.out.println(yollar);
        Sehirler sehir1[] = new Sehirler[yollar.size()];
        int i = 0;
        for (Sehirler sehir = nereye; sehir != null; sehir = sehir.getOncekisehirSehir()) {


            sehir1[i] = sehir;
            i++;
        }


        double y = Math.abs(sehir1[yollar.size() - 1].rakim - sehir1[yollar.size() - 2].rakim + 50);
        double aci = Math.toDegrees(Math.atan2(y, sehir1[yollar.size() - 2].getEnKucukUzaklik()));
        // System.out.println(aci);
        if (aci < 0) {
            aci = aci + 360;
        }

        if (aci < zeplin.getEgim()) {

            nerden.getKomsulukListesi();

            List<Baglanti> komsuluk = sehir1[yollar.size() - 2].getKomsulukListesi();
            List<Baglanti> komsuluk2 = nerden.getKomsulukListesi();


            for (int j = 0; j < komsuluk.size(); j++) {
                if (komsuluk.get(j).getGidilecekSehir() == sehir1[yollar.size() - 1]) {
                    sehir1[yollar.size() - 1].komsuSil(sehirArray.get(40).getKomsulukListesi().get(3));
                    System.out.println(sehirArray.get(40).getKomsulukListesi().get(3).getMaaliyet());

                    // System.out.println(sehir1[yollar.size() - 1].getKomsulukListesi().get(1).getMaaliyet());

                    //System.out.println(komsuluk.get(j).getMaaliyet());
                    //  sehir1[yollar.size() - 1].komsuEkle(new Baglanti(Double.MAX_VALUE,sehir1[yollar.size() - 1],sehir1[yollar.size() - 2]));







                }


            }

            for (int j = 0; j < komsuluk2.size(); j++) {

                if (komsuluk2.get(j).getGidilecekSehir() == sehir1[yollar.size() - 2]) {

                    sehir1[yollar.size() - 2].komsuSil(komsuluk2.get(j));
                    //  sehir1[yollar.size() - 2].komsuEkle(new Baglanti(Double.MAX_VALUE,sehir1[yollar.size() - 2],sehir1[yollar.size() - 1]));


                    // komsuluk2.get(j).setMaaliyet(Double.MAX_VALUE);




                }
            }









        }


    }



}


