package database.samples;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import database.DBHelper;
import database.DBHelperPojo;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;

public final class SampleTest {

  private SampleTest() {
    // overriding default constructor to avoid instantiation of class
  }

  public static void main(String[] args) throws Exception {
    DBHelperPojo dbHelperPojo = new DBHelperPojo();
    dbHelperPojo.setSqlDburl("jdbc:sqlserver://NameOfTheServer;databaseName=NameOfTheDB");
    dbHelperPojo.setSqlUserName("user");
    dbHelperPojo.setSqlPassword("pass");
    dbHelperPojo.setSqlQuery("select TOP 5 * from TABLE");

    Map<Integer,String> dataOriginal = new DBHelper().dbType(DBHelper.DBType.SQL).find(dbHelperPojo);
    dbHelperPojo.setSqlQuery("select TOP 5 * from TABLE where A=B");
    Map<Integer,String> dataNew = new DBHelper().dbType(DBHelper.DBType.SQL).find(dbHelperPojo);

    HashSet<String> mismatches= new HashSet<>();
    Integer originalSize = dataOriginal.keySet().size();
    Integer newSize = dataNew.keySet().size();

    if(originalSize!=dataNew.size()){
      mismatches.add("Row count mismatch. Original = " + originalSize + ". New = " + newSize);
    }
    else {
      Gson g = new Gson();
      Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
      for(int counter=1;counter<=originalSize;counter++){
        Map<String, Object> firstMap = g.fromJson(dataOriginal.get(counter), mapType);
        Map<String, Object> secondMap = g.fromJson(dataNew.get(counter), mapType);
        MapDifference mapDifference = Maps.difference(firstMap, secondMap);
        if(mapDifference.entriesDiffering().size()!=0){
          mismatches.add("Row " + counter + " Difference = " + mapDifference.entriesDiffering().toString());
        }
        if(mapDifference.entriesOnlyOnLeft().size()!=0){
          mismatches.add("Row " + counter + " : Found in source but not in destination  = " + mapDifference.entriesOnlyOnLeft().toString());
        }
        if(mapDifference.entriesOnlyOnRight().size()!=0){
          mismatches.add("Row " + counter + " : Found in destination but not in source  = " + mapDifference.entriesOnlyOnRight().toString());
        }
      }
    }
    if(mismatches.size()==0){
      System.out.println("No Mismatch");
    }
    else{
      System.out.println("Mismatches = " + mismatches.toString());
    }
  }
}
