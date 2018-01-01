package com.cityfault.model;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;


import javax.persistence.*;
import java.util.Base64;

@Data
@Entity
public class Defect {
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

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime createDate;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime resolveDate;

    public String getCreateDate() {
        return createDate.toString();
    }
    public String getResolveDate() {
        return resolveDate.toString();
    }
    public String getEncodedPhoto() {
        return Base64.getEncoder().encodeToString(photo.getPhoto());
    }
}
