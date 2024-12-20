package com.baf.services.impl;

import java.util.List;

import com.baf.data.entities.DetailDebtRequest;
import com.baf.data.repositories.DetailDebtRequestRepository;
import com.baf.services.DetailDebtRequestService;

public class DetailDebtRequestServiceImpl implements DetailDebtRequestService {
    private DetailDebtRequestRepository detailDebtRepository;

    public DetailDebtRequestServiceImpl(DetailDebtRequestRepository detailDebtRepository) {
        this.detailDebtRepository = detailDebtRepository;
    }
    @Override
    public List<DetailDebtRequest> selectAll() {
        return detailDebtRepository.selectAll();
    }

    @Override
    public void insert(DetailDebtRequest data) {
        detailDebtRepository.insert(data);
    }

    @Override
    public DetailDebtRequest getDetailDebtByDebt(int debtId) {
        return detailDebtRepository.getDetailDebtById(debtId);
    }
    
}
