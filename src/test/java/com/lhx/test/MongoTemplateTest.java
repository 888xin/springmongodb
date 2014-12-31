package com.lhx.test;

import com.lhx.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by lhx on 14-12-23 上午9:21
 *
 * @project springmongodb
 * @package com.lhx.test
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description spring 引入 mongoDbFactory ，mongoTemplate 引用 mongoDbFactory，mongoTemplate本身可以直接对mongodb进行增删改查
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application.xml")
public class MongoTemplateTest {

    @Autowired
    private MongoTemplate mongo;


    @Test
    public void testInsert(){
        Customer c = new Customer();
        c.setFirstName("李");
        c.setLastName("新新");
        c.setAge(27);
        c.setBirthday(new Date());
        c.setSalary(3456.3456);
        mongo.insert(c);
    }
}
