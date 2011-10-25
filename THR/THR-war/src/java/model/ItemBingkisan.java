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
public class ItemBingkisan {
    private int idi;
    private String name;
    private String description;
    private int basic_price;
    private int id_tempe;

    public int getBasic_price() {
        return basic_price;
    }

    public void setBasic_price(int basic_price) {
        this.basic_price = basic_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_tempe() {
        return id_tempe;
    }

    public void setId_tempe(int id_tempe) {
        this.id_tempe = id_tempe;
    }

    public int getIdi() {
        return idi;
    }

    public void setIdi(int idi) {
        this.idi = idi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<ItemBingkisan> getItem(){
        Database db = new Database();
        ResultSet rs;
        ArrayList<ItemBingkisan> temp = new ArrayList<ItemBingkisan>();
        String sql;
        try{
            sql="SELECT * FROM item_bingkisan";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                ItemBingkisan pb = new ItemBingkisan();
                pb.setIdi(rs.getInt("idi"));
                pb.setName(rs.getString("name"));
                pb.setDescription(rs.getString("description"));
                pb.setBasic_price(rs.getInt("basic_price"));
                pb.setId_tempe(rs.getInt("id_tempe"));
                temp.add(pb);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public ArrayList<ItemBingkisan> getItem(String idp){
        Database db = new Database();
        ResultSet rs;
        ArrayList<ItemBingkisan> temp = new ArrayList<ItemBingkisan>();
        String sql;
        try{
            sql="SELECT distinct item_bingkisan.idi, item_bingkisan.name, item_bingkisan.description, "
                    + "item_bingkisan.basic_price, item_bingkisan.id_tempe, ip_bingkisan.nitem "
                    + "FROM paket_bingkisan JOIN ip_bingkisan JOIN item_bingkisan ON paket_bingkisan.idp = "+ idp
                    + " AND item_bingkisan.idi = ip_bingkisan.idi AND ip_bingkisan.idp = paket_bingkisan.idp";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                ItemBingkisan pb = new ItemBingkisan();
                pb.setIdi(rs.getInt("idi"));
                pb.setName(rs.getString("name"));
                pb.setDescription(rs.getString("description"));
                pb.setBasic_price(rs.getInt("basic_price"));
                pb.setId_tempe(rs.getInt("id_tempe"));
                temp.add(pb);
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
        
        ArrayList<ItemBingkisan> aPB = this.getItem(idp);
        
        json = new Gson().toJson(aPB);
        return json;
    }
    
    public static void main(String[] args) {
        ItemBingkisan pj = new ItemBingkisan();
        ArrayList<ItemBingkisan> apj = new ArrayList<ItemBingkisan>();
        apj = pj.getItem("3");
        
        for(int i=0;i<apj.size();++i){
            System.out.println(apj.get(i).getName());
        }
    }
    
}
