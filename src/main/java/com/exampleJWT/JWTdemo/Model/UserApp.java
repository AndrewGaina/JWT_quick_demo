package com.exampleJWT.JWTdemo.Model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserApp userApp = (UserApp) o;
        return id != null && Objects.equals(id, userApp.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
