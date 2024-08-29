package com.ebl.userapproval.service;
import com.ebl.userapproval.model.Upr;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CallCbs {
    // Method to query user data based on userId
    public List<Upr> queryUserData(String userId) {
        // Database connection parameters
        String url = "jdbc:oracle:thin:@//10.1.7.11:1521/EBLFIN"; // Update with your database details
        String username = "EBLAPP";
        String password = "thinkeblapp";

        // SQL query with a placeholder for the parameter
        String sql = "SELECT USER_WORK_CLASS, USER_APPL_NAME, ROLE_ID, USER_EMP_ID " +
                "FROM upr " +
                "WHERE user_id = ?";

        List<Upr> result = new ArrayList<>();

        // Establishing the connection and executing the query
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the parameter value
            preparedStatement.setString(1, userId);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Process the results
                while (resultSet.next()) {
                    String userWorkClass = resultSet.getString("USER_WORK_CLASS");
                    String userApplName = resultSet.getString("USER_APPL_NAME");
                    String roleId = resultSet.getString("ROLE_ID");
                    String userEmpId = resultSet.getString("USER_EMP_ID");

                    // Print out the results
                    System.out.println("USER_WORK_CLASS: " + userWorkClass);
                    System.out.println("USER_APPL_NAME: " + userApplName);
                    System.out.println("ROLE_ID: " + roleId);
                    System.out.println("USER_EMP_ID: " + userEmpId);
                    System.out.println();
                    Upr upr = new Upr();
                    upr.setUSER_WORK_CLASS(userWorkClass);
                    upr.setUSER_APPL_NAME(userApplName);
                    upr.setROLE_ID(roleId);
                    upr.setUSER_EMP_ID(userEmpId);

                    result.add(upr);

                    // Return the result list, or null if empty

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the result list, or null if no rows are found
        return result;
    }
}
