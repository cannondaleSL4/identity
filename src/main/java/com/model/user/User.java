package com.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by dima on 21.01.18.
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "USERAPP")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class User {
    @Id
    @Column(name= "id_user")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    String name;

    @Column(name = "password")
    String password;

    @JsonIgnore
    @Setter
    @Column(name= "is_active")
    private boolean isActive;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = { @JoinColumn(name="id_user",referencedColumnName="id_user")},
            inverseJoinColumns = { @JoinColumn(name="id_role",referencedColumnName="id_role",unique=true)}
    )
    private Set<Role> roles = new TreeSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRole(String role){
        roles.add(new Role.RoleBuilder()
                .role(role)
                .build());
    }
}
