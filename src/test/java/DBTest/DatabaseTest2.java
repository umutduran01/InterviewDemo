package DBTest;

import java.sql.*;

public class DatabaseTest2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String username = "syntax_hrm";
        String password = "syntaxhrm123";


        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is created.");

            Statement statement = conn.createStatement();

            //ResultSet resultSet = statement.executeQuery("select FirstName, LastName from person");
            ResultSet resultSet = statement.executeQuery("select firstname, lastname, age, city from person where city is not null;");

            //ResultSetMetaData has object that contains info about the result such as in the table how many columns are there, name of the columns, rows and number of the rows.
            ResultSetMetaData metaData = resultSet.getMetaData();

            //print all column header value
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                System.out.println(columnName);
            }

            //loop through every column and every row
            while (resultSet.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String value = resultSet.getString(metaData.getColumnName(i));
                    System.out.println(value + " ");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
