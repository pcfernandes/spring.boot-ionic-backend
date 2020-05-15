package com.innowave.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    public PaymentWireTransfer(Integer id, PaymentStatus paymentStatus, ClientOrder clientOrder, Date dueDate, Date paymentDate) {
        super(id, paymentStatus, clientOrder);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }
}

