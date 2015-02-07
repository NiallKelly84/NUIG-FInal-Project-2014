package com.needABuilder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
 
/*
 * 
 * Class to make database connection
 * 
 */
public class Database {
 
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/NeedABuilder",
                    "root", "Niall");	//connection with database username and password included
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }
 
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}