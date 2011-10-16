package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class Customer {
    private int idc;
    private String first_name;
    private String last_name;
    private String address;
    private String phone;
    private String password;
    private String email;
    private String place_of_birth;
    private Date date_of_birth;
    private String date_of_birth2;

    public String getDate_of_birth2() {
        return date_of_birth2;
    }

    public void setDate_of_birth2(String date_of_birth2) {
        this.date_of_birth2 = date_of_birth2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

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

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }
   
    public Customer getCustomer(String idc){
        ResultSet rs;
        Customer c = new Customer();
        String Sql;
        Sql="SELECT * FROM customer WHERE idc='"+idc+"'";
        Database.setConnection();
        rs = Database.executingQuery(Sql);
        try {
            while(rs.next()){
                c.setFirst_name(rs.getString("first_name"));
                c.setLast_name(rs.getString("last_name"));
                c.setAddress(rs.getString("address"));
                c.setDate_of_birth(rs.getDate("date_of_birth"));
                c.setPlace_of_birth(rs.getString("place_of_birth"));
                c.setPhone(rs.getString("phone"));
                c.setIdc(rs.getInt("idc"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    public ArrayList<Customer> getallCustomer(){
        ResultSet rs;
        ArrayList<Customer> temp =new ArrayList<Customer>();
        String sql;
        try{
            sql="SELECT * FROM customer";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                Customer c = new Customer();
                c.setIdc(rs.getInt("idc"));
                c.setFirst_name(rs.getString("first_name"));
                c.setLast_name(rs.getString("last_name"));
                c.setAddress(rs.getString("address"));
                c.setDate_of_birth(rs.getDate("date_of_birth"));
                c.setPlace_of_birth(rs.getString("place_of_birth"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                temp.add(c);
                System.out.println(c.getIdc());
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public static void insertCustomer(String first_name, String last_name, String email, String Password){
        String sql = "INSERT INTO customer(`first_name`,`last_name`,`email`,`password`) VALUES('"+first_name+"','"+last_name+"','"+email+"','"+Password+"')";
        Database.setConnection();
        Database.updatingQuery(sql);
    }
    
    public String addCustomer()
    {
        Database db = new Database();
        EmailHandler em = new EmailHandler();
        String sql = null;
        try{
            sql="INSERT INTO customer (first_name,last_name,address,phone,email,place_of_birth,date_of_birth,password) VALUES "
                   /*+ "('aa','las','add','phon','emil','pla','00-00-0000','asd')";*/
                    
                    
                    
                    + "('"+this.getFirst_name()+"','"+this.getLast_name()+"'" + ",'"+this.getAddress()+"','"
                    + this.getPhone() +"','"+this.getEmail()+"','"+this.getPlace_of_birth()+
                    "','"+this.getDate_of_birth2()+"','"+this.getPassword()+"')";
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
    
    public String deleteCustomer(String idc)
    {
        Database db = new Database();
        String sql = null;
        try{
            sql="DELETE FROM customer WHERE idc="+idc;
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
    
    
}
