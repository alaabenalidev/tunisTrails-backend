package com.example.Back.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Agency {

    @Id
    @GeneratedValue
    private Integer idAgency;

    private String name;

    private Double phone;

    private String address;

    private String email;

    private String password;

}
