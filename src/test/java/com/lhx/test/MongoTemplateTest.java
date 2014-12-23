package com.lhx.test;

import com.lhx.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lhx on 14-12-23 上午9:21
 *
 * @project springmongodb
 * @package com.lhx.test
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application.xml")
public class MongoTemplateTest {

    @Autowired
    private MongoTemplate mongo;


    @Test
    public void test(){
        Customer c = new Customer();
        c.setFirstName("wu");
        c.setLastName("wei");
        mongo.insert(c);
    }
}
