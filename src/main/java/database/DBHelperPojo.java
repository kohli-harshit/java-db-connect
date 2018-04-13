package database;

public class DBHelperPojo {
	private String sqlDburl;
	private String sqlUserName;
	private String sqlPassword;
	private String sqlQuery;
	private String mongoDbHost;
	private int mongoDbPort = 27017;
	private String mongoDbName;
	private String mongoDbUsername;
	private String mongoDbPassword;
	private String mongoDbCollectionName;
	private String mongoDbQuery;
	private String mongoDbUpdateQuery;
	private String mongoDbConnectionUri;
	private String sqlStoredProcFunc;

	public String getMongoDbCollectionName() {
		return mongoDbCollectionName;
	}

	public void setMongoDbCollectionName(String mongoDbCollectionName) {
		this.mongoDbCollectionName = mongoDbCollectionName;
	}

	public String getMongoDbQuery() {
		return mongoDbQuery;
	}

	public void setMongoDbQuery(String mongoDbQuery) {
		this.mongoDbQuery = mongoDbQuery;
	}

	public String getMongoDbUpdateQuery() {
		return mongoDbUpdateQuery;
	}

	public void setMongoDbUpdateQuery(String mongoDbUpdateQuery) {
		this.mongoDbUpdateQuery = mongoDbUpdateQuery;
	}

	public String getMongoDbUsername() {
		return mongoDbUsername;
	}

	public void setMongoDbUsername(String mongoDbUsername) {
		this.mongoDbUsername = mongoDbUsername;
	}

	public String getMongoDbPassword() {
		return mongoDbPassword;
	}

	public void setMongoDbPassword(String mongoDbPassword) {
		this.mongoDbPassword = mongoDbPassword;
	}

	public String getSqlDburl() {
		return sqlDburl;
	}

	public void setSqlDburl(String sqlDburl) {
		this.sqlDburl = sqlDburl;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public String getMongoDbHost() {
		return mongoDbHost;
	}

	public void setMongoDbHost(String mongoDbHost) {
		this.mongoDbHost = mongoDbHost;
	}

	public int getMongoDbPort() {
		return mongoDbPort;
	}

	public void setMongoDbPort(int mongoDbPort) {
		this.mongoDbPort = mongoDbPort;
	}

	public String getMongoDbName() {
		return mongoDbName;
	}

	public void setMongoDbName(String mongoDbName) {
		this.mongoDbName = mongoDbName;
	}

	public String getSqlUserName() {
		return sqlUserName;
	}

	public void setSqlUserName(String sqlUserName) {
		this.sqlUserName = sqlUserName;
	}

	public String getSqlPassword() {
		return sqlPassword;
	}

	public void setSqlPassword(String sqlPassword) {
		this.sqlPassword = sqlPassword;
	}
	
	public String getMongoDbConnectionUri() {
    return mongoDbConnectionUri;
  }

    public void setMongoDbConnectionUri(String mongoDbConnectionUri) {
    this.mongoDbConnectionUri = mongoDbConnectionUri;
  }

	public String getSqlStoredProcFunc() {
		return sqlStoredProcFunc;
	}

	public void setSqlStoredProcFunc(String sqlStoredProcFunc) {
		this.sqlStoredProcFunc = sqlStoredProcFunc;
	}

}
