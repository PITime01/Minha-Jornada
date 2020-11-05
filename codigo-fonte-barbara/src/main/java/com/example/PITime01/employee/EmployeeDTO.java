package com.example.PITime01.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
   class UserDTO is used to publicly expose data stored in the database
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private long id;
    private String name;
    private Profile profile;
    private Status status;
    private String cpf;
    private String registration;
}
