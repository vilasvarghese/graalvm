package com.quarkus.demo;

public class Employee {

    String id;
    String name;
    String dept;

    public Employee(String id, String name, String dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public Employee() {
    }
}
