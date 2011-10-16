package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Database;
import util.EmailHandler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */
public class Staff {
    private int ids;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Privilege previlage;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrevilage() {
        return previlage.getValue();
    }

    public void setPrevilage(Privilege previlage) {
        this.previlage = previlage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Staff getStaff(String ids){
        Database db = new Database();
        ResultSet rs;
        Staff s = new Staff();
        String Sql;
        Sql="SELECT * FROM Staff WHERE ids='"+ids+"'";
        db.setConnection();
        rs = db.executingQuery(Sql);
        try {
            while(rs.next()){
                s.setIds(rs.getInt("ids"));
                s.setUsername(rs.getString("username"));
                s.setFirst_name(rs.getString("first_name"));
                s.setLast_name(rs.getString("last_name"));
                s.setEmail(rs.getString("email"));
                s.setPrevilage(new Privilege(rs.getString("previlege")));
                s.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public String addStaff()
    {
        Database db = new Database();
        EmailHandler em = new EmailHandler();
        String sql = null;
        try{
            String passmd5 = em.getStringMD5(this.getPassword());
            sql="INSERT INTO staff (username,first_name,last_name,email,previlege,password) VALUES "
                    + "('"+this.getUsername()+"','"+this.getFirst_name()+"','"+this.getLast_name()+"'"
                    + ",'"+this.getEmail()+"','"
                    + this.getPrevilage() +"','"+this.getPassword()+"')";
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
    
    public String deleteStaff(String ids)
    {
        Database db = new Database();
        String sql = null;
        try{
            sql="DELETE FROM staff WHERE ids="+ids;
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
    
    public ArrayList<Staff> getallStaff(){
        Database db = new Database();
        ResultSet rs;
        ArrayList<Staff> temp =new ArrayList<Staff>();
        String sql;
        try{
            sql="SELECT * FROM staff";
            db.setConnection();
            rs = db.executingQuery(sql) ;
            while (rs.next()) {
                Staff s = new Staff();
                s.setIds(rs.getInt("ids"));
                s.setUsername(rs.getString("username"));
                s.setFirst_name(rs.getString("first_name"));
                s.setLast_name(rs.getString("last_name"));
                s.setEmail(rs.getString("email"));
                s.setPrevilage(new Privilege(rs.getString("previlege")));
                s.setPassword(rs.getString("password"));
                temp.add(s);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            db.unsetConnection();
        }
        return temp ;
    }
}
