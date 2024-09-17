package com.StudentLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginDao {

    public boolean insert(User user) throws SQLException {

        Connection con = null;
        boolean result = false;

        // Register the SQL Server JDBC driver
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // JDBC connection
            con = DriverManager.getConnection("jdbc:sqlserver://BCTCL-L1423:1433;databaseName=Sample;integratedSecurity=true;encrypt=false");

            String query = "INSERT INTO Login (UserName,Password) VALUES(?,?)";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, user.getUserName());
            pst.setString(2, user.getPassword());

            // Use executeUpdate() for INSERT, UPDATE, or DELETE statements
            int rowsAffected = pst.executeUpdate();

            // If rows were affected, the insert was successful
            result = (rowsAffected > 0);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close(); // Close the connection
            }
        }

        return result;
    }
}
