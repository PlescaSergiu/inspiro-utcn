package com.inspiro.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity implements Serializable, UserDetails {

    private String username;
    private String password;


    @NotNull
    @NotEmpty
    private String email;
    private Role role = Role.USER;
    private String activationCode;
    private boolean active = false;
    private String image;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private List<Post> posts;

    @OneToOne
    @JoinColumn(name = "person")
    private Person person;

    public User(String username,String email,String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
