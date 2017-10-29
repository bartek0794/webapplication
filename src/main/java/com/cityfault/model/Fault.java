package com.cityfault.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Fault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faultId;
    private String email;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;


}
