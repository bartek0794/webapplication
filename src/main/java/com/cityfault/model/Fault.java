package com.cityfault.model;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;


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
    private String status;
    private String priority;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime createDate;

    public String getCreateDate() {
        return createDate.toString();
    }
}
