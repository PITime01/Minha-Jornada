package com.example.PITime01.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle", schema = "springproject")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String trackerBrand;
    private String trackerVersion;
    private String integrationID; //the vehicle tracker integration ID
    private String chassi;
    private String registration;

}
