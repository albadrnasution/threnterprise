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
     
    public ArrayList<PaketBingkisan> getPaket(){
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketBingkisan> temp = new ArrayList<PaketBingkisan>();
        String sql;
        try{
            sql="SELECT * FROM paket_bingkisan";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PaketBingkisan pb = new PaketBingkisan();
                pb.setIdp(rs.getInt("idp"));
                pb.setPaket_name(rs.getString("paket_name"));
                pb.setDescription(rs.getString("description"));
                pb.setPrice(rs.getInt("price"));
                temp.add(pb);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    public ArrayList<PaketBingkisan> getPaket(String idp){
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketBingkisan> temp = new ArrayList<PaketBingkisan>();
        String sql;
        try{
            sql="SELECT * FROM paket_bingkisan WHERE idp"+idp;
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PaketBingkisan pb = new PaketBingkisan();
                pb.setIdp(rs.getInt("idp"));
                pb.setPaket_name(rs.getString("paket_name"));
                pb.setDescription(rs.getString("description"));
                pb.setPrice(rs.getInt("price"));
                temp.add(pb);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public ArrayList<PaketBingkisan> getSearchResult(String h, String op, String n, String d){
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketBingkisan> temp = new ArrayList<PaketBingkisan>();
        String sql = null;
        String harga = null,nama = null;
        String con;
        try{
            if(h.equals("") ||  op.equals("")){
                harga = "";
            }else if(!h.equals("") &&  !op.equals("")){
                if(n.equals("")){
                    harga = " WHERE price " + op + " '" + h + "'";
                }else if(d.equals("")){
                    harga = " WHERE price " + op + " '" + h + "'";
                }else{
                    harga = " WHERE price " + op + " '" + h + "'";
                }
            }
            if(n.equals("") &&  d.equals("")){
                nama = "";
            }else if(!n.equals("") ||  !d.equals("")){
                if(h.equals("") ||  op.equals("")){
                    con = " WHERE";
                }else{ 
                    con = " AND";
                }
                if(n.equals("")){
                    nama = con + " description like \"%"+ d +"%\"";
                }else if(d.equals("")){
                    nama = con + " paket_name like \"%"+ n +"%\"";
                }else{
                    nama = con + " paket_name like \"%"+ n +"%\" or description like \"%"+ d +"%\"";
                }
            }
            if(!h.equals("") &&  !op.equals("")){
                if(!n.equals("") ||  !d.equals("")){
                    sql="SELECT * FROM paket_bingkisan" + harga + nama;
                }else{
                    sql="SELECT * FROM paket_bingkisan" + harga;
                }
            }else{
                sql="SELECT * FROM paket_bingkisan" + nama;
            }
            System.out.println(sql);
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PaketBingkisan pj = new PaketBingkisan();
                pj.setIdp(rs.getInt("idp"));
                pj.setPaket_name(rs.getString("paket_name"));
                pj.setDescription(rs.getString("description"));
                pj.setPrice(rs.getInt("price"));
                temp.add(pj);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public static void main(String[] args) {
        PaketBingkisan pj = new PaketBingkisan();
        ArrayList<PaketBingkisan> apj = new ArrayList<PaketBingkisan>();
        apj = pj.getSearchResult("100000", "<", "", "");
        
        for(int i=0;i<apj.size();++i){
            System.out.println(apj.get(i).getPaket_name());
        }
    }
    
}