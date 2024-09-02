package com.ebl.userapproval.service;
import com.ebl.userapproval.model.Cbs;
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
    public void insertData(List<Cbs> data) {

        // Database connection parameters
        String URL = "jdbc:oracle:thin:@//10.1.7.11:1521/EBLFIN";
        String USERNAME = "SYSTEM";
        String PASSWORD = "metsys12";
        // SQL insert statement
        String sql = "INSERT INTO user_management (role, service_type, existing_service_type, from_date, to_date, cbs_user_id) "
                + "VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

//            // Data to be inserted
//            String[][] data = {
//                    {"workclass", "FDRD", "ndf", "23-01-2024", "23-02-2024", "AYUSH1654"},
//                    {"workclass", "120", null, null, null, "AYUSH1655"},
//                    {"workclass", "CHFMG", null, null, null, "AYUSH1656"}
//            };

            for (Cbs row : data) {
                // Setting parameters for the prepared statement
                preparedStatement.setString(1, row.getRoleTypeId()); // role
                preparedStatement.setString(2, row.getServiceType()); // service_type
                preparedStatement.setString(3, row.getExsistingServiceType()); // existing_service_type
                preparedStatement.setString(4, row.getFromDate()); // from_date
                preparedStatement.setString(5, row.getToDate()); // to_date
                preparedStatement.setString(6, row.getCbsId()); // cbs_user_id

                // Execute the insert statement
                preparedStatement.executeUpdate();
            }

            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
