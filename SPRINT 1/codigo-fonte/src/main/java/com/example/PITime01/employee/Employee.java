package com.example.PITime01.employee;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee", schema = "springproject")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Profile profile;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    private String cpf;
    private String registration;

    public EmployeeDTO toEmployeeDTO() {
        return new EmployeeDTO(
            this.id,
            this.name,
            this.profile,
            this.status,
            this.cpf.substring(0, 5) + this.cpf.substring(5).replaceAll("[0-9]", "*"),
            this.registration
        );
    }
}