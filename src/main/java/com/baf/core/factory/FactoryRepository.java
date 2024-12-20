package com.baf.core.factory;

import com.baf.data.repositories.ArticleRepository;
import com.baf.data.repositories.ClientRepository;
import com.baf.data.repositories.DebtRepository;
import com.baf.data.repositories.DebtRequestRepository;
import com.baf.data.repositories.DetailDebtRepository;
import com.baf.data.repositories.DetailDebtRequestRepository;
import com.baf.data.repositories.PaymentRepository;
import com.baf.data.repositories.UserRepository;
import com.baf.data.repositories.db.ArticleRepositoryImplDB;
import com.baf.data.repositories.db.ClientRepositoryImplDB;
import com.baf.data.repositories.db.DebtRepositoryImplDB;
import com.baf.data.repositories.db.DebtRequestRepositoryImplDB;
import com.baf.data.repositories.db.DetailDebtRepositoryImplDB;
import com.baf.data.repositories.db.DetailDebtRequestRepositoryImplDB;
import com.baf.data.repositories.db.PaymentRepositoryImplDB;
import com.baf.data.repositories.db.UserRepositoryImplDB;

public class FactoryRepository {

    private static ClientRepository clientRepository;
    private static UserRepository userRepository;
    private static ArticleRepository articleRepository;
    private static DebtRepository debtRepository;
    private static PaymentRepository paymentRepository;
    private static DebtRequestRepository debtRequestRepository;
    private static DetailDebtRequestRepository detailDebtRequestRepository;
    private static DetailDebtRepository detailDebtRepository;

    // Méthode pour créer un clientRepository
    public static ClientRepository createClientRepository() {
        if (clientRepository == null) {
            clientRepository = new ClientRepositoryImplDB();
        }
        return clientRepository;
    }

    // Méthode pour créer un userRepository
    public static UserRepository createUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepositoryImplDB();
        }
        return userRepository;
    }

    // Méthode pour créer un articleRepository
    public static ArticleRepository createArticleRepository() {
        if (articleRepository == null) {
            articleRepository = new ArticleRepositoryImplDB();
        }
        return articleRepository;
    }

    public static DebtRepository createDebtRepository() {
        if (debtRepository == null) {
            debtRepository = new DebtRepositoryImplDB();
        }
        return debtRepository;
    }

    public static PaymentRepository createPaymentRepository() {
        if (paymentRepository == null) {
            paymentRepository = new PaymentRepositoryImplDB();
        }
        return paymentRepository;
    }

    public static DebtRequestRepository createDebtRequestRepository() {
        if (debtRequestRepository == null) {
            debtRequestRepository = new DebtRequestRepositoryImplDB();
        }
        return debtRequestRepository;
    }

    public static DetailDebtRequestRepository createDetailDebtRequestRepository() {
        if (detailDebtRequestRepository == null) {
            detailDebtRequestRepository = new DetailDebtRequestRepositoryImplDB();
        }
        return detailDebtRequestRepository;
    }

    public static DetailDebtRepository createDetailDebtRepository() {
        if (detailDebtRepository == null) {
            detailDebtRepository = new DetailDebtRepositoryImplDB();
        }
        return detailDebtRepository;
    }


}
