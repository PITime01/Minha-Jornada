package com.example.PITime01.vehicle;

import com.example.PITime01.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;


    public List<Vehicle> listAll() {
        return repository.findAll()
                .stream()
                .map(Employee::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public Vehicle get(Long id) {
        return repository.findById(id).get();
    }

    public void save(Vehicle vehicle) {
        //vehicle.setPassword(passwordEncoder.encode(client.getPassword()));
        repository.save(vehicle);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
