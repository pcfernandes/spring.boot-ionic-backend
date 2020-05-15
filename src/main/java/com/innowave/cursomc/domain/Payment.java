package com.innowave.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innowave.cursomc.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    @Id
    private Integer id;
    private Integer paymentStatus;

    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    @JsonIgnore
    private ClientOrder clientOrder;

    public Payment(Integer id, PaymentStatus paymentStatus, ClientOrder clientOrder){
        this.id = id;
        this.paymentStatus = paymentStatus.getCod();
        this.clientOrder=clientOrder;
    }

    public PaymentStatus getPaymentStatus() {
        return PaymentStatus.toEnum(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus.getCod();
    }
}
