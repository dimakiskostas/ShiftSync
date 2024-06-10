package Emploey;

import Config.Config;

import java.sql.*;

public class LoginChecker {


    public boolean checkif(String username, String password){
        String url = "jdbc:mysql://localhost:3306/shedule";
        String user = "root";
        String pa = "root";
        boolean stringExists = false;
        PreparedStatement pr = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pa);
            //Statement st = con.createStatement();
            String query = "SELECT * FROM employeedetails WHERE employee_username = ? AND employee_passowrd = ? ";
            pr = con.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                // String exists in the database
                stringExists = true;
            }

            pr.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if(stringExists){
            return true;
        }
        else{
            return false;
        }

    }


}
