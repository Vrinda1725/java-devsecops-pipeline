/*
#Added a Java File for Demonstration of Code Coverage Percentage update on SonarCloud Dashboard
#By ASecurityGuru
#5/11/2022 11:14 AM IST
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Calculator {

    // 🚨 Hardcoded API Key - CodeQL should flag this
    private static final String API_KEY = "sk_live_1234567890abcdef"; 

    public int addition(String arguments) {
        int sum = 0;
        for (String add : arguments.split("\\+")) {
            sum += Integer.valueOf(add);  // 🚨 Unvalidated Input (Potential NumberFormatException)
        }
        return sum;
    }

    public void getUserData(String userId) {
        try {
            // 🚨 Potential SQL Injection (Unsafe Query Construction)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE id = '" + userId + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User: " + rs.getString("name"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
