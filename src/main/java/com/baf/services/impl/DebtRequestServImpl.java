package com.baf.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;
import com.baf.data.repositories.DebtRequestRepository;
import com.baf.services.DebtRequestServ;

public class DebtRequestServImpl implements DebtRequestServ {
    private DebtRequestRepository debtRequestRepo;

    public DebtRequestServImpl(DebtRequestRepository debtRequestRepo) {
        this.debtRequestRepo = debtRequestRepo;
    }

    public void insert(DebtRequest debtRequest) {
        debtRequestRepo.insert(debtRequest);
    }


    public List<DebtRequest> selectAll() {
        return debtRequestRepo.selectAll();
    }

    public List<DebtRequest> selectPendingRequestsForCl(Client client) {
        List<DebtRequest> pendingRequests = new ArrayList<>();
        for (DebtRequest debtRequest : debtRequestRepo.selectAll()) {
            if (debtRequest.getStatus() != null && debtRequest.getStatus().equals("en cours") && debtRequest.getClient().getTelephone().equals(client.getTelephone())) {
                pendingRequests.add(debtRequest);
            }
        }
        return pendingRequests;
    }

    public List<DebtRequest> selectCanceledRequestsForCl(Client client) {
        List<DebtRequest> canceledRequests = new ArrayList<>();
        for (DebtRequest debtRequest : debtRequestRepo.selectAll()) {
            if (debtRequest.getStatus() != null &&debtRequest.getStatus().equals("annuler") && debtRequest.getClient().getTelephone().equals(client.getTelephone())) {
                canceledRequests.add(debtRequest);
            }
        }
        return canceledRequests;
    }

    @Override
    public void toggleStatus(int idDebtRequest, String status) {
        debtRequestRepo.updateStatus(idDebtRequest, status);
    }

    @Override
    public DebtRequest getDebtRequestById(int idDebtRequest) {
        return debtRequestRepo.selectById(idDebtRequest);
    }

    @Override
    public int insertWithId(DebtRequest debtRequest, int index) {
        return debtRequestRepo.insertWithId(debtRequest, index);
    }
}
