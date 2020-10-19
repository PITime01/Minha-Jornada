package com.example.PITime01.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<EmployeeDTO> listAll() {
        return repository.findAll()
            .stream()
            .map(Employee::toEmployeeDTO)
            .collect(Collectors.toList());
    }

    public Employee get(Long id) {
        return repository.findById(id).get();
    }

    public void save(Employee client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        repository.save(client);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
