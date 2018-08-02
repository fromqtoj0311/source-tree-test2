package com.fromqtoj.controller;

import com.fromqtoj.bean.Department;
import com.fromqtoj.bean.Employee;
import com.fromqtoj.mapper.DepartmentMapper;
import com.fromqtoj.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
  private Logger logger = LoggerFactory.getLogger(DeptController.class);

  @Autowired DepartmentMapper departmentMapper;

  @GetMapping("/dept/id")
  public Department getDepartment(@RequestParam("id") Integer id) {
    return departmentMapper.getDeptById(id);
  }

  @GetMapping("/dept")
  public Department insertDept(Department department) {
    department.setDepartmentName("国家安全部");
    departmentMapper.insertDept(department);
    return department;
  }
}
