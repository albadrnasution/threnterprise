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
public class IPJalan {
    private int idi;
    private int idp;

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
    
    public ArrayList<IPJalan> getIPJ(){
        ArrayList<IPJalan> ipj = new ArrayList<IPJalan>();
        ResultSet rs;
        String sql;
        try{
            sql="SELECT * FROM ip_jalan";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                IPJalan c = new IPJalan();
                c.setIdi(rs.getInt("idi"));
                c.setIdp(rs.getInt("idp"));
                ipj.add(c);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return ipj;
    }
    
    public void setIPJalan(String idi, String idp){
        Database db = new Database();
        String sql;
        try{
            Database.setConnection();
            sql = "INSERT INTO ip_jalan (`idi`,`idp`) VALUES('"+idi+"','"+idp+"')";
            Database.updatingQuery(sql);
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
    }
    
    public void deleteIP(String idp){
        Database db = new Database();
        String sql = null;
        try{
            sql="DELETE FROM ip_jalan WHERE idp="+idp;
            Database.setConnection();
            Database.updatingQuery(sql);
            System.out.println(sql);
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
    }
    
}
