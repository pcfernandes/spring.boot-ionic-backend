package com.innowave.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innowave.cursomc.domain.enums.ClientType;
import com.innowave.cursomc.domain.enums.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique=true)
    private String email;
    private String nif;
    private Integer type;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="PHONE")
    //TODO: Minimum is 1, maximum is 3
    private Set<String> phones = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<ClientOrder> clientOrders = new ArrayList<>();

    private String imageUrl;

    public Client(){
        addProfile(Profile.CLIENT);
    }
    public Client(Integer id, String name, String email, String nif, ClientType type, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nif = nif;
        this.type = (type==null)? null : type.getCod();
        this.password = password;
        addProfile(Profile.CLIENT);

    }

    public ClientType getType() {
        return ClientType.toEnum(type);
    }

    public void setType(ClientType type) {
        this.type = type.getCod();
    }

    public Set<Profile> getProfiles(){
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile){
        profiles.add(profile.getCod());
    }

}
