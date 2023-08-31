package com.ahmed.recipehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String password;

    private String name;

    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    private List<Recipe> recipes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    Set<Authority> authorities = new HashSet<>();

//    @Override
//    public String toString() {
//        return "Account{" +
//                ", Name='" + name + "'" +
//                ", Email='" + email + "'" +
//                ", Authorities='" + authorities + "'" +
//                "}";
//    }
}
