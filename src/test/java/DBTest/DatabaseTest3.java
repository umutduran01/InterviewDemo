package DBTest;

import java.sql.*;
import java.util.*;

public class DatabaseTest3 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String username = "syntax_hrm";
        String password = "syntaxhrm123";


        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is created.");

            Statement statement = conn.createStatement();
            String query = "select * from person;";
            ResultSet resultSet = statement.executeQuery(query);
            //extract data from resultSet and store it in Java data structure
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            List<Map<String, String>> listFromResultSet = new ArrayList<>();

            //iterate through the rows
            while (resultSet.next()) {
                Map<String, String> map = new LinkedHashMap<>();
                //iterate through the columns
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    //fetching key and value from columns
                    String key = resultSetMetaData.getColumnName(i); //get keys from metadata
                    String value = resultSet.getString(key); //get values from resultSet
                    map.put(key, value); //add them to the map
                }
                System.out.println(map);
                listFromResultSet.add(map); //add maps to the list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
