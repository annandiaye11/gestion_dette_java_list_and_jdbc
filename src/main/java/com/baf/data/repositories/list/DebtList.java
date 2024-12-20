package com.baf.data.repositories.list;

import java.util.ArrayList;
import java.util.List;

import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;
import com.baf.data.repositories.DebtRepository;

public class DebtList extends RepositoryImplList<Debt> implements DebtRepository {

    private List<Debt> debts = new ArrayList<>();
    @Override
    public List<Debt> selectAll() {
        return debts;
    }

    @Override
    public List<Debt> getAllPaidDebt() {
        List<Debt> debtsPaid = new ArrayList<>();
        for (Debt debt : debts) {
            if (debt.getRemainingMount() == 0) {
                debtsPaid.add(debt);
            }
        }
        return debtsPaid;
    }

    @Override
    public List<Debt> getAllUnpaidDebt(Client client) {
        List<Debt> debtsUnpaid = new ArrayList<>();
        for (Debt debt : debts) {
            if (debt.getClient().equals(client) && debt.getRemainingMount() != 0) {
                debtsUnpaid.add(debt);
            }
        }
        return debtsUnpaid;
    }

    @Override
    public void archivePaidDebt(List<Debt> debts) {
        for (Debt debt : debts) {
            debt.setAchieved(debt.getRemainingMount() == 0);
        }
    }

    @Override
    public void insert(Debt debt) {
        debts.add(debt);
    }

    @Override
    public List<Debt> getDebtsFromClient(Client client) {
        List<Debt> debtsFromClient = new ArrayList<>();
        for (Debt debt : debts) {
            if (debt.getClient().equals(client)) {
                debtsFromClient.add(debt);
            }
        }
        return debtsFromClient;
    }

    @Override
    public Debt getDebtById(int id) {
        for (Debt debt : debts) {
            if (debt.getIdDebt() == id) {
                return debt;
            }
        }
        return null;
    }

    @Override
    public void updateDebt(Debt debt) {
        for (Debt d : debts) {
            if (d.getIdDebt() == debt.getIdDebt()) {
                d = debt;
            }
        }
    }

   

   

    
    
}
