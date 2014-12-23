package com.lhx.domain;

/**
 * Created by lhx on 14-12-23 上午9:31
 *
 * @project springmongodb
 * @package com.lhx.domain
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description
 */
public class Customer {
    private String firstName ;
    private String lastName ;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
