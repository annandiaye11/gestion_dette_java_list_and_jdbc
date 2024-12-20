package com.baf.services;

import java.util.List;

import com.baf.core.Service;
import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;

public interface DebtServ extends Service<Debt> {
    void archivePaidDebt(List<Debt> debts);

    List<Debt> getAllPaidDebt();

    List<Debt> getAllUnpaidDebt(Client client);

    List<Debt> getDebtsFromClient(Client client);

    Debt getDebtById(int id);
    void updateDebt(Debt debt);
    
}
