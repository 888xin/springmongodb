package com.lhx.dao;

import com.lhx.domain.Person;

import java.util.List;

/**
 * Created by lhx on 14-12-23 上午9:49
 *
 * @project springmongodb
 * @package com.lhx.dao
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description
 */
public interface AbstractRepository {

    public void insert(Person person);
    public Person findOne(String id);
    public List<Person> findAll();
    public List<Person> findByRegex(String regex) ;
    public void removeOne(String id) ;
    public void removeAll();
    public void findAndModify(String id);

}
