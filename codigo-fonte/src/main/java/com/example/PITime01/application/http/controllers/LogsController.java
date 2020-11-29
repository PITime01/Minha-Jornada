package com.example.PITime01.application.http.controllers;

import com.example.PITime01.application.dto.EmployeeAuditDTO;
import com.example.PITime01.application.repositories.EmployeeRepository;
import com.example.PITime01.application.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
public class LogsController implements WebMvcConfigurer {

    private final EmployeeRepository employeeRepository;

    private final EmployeeService employeeService;

    public LogsController(EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @RequestMapping("employee/audit")
    public String listEmployee(Model model) {

        List<EmployeeAuditDTO> rep = employeeService.listAllAudit();
        model.addAttribute("clienteList", rep);

        return "audit";
    }
}
