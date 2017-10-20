package com.cityfault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class Fault {
    String email;
    String description;
    Fault(String email, String description) {
        this.email = email;
        this.description = description;
    }
}
