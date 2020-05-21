package com.innowave.cursomc.services;

import com.innowave.cursomc.domain.PaymentWireTransfer;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class PaymentWireTransferService {

    public void fillPaymentWithTransfer(PaymentWireTransfer paymentWireTransfer, Date orderInstant){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderInstant);
        calendar.add(Calendar.DAY_OF_MONTH,7);
        paymentWireTransfer.setDueDate(calendar.getTime());
    }
}
