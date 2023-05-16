package DBTest;

import java.sql.*;

public class DatabaseTest {
    public static void main(String[] args) {
        //to build the connection with the database, we need 3 things: URL, username, password

        String url = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String username = "syntax_hrm";
        String password = "syntaxhrm123";

        //we need to establish the connection to the database

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is created.");

            //create a statement to send sql queries
            Statement statement = conn.createStatement();

            //when we send any query to the DB then DB returns result set
            ResultSet resultSet = statement.executeQuery("select FirstName, LastName from person");

            //we get keys in the first line from the table, so we need actual data

            resultSet.next();
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");

            System.out.println(firstName + " " + lastName);

            /*
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");

                //System.out.println(firstName + " " + lastName);
            }
            */

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
