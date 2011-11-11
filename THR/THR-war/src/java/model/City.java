package model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.ResultSet;
import java.util.ArrayList;
import util.Database;
/**
 *
 * @author Didik
 */
public class City {
    private int idcity;
    private String name;

    public int getIdcity() {
        return idcity;
    }

    public void setIdcity(int idcity) {
        this.idcity = idcity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<City> getCity(){
        Database db = new Database();
        ResultSet rs;
        ArrayList<City> temp =new ArrayList<City>();
        String sql;
        try{
            sql="SELECT * FROM city";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                City c = new City();
                c.setIdcity(rs.getInt("idcity"));
                c.setName(rs.getString("name"));
                temp.add(c);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public City getCity(int idcity){
        Database db = new Database();
        ResultSet rs;
        City c = new City();
        String sql;
        try{
            sql="SELECT * FROM city where idcity="+idcity;
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                c.setIdcity(rs.getInt("idcity"));
                c.setName(rs.getString("name"));
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return c;
    }
    
    public static void main(String[] args) {
        City c = new City();
        ArrayList<City> ac = new ArrayList<City>();
        ac = c.getCity();
        System.out.println(ac.get(0).getName());
    }
}
