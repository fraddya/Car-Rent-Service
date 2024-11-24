package com.unreallabss.carrent.domain.user;

import com.unreallabss.carrent.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@TranslatableField
    private String name;

    private Boolean predefine;

    private LocalDate createdAt = LocalDate.now();

    private Status status;

    @ManyToMany
    @JoinTable(name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    @Where(clause = "type='A'")
    private Set<Authority> authorities;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users;

    /*@OneToMany(mappedBy = "role")
    private Set<User> users;*/

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                /*", authorities=" + authorities +*/
                '}';
    }
}
