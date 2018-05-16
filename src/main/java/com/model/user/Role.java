package com.model.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by dima on 21.01.18.
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name ="USERROLE")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Role implements GrantedAuthority, Comparable<Role> {

    @Id
    @Column(name= "id_role")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idRole;

    @Column(name = "rolename")
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }

    @Override
    public int compareTo(Role o) {
        if (this.role.equals("ADMIN"))return 1;
        else if(o.role.equals("ADMIN"))return 0;

        return this.role.compareTo(o.getRole());
    }
}
