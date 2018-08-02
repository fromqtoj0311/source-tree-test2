package com.fromqtoj.controller;

import com.fromqtoj.bean.Employee;
import com.fromqtoj.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class EmpController {
  private Logger logger = LoggerFactory.getLogger(EmpController.class);

  @Autowired EmployeeMapper employeeMapper;

  @RequestMapping("/emp/form")
  public Employee insertEmployee(@RequestParam("did") Integer did) {
    Employee employee = createEmployeeForm(did);
    logger.trace("EmpController createEmployeeForm 日志输出 trace");
    logger.debug("EmpController createEmployeeForm 日志输出 debug");
    logger.info(" EmpController createEmployeeForm 日志输出 info");
    logger.warn(" EmpController createEmployeeForm 日志输出 warn");
    logger.error("EmpController createEmployeeForm 日志输出 error");
    employeeMapper.insertEmp(employee);
    return employee;
  }

  @RequestMapping("/emp/json")
  public Employee insertEmployee(@RequestBody String did) {
    Employee employee = createEmployeJson(did);
    logger.trace("EmpController createEmployeJson 日志输出 trace");
    logger.debug("EmpController createEmployeJson 日志输出 debug");
    logger.info(" EmpController createEmployeJson 日志输出 info");
    logger.warn(" EmpController createEmployeJson 日志输出 warn");
    logger.error("EmpController createEmployeJson 日志输出 error");
    employeeMapper.insertEmp(employee);
    return employee;
  }

  private Employee createEmployeeForm(int did) {
    Employee employee = new Employee();
    employee.setEmail("123@jd.com");
    employee.setGender(1);
    employee.setLastName("log-back:" + did);
    employee.setdId(did);
    return employee;
  }

  private Employee createEmployeJson(String did) {
    Employee employee = new Employee();
    employee.setEmail("123@jd.com");
    employee.setGender(1);
    employee.setLastName("log-back:" + did);
    return employee;
  }

  @GetMapping("/emp/delete")
  public void deleteEmp(@RequestParam("id") Integer id) {
    employeeMapper.deleteEmpbyId(id);
  }

  @GetMapping("/emp/update")
  public void updateEmpbyId(@RequestParam("id") Integer id) {
    employeeMapper.updateEmpbyId(id);
  }

  @GetMapping("/emp/select")
  public Employee selectEmp(@RequestParam("id") Integer id) {
    return employeeMapper.selectEmpById(id);
  }
}
