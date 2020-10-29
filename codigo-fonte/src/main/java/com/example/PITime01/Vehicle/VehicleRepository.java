package com.example.PITime01.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends jpaRepository<Vehicle,long> {
    Optional<Vehicle> findByName(String name);
}
