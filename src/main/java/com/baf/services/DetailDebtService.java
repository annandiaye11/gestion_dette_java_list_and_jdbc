package com.baf.services;

import com.baf.core.Service;
import com.baf.data.entities.DetailDebt;

public interface DetailDebtService extends Service<DetailDebt> {
    DetailDebt getDetailDebtByDebt(int debtId);
}
