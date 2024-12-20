package com.baf.data.repositories;

import java.util.List;

import com.baf.core.Repository;
import com.baf.data.entities.Payment;

public interface PaymentRepository extends Repository<Payment> {
    public List<Payment> selectAllByDebtId(int idDebt);
}
