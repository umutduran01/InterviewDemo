package Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtility {

    static Connection conn = null;
    static Statement statement = null;
    private static ResultSet resultSet;
    private static ResultSetMetaData resultSetMetaData;

    //This method creates connection to DB, execute query and return object for resultSet
    public static ResultSet getResultSet(String sqlQuery) {
        try {
            conn = DriverManager.getConnection(
                    ConfigReader.getPropertyValue("urlDB"),
                    ConfigReader.getPropertyValue("usernameDB"),
                    ConfigReader.getPropertyValue("passwordDB"));

            statement = conn.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //This method will return the object of ResultSetMetaData
    public static ResultSetMetaData getResultSetMetaData(String query) {
        resultSet = getResultSet(query);
        resultSetMetaData = null;

        //We use this line to get the data in tabular format so that we can use these in column keys and values for retrieval operation
        try {
            resultSetMetaData = resultSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSetMetaData;
    }

    //This method is extracting the data which will be stored in list of maps
    public static List<Map<String, String>> getListOfMapsFromResultSet(String query) {
        resultSetMetaData = getResultSetMetaData(query);
        List<Map<String, String>> listFromResultSet = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Map<String, String> map = new LinkedHashMap<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String key = resultSetMetaData.getColumnName(i); //get keys from metadata
                    String value = resultSet.getString(key); //get values from resultSet
                    map.put(key, value); //add them to the map
                }
                System.out.println(map);
                listFromResultSet.add(map); //add maps to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.closeResultSet(resultSet);
            DBUtility.closeStatement(statement);
            DBUtility.closeConnection(conn);
        }
        return listFromResultSet;
    }

    //Order to make connection: connection, statement, resultSet
    //Order of closing: resultSet, statement, connection

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
