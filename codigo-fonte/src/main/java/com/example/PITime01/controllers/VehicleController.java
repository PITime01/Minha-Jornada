package com.example.PITime01.controllers;

import com.example.PITime01.vehicle.Vehicle;
import com.example.PITime01.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

public class VehicleController implements WebMvcConfigurer {

    @Autowired
    private VehicleService service;

    private final String viewFolder = "vehicle/";

    @RequestMapping("/vehicle/new")
    public String showNewVehicleForm(Model model){
        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);
        return viewFolder + "new";
    }

    @RequestMapping("/vehicle/edit/{id}")
    public String showEditVehicleForm(@PathVariable(id = "id")Long id, Model model){
        Vehicle vehicle = service.get(id);
        model.addAttribute("vehicle", vehicle);

        return viewFolder + "edit";
    }

    @RequestMapping("/vehicle/list")
    public String showVehicleList(Model model){
        List<Vehicle> vehicleList = service.listAll();
        model.addAttribute("vehicleList", vehicleList);
        model.addAttribute("vehicle", vehicleList);

        return viewFolder + "list";
    }

    @RequestMapping(value = "/vehicle/save",method = RequestMethod.POST)
    public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle){
        service.save(vehicle);

        return "redirect:/vehicle/list";
    }

    @RequestMapping(value = "/vehicle/edit",method = RequestMethod.POST)
    public String editVehicle(@ModelAttribute("vehicle") Vehicle vehicle){
        Vehicle vehicleFound = service.get(vehicle.getId());
        vehicleFound.setChassi(vehicle.getChassi());
        vehicleFound.setRegistration(vehicle.getRegistration());
        service.save(vehicleFound);
        return "redirect:/vehicle/list";
    }

    @RequestMapping(value = "/vehicle/delete/{id}", method = RequestMethod.GET)
    public String deleteVehicle(@PathVariable("id") long id){
        service.delete(id);
        return "redirect:/vehicle/list";
    }
}
