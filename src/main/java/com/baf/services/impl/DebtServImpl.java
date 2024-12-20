package com.baf.services.impl;

import java.util.List;

import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;
import com.baf.data.repositories.DebtRepository;
import com.baf.services.DebtServ;

public class DebtServImpl implements DebtServ {
    private DebtRepository debtRepo;

    public DebtServImpl(DebtRepository debtRepo) {
        this.debtRepo = debtRepo;
    }

    public void archivePaidDebt(List<Debt> debts) {
        debtRepo.archivePaidDebt(debts);
    }

    public List<Debt> getAllPaidDebt() {
        return debtRepo.getAllPaidDebt();
    }

    public List<Debt> getAllUnpaidDebt(Client client) {
        return debtRepo.getAllUnpaidDebt(client);
    }

    public List<Debt> selectAll() {
        return debtRepo.selectAll();
    }

    public void insert(Debt debt) {
        debtRepo.insert(debt);
    }

    public List<Debt> getDebtsFromClient(Client client) {
        return debtRepo.getDebtsFromClient(client);
    }

    public Debt getDebtById(int id) {
        return debtRepo.getDebtById(id);
    }

    @Override
    public void updateDebt(Debt debt) {
        debtRepo.updateDebt(debt);
    }

    

}
