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
public class IPBingkisan {
    private int idi;
    private int idp;
    private int nitem;
    
    public IPBingkisan() {}

    public int getIdi() {
        return idi;
    }

    public void setIdi(int idi) {
        this.idi = idi;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getNitem() {
        return nitem;
    }

    public void setNitem(int nitem) {
        this.nitem = nitem;
    }
    
    public void setIPBingkisan(String idi, int idp, String n){
        Database db = new Database();
        String sql;
        try{
            Database.setConnection();
            sql = "INSERT INTO ip_bingkisan (`idi`,`idp`,`nitem`) VALUES('"+idi+"','"+idp+"','"+n+"')";
            Database.updatingQuery(sql);
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
    }
    
    public IPBingkisan getIPB(String idi, String idp){
        IPBingkisan c = new IPBingkisan();
        ResultSet rs;
        String sql;
        try{
            sql="SELECT * FROM ip_bingkisan where idi='"+idi+"' and idp='"+idp+"'";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while(rs.next()){
                c.setIdi(rs.getInt("idi"));
                c.setIdp(rs.getInt("idp"));
                c.setNitem(rs.getInt("nitem"));
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return c;
    }
    
    public String updateIPB(String idi, String idp, String nitem)
    {
        Database db = new Database();
        String sql = null;
        try{
            sql="UPDATE ip_bingkisan SET  idi =  '"+ idi +"', idp = '"+ idp +"', "
                    + "nitem = '"+ nitem +"' WHERE idi = "+idi +" and idp = "+idp;
            Database.setConnection();
            Database.updatingQuery(sql);
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return sql;
    }
    
    public void deleteIPB(String idp){
        Database db = new Database();
        String sql = null;
        try{
            sql="DELETE FROM ip_bingkisan WHERE idp='"+idp+"'";
            Database.setConnection();
            Database.updatingQuery(sql);
            System.out.println(sql);
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
    }
    
    public static void main(String[] args) {
        IPBingkisan pj = new IPBingkisan();
        String res= null;
        res = pj.updateIPB("1", "2", "50");
        System.out.println(pj.getIPB("1", "2").getNitem());
        
    }
}
