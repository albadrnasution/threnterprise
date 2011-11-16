/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.Database;

/**
 *
 * @author user
 */
public class Webservice {
    private int id=0;
    private String nama = new String();
    private String jenis = new String();
    private String server = new String();
    private String port = new String();
    private String username = new String();
    private String password = new String();

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    
    public String getNama() {
        return nama;
    }

    
    public void setNama(String nama) {
        this.nama = nama;
    }

   
    public String getJenis() {
        return jenis;
    }

   
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

   
    public String getServer() {
        return server;
    }

    
    public void setServer(String server) {
        this.server = server;
    }

   
    public String getPort() {
        return port;
    }

    
    public void setPort(String port) {
        this.port = port;
    }

    
    public String getUsername() {
        return username;
    }

   
    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Webservice getWebservice(String ids) throws SQLException{
        Database db = new Database();
        ResultSet rs;
        Webservice ws = new Webservice();
        String sql;
        sql = "SELECT * FROM webservice WHERE id="+ids;
        db.setConnection();
        rs=db.executingQuery(sql);
        while(rs.next()){
          ws.setId(rs.getInt("id"));
          ws.setJenis(rs.getString("jenis"));
          ws.setNama(rs.getString("nama"));
          ws.setPassword(rs.getString("password"));
          ws.setServer(rs.getString("server"));
          ws.setPort(rs.getString("port"));
          ws.setUsername(rs.getString("username"));
        }
        
        return ws;
    }
    
    public void editWebService(String id, String nama, String jenis, String server, String port, String username, String password){
        Database db = new Database();
        String sql= null;
        
        sql = "UPDATE webservice SET nama='"+nama+"',jenis='"+jenis+"',server='"+server+"',port='"+port+"', username='"+username+"', password='"+password+"' WHERE id='"+id+"'";
        db.setConnection();
        db.updatingQuery(sql);
        
        db.unsetConnection();
    }
    
    public void addWebService(String nama, String jenis, String server, String port, String username, String password){
        Database db = new Database();
        String sql = null;
        
        sql = "INSERT INTO webservice (nama,jenis,server,port,username,password) VALUES '"+nama+"','"+jenis+"','"+server+"','"+port+"','"+username+"','"+password+"'";
        
        db.setConnection();
        db.updatingQuery(sql);
        
        db.unsetConnection();
    }
    
    public void deleteWebService(String id){
        Database db = new Database();
        String sql = null;
        
        sql = "DELETE FROM webservice WHERE id='"+id+"'";
        
        db.setConnection();
        db.updatingQuery(sql);
        
        db.unsetConnection();
    }
    
    public ArrayList<Webservice> getAllWebservice() throws SQLException{
        Database db = new Database();
        ResultSet rs;
        String sql;
        ArrayList<Webservice> temp=new ArrayList<Webservice>();
        
        sql="SELECT * FROM webservice";
        db.setConnection();
        rs=db.executingQuery(sql);
        while(rs.next()){
          Webservice ws = new Webservice();
          ws.setId(rs.getInt("id"));
          ws.setJenis(rs.getString("jenis"));
          ws.setNama(rs.getString("nama"));
          ws.setPassword(rs.getString("password"));
          ws.setServer(rs.getString("server"));
          ws.setPort(rs.getString("port"));
          ws.setUsername(rs.getString("username"));
          temp.add(ws);
        }
        
        db.unsetConnection();
        return temp;
    }
    public ArrayList<Webservice> getWSByjenis(String jenis) throws SQLException{
        Database db = new Database();
        ResultSet rs;
        String sql;
        ArrayList<Webservice> temp=new ArrayList<Webservice>();
        
        sql="SELECT * FROM webservice where jenis='"+jenis+"'";
        Database.setConnection();
        rs=Database.executingQuery(sql);
        while(rs.next()){
          Webservice ws = new Webservice();
          ws.setId(rs.getInt("id"));
          ws.setJenis(rs.getString("jenis"));
          ws.setNama(rs.getString("nama"));
          ws.setPassword(rs.getString("password"));
          ws.setServer(rs.getString("server"));
          ws.setPort(rs.getString("port"));
          ws.setUsername(rs.getString("username"));
          temp.add(ws);
        }
        
        Database.unsetConnection();
        return temp;
    }
}
