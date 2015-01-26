package com.lhx.util;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhx on 15-1-13 上午11:13
 * 初始化：DBManager.getInstance().init("74.208.78.5",27017,2); 之后，每次通过下面的代码获取数据库对象
 * DBManager.getInstance().getDB();
 * @project springmongodb
 * @package com.lhx.util
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class DBManager {
    private DBManager() {
    }

    private Mongo mongo ;

    private static class InnerHolder{
        static final DBManager INSTANCE = new DBManager() ;
    }

    public static DBManager getInstance(){
        return InnerHolder.INSTANCE ;
    }

    public DB getDB(String dbname){
        return mongo.getDB(dbname) ;
    }

    public DBCollection getDBCollection(String dbname, String collectionName){
        DB db = mongo.getDB(dbname);
        return db.getCollection(collectionName) ;
    }

    public void init(String ip, int port, int poolSize){
        System.setProperty("MONGO.POOLSIZE", String.valueOf(poolSize));
        if (mongo == null){
            try {
                mongo = new Mongo(ip, port);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            MongoOptions options = mongo.getMongoOptions();
            options.autoConnectRetry = true ;
            options.connectionsPerHost = poolSize ;
        }
    }

    /**
     * 无条件分页查询
     * @param start
     * @param limit
     * @return
     */
    public static List<DBObject> serachDBObjectsByPage(DBCollection collection, int start, int limit){
        DBCursor cursorDoc = collection.find().skip(start).limit(limit);
        List<DBObject> list = new ArrayList<DBObject>();
        while (cursorDoc.hasNext()){
            list.add(cursorDoc.next());
        }
        return list ;
    }

//    public static void main(String[] args) {
//        try {
//            DBManager.getInstance().init("localhost",27017,2);
//            DBCollection collection = DBManager.getInstance().getDBCollection("lifeix","customer");
//            DBCursor cursorDoc = collection.find();
//            List<DBObject> list = new ArrayList<DBObject>();
//            while (cursorDoc.hasNext()){
//                list.add(cursorDoc.next());
//            }
//            for (DBObject dbObject : list) {
//                System.out.println(dbObject.get("lastName"));
//            }
//            System.out.println(list.size());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        DBManager.getInstance().init("localhost",27017,2);
        DBCollection collection = DBManager.getInstance().getDBCollection("lifeix","customer");
        for (int i = 0; i < 2000; i++) {
            List<DBObject> list = DBManager.serachDBObjectsByPage(collection, i*100, 100);
            for (DBObject dbObject : list) {
                System.out.println(dbObject.get("lastName"));
            }
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

}
