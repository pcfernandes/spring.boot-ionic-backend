package com.innowave.cursomc.domain;

import com.innowave.cursomc.domain.enums.PaymentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class CardPayment extends Payment {
    private Integer installmentsNumber;

    public CardPayment(Integer id, PaymentStatus paymentStatus, ClientOrder clientOrder, Integer installmentsNumber) {
        super(id, paymentStatus, clientOrder);
        this.installmentsNumber = installmentsNumber;
    }
}
