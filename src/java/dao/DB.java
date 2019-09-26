/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author darkshadow
 */
public class DB {
    private Connection cnx;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int ok;
    
    private void getConnection() {
        String url = "jdbc:mysql://localhost:3306/banquejeeandroid";
        String user = "stevlucas";
        String password = "passer";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void initPrepar(String sql) {
        try {
            getConnection();
            pstm = cnx.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet executeSelect() {
        try {
            rs = pstm.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    public int executeMaj() {
        try {
            ok = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }
    
    public void closeConnection() {
        try {
            if(cnx != null)
                cnx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public PreparedStatement getPstm() {
        return pstm;
    }
    
}
