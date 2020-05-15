package com.innowave.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(of={"id","instant","payment","deliveryAddress","itemOrders"})
public class ClientOrder implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "clientOrder")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="deliveryAddress_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.clientOrder")
    private List<ItemOrder> itemOrders = new ArrayList<>();

    public ClientOrder(Integer id, Date instant, Client client, Address deliveryAddress) {
        super();
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
    }
}
