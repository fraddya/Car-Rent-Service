package com.unreallabss.carrent.domain;


import com.unreallabss.carrent.domain.base.CreateModifyAwareBaseEntity;
import com.unreallabss.carrent.enums.GenderType;
import com.unreallabss.carrent.enums.Status;
import com.unreallabss.carrent.enums.UserStatus;
import com.unreallabss.carrent.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class User extends CreateModifyAwareBaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String username;//email
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
    //private String email;
    private LocalDateTime lastLoginDate;
    private String password;
    //private UserType role;
    private UserStatus status;
    private String rating;//doing this with some one rate this persion
    private Integer failedLoginAttemptCount;
    private LocalDateTime lastFailedLoginDate;
    @Column(name = "notification_token")
    private String notificationToken;
    //todo raiing for customer , rent vehicles count

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;


    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        if (roles != null) {
            roles.forEach(role -> {
                if (role.getAuthorities() != null) {
                    authorities.addAll(role.getAuthorities());
                }
            });
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status != UserStatus.DELETED;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (status == UserStatus.ACTIVE) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status != UserStatus.DELETED;
    }

    @Override
    public boolean isEnabled() {
        return !(status == UserStatus.INACTIVE || status == UserStatus.PENDING_ACTIVATION);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
