package database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBHelper {
	private DBType dbType;

	public enum DBType {
		SQL,MYSQL,ORACLE,MONGODB, SQLITE ;
	}

	public DBHelper dbType(DBType dbType) {
		this.dbType = dbType;
		return this;
	}

	/**
	 * 
	 * @param dbHelperPojo
	 * @return
	 * @throws SQLException
	 */
	public Map<Integer, String> find(DBHelperPojo dbHelperPojo) throws SQLException {
		switch (dbType) {
		case SQL:
			return (Map<Integer, String>)(new SqlDbHelper().find(dbHelperPojo));
		case MYSQL:
			return (Map<Integer, String>)(new MySqlDbHelper().find(dbHelperPojo));
		case ORACLE:
			return (Map<Integer, String>)(new OracleDbHelper().find(dbHelperPojo));
		case MONGODB:
			return (Map<Integer, String>)(new MongoDbHelper().find(dbHelperPojo));
		case SQLITE:
			return (Map<Integer, String>)(new SqliteDbHelper().find(dbHelperPojo));
		default:
			return (Map<Integer, String>)(new SqlDbHelper().find(dbHelperPojo));
		}
	}


	/**
	 *
	 * @param dbHelperPojo
	 * @return
	 * @throws SQLException
	 */
	public List<Map<Integer, String>> findFromMultipleTable(DBHelperPojo dbHelperPojo) throws Exception {
		switch (dbType) {
			case SQL:
				return new SqlDbHelper().findFromMultipleTable(dbHelperPojo);
			case MYSQL:
				return new MySqlDbHelper().findFromMultipleTable(dbHelperPojo);
			case ORACLE:
				return new OracleDbHelper().findFromMultipleTable(dbHelperPojo);
			case MONGODB:
				return new MongoDbHelper().findFromMultipleTable(dbHelperPojo);
			case SQLITE:
				return new SqliteDbHelper().findFromMultipleTable(dbHelperPojo);
			default:
				return new SqlDbHelper().findFromMultipleTable(dbHelperPojo);
		}
	}



	/**
	 * 
	 * @param dbHelperPojo
	 * @return
	 * @throws SQLException
	 */
	public String update(DBHelperPojo dbHelperPojo) throws SQLException {
		switch (dbType) {
			case SQL:
				return new SqlDbHelper().update(dbHelperPojo);
			case MYSQL:
				return new MySqlDbHelper().update(dbHelperPojo);
			case ORACLE:
				return new OracleDbHelper().update(dbHelperPojo);
			case MONGODB:
				return new MongoDbHelper().update(dbHelperPojo);
			case SQLITE:
				return new SqliteDbHelper().update(dbHelperPojo);
			default:
				return new SqlDbHelper().update(dbHelperPojo);
		}
	}

	/**
	 * 
	 * @param dbHelperPojo
	 * @return
	 * @throws SQLException
	 */
	public String insert(DBHelperPojo dbHelperPojo) throws SQLException {
		switch (dbType) {
			case SQL:
				return new SqlDbHelper().insert(dbHelperPojo);
			case MYSQL:
				return new MySqlDbHelper().insert(dbHelperPojo);
			case ORACLE:
				return new OracleDbHelper().insert(dbHelperPojo);
			case MONGODB:
				return new MongoDbHelper().insert(dbHelperPojo);
			case SQLITE:
				return new SqliteDbHelper().insert(dbHelperPojo);
			default:
				return new SqlDbHelper().insert(dbHelperPojo);
		}
	}

	/**
	 * @param dbHelperPojo
	 * @return
	 * @throws SQLException
	 */
	public String delete(DBHelperPojo dbHelperPojo) throws SQLException {
		switch (dbType) {
			case SQL:
				return new SqlDbHelper().delete(dbHelperPojo);
			case MYSQL:
				return new MySqlDbHelper().delete(dbHelperPojo);
			case ORACLE:
				return new OracleDbHelper().delete(dbHelperPojo);
			case MONGODB:
				return new MongoDbHelper().delete(dbHelperPojo);
			case SQLITE:
				return new SqliteDbHelper().delete(dbHelperPojo);
			default:
				return new SqlDbHelper().delete(dbHelperPojo);
		}
	}
}
