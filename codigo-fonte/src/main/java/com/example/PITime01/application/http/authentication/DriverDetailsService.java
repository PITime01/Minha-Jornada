package com.example.PITime01.application.http.authentication;

import com.example.PITime01.application.repositories.DriverRepository;
import com.example.PITime01.domain.Driver;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverDetailsService implements UserDetailsService {

    final DriverRepository driverRepository;

    public DriverDetailsService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public DriverDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Driver> user = driverRepository.findByName(username);

        user.orElseThrow(() -> new UsernameNotFoundException("User not found, username:  " + username));

        return user.map(DriverDetails::new).get();
    }
}
