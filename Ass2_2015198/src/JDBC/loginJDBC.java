/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class loginJDBC {
    String HOST = "localhost" ;
    int PORT  = 3306;
    String USER = "root";
    String PASSWORD = "";
    String DBNAME = "login";
    String DRIVER = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://localhost:3306/login";
    public boolean search(String username, String password){
         // connect with database
        // select all the records from database
        boolean res = false;
        try{
            //try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM login WHERE username=? AND password=?";
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, username);
            pstat.setString(2, password);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                //System.out.println(rs.getInt("id")+", "+rs.getString("username")+", "+rs.getString("password"));
                res = true;
            }
            pstat.close();
            rs.close();
            conn.close();
                        
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
            //if any errors occured error message will display
            res = false;
        }
        return res;
    }
}
