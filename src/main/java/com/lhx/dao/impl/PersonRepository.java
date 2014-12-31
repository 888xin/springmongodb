package com.lhx.dao.impl;

import com.lhx.dao.AbstractRepository;
import com.lhx.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by lhx on 14-12-23 上午9:52
 *
 * @project springmongodb
 * @package com.lhx.dao.impl
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description 实体操作类实现mongodb操作接口类，实现各种对mongodb的操作，这是一种面向对象的操作
 */
public class PersonRepository implements AbstractRepository{

//    @Autowired
    private MongoTemplate mongoTemplate ;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void insert(Person person) {
        mongoTemplate.insert(person);
    }

    @Override
    public Person findOne(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),Person.class);
    }

    @Override
    public List<Person> findAll() {
        return mongoTemplate.find(new Query(), Person.class);
    }

    @Override
    public List<Person> findByRegex(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria("name").regex(pattern.toString());
        return mongoTemplate.find(new Query(criteria),Person.class);
    }

    @Override
    public void removeOne(String id) {
        Criteria criteria = Criteria.where("id").in(id);
        if (criteria != null){
            Query query = new Query(criteria);
            Person person = mongoTemplate.findOne(query, Person.class);
            if ( person!= null){
                mongoTemplate.remove(person) ;
            }
        }
    }

    @Override
    public void removeAll() {
        List<Person> list = this.findAll();
        if (list != null){
            for (Person person : list) {
                mongoTemplate.remove(person);
            }
        }
    }

    @Override
    public void findAndModify(String id) {
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), new Update().inc("age", 3), Person.class);
    }



}
