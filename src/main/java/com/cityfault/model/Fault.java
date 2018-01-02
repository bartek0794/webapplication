package com.cityfault.model;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;


import javax.persistence.*;
import java.util.Base64;

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
