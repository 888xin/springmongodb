package com.lhx.domain;

/**
 * Created by lhx on 14-12-23 上午9:47
 *
 * @project springmongodb
 * @package com.lhx.domain
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description
 */
public class Person {
    private String id ;
    private String name ;
    private int age ;
    public Person() {
        super();
    }
    public Person(String id, String name, int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Person[id="+id+",name="+name+",age="+age+"]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
