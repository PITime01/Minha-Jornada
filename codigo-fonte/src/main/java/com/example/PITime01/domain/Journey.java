package com.example.PITime01.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "journey", schema = "springproject")
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long iddriver;
    private String date;
    private float mealtime;
    private float workingtime;
    private float resttime;

    public Long getIdDriver() {
        return iddriver;
    }

    public void setIdDriver(Long idDriver) {
        this.iddriver = idDriver;
    }
}
