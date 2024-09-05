package com.unreallabss.carrent.domain;

import com.unreallabss.carrent.domain.base.CreateModifyAwareBaseEntity;
import com.unreallabss.carrent.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table
public class Company extends CreateModifyAwareBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String code;
    private String description;
    private String address;
    private String contactNo;
    private String email;
    private String image;
    private String webLogo;
    private String coverImage;
    private String registerDate;
    private String webSite;
    private String rating;
    private Status status;

    @OneToMany
    private List<Vehicle> vehicles;

    @OneToMany
    private List<User> employees; //to give car rent permission to other employee in rent service
}
