package com.baf.services.impl;

import java.util.List;

import com.baf.data.entities.Payment;
import com.baf.data.repositories.PaymentRepository;
import com.baf.services.PaymentServ;

public class PaymentsServImpl implements PaymentServ {
    private PaymentRepository paymentRepository;


    public PaymentsServImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

   
    public void insert(Payment payment) {
        paymentRepository.insert(payment);
    }

    public List<Payment> selectAll() {
        return paymentRepository.selectAll();
    }
}