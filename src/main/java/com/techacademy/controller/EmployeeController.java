package com.techacademy.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.service.AuthenticationService;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getEmployeeList(Model model) {
        model.addAttribute("employeelist", employeeService.getEmployeeList());
        return "employee/list";
    }

    @GetMapping("/detail/{id}")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "employee/detail";
    }

    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        return "employee/register";
    }

    @PostMapping("/register")
    public String postRegister(@Validated Employee employee, BindingResult res, Model model) {
        if (res.hasErrors()) {
            return getRegister(employee);
        }
        employee.setDeleteFlag(0);
        employee.getAuthentication().setValidDate(Date.valueOf(LocalDate.now().plusYears(5)));
        employee.getAuthentication().setEmployee(employee);
        employeeService.saveEmployee(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, Model model) {
        if (id != null) {
            Employee employee = employeeService.getEmployee(id);
            employee.getAuthentication().setPassword(null);
            model.addAttribute("employee", employee);
        }
        return "employee/update";
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@Validated Employee employee, BindingResult res, Model model) {
        if (res.hasErrors()) {
            return getUpdate(null, model);
        }
        Employee dbEmployee = employeeService.getEmployee(employee.getId());
        Authentication dbAuthentication = dbEmployee.getAuthentication();
        Authentication authentication = employee.getAuthentication();
        dbEmployee.setName(employee.getName());
        if (authentication.getPassword() != "") {
            dbAuthentication.setPassword(authentication.getPassword());
        }
        dbAuthentication.setRole(authentication.getRole());
        employeeService.saveEmployee(dbEmployee);
        return "redirect:/employee/list";
    }

    @PostMapping("/delete/{id}")
    public String getDelete(Employee employee) {
        Employee dbEmployee = employeeService.getEmployee(employee.getId());
        dbEmployee.setDeleteFlag(1);
        employeeService.saveEmployee(dbEmployee);
        return "redirect:/employee/list";
    }
}
