package model;


import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */
public class PaketJalan {
    private int idp;
    private String paket_nama;
    private String description;
    private int total_price;
    private int nadult;
    private int nchild;
    private Date time;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getNadult() {
        return nadult;
    }

    public void setNadult(int nadult) {
        this.nadult = nadult;
    }

    public int getNchild() {
        return nchild;
    }

    public void setNchild(int nchild) {
        this.nchild = nchild;
    }

    public String getPaket_nama() {
        return paket_nama;
    }

    public void setPaket_nama(String paket_nama) {
        this.paket_nama = paket_nama;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
    
    
}
