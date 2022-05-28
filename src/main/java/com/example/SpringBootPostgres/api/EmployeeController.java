package com.example.SpringBootPostgres.api;

import com.example.SpringBootPostgres.model.Employee;
import com.example.SpringBootPostgres.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RequestMapping("api/v1/employee")
@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    private String FOLDER="employee";

    @GetMapping
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView(FOLDER+"/index");
        List<Employee> listing = employeeRepository.findAll();
        mav.addObject("path","employee");
        mav.addObject("employees", listing);
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addEmployee() {
        ModelAndView mav = new ModelAndView(FOLDER+"/add");
        Employee employee = new Employee();
        mav.addObject("path","employee");
       // employee.setName("tee");
        mav.addObject("employee", employee);
        return mav;
    }

    @PostMapping("/add")
    public String saveEmployee(@ModelAttribute Employee employee) {
        System.out.println(" em "+employee.toString());
        employeeRepository.save(employee);

        ModelAndView mav = new ModelAndView(FOLDER+"/saved");
        mav.addObject("employee", employee);
        mav.addObject("path","employee");
        return "redirect:/api/v1/employee/";
        //return mav;
    }


}
