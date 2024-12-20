package com.baf;

import java.util.Scanner;

import com.baf.core.factory.FactoryService;
import com.baf.core.factory.FactoryView;
import com.baf.data.entities.Article;
import com.baf.data.entities.Client;
import com.baf.data.entities.User;
import com.baf.services.ArticleService;
import com.baf.services.ClientService;
import com.baf.services.DebtRequestServ;
import com.baf.services.DebtServ;
import com.baf.services.PaymentServ;
import com.baf.services.UserServ;
import com.baf.views.impl.ArticleView;
import com.baf.views.impl.ClientView;
import com.baf.views.impl.DebtRequestView;
import com.baf.views.impl.DebtView;
import com.baf.views.impl.MenuView;
import com.baf.views.impl.PaymentView;
import com.baf.views.impl.UserView;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // ----------------------------Services--------------------------------
        ClientService clientService = FactoryService.createClientService();
        UserServ userServ = FactoryService.createUserService();
        ArticleService articleService = FactoryService.createArticleService();
        DebtServ debtServ = FactoryService.createDebtService();
        PaymentServ paymentServ = FactoryService.createPaymentService();
        DebtRequestServ debtRequestServ = FactoryService.createDebtRequestService();
        // ----------------------------Vues-------------------------------------
        UserView userView = FactoryView.createUserView();
        ClientView clientView = FactoryView.createClientView();
        ArticleView articleView = FactoryView.createArticleView();
        DebtView debtView = FactoryView.createDebtView();
        PaymentView paymentView = FactoryView.createPaymentView();
        DebtRequestView debtRequestView = FactoryView.createDebtRequestView();

        int choice;
        do {
            choice = showMainMenu();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    int choiceAdmin;
                    do {
                        choiceAdmin = MenuView.adminMenu();
                        switch (choiceAdmin) {
                            case 1:
                                // Creer un user account pour un en ayant pas
                                String surnameClient = clientView.showClientWhitoutAccount();
                                User userWhithAccount = userView.createUserForClient(surnameClient);
                                userServ.insert(userWhithAccount);
                                break;
                            case 2:
                                // Creer un user avec un role boutiquier ou admin
                                // Un user c'est aussi un client !!
                                User user = userView.createUser();
                                userServ.insert(user);
                                break;
                            case 3:
                                // Désactiver/Activer un compte utilisateur
                                userView.toggleUserAccount();
                                break;
                            case 4:
                                // Afficher les comptes utilisateurs actifs ou par rôle
                                int subChoiceFilter = userView.subMenuFilter();
                                switch (subChoiceFilter) {
                                    case 1:
                                        userView.showAllActifUsers();
                                        break;
                                    case 2:
                                        userView.showAllUsersByRole();
                                        break;
                                    default:
                                        System.out.println("Option non existante");
                                        break;
                                }
                                break;
                            case 5:
                                // Creer un article
                                Article article = articleView.saisie();
                                articleService.insert(article);
                                break;
                            case 6:
                                // Lister les articles
                                articleView.liste(articleService.selectAll());
                                break;
                            case 7:
                                // Afficher les articles disponibles
                                articleView.showAvailableArticles();
                                break;
                            case 8:
                                // Mettre à jour la quantité d'un article
                                articleView.updateQteStock();
                                break;
                            case 9:
                                // Archiver les dettes soldées
                                debtView.archivePaidDebts();
                                break;
                            case 10:
                                // Quitter
                                System.out.println("Au revoir");
                                break;
                            default:
                                System.out.println("Option non existante");
                                break;
                        }
                    } while (choiceAdmin != 10);
                    break;
                case 2:
                    int choiceMenu;
                    do {
                        choiceMenu = MenuView.boutiquierMenu();
                        switch (choiceMenu) {
                            case 1:
                                clientService.insert(clientView.saisie());
                                break;
                            case 2:
                                clientView.filtre(clientService.selectAll());
                                break;
                            case 3:
                                System.out.println("Liste de tous les clients");
                                clientView.liste(clientService.selectAll());
                                System.out.println("Veillez entrer le numero de tel");
                                String tel = scanner.nextLine();
                                Client client = clientService.selectByTel(tel.trim());
                                if (client != null) {
                                    clientView.show(client);
                                } else {
                                    System.out.println("Ce client n'existe pas");
                                }
                                break;
                            case 4:
                                debtServ.insert(debtView.saisie());
                                break;
                            case 5:
                                // Enregistrer un paiement pour une dette
                                paymentServ.insert(paymentView.saisie());
                                break;
                            case 6:
                                debtView.showDetteByClient();
                                break;
                            case 7:
                                
                                break;
                            case 8:
                                // Gérer les demandes de dette (valider/refuser)
                                debtRequestView.handleDebtRequest();
                            case 0:
                                System.out.println("Retour au menu login");
                                break;
                            default:
                                break;
                        }

                    } while (choiceMenu != 0);
                    break;
                    case 3:
                    // Cases pour le client
                    int choiceClient;
                    do {
                        choiceClient = MenuView.showClientMenu();
                        switch (choiceClient) {
                            case 1:
                                // Lister mes dettes non soldées
                                debtView.displayAllUnpaidDebts();
                                break;
                            case 2:
                                // Faire une demande de dette
                                debtRequestServ.insert(debtRequestView.saisie());

                                break;
                            case 3:
                                // Lister mes demandes de dette avec option de filtre
                                Client client = clientView.findClientByTel();
                                if (client == null) {
                                    System.out.println("Client non trouvé");
                                    break;
                                }
                                debtRequestView.liste(debtRequestServ.selectAll());
                                int subChoiceFilter ;
                                do {
                                     subChoiceFilter = debtRequestView.subMenuFilter();
                                    switch (subChoiceFilter) {
                                        case 1:
                                            debtRequestView.liste(debtRequestServ.selectPendingRequestsForCl(client));
                                            break;
                                        case 2:
                                            debtRequestView.liste(debtRequestServ.selectCanceledRequestsForCl(client));
                                            break;
                                        case 0:
                                            System.out.println("Retour");
                                            break;
                                        default:
                                            System.out.println("Option non existante");
                                            break;
                                    }
                                } while (subChoiceFilter != 0);
                                break;
                            case 4:
                                // Envoyer une relance pour une demande annulée
                                System.out.println("Pour faire une relance veillez selectionner l'option 2 Faire une demande de dette");
                                System.out.println("Merci pour votre comprehension");
                                break;
                            case 0:
                                System.out.println("Au revoir !");
                            default:
                                System.out.println("Choix invalide, essayez encore!");
                                break;
                        }

                    } while (choiceClient != 0);
                    
                    break;
                default:
                    System.out.println("Choix invalide, essayez encore !");
                    break;
            }

        } while (choice != 0);

    }

    public static int showMainMenu() {
        System.out.println("=== Bienvenue dans l'application de gestion ===");
        System.out.println("Veuillez choisir un rôle :");
        System.out.println("1. Admin");
        System.out.println("2. Boutiquier");
        System.out.println("3. Client");
        System.out.println("0. Quitter");
        return scanner.nextInt();
    }

}