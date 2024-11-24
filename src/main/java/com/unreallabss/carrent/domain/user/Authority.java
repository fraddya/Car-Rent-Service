package com.unreallabss.carrent.domain.user;

import com.unreallabss.carrent.enums.AuthorityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private AuthorityType type;

    private Boolean visibleInRoleCreation;

    public Authority(String code) {
        this.code = code;
    }

    public Authority(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Authority() {

    }

    @Override
    public String getAuthority() {
        return code;
    }
}
