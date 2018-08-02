package com.fromqtoj.bean;

public class Department {

    private Integer id;
    private String department_Name;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        this.department_Name = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public String getDepartmentName() {
        return department_Name;
    }
}
