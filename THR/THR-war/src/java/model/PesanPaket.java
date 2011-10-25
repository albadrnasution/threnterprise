package model;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import util.Database;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */
public class PesanPaket {
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
    
    public String addPesanPaket()
    {
        Database db = new Database();
        String sql = null;
        try{
            sql="INSERT INTO pesan_paket (idp,idc,jumlah_paket,order_date,due_date) VALUES "
                   /*+ "('aa','las','add','phon','emil','pla','00-00-0000','asd')";*/
                    
                    
                    
                    + "("+this.getIdp()+","+this.getIdc()+","+this.getJumlah_paket()+","
                    + "NOW()" +","+"NOW()"+")";
            db.setConnection();
            db.updatingQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            db.unsetConnection();
        }
        return sql;
    }
    
    public ArrayList<PesanPaket> getPesanPaketbyIdc(String idc){
        ResultSet rs;
        ArrayList<PesanPaket> temp =new ArrayList<PesanPaket>();
        String sql;
        try{
            sql="SELECT * FROM pesan_paket WHERE idc="+idc;
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanPaket c = new PesanPaket();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idc"));
                c.setJumlah_paket(rs.getInt("jumlah_paket"));
                c.setOrder_date(rs.getDate("order_date"));
                c.setDue_date(rs.getDate("due_date"));
                if (rs.getInt("pay_status")==1)
                    c.setPay_status(true);
                else c.setPay_status(false);
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
