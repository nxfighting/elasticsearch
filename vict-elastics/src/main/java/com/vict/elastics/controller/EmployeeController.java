package com.vict.elastics.controller;

import com.vict.elastics.domain.Employee;
import com.vict.elastics.registry.EmployeeReposirtory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author
 * @create 2018-12-18 14:25
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeReposirtory employeeReposirtory;
    @RequestMapping("/add")
    public ResponseEntity<?> add(){
        for (int i = 0; i < 100000; i++) {
            Employee employee = new Employee();
            employee.setId(((i+1)+""));
            employee.setAge((int)(Math.random()*100));
            employee.setFirstName(UUID.randomUUID().toString());
            employee.setLastName("Last"+i);
            this.employeeReposirtory.save(employee);
            System.out.println(i+"_____"+employee.getAge());
        }
        return ResponseEntity.ok().body(new Employee());
    }
    @RequestMapping("/delete")
    public String delete(){
        return null;
    }
    @RequestMapping("/find")
    public List<Employee> find(String firstName){
        return this.employeeReposirtory.queryEmployeeByFirstName(firstName);
    }
    @RequestMapping("/update")
    public String update(){
        return null;
    }
}
