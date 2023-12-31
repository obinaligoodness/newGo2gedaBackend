package com.go2geda.Go2GedaApp.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
@ToString
@Table(name= "Go2GedaUser")
public class User {
    public boolean isActive;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private BasicInformation basicInformation;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Review> reviews;
    @OneToMany
    private List<Trip>trips;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String profilePicture;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Wallet wallet;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isVerified;

}
