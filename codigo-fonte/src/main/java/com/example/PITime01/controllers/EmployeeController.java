package com.example.PITime01.controllers;


import com.example.PITime01.vehicle.EmployeeService;
import com.example.PITime01.vehicle.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// TODO: Arrumar nome das paginas HTML
// TODO: PAGINAR A PAGINA DE LISTAGEM
// TODO: CRIAR UMA PASTA PROS HTMLS DE FUNCIONARIO
// TODO: Quebrar a navbar em um Thymeleaf.Fragment e usar ele nas outras paginas, para ter consistencia!
@Controller
public class EmployeeController implements WebMvcConfigurer {

    @Autowired
    private EmployeeService service;

    private final String viewFolder = "employee/";

    @RequestMapping("/employee/new")
    public String showNewClientForm(Model model){
        // TODO: Validar CPF la no HTML com Javascript antes de submeter o formulario!
        Employee employee = new Employee();
        model.addAttribute("cliente", employee);
        model.addAttribute("availableProfiles", Profile.values());
        return viewFolder + "new";
    }

    @RequestMapping("/employee/edit/{id}")
    public String showEditClientForm(@PathVariable(name = "id")Long id, Model model){
        // TODO: Adicionar um combobox igual o de Perfis que tem na aba NEW, so que pra Status na pagina de EDIT
        Employee employee = service.get(id);
        model.addAttribute("cliente", employee);

        return viewFolder + "edit";
    }

    @RequestMapping("/employee/list")
    public String showClientList(Model model){
        List<EmployeeDTO> userList = service.listAll();
        model.addAttribute("clienteList", userList);
        model.addAttribute("cliente", userList);

        return viewFolder + "list";
    }

    @RequestMapping(value = "/employee/save",method = RequestMethod.POST)
    public String saveClient(@ModelAttribute("cliente") Employee employee){
        service.save(employee);

        return "redirect:/employee/list";
    }

    @RequestMapping(value = "/employee/edit",method = RequestMethod.POST)
    public String editClient(@ModelAttribute("cliente") Employee employee){
        Employee foundEmployee = service.get(employee.getId());
        foundEmployee.setName(employee.getName());
        foundEmployee.setRegistration(employee.getRegistration());
        service.save(foundEmployee);
        return "redirect:/employee/list";
    }

    @RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.GET)
    public String deleteClient(@PathVariable("id") long id){
        // TODO: ROLES MUST HAVE PRIORITY (lower roles cannot edit higher ones!!)
        service.delete(id);
        return "redirect:/employee/list";
    }
}
