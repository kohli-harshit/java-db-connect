package database.samples;


import database.DBHelper;
import database.DBHelperPojo;

import java.sql.SQLException;
import java.util.Map;

public final class DBHelperSample {

  private DBHelperSample() {
    // overriding default constructor to avoid instantiation of class
  }

  public static void main(String[] args) throws SQLException {
    DBHelperPojo dbHelperPojo = new DBHelperPojo();
    dbHelperPojo.setMongoDbCollectionName("");
    dbHelperPojo.setMongoDbPort(2017);
    // etc .....

    String insert = new DBHelper().dbType(DBHelper.DBType.MONGODB).insert(dbHelperPojo);
    Map<Integer, String> find =  new DBHelper().dbType(DBHelper.DBType.SQLITE).find(dbHelperPojo);
  }
}
