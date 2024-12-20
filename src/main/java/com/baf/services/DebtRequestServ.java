package com.baf.services;

import java.util.List;

import com.baf.core.Service;
import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;

public interface DebtRequestServ extends Service<DebtRequest> {
    List<DebtRequest> selectPendingRequestsForCl(Client client);
    List<DebtRequest> selectCanceledRequestsForCl(Client client);
    void toggleStatus(int idDebtRequest, String status); 
    DebtRequest getDebtRequestById(int idDebtRequest);      
    int insertWithId(DebtRequest debtRequest, int index); 
}
