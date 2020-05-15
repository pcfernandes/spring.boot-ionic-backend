package com.innowave.cursomc.domain;

import com.innowave.cursomc.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class PaymentWireTransfer extends Payment {

    private Date dueDate;
    private Date paymentDate;

    public PaymentWireTransfer(Integer id, PaymentStatus paymentStatus, ClientOrder clientOrder, Date dueDate, Date paymentDate) {
        super(id, paymentStatus, clientOrder);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }
}

