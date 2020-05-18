package com.innowave.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innowave.cursomc.domain.enums.ClientType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String nif;
    private Integer type;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="PHONE")
    //TODO: Minimum is 1, maximum is 3
    private Set<String> phones = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<ClientOrder> clientOrders = new ArrayList<>();

    public Client(Integer id, String name, String email, String nif, ClientType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nif = nif;
        this.type = (type==null)? null : type.getCod();
    }

    public ClientType getType() {
        return ClientType.toEnum(type);
    }

    public void setType(ClientType type) {
        this.type = type.getCod();
    }
}
