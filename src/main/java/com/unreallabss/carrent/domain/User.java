package com.unreallabss.carrent.domain;


import com.unreallabss.carrent.domain.base.CreateModifyAwareBaseEntity;
import com.unreallabss.carrent.enums.GenderType;
import com.unreallabss.carrent.enums.Status;
import com.unreallabss.carrent.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class User extends CreateModifyAwareBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String firstName;
    private String lastName;
    private String contactNo;
    private LocalDate dateJoin;
    private Integer age;
    private GenderType genderType;
    private String nic;
    private String nationality;
    private String image;
    private String religion;
    private String email;
    private LocalDateTime userLogging;
    private String passWord;
    private UserType role;
    private Status status;
    private String rating;//doing this with some one rate this persion
    //todo raiing for customer , rent vehicles count

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
