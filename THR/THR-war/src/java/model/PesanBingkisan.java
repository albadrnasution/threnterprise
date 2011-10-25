package model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Database;

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
    private String due_date2;

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }
    
    public void setDue_date2(String due_date) {
        this.due_date2 = due_date;
    }
    
    public String getDue_date2() {
        return due_date2 ;
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
    
    public String addPesanBingkisan()
    {
        Database db = new Database();
        String sql = null;
        try{
            sql= /*"INSERT INTO customer (first_name,last_name,address,phone,email,place_of_birth,date_of_birth,password) VALUES "
                   + "('aa','las','add','phon','emil','pla','00-00-0000','asd')";*/
                    "INSERT INTO pesan_bingkisan (idp,idc,jumlah_paket) VALUES "
                   + "(1,5,123)";
                    
                    /*+ "("+this.getIdp()+","+this.getIdc() + ","+this.getJumlah_paket()+","
                    + "NOW()" +","+this.getDue_date2()+")";*/
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
    
    public int lastPesanBingkisan()
    {
        ResultSet rs;
        PesanBingkisan c = new PesanBingkisan();
        String Sql;
        Sql="SELECT MAX(ido) AS max FROM pesan_bingkisan ";
        int result = 0;
        Database.setConnection();
        rs = Database.executingQuery(Sql);
        try {
            while(rs.next()){
                result = rs.getInt("max");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public ArrayList<PesanBingkisan> getPesanBingkisanbyIdc(String idc){
        ResultSet rs;
        ArrayList<PesanBingkisan> temp =new ArrayList<PesanBingkisan>();
        String sql;
        try{
            sql="SELECT * FROM pesan_bingkisan WHERE idc="+idc;
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanBingkisan c = new PesanBingkisan();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idc"));
                c.setDue_date2(rs.getString("due_date"));
                c.setOrder_date(rs.getDate("order_date"));
                c.setJumlah_paket(rs.getInt("jumlah_paket"));
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
