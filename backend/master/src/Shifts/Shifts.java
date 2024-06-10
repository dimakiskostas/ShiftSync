package Shifts;

import java.lang.reflect.GenericDeclaration;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class Shifts {

    public void printallshifts() {

        String url = "jdbc:mysql://localhost:3306/shedule";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String query = "select * from shifts";
            ResultSet rs = st.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();

            for (int i = 1; i <= numColumns; i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            System.out.println();


            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void printallshift() {

        String url = "jdbc:mysql://localhost:3306/shedule";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String query = "select * from shifts";
            ResultSet rs = st.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void printemploeyshift(int j) {
        String url = "jdbc:mysql://localhost:3306/shedule";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String query = "select * from shifts where EmploeyID = " + j;
            ResultSet rs = st.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


    }

    public String printemploeyshift(String usernam) {
        String url = "jdbc:mysql://localhost:3306/shedule";
        String username = "root";
        String password = "root";
        List<String> itemslist = new ArrayList<>();
        StringBuilder serializedList2 = new StringBuilder();

        String serializedList;
        try {
            String query = "select * from shifts inner join employeedetails on employeedetails.EmploeyID = shifts.EmploeyID where employeedetails.employee_username = ? ";
            try (Connection con = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, usernam);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {

                       String date = rs.getString("Data");
                       String StartTime = rs.getString("StartTime");
                       String EndTime = rs.getString("EndTime");


                        String[] dateParts = date.split("-");
                        String formattedDate = dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];


                        serializedList2.append("Date: ").append(formattedDate)
                                .append(", Start Time: ").append(StartTime)
                                .append(", End Time: ").append(EndTime)
                                .append("\n");
                       itemslist.add(date);
                       itemslist.add(StartTime);
                       itemslist.add(EndTime);

                    }
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }if(itemslist.isEmpty()){
            return "No available shift";
        }else{
            serializedList = String.join("\n ", itemslist);
            return serializedList2.toString();
        }


    }

    public String printemploeyavailability(String usernam) {
        String url = "jdbc:mysql://localhost:3306/shedule";
        String username = "root";
        String password = "root";
        List<String> itemslist = new ArrayList<>();
        StringBuilder serializedList2 = new StringBuilder();

        String serializedList;
        try {
            String query = "select * from availability inner join employeedetails on employeedetails.EmploeyID = availability.EmploeyID where employeedetails.employee_username = ? ";
            try (Connection con = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, usernam);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {

                        String date = rs.getString("date");
                        String StartTime = rs.getString("StartTime");
                        String EndTime = rs.getString("EndTime");


                        String[] dateParts = date.split("-");
                        String formattedDate = dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];

                        serializedList2.append("Date: ").append(formattedDate)
                                .append(", Start Time: ").append(StartTime)
                                .append(", End Time: ").append(EndTime)
                                .append("\n");
                        itemslist.add(date);
                        itemslist.add(StartTime);
                        itemslist.add(EndTime);

                    }
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }if(itemslist.isEmpty()){
            return "No availability set";
        }else{
            serializedList = String.join("\n ", itemslist);
            return serializedList2.toString();
        }


    }



    public List<String> printemploeyshiftList(String usernam) {
        String url = "jdbc:mysql://localhost:3306/shedule";
        String username = "root";
        String password = "root";
        List<String> itemslist = new ArrayList<>();

        String serializedList;
        try {
            String query = "select * from shifts inner join employeedetails on employeedetails.EmploeyID = shifts.EmploeyID where employeedetails.employee_username = ? ";
            try (Connection con = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, usernam);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {

                        String date = rs.getString("Data");
                        String StartTime = rs.getString("StartTime");
                        String EndTime = rs.getString("EndTime");

                        itemslist.add(date);
                        itemslist.add(StartTime);
                        itemslist.add(EndTime);

                    }
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return itemslist;


    }

    public String printemploeyinfo(String usernam) {
        String url = "jdbc:mysql://localhost:3306/shedule";
        String username = "root";
        String password = "root";
        List<String> itemslist = new ArrayList<>();
        String serializedList;
        try {
            String query = "select FirstName, LastName from employeedetails WHERE employee_username = ? ";
            try (Connection con = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, usernam);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String firstName = rs.getString("FirstName");
                        String lastName = rs.getString("LastName");
                        itemslist.add(firstName);
                        itemslist.add(lastName);

                        serializedList = String.join(" ", itemslist);
                        return serializedList;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return "Unable to find name";
    }

    public String printemploeyPhone(String usernam) {
        String url = "jdbc:mysql://localhost:3306/shedule";
        String username = "root";
        String password = "root";
        List<String> itemslist = new ArrayList<>();
        String serializedList;
        try {
            String query = "select PhoneNumber from employeedetails WHERE employee_username = ? ";
            try (Connection con = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, usernam);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String phone = rs.getString("PhoneNumber");
                        itemslist.add(phone);

                        serializedList = String.join(" ", itemslist);
                        return serializedList;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return "Qwer";
    }



    public boolean insertavailability(String username, String datestring, String StartTime, String EndTime){
        String url = "jdbc:mysql://localhost:3306/shedule";
        String user = "root";
        String pa = "root";
        boolean success = false;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pr = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pa);
            con.setAutoCommit(false);
            int EmployeID = 0;


            String check = "SELECT EmploeyID FROM employeedetails WHERE employee_username = ? ";

            pr = con.prepareStatement(check);
            pr.setString(1, username);

            rs = pr.executeQuery();

            if(rs.next()){
                EmployeID = rs.getInt("EmploeyID");
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utildate = null;
            java.sql.Date sqlDate = null;
            try {
                utildate = dateFormat.parse(datestring);
                sqlDate = new java.sql.Date(utildate.getTime());

            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd-MM-yyyy format.");
            }


            String query = "INSERT INTO availability (EmploeyID, date, StartTime, EndTime) VALUES (?,?, ?, ?)";
            pr = con.prepareStatement(query);
            pr.setInt(1, EmployeID);
            pr.setDate(2, sqlDate);
            pr.setTime(3, Time.valueOf(StartTime));
            pr.setTime(4, Time.valueOf(EndTime));
            int rowsAffected = pr.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
                System.out.print("New availability set");
            }

            con.commit();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                    if (pr != null) {
                        pr.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error closing resources: " + ex.getMessage());
                }
            }
        }

        return success;
    }


    public boolean deleteAvailability(String username, String datestring){
        String url = "jdbc:mysql://localhost:3306/shedule";
        String user = "root";
        String pa = "root";
        boolean success = false;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pr = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pa);
            con.setAutoCommit(false);
            int EmployeID = 0;


            String check = "SELECT EmploeyID FROM employeedetails WHERE employee_username = ? ";

            pr = con.prepareStatement(check);
            pr.setString(1, username);

            rs = pr.executeQuery();

            if(rs.next()){
                EmployeID = rs.getInt("EmploeyID");
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utildate = null;
            java.sql.Date sqlDate = null;
            try {
                utildate = dateFormat.parse(datestring);
                sqlDate = new java.sql.Date(utildate.getTime());

            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd-MM-yyyy format.");
            }


            String query = "DELETE FROM availability WHERE EmploeyID = ? AND date = ?";
            pr = con.prepareStatement(query);
            pr.setInt(1, EmployeID);
            pr.setDate(2, sqlDate);
            int rowsAffected = pr.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
                System.out.print("Availability deleted!!");
            }

            con.commit();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                    if (pr != null) {
                        pr.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error closing resources: " + ex.getMessage());
                }
            }
        }

        return success;
    }


    public boolean insertalert(String username, String t){
        String url = "jdbc:mysql://localhost:3306/shedule";
        String user = "root";
        String pa = "root";
        boolean success = false;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pr = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pa);
            con.setAutoCommit(false);
            int EmployeID = 0;


            String check = "SELECT EmploeyID FROM employeedetails WHERE employee_username = ? ";

            pr = con.prepareStatement(check);
            pr.setString(1, username);

            rs = pr.executeQuery();

            if(rs.next()){
                EmployeID = rs.getInt("EmploeyID");
            }



            String query = "INSERT INTO alert (EmploeyID, reportText) VALUES (?,?)";
            pr = con.prepareStatement(query);
            pr.setInt(1, EmployeID);
            pr.setString(2, t);
            int rowsAffected = pr.executeUpdate();

            if (rowsAffected > 0) {

                success = true;
                System.out.print("Alter added");
            }

            con.commit();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                    if (pr != null) {
                        pr.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error closing resources: " + ex.getMessage());
                }
            }
        }

        return success;
    }


    public boolean inserreport(String username, String t, String s, String k){
        String url = "jdbc:mysql://localhost:3306/shedule";
        String user = "root";
        String pa = "root";
        boolean success = false;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pr = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pa);
            con.setAutoCommit(false);
            int EmployeID = 0;


            String check = "SELECT EmploeyID FROM employeedetails WHERE employee_username = ? ";

            pr = con.prepareStatement(check);
            pr.setString(1, username);

            rs = pr.executeQuery();

            if(rs.next()){
                EmployeID = rs.getInt("EmploeyID");
            }


            Calendar calendar = Calendar.getInstance();
            Date currentDate = new Date(calendar.getTimeInMillis());

            String query = "INSERT INTO report (CommentDate, CommentText, EmploeyID, sales, stock) VALUES (?, ?, ?, ?,?)";
            pr = con.prepareStatement(query);
            pr.setDate(1, currentDate);
            pr.setString(2, t);
            pr.setInt(3, EmployeID);
            pr.setString(4, s);
            pr.setString(5, k);

            int rowsAffected = pr.executeUpdate();

            if (rowsAffected > 0) {

                success = true;
                System.out.print("Report added");
            }

            con.commit();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                    if (pr != null) {
                        pr.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error closing resources: " + ex.getMessage());
                }
            }
        }

        return success;
    }





    public static int getShiftId(String username, String date){
        String url = "jdbc:mysql://localhost:3306/shedule";
        String user = "root";
        String pa = "root";
        boolean success = false;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pr = null;
        int ShiftID = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pa);
            con.setAutoCommit(false);
            int EmployeID = 0;


            String check = "SELECT EmploeyID FROM employeedetails WHERE employee_username = ? ";

            pr = con.prepareStatement(check);
            pr.setString(1, username);

            rs = pr.executeQuery();

            if(rs.next()){
                EmployeID = rs.getInt("EmploeyID");
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utildate = null;
            java.sql.Date sqlDate = null;
            try {
                utildate = dateFormat.parse(date);
                sqlDate = new java.sql.Date(utildate.getTime());

            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd-MM-yyyy format.");
            }


            String query = "SELECT shiftid FROM shifts WHERE EmploeyID = ? and data = ?";
            pr = con.prepareStatement(query);
            pr.setInt(1, EmployeID);
            pr.setDate(2, sqlDate);

            rs = pr.executeQuery();

            if (rs.next()) {
                ShiftID = rs.getInt("ShiftID");
            }

            con.commit();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                    if (pr != null) {
                        pr.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error closing resources: " + ex.getMessage());
                }
            }
        }

        return ShiftID;
    }






    public boolean insertreport(String username,String date, String sales, String stocks, String comments){
        String url = "jdbc:mysql://localhost:3306/shedule";
        String user = "root";
        String pa = "root";
        boolean success = false;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pr = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pa);
            con.setAutoCommit(false);
            int EmployeID = 0;
            int ShiftID = Shifts.getShiftId(username, date);


            String check = "SELECT EmploeyID FROM employeedetails WHERE employee_username = ? ";

            pr = con.prepareStatement(check);
            pr.setString(1, username);

            rs = pr.executeQuery();

            if(rs.next()){
                EmployeID = rs.getInt("EmploeyID");
            }



            String query = "INSERT INTO report (CommentDate, CommentText, EmploeyID, sales, ShiftID, stock) VALUES (?,?, ?, ?, ? ,?)";
            pr = con.prepareStatement(query);
            pr.setDate(1, getCurrentDate());
            pr.setString(2, comments);
            pr.setInt(3, EmployeID);
            pr.setString(4, sales);
            pr.setInt(5, ShiftID);
            pr.setString(6, stocks);

            int rowsAffected = pr.executeUpdate();

            if (rowsAffected > 0) {

                success = true;
                System.out.print("Report added!");
            }

            con.commit();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                    if (pr != null) {
                        pr.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error closing resources: " + ex.getMessage());
                }
            }
        }

        return success;
    }




    private java.sql.Date getCurrentDate() {
        java.util.Date currentDate = new java.util.Date();
        return new java.sql.Date(currentDate.getTime());
    }



}
