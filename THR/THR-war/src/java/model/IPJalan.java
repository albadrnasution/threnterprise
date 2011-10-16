package model;

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
    
    public void setIPJalan(String idi, int idp){
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
    
}
