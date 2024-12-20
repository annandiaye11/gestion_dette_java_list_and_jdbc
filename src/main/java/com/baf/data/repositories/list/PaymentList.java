package com.baf.data.repositories.list;

import java.util.List;

import com.baf.data.entities.Payment;
import com.baf.data.repositories.PaymentRepository;

public class PaymentList extends RepositoryImplList<Payment> implements PaymentRepository{

    @Override
    public List<Payment> selectAllByDebtId(int idDebt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAllByDebtId'");
    }
    
}