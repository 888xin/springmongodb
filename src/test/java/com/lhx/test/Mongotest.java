package com.lhx.test;

import com.lhx.dao.AbstractRepository;
import com.lhx.dao.impl.PersonRepository;
import com.lhx.domain.Person;
import com.lhx.domain.SentencesSet;
import com.lhx.util.JdbcUtils;
import com.mongodb.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lhx on 14-12-23 上午10:13
 *
 * @project springmongodb
 * @package com.lhx.test
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application.xml")
public class Mongotest {

    @Autowired
    private PersonRepository personRepository ;

    @Test
    public void insert(){
        Person person = new Person("24235645645", "张三",36);
        personRepository.insert(person);
        System.out.println("success");
    }

    @Test
    public void findOne(){
        String id = "5498d0935f1d37a06fcb5555";
        Person p = personRepository.findOne(id);
        System.out.println(p);
    }

    @Test
    public void listAll(){
        List<Person> list = personRepository.findAll();
        for (Person person : list) {
            System.out.println(person);
        }
    }

    @Test
    public void findByRegex(){
        List<Person> persons = personRepository.findByRegex("l");
        System.out.println(persons);
    }

    @Test
    public void removeOne(){
        personRepository.removeOne("5498d5405f1db04dc075753e");
    }

    @Test
    public void removeAll(){
        personRepository.removeAll();
    }

    @Test
    public void within(){

        try {
            Mongo mongo =new Mongo();
            DB db=mongo.getDB("lifeix");
            DBCollection collection = db.getCollection("withinuser");
            DBObject dbObject = new BasicDBObject();
            List<Double[]> polygon = new ArrayList<Double[]>();
            polygon.add(new Double[]{121.46326251,31.22373576});
            polygon.add(new Double[]{121.46397061,31.21879961});
            polygon.add(new Double[]{121.47126622,31.22188244});
            polygon.add(new Double[]{121.46748967,31.22329537});
            polygon.add(new Double[]{121.46326251,31.22373576});
            DBObject searchObj = new BasicDBObject("$within",new BasicDBObject("$polygon",polygon));
            dbObject.put("theGeom.coordinates",searchObj);
            System.out.println("dbObject = " + dbObject);
            DBCursor dbCursor = collection.find(dbObject);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertSentence(){
        Connection connection = JdbcUtils.getConnection("jdbc:mysql://192.168.2.3:3306/anti_center",
                "skst", "TestDBSkst$@") ;
        List<Map<String, Object>> list = JdbcUtils.getData(connection, "select * from sentences_set");
        List<SentencesSet> list1 = new ArrayList<SentencesSet>();
        SentencesSet sentencesSet = null ;
        for (Map<String, Object> map : list) {
            sentencesSet = new SentencesSet();
            sentencesSet.setId((Integer) map.get("id"));
            sentencesSet.setKeyword((String) map.get("keyword"));
            sentencesSet.setSample((String) map.get("sample"));
            sentencesSet.setSentence((String) map.get("sentence"));
            list1.add(sentencesSet);
        }
        //用完数据库，释放连接
        JdbcUtils.free(null,null,connection);
    }

}
