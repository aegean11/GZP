/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gzp;
   private int yolcuKapasitesi;
    private float egim;
    private float maliyet;

/**
 *
 * @author EGE
 */
public class Zeplin {
       this.yolcuKapasitesi = yolcuKapasitesi;
    }

    public float getEgim() {
        return 80-this.getYolcuKapasitesi();
    }

   /* public void setEgim(float egim) {
        this.egim = egim;
    }
*/
    public float getMaliyet() {
        return maliyet;
    }

    public void setMaliyet(float maliyet) {
        this.maliyet = maliyet;
    }

    public int getYolcuKapasitesi() {

        return yolcuKapasitesi;
    }

    public void setYolcuKapasitesi(int yolcuKapasitesi) {
        this.yolcuKapasitesi = yolcuKapasitesi;
    }
}

}
