package com.cityfault.model;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Base64;
import java.util.Set;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Email
    @NotEmpty(message="{notNull.name}")
    @Size(min=5, max=30, message="{size.name}")
    private String email;
    @NotEmpty(message="{notNull.name}")
    private String password;
    @Column(name = "active")
    private int active;
    @NotEmpty(message="{notNull.name}")
    @Size(max=20, message="{size20.name}")
    private String firstName;
    @NotEmpty(message="{notNull.name}")
    @Size(max=20, message="{size20.name}")
    private String lastName;
    @NotEmpty(message="{notNull.name}")
    @Pattern(regexp="(^$|[0-9]{9})")
    private String phoneNumber;
    private byte[] avatar;
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public String getEncodedAvatar() {
        return Base64.getEncoder().encodeToString(avatar);
    }
}