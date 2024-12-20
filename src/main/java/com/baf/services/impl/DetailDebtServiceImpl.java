package com.baf.services.impl;

import java.util.List;

import com.baf.data.entities.DetailDebt;
import com.baf.data.repositories.DetailDebtRepository;
import com.baf.services.DetailDebtService;

public class DetailDebtServiceImpl implements DetailDebtService {
    private DetailDebtRepository detailDebtRepository;

    public DetailDebtServiceImpl(DetailDebtRepository detailDebtRepository) {
        this.detailDebtRepository = detailDebtRepository;
    }
  
   
    @Override
    public void insert(DetailDebt data) {
        detailDebtRepository.insert(data);
    }


    @Override
    public List<DetailDebt> selectAll() {
        return detailDebtRepository.selectAll();
    }


    @Override
    public DetailDebt getDetailDebtByDebt(int debtId) {
        return detailDebtRepository.getDetailDebtById(debtId);
    }
  
    
}
