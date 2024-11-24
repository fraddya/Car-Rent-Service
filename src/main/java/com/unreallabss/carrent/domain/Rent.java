package com.unreallabss.carrent.domain;

import com.unreallabss.carrent.domain.base.CreateModifyAwareBaseEntity;
import com.unreallabss.carrent.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table
public class Rent extends CreateModifyAwareBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String rentNo;
    private String rentDate;
    private String returnDate;
    private String rentStatus;
    private String rentType;
    private String description;
    private String returnStatus;
    private BigInteger vehicleCurrentMileage;
    private BigInteger vehicleReturnMileage;
    private BigInteger freeMileage;
    private BigInteger totalCost;
    private String paymentStatus;
    private String paymentType;
    private String paymentDate;
    private BigDecimal paymentAmount;
    private BigDecimal discountAmount;
    private BigDecimal advancedPayment;
    private LocalDateTime bookingDate;
    private LocalDateTime rentStartDate;
    private LocalDateTime rentEndDate;
    private BigDecimal freeKm;
    private BigDecimal extraKm;
    private BigDecimal extraKmCost;
    //Todo extra milage ekakta ayakarana ganak thiyenava meka ganna ona vehicle eken makata edit karannath danna puluvan onnam kohoma hari meke save vena data ekakin thmai calculate eka yanne
    //Todo photos danna thiyenna ona eg:- milage photo, fuel meter photo

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User customer;
}
