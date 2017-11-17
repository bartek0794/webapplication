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
    private String status;
    private String priority;
    private byte[] photo;
    private double latitude;
    private double longitude;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime createDate;

    public String getCreateDate() {
        return createDate.toString();
    }

    public String getEncodedPhoto() {
        return Base64.getEncoder().encodeToString(photo);
    }
}
