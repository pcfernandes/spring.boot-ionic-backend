package com.innowave.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

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
    public void setProduct(Product product) {
        id.setProduct(product);
    }
    public void setClientOrder(ClientOrder clientOrder) {
        id.setClientOrder(clientOrder);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "PT"));
        StringBuilder builder = new StringBuilder();
        builder.append(this.getProduct().getName());
        builder.append(", Qty: ");
        builder.append(this.getQuantity());
        builder.append(", Unit Price: ");
        builder.append(nf.format(this.getPrice()));
        builder.append(", Subtotal: ");
        builder.append(nf.format(getSubTotal()));
        builder.append("\n");
        return builder.toString();
    }

}
