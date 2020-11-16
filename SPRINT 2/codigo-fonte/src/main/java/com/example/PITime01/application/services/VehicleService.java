package com.example.PITime01.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository repo;

    public List<Vehicle> listAll(){
        return repo.findAll();
    }

    public void Save(Vehicle vehicle){
        repo.save(vehicle);
    }

    public Vehicle get(Long id){
        return repo.findById(id).get();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
