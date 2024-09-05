package com.unreallabss.carrent.domain;

import com.unreallabss.carrent.domain.base.CreateModifyAwareBaseEntity;
import com.unreallabss.carrent.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table
public class Vehicle extends CreateModifyAwareBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String vehicleNo;
    private String model;
    private String year;
    private String color;
    private String type;
    private String description;
    private BigDecimal pricePerDay;
    private BigDecimal pricePerKm;
    private BigDecimal freeKm;
    private Status status;
    //todo  vadi vena km ekata yana gana
    //private List<Image> images;

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;*/

}
