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
public class PesanBingkisan {
    private int ido;
    private int idp;
    private int idc;
    private boolean pay_status;
    private int jumlah_paket;
    private Date order_date;
    private Date due_date;

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getJumlah_paket() {
        return jumlah_paket;
    }

    public void setJumlah_paket(int jumlah_paket) {
        this.jumlah_paket = jumlah_paket;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public boolean isPay_status() {
        return pay_status;
    }

    public void setPay_status(boolean pay_status) {
        this.pay_status = pay_status;
    }
    
    
}
