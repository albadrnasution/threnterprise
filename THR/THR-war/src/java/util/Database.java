/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.sql.*;

/**
 *
 * @author soleman
 */
public class Database {

    public Database() {
    }
    public static Connection con;
    public static Statement stmt;

    //setting connection
    public static void setConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/thr", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("setting connection error");
            e.printStackTrace();
        }
    }

    //menutup connection
    public static void unsetConnection() {
        try {
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("closing connection error");
            e.printStackTrace();
        }
    }

    public static ResultSet executingQuery(String queryToExecute) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(queryToExecute);
        } catch (Exception e) {
            System.out.println("query execute error");
            e.printStackTrace();
        }
        return rs;
    }

    public static void updatingQuery(String queryToUpdate) {
        try {
            stmt.executeUpdate(queryToUpdate);
        } catch (Exception e) {
            System.out.println("query execute error");
            e.printStackTrace();
        }
    }
}

