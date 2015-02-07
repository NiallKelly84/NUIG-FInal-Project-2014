package com.needABuilder.dao;

import java.sql.*;

/*
 * 
 *Data Access Object (DAO) class 
 * 
 */

public class UserDAO {      
     public static boolean login(String email, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select email, password from business_accounts where email= ? and password= ? ");
            ps.setString(1, email);
            ps.setString(2, password);
  
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                System.out.println(rs.getString("email"));
                return true;
            }
            else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }
}