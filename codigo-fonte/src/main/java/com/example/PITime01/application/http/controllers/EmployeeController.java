package com.example.PITime01.application.http.controllers;

import com.example.PITime01.application.dto.EmployeeDTO;
import com.example.PITime01.application.services.EmployeeService;
import com.example.PITime01.domain.Employee;
import com.example.PITime01.domain.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// TODO: Arrumar nome das paginas HTML
// TODO: PAGINAR A PAGINA DE LISTAGEM
// TODO: CRIAR UMA PASTA PROS HTMLS DE FUNCIONARIO
// TODO: Quebrar a navbar em um Thymeleaf.Fragment e usar ele nas outras paginas, para ter consistencia!
@Controller
public class EmployeeController implements WebMvcConfigurer {

    private final EmployeeService service;

    private final PasswordEncoder passwordEncoder;

    private final String viewFolder = "employee/";

    public EmployeeController(EmployeeService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/employee/new")
    public String newEmployee(Model model) {
        // TODO: Validar CPF la no HTML com Javascript antes de submeter o formulario!
        Employee employee = new Employee();
        model.addAttribute("cliente", employee);
        model.addAttribute("availableProfiles", Profile.values());
        return viewFolder + "new";
    }

    @RequestMapping("/passwordChange")
    public String editPassword(Model model) {
        return "password";
    }

    @RequestMapping(value = "/passwordSuccess", method = RequestMethod.POST)
    public String editPassword(@RequestParam(value = "newPass", required = false) String newPass) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee foundEmployee = service.getName(authentication.getName());

        foundEmployee.setPassword(passwordEncoder.encode(newPass));
        service.save(foundEmployee);
        return "redirect:/";
    }

    @RequestMapping("/employee/edit/{id}")
    public String editEmployee(@PathVariable(name = "id") Long id, Model model) {
        // TODO: Adicionar um combobox igual o de Perfis que tem na aba NEW, so que pra Status na pagina de EDIT
        Employee employee = service.get(id);
        model.addAttribute("cliente", employee);

        return viewFolder + "edit";
    }

    @RequestMapping(value = "/employee/edit", method = RequestMethod.POST)
    public String editEmployee(@ModelAttribute("cliente") Employee employee) {
        Employee foundEmployee = service.get(employee.getId());
        foundEmployee.setName(employee.getName());
        foundEmployee.setRegistration(employee.getRegistration());
        service.save(foundEmployee);
        return "redirect:/employee/list";
    }

    @RequestMapping("/employee/list")
    public String listEmployee(Model model) {
        List<EmployeeDTO> userList = service.listAll();
        model.addAttribute("clienteList", userList);
        model.addAttribute("cliente", userList);

        return viewFolder + "list";
    }

    @RequestMapping(value = "/employee/save", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("cliente") Employee employee) {
        service.save(employee);

        return "redirect:/employee/list";
    }

    @RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("id") long id) {
        // TODO: ROLES MUST HAVE PRIORITY (lower roles cannot edit higher ones!!)
        service.delete(id);
        return "redirect:/employee/list";
    }
}
