package com.baf.core.factory;

import com.baf.services.ArticleService;
import com.baf.services.ClientService;
import com.baf.services.DebtRequestServ;
import com.baf.services.DebtServ;
import com.baf.services.DetailDebtRequestService;
import com.baf.services.DetailDebtService;
import com.baf.services.PaymentServ;
import com.baf.services.UserServ;
import com.baf.services.impl.ArticleServiceImpl;
import com.baf.services.impl.ClientServiceImpl;
import com.baf.services.impl.DebtRequestServImpl;
import com.baf.services.impl.DebtServImpl;
import com.baf.services.impl.DetailDebtRequestServiceImpl;
import com.baf.services.impl.DetailDebtServiceImpl;
import com.baf.services.impl.PaymentsServImpl;
import com.baf.services.impl.UserServImpl;

public class FactoryService {

    // Cache pour vérifier si l'instance existe déjà
    private static ClientService clientService;
    private static UserServ userService;
    private static ArticleService articleService;
    private static DebtServ debtService;
    private static PaymentServ paymentService;
    private static DebtRequestServ debtRequestService;
    private static DetailDebtRequestService detailDebtRequestService;
    private static DetailDebtService detailDebtService;
    private static DetailDebtRequestService detailDebt;
    // Méthode pour créer ou récupérer une instance de ClientService
    public static ClientService createClientService() {
        if (clientService == null) {
            clientService = new ClientServiceImpl(FactoryRepository.createClientRepository());
        }
        return clientService;
    }

    // Méthode pour créer ou récupérer une instance de UserService
    public static UserServ createUserService() {
        if (userService == null) {
            userService = new UserServImpl(FactoryRepository.createUserRepository());
        }
        return userService;
    }

    // Méthode pour créer ou récupérer une instance de ArticleService
    public static ArticleService createArticleService() {
        if (articleService == null) {
            articleService = new ArticleServiceImpl(FactoryRepository.createArticleRepository());
        }
        return articleService;
    }

    // Méthode pour créer ou récupérer une instance de DebtService
    public static DebtServ createDebtService() {
        if (debtService == null) {
            debtService = new DebtServImpl(FactoryRepository.createDebtRepository());
        }
        return debtService;
    }

    // Méthode pour créer ou récupérer une instance de PaymentService
    public static PaymentServ createPaymentService() {
        if (paymentService == null) {
            paymentService = new PaymentsServImpl(FactoryRepository.createPaymentRepository());
        }
        return paymentService;
    }

    // Méthode pour créer ou récupérer une instance de DebtRequestService
    public static DebtRequestServ createDebtRequestService() {
        if (debtRequestService == null) {
            debtRequestService = new DebtRequestServImpl(FactoryRepository.createDebtRequestRepository());
        }
        return debtRequestService;
    }

    // Méthode pour créer ou récupérer une instance de DetailDebtRequestService
    public static DetailDebtRequestService createDetailDebtRequestService() {
        if (detailDebtRequestService == null) {
            detailDebtRequestService = new DetailDebtRequestServiceImpl(FactoryRepository.createDetailDebtRequestRepository());
        }
        return detailDebtRequestService;
    }

    // Méthode pour créer ou récupérer une instance de DetailDebtService
    public static DetailDebtService createDetailDebtService() {
        if (detailDebtService == null) {
            detailDebtService = new DetailDebtServiceImpl(FactoryRepository.createDetailDebtRepository());
        }
        return detailDebtService;
    }


}
