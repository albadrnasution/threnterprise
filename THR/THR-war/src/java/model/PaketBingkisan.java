package model;

import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public String getPaket_asJSON(){
        String json = "";
        
        ArrayList<PaketBingkisan> aPB = getPaket();
        
        Gson gson = new Gson();
        json = gson.toJson(aPB);
        return json;
    }
    
    public String getPaket_asJSON(String idp){
        String json = "";
        ArrayList<PaketBingkisan> bingkisan = getPaket(idp);
        json = new Gson().toJson(bingkisan);
        return json;
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
            sql="SELECT * FROM paket_bingkisan WHERE idp="+idp;
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
    
    public void setPaket(String nama, String desk, String harga){
        Database db = new Database();
        String sql;
        try{
            Database.setConnection();
            sql = "INSERT INTO paket_bingkisan (`paket_name`,`description`,`price`) VALUES('"+nama+"','"+desk+"','"+harga+"')";
            Database.updatingQuery(sql);
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
    }
    
    public int lastID(){
        int i = 0;
        Database db = new Database();
        String sql;
        ResultSet rs;
        try {
            Database.setConnection();
            sql = "select idp from paket_bingkisan order by idp desc limit 1";
            rs = Database.executingQuery(sql);
            if(rs.next()){
                i = rs.getInt(1);
            }
        }catch(Exception e){}
        finally{
            Database.unsetConnection();
        }
        return i;
    }
    
    public void deleteP(String id){
        Database db = new Database();
        String sql = null;
        try{
            sql="DELETE FROM paket_bingkisan WHERE idp='"+id+"'";
            Database.setConnection();
            Database.updatingQuery(sql);
            System.out.println(sql);
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
    }
    
    public PaketBingkisan getPaketbyid(String id){
        ResultSet rs;
        PaketBingkisan pb = new PaketBingkisan();
        String Sql;
        Sql="SELECT * FROM paket_bingkisan WHERE idp='"+id+"'";
        Database.setConnection();
        rs = Database.executingQuery(Sql);
        try {
            while(rs.next()){
                pb.setIdp(rs.getInt("idp"));
                pb.setPaket_name(rs.getString("paket_name"));
                pb.setDescription(rs.getString("description"));
                pb.setPrice(rs.getInt("price"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pb;
    }
    
    public static void main(String[] args) {
//        PaketBingkisan pj = new PaketBingkisan();
//        ArrayList<PaketBingkisan> apj = new ArrayList<PaketBingkisan>();
//        //apj = pj.getSearchResult("100000", "<", "", "");
//        apj = pj.getPaket("3");
//        
//        for(int i=0;i<apj.size();++i){
//            System.out.println(apj.get(i).getPaket_name());
//        }
        PaketBingkisan pb = new PaketBingkisan();
        String json = pb.getPaket_asJSON();
        System.out.println(json);
    }
    
}
