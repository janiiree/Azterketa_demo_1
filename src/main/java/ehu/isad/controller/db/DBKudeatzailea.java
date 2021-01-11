package ehu.isad.controller.db;

import ehu.isad.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Properties;

public class DBKudeatzailea {
    
    Connection conn = null;
    
    private static final DBKudeatzailea instance = new DBKudeatzailea();
    
    public static DBKudeatzailea getInstance() {
        return instance;
    }
    
    private DBKudeatzailea() {
        connOpen();
    }

    private void connOpen() {
        Properties properties = Utils.lortuEzarpenak();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", properties);
            conn.setCatalog(properties.getProperty("dbname"));
        } catch (SQLException e) {
            //handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public ResultSet execSQL(String query) {
        int count = 0;
        Statement s;
        ResultSet rs = null;
        try {
            s = (Statement) conn.createStatement();
            if (query.toLowerCase().indexOf("select") == 0) {
                rs = query(s, query);
            } else {
                count = s.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private ResultSet query(Statement s, String query) {
        ResultSet rs = null;
        try {
            s.executeQuery(query);
            rs = s.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
