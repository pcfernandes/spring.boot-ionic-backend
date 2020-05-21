package com.innowave.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(of={"id","instant","payment","deliveryAddress"})
public class ClientOrder implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
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

    public double getTotalValue(){
        double sum = 0.0;
        for (ItemOrder io : itemOrders){
            sum = sum + io.getSubTotal();
        }
        return sum;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "PT"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Client Order number: ");
        builder.append(getId());
        builder.append(", Instant: ");
        builder.append(sdf.format(this.getInstant()));
        builder.append(", Client: ");
        builder.append(this.getClient().getName());
        builder.append(", Payment Status: ");
        builder.append(this.payment.getPaymentStatus().getDescription());
        builder.append("\nDetails:\n");
        for (ItemOrder io : this.getItemOrders()) {
            builder.append(io.toString());
        }
        builder.append("Total Value: ");
        builder.append(nf.format(this.getTotalValue()));
        return builder.toString();
    }
}
