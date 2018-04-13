package database;

import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OracleDbHelper implements IdBHelper{

    private Connection getConnection(String dbHost, String userName, String dbPassword) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            if (null != userName) {
                return DriverManager.getConnection(dbHost, userName, dbPassword);
            }
            return DriverManager.getConnection(dbHost);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private ResultSet executeQuery(String query, Connection connection) throws SQLException {
        ResultSet resultSet;
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    @Override
    public Map<Integer, String> find(DBHelperPojo dbHelperPojo) throws SQLException {
        Gson gson = new Gson();
        Map<Integer, String> map = new HashMap<>();
        Connection conn = getConnection(dbHelperPojo.getSqlDburl(), dbHelperPojo.getSqlUserName(),
                dbHelperPojo.getSqlPassword());
        try {
            ResultSet resultSet = executeQuery(dbHelperPojo.getSqlQuery(), conn);
            while (resultSet.next()) {
                Map<Object, Object> innerMap = new HashMap<>();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int row = resultSet.getRow();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    innerMap.put(resultSetMetaData.getColumnName(i),
                            resultSet.getString(resultSetMetaData.getColumnName(i)));
                }

                map.put(row, gson.toJson(innerMap));
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return map;
    }

    @Override
    public List<Map<Integer, String>> findFromMultipleTable(DBHelperPojo dbHelperPojo) throws SQLException {
        Gson gson = new Gson();

        List<Map<Integer, String>> mapList= new ArrayList<>();
        Connection conn = getConnection(dbHelperPojo.getSqlDburl(), dbHelperPojo.getSqlUserName(),
                dbHelperPojo.getSqlPassword());
        try {
            CallableStatement procedureStatement = conn.prepareCall("{ call "+ dbHelperPojo.getSqlStoredProcFunc() +" };");
            boolean results = procedureStatement.execute();
            int rsCount = 0;

            while(results) {
                ResultSet rs = procedureStatement.getResultSet();
                Map<Integer, String> map = new HashMap<>();
                //Retrieve data from the result set.
                rs.next();
                do {
                    Map<Object, Object> innerMap = new HashMap<>();
                    ResultSetMetaData resultSetMetaData = rs.getMetaData();
                    int row = rs.getRow();
                    int columnCount = resultSetMetaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        innerMap.put(resultSetMetaData.getColumnName(i),
                                rs.getString(resultSetMetaData.getColumnName(i)));
                    }

                    map.put(row, gson.toJson(innerMap));

                }while (rs.next());
                mapList.add(map);
                rs.close();

                //Check for the next result set
                for(int tryCount=1;tryCount<=5;tryCount++){
                    results = procedureStatement.getMoreResults();
                    if(results){
                        break;
                    }
                }

                System.out.println(procedureStatement.toString());

            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return mapList;
    }

    @Override
    public String insert(DBHelperPojo dbHelperPojo) throws SQLException {
        String updated = null;
        Connection conn = null;
        try {
            conn = getConnection(dbHelperPojo.getSqlDburl(), dbHelperPojo.getSqlUserName(),
                    dbHelperPojo.getSqlPassword());
            updated = String.valueOf(conn.createStatement().executeUpdate(dbHelperPojo.getSqlQuery()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return updated;

    }

    @Override
    public String update(DBHelperPojo dbHelperPojo) throws SQLException {
        return insert(dbHelperPojo);
    }

    @Override
    public String delete(DBHelperPojo dbHelperPojo) throws SQLException {
        return insert(dbHelperPojo);
    }
}
