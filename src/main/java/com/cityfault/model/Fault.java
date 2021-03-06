package com.cityfault.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Base64;

@Data
@Entity
public class Fault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faultId;
    private String email;
    private String title;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;
    @ManyToOne(cascade = CascadeType.ALL)
    private Status status;
    @ManyToOne(cascade = CascadeType.ALL)
    private Priority priority;
    @ManyToOne(cascade = CascadeType.ALL)
    private Photo photo;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private String resolveDescription;
    private double latitude;
    private double longitude;
    private String createDate;
    private String resolveDate;

    public String getEncodedPhoto() {
        return Base64.getEncoder().encodeToString(photo.getPhoto());
    }
}
