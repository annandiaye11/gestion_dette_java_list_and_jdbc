package com.baf.views.impl;

import java.util.Date;

import com.baf.data.entities.Debt;
import com.baf.data.entities.Payment;
import com.baf.services.DebtServ;

public class PaymentView extends ViewImpl<Payment> {
    private DebtServ debtServ;
    private DebtView debtView;
     

    public PaymentView(DebtServ debtServ, DebtView debtView) {
       this.debtServ = debtServ;
        this.debtView = debtView;
    }

    @Override
    public Payment saisie() {
        Payment payment = new Payment();
        debtView.displayAllUnpaidDebts();
        System.out.println("Saisir l'id de la dette : ");
        int idDebt = scanner.nextInt(); 
        Debt dette = debtServ.getDebtById(idDebt);
        if (dette == null) {
            System.out.println("Dette inexistante");
            return null;
        }
        
        System.out.println("Saisir le montant du paiement : ");
        int amount = scanner.nextInt();
        if (amount <= 0) {
            System.out.println("Le montant doit être supérieur à 0");
            return null;
        }
        payment.setAmount(amount);
        payment.setDate(new Date());
        dette.setPaidMount(dette.getPaidMount() + amount);
        dette.setRemainingMount(dette.getRemainingMount() - amount);
        payment.setDebt(dette);
        dette.addPayment(payment);
        debtServ.updateDebt(dette);
        return payment;
    }

    @Override
    public void show(Payment data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
}
