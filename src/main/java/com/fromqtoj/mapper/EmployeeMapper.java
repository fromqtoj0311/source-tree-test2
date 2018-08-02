package com.fromqtoj.mapper;

import com.fromqtoj.bean.Employee;

//@Mapper或者@MapperScan将接口扫描装配到容器中
public interface EmployeeMapper {



    public void insertEmp(Employee employee);

    public void deleteEmpbyId(Integer id);

    public void updateEmpbyId(Integer id);

    public Employee selectEmpById(Integer id);
}
