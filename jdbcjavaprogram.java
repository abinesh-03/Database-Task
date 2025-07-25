import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcInsertExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/company";
        String user = "root"; 
        String password = "root123";

        // Employee data
        int[][] empData = {
            {101, 25, 10000},
            {102, 30, 20000},
            {103, 20, 40000},
            {104, 40, 80000},
            {105, 25, 90000}
        };

        String[] empNames = {"Jenny", "Jacky", "Joe", "John", "Shameer"};

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, password);

            // Insert query
            String sql = "INSERT INTO employee (empcode, empname, empage, esalary) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            // Insert each employee
            for (int i = 0; i < empData.length; i++) {
                pst.setInt(1, empData[i][0]);
                pst.setString(2, empNames[i]);
                pst.setInt(3, empData[i][1]);
                pst.setInt(4, empData[i][2]);

                pst.executeUpdate();
            }

            System.out.println("Data inserted successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
