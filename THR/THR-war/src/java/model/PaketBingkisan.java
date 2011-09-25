package model;

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
public class PaketBingkisan {
    private int idp;
    private String paket_name;
    private String description;
    private int price;

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

    public String getPaket_name() {
        return paket_name;
    }

    public void setPaket_name(String paket_name) {
        this.paket_name = paket_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
     
    public ArrayList<PaketBingkisan> getPaketB(){
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketBingkisan> temp = new ArrayList<PaketBingkisan>();
        String sql;
        try{
            sql="SELECT * FROM paket_bingkisan";
            db.setConnection();
            rs = db.executingQuery(sql) ;
            while (rs.next()) {
                PaketBingkisan pb = new PaketBingkisan();
                pb.setIdp(rs.getInt("idp"));
                pb.setPaket_name(rs.getString("paket_name"));
                pb.setDescription(rs.getString("description"));
                pb.setPrice(rs.getInt("price"));
                temp.add(pb);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            db.unsetConnection();
        }
        return temp ;
    }
    public ArrayList<PaketBingkisan> getPaketB(int idp){
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketBingkisan> temp = new ArrayList<PaketBingkisan>();
        String sql;
        try{
            sql="SELECT * FROM paket_bingkisan WHERE idp"+idp;
            db.setConnection();
            rs = db.executingQuery(sql) ;
            while (rs.next()) {
                PaketBingkisan pb = new PaketBingkisan();
                pb.setIdp(rs.getInt("idp"));
                pb.setPaket_name(rs.getString("paket_name"));
                pb.setDescription(rs.getString("description"));
                pb.setPrice(rs.getInt("price"));
                temp.add(pb);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            db.unsetConnection();
        }
        return temp ;
    }
    public static void main(String[] args) {
        PaketBingkisan pj = new PaketBingkisan();
        ArrayList<PaketBingkisan> apj = new ArrayList<PaketBingkisan>();
        apj = pj.getPaketB();
        
        for(int i=0;i<apj.size();++i){
            System.out.println(apj.get(i).getPaket_name());
        }
    }
    
}
