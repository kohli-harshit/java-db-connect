package database;

import java.util.List;
import java.util.Map;


 interface IdBHelper {

	Map<Integer, String> find(DBHelperPojo dbHelperPojo) throws Exception;

	List<Map<Integer, String>> findFromMultipleTable(DBHelperPojo dbHelperPojo) throws Exception;

	String insert(DBHelperPojo dbHelperPojo) throws Exception ;

	String update(DBHelperPojo dbHelperPojo) throws Exception ;

	String delete(DBHelperPojo dbHelperPojo) throws Exception ;

}
