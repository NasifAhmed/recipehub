package com.ahmed.recipehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<Recipe> recipes;
}
