/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gzp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
/**
 *
 * @author EGE
 */
public class Dijkstra {
     public void yolHesapla(Sehirler ilksehir){
        ilksehir.setEnKucukUzaklik(0);
        PriorityQueue<Sehirler> priorityQueue=new PriorityQueue<>();
        priorityQueue.add(ilksehir);

        while (!priorityQueue.isEmpty()) {
            Sehirler guncelSehir=priorityQueue.poll();
            for(Baglanti baglanti:guncelSehir.getKomsulukListesi())
            {
                Sehirler g=baglanti.getGidilecekSehir();

                double maaliyet=baglanti.getMaaliyet();
                double minuzaklıkG=guncelSehir.getEnKucukUzaklik()+maaliyet;

                if(minuzaklıkG < g.getEnKucukUzaklik())
                {
                    priorityQueue.remove(g);
                    g.setoncekiSehir(guncelSehir);
                    g.setEnKucukUzaklik(minuzaklıkG);
                    priorityQueue.add(g);

                }

            }
        }
    }


    public List<Sehirler> enkisayolugetir(Sehirler gidilecekSehir)
    {
        List<Sehirler> yol=new ArrayList<>();
        for(Sehirler sehir=gidilecekSehir;sehir!=null;sehir=sehir.getOncekisehirSehir())
        {
            yol.add(sehir);
        }

        Collections.reverse(yol);
        return yol;
    }

}


