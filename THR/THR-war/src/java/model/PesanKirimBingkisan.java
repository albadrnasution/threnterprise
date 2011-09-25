package model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */
public class PesanKirimBingkisan {
    private int ido;
    private int idc;
    private int idp;
    private String alamat;
    private int harga;
    private SatusKirim stat_kirim;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
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

    public SatusKirim getStat_kirim() {
        return stat_kirim;
    }

    public void setStat_kirim(SatusKirim stat_kirim) {
        this.stat_kirim = stat_kirim;
    }
    
    
}
