package com.example.PITime01.vehicle;

import java.util.Optional;

public interface VehicleRepository extends jpaRepository<Vehicle,Long> {
    Optional<Vehicle> findByName(String name);
}
