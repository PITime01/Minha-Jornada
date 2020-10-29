package com.example.PITime01.authentication;

import com.example.PITime01.employee.Employee;
import com.example.PITime01.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        Optional<Employee> user = employeeRepository.findByName(username);

        user.orElseThrow(() -> new UsernameNotFoundException("User not found, username:  " + username));

        return user.map(MyUserDetails::new).get();
    }
}
