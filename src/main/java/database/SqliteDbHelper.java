package database;

import com.google.gson.Gson;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SqliteDbHelper implements IdBHelper {

	private Connection getConnection(String dbHost, String userName, String dbPassword) {
		try {
			Class.forName("org.sqlite.JDBC");
			if (null != userName) {
				return DriverManager.getConnection(dbHost, userName, dbPassword);
			}
			return DriverManager.getConnection("jdbc:sqlite:" + dbHost);
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
				map.put(row, new Gson().toJson(innerMap));
			}
		} finally {
			conn.close();
		}
		return map;
	}

	//TODO
	@Override
	public List<Map<Integer, String>> findFromMultipleTable(DBHelperPojo dbHelperPojo) throws Exception {
		return null;
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
