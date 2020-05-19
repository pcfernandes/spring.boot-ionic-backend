package com.innowave.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
public class ItemOrder implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private ItemOrderPK id = new ItemOrderPK();
    private Double discount;
    private Integer quantity;
    private Double price;

    public ItemOrder(Product product, ClientOrder clientOrder, Double discount, Integer quantity, Double price) {
        this.id.setProduct(product);
        this.id.setClientOrder(clientOrder);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public ClientOrder getClientOrder(){
        return id.getClientOrder();
    }
    public Product getProduct(){
        return id.getProduct();
    }
    public double getSubTotal(){
        return(price-discount)*quantity;
    }

}
