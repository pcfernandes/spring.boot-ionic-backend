package com.innowave.cursomc.Security;

import com.innowave.cursomc.domain.enums.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {

    private Integer id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(){

    }

    public UserSS(Integer id, String email, String password, Set<Profile> profiles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
    }

    public Integer getId(){
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        //Changed to true, a better logic must be implemented if necessary
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //Changed to true, a better logic must be implemented if necessary
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //Changed to true, a better logic must be implemented if necessary
        return true;
    }

    @Override
    public boolean isEnabled() {
        //Changed to true, a better logic must be implemented if necessary
        return true;
    }
    public boolean hasRole(Profile profile){
        return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));
    }
}

