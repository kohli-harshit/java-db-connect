package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class MongoDbHelper implements IdBHelper {

  /**
   * @param dbHelperPojo
   * @param collectionName
   * @return
   * @throws UnknownHostException
   */
  @SuppressWarnings("resource")
  private synchronized MongoCollection<Document> getMongoDBCollection(DBHelperPojo dbHelperPojo,
                                                                      String collectionName) {

    MongoClient mongoClient;
    MongoDatabase db = null;

    if (dbHelperPojo.getMongoDbConnectionUri() != null) {
      MongoClientURI mongoClientURI = new MongoClientURI(dbHelperPojo.getMongoDbConnectionUri());
      mongoClient = new MongoClient(mongoClientURI);
      db = mongoClient.getDatabase(dbHelperPojo.getMongoDbName());

    } else {
      if (dbHelperPojo.getMongoDbUsername() != null) {
        MongoCredential credential = MongoCredential.createCredential(
                dbHelperPojo.getMongoDbUsername(), dbHelperPojo.getMongoDbName(),
                dbHelperPojo.getSqlPassword().toCharArray());
        mongoClient = new MongoClient(
                new ServerAddress(dbHelperPojo.getMongoDbHost(), dbHelperPojo.getMongoDbPort()),
                Arrays.asList(credential));
        db = mongoClient.getDatabase(dbHelperPojo.getMongoDbName());

      } else {
        mongoClient = new MongoClient(dbHelperPojo.getMongoDbHost(), dbHelperPojo.getMongoDbPort());
        db = mongoClient.getDatabase(dbHelperPojo.getMongoDbName());
      }
    }
    return db.getCollection(collectionName);

  }

  public Map<Integer, String> find(DBHelperPojo dbHelperPojo) {

    MongoDbHelper dbHelper = new MongoDbHelper();
    Map<Integer, String> map = new HashMap<>();
    Document findQuery = Document.parse(dbHelperPojo.getMongoDbQuery());
    MongoCollection<Document> mongoDBCollection = dbHelper.getMongoDBCollection(dbHelperPojo,
            dbHelperPojo.getMongoDbCollectionName());
    MongoCursor<Document> cursor = mongoDBCollection.find(findQuery).iterator();
    try {
      int i = 1;
      while (cursor.hasNext()) {
        Document doc = cursor.next();
        String json = doc.toJson();
        map.put(i, doc.toJson());
        i++;
      }
    } finally {
      cursor.close();
    }
    return map;
  }

  public String update(DBHelperPojo dbHelperPojo) {
    MongoDbHelper dbHelper = new MongoDbHelper();
    Document findQuery = Document.parse(dbHelperPojo.getMongoDbQuery());
    Document updateQuery = Document.parse(dbHelperPojo.getMongoDbUpdateQuery());
    return dbHelper.getMongoDBCollection(dbHelperPojo, dbHelperPojo.getMongoDbCollectionName())
            .updateOne(findQuery, updateQuery).toString();

  }

  public String insert(DBHelperPojo dbHelperPojo) {
    MongoDbHelper dbHelper = new MongoDbHelper();
    Document findQuery = Document.parse(dbHelperPojo.getMongoDbQuery());
    dbHelper.getMongoDBCollection(dbHelperPojo, dbHelperPojo.getMongoDbCollectionName())
            .insertOne(findQuery);

    return "Done";
  }

  /**
   *
   * @param dbHelperPojo
   * @throws UnknownHostException
   */
  public String delete(DBHelperPojo dbHelperPojo) {
    MongoDbHelper dbHelper = new MongoDbHelper();
    Document findQuery = Document.parse(dbHelperPojo.getMongoDbQuery());

    return dbHelper.getMongoDBCollection(dbHelperPojo, dbHelperPojo.getMongoDbCollectionName())
            .deleteOne(findQuery).toString();
  }

  @Override
  public List<Map<Integer, String>> findFromMultipleTable(DBHelperPojo dbHelperPojo) throws Exception {
    return null;
  }

}