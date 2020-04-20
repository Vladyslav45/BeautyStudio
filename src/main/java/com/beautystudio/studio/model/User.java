package com.beautystudio.studio.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Size(max = 40)
    private String name;

    @Column
    @Size(max = 60)
    private String surname;

    @Column
    private String email;

    @Column
    @Size(min = 6)
    private String password;

    @Column
    @Size(max = 9)
    private String phoneNumber;

    @Column
    private int active;

    @OneToOne
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;
}
