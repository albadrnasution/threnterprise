package model;

import com.google.gson.Gson;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.Database;

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
    private int banyak_paket;

    public int getBanyak_paket() {
        return banyak_paket;
    }

    public void setBanyak_paket(int banyak_paket) {
        this.banyak_paket = banyak_paket;
    }

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
    
    public String addPesanKirimBingkisan()
    {
        Database db = new Database();
        String sql = null;
        try{
            sql="INSERT INTO pesan_kirim_bing (idp,idc,no_paket,alamat,harga,stat_kirim,banyak_paket) VALUES "
                   /*+ "('aa','las','add','phon','emil','pla','00-00-0000','asd')";*/
                + "("+this.getIdp()+","+this.getIdc()+","+0+",'"
                    + this.getAlamat() +"',"+this.getHarga()+","+"'pending',"+this.banyak_paket+")";
            
            Database.setConnection();
            Database.updatingQuery(sql);
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return sql;
    }
    
    public ArrayList<PesanKirimBingkisan> getPesanKirimBingkisanbyIdc(String idc){
        ResultSet rs;
        ArrayList<PesanKirimBingkisan> temp =new ArrayList<PesanKirimBingkisan>();
        String sql;
        try{
            sql="SELECT * FROM pesan_kirim_bing WHERE idc="+idc;
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanKirimBingkisan c = new PesanKirimBingkisan();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idc"));
                c.setAlamat(rs.getString("alamat"));
                c.setHarga(rs.getInt("harga"));
                temp.add(c);
                System.out.println(c.getIdc());
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public String getPesanKirimBingkisanbyIdc_asJSON(String cu) {
        String json = "";
        ArrayList<PesanKirimBingkisan> arrayPesan = getPesanKirimBingkisanbyIdc(cu);
        json = new Gson().toJson(arrayPesan);
        return json;
    }
    
    public ArrayList<PesanKirimBingkisan> getAllPesanKirimBingkisan(){
        ResultSet rs;
        ArrayList<PesanKirimBingkisan> temp =new ArrayList<PesanKirimBingkisan>();
        String sql;
        try{
            sql="SELECT * FROM pesan_kirim_bing ORDER BY ido DESC";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanKirimBingkisan c = new PesanKirimBingkisan();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idc"));
                c.setAlamat(rs.getString("alamat"));
                c.setHarga(rs.getInt("harga"));
                temp.add(c);
                System.out.println(c.getIdc());
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
}
