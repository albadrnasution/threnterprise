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
public class ItemJalan {
    private int idi;
    private String name;
    private String description;
    private Moda moda = new Moda();
    private int origin;
    private int dest;
    private int bprice_child;
    private int bprice_adult;
    private int idmoedik;

    public int getBprice_child() {
        return bprice_child;
    }

    public void setBprice_child(int bprice_child) {
        this.bprice_child = bprice_child;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDest() {
        return dest;
    }

    public int getBprice_adult() {
        return bprice_adult;
    }

    public void setBprice_adult(int bprice_adult) {
        this.bprice_adult = bprice_adult;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public int getIdi() {
        return idi;
    }

    public void setIdi(int idi) {
        this.idi = idi;
    }

    public int getIdmoedik() {
        return idmoedik;
    }

    public void setIdmoedik(int idmoedik) {
        this.idmoedik = idmoedik;
    }

    public String getModa() {
        return moda.getValue();
    }

    public void setModa(String m) {
        this.moda.setValue(m);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }
    
    public ArrayList<ItemJalan> getItem(){
        Database db = new Database();
        ResultSet rs;
        ArrayList<ItemJalan> temp = new ArrayList<ItemJalan>();
        String sql;
        try{
            sql="SELECT * FROM item_jalan";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                ItemJalan pj = new ItemJalan();
                pj.setIdi(rs.getInt("idi"));
                pj.setName(rs.getString("name"));
                pj.setDescription(rs.getString("description"));
                pj.setModa(rs.getString("moda"));
                pj.setOrigin(rs.getInt("origin"));
                pj.setDest(rs.getInt("dest"));
                pj.setBprice_child(rs.getInt("bprice_child"));
                pj.setBprice_adult(rs.getInt("bprice_adult"));
                pj.setIdmoedik(rs.getInt("idmoedik"));
                temp.add(pj);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public ArrayList<ItemJalan> getItem(String idp){
        Database db = new Database();
        ResultSet rs;
        ArrayList<ItemJalan> temp = new ArrayList<ItemJalan>();
        String sql;
        try{
            sql="SELECT distinct item_jalan.idi, item_jalan.name, item_jalan.description, "
                    + "item_jalan.moda, item_jalan.origin, item_jalan.dest, item_jalan.bprice_adult, item_jalan.bprice_child, "
                    + "item_jalan.idmoedik FROM paket_jalan JOIN ip_jalan JOIN item_jalan ON paket_jalan.idp = " + idp
                    + " AND item_jalan.idi = ip_jalan.idi AND ip_jalan.idp = paket_jalan.idp";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                ItemJalan pj = new ItemJalan();
                pj.setIdi(rs.getInt("idi"));
                pj.setName(rs.getString("name"));
                pj.setDescription(rs.getString("description"));
                pj.setModa(rs.getString("moda"));
                pj.setOrigin(rs.getInt("origin"));
                pj.setDest(rs.getInt("dest"));
                pj.setBprice_child(rs.getInt("bprice_child"));
                pj.setBprice_adult(rs.getInt("bprice_adult"));
                pj.setIdmoedik(rs.getInt("idmoedik"));
                temp.add(pj);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    
    public String getItems_asJSON(String idp){
        String json = "";
        ArrayList<ItemJalan> itemJalan = this.getItem(idp);
        json = new Gson().toJson(itemJalan);
        return json;
    }
     
    public static void main(String[] args) {
        ItemJalan ij = new ItemJalan();
        ArrayList<ItemJalan> aij = new ArrayList<ItemJalan>();
        aij = ij.getItem("2");
        
        for(int i=0;i<aij.size();++i){
            System.out.println(aij.get(i).getName());
        }
    }
}
