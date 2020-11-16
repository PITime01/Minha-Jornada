package com.example.PITime01.application.services;

import com.example.PITime01.application.dto.EmployeeDTO;
import com.example.PITime01.application.repositories.EmployeeRepository;
import com.example.PITime01.domain.Employee;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<EmployeeDTO> listAll() {
        return repository.findAll()
                .stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }

    public Employee get(Long id) {
        return repository.findById(id).get();
    }

    public Employee getName(String name) {
        return repository.findByName(name).get();
    }

    public void save(Employee client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        repository.save(client);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void updatePassword(Employee user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        repository.save(user);
    }
}
