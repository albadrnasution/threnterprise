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
public class IPBingkisan {
    private int idi;
    private int idp;
    private int nitem;

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
    
}
