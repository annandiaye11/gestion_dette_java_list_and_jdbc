package com.baf.services;

import com.baf.core.Service;
import com.baf.data.entities.DetailDebtRequest;

public interface DetailDebtRequestService extends Service<DetailDebtRequest> {
    DetailDebtRequest getDetailDebtByDebt(int debtId);
}
