package com.baf.core.factory;

import com.baf.views.impl.ArticleView;
import com.baf.views.impl.ClientView;
import com.baf.views.impl.DebtRequestView;
import com.baf.views.impl.DebtView;
import com.baf.views.impl.PaymentView;
import com.baf.views.impl.UserView;

public class FactoryView {

    // Cache pour vérifier si l'instance existe déjà
    private static UserView userView;
    private static ClientView clientView;
    private static ArticleView articleView;
    private static DebtView debtView;
    private static PaymentView paymentView;
    private static DebtRequestView debtRequestView;

    // Méthode pour créer ou récupérer une instance de UserView
    public static UserView createUserView() {
        if (userView == null) {
            userView = new UserView(FactoryService.createUserService(), FactoryService.createClientService());
        }
        return userView;
    }

    // Méthode pour créer ou récupérer une instance de ClientView
    public static ClientView createClientView() {
        if (clientView == null) {
            clientView = new ClientView(FactoryService.createClientService(), createUserView());
        }
        return clientView;
    }

    // Méthode pour créer ou récupérer une instance de ArticleView
    public static ArticleView createArticleView() {
        if (articleView == null) {
            articleView = new ArticleView(FactoryService.createArticleService());
        }
        return articleView;
    }

    // Méthode pour créer ou récupérer une instance de DebtView
    public static DebtView createDebtView() {
        if (debtView == null) {
            debtView = new DebtView(FactoryService.createDebtService(), FactoryService.createArticleService(),
                    createArticleView(),
                    FactoryService.createClientService(), createClientView(),
                    FactoryService.createDetailDebtService());
        }
        return debtView;
    }

    // Méthode pour créer ou récupérer une instance de PaymentView
    public static PaymentView createPaymentView() {
        if (paymentView == null) {
            paymentView = new PaymentView(FactoryService.createDebtService(), createDebtView());
        }
        return paymentView;
    }

    // Méthode pour créer ou récupérer une instance de DebtRequestView
    public static DebtRequestView createDebtRequestView() {
        if (debtRequestView == null) {
            debtRequestView = new DebtRequestView(createArticleView(), FactoryService.createArticleService(),
                    createClientView(), FactoryService.createDebtRequestService(),
                    FactoryService.createDetailDebtRequestService(), FactoryService.createDebtService());
        }
        return debtRequestView;
    }
}
