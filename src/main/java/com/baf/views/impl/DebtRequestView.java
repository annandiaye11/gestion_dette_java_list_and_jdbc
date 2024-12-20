package com.baf.views.impl;

import java.util.Date;
import java.util.List;

import com.baf.data.entities.Article;
import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;
import com.baf.data.entities.DebtRequest;
import com.baf.data.entities.DetailDebt;
import com.baf.data.entities.DetailDebtRequest;
import com.baf.services.ArticleService;
import com.baf.services.DebtRequestServ;
import com.baf.services.DebtServ;
import com.baf.services.DetailDebtRequestService;

public class DebtRequestView extends ViewImpl<DebtRequest> {
    private DebtServ debtServ;
    private ClientView clientView;
    private ArticleView articleView;
    private ArticleService articleService;
    private DebtRequestServ detteRequestServ;
    private DetailDebtRequestService detailDebtRequestService;

    public DebtRequestView(ArticleView articleView, ArticleService articleService, ClientView clientView,
            DebtRequestServ detteRequestServ, DetailDebtRequestService detailDebtRequestService,
            DebtServ debtServ) {
        this.debtServ = debtServ;
        this.articleView = articleView;
        this.articleService = articleService;
        this.clientView = clientView;
        this.detteRequestServ = detteRequestServ;
        this.detailDebtRequestService = detailDebtRequestService;
    }

    public int subMenuFilter() {
        System.out.println("1. Afficher les demandes de dette en cours");
        System.out.println("2. Afficher les demandes annulées");
        System.out.println("0. Retour");
        return scanner.nextInt();
    }

    // @Override
    // public void liste(List<DebtRequest> detteRequests) {
    //     if (detteRequests.isEmpty()) {
    //         System.out.println("Aucune demande de dette trouvée.");
    //         return;
    //     }
    //     detteRequests.forEach(detteRequest -> System.out.println(detteRequest.toString()));
    // }

    @Override

    public DebtRequest saisie() {
        DebtRequest debtRequest = new DebtRequest();
        System.out.println("Identifie toi");

        Client client = clientView.findClientByTel();
        if (client == null) {
            System.out.println("Client non trouvé");
            return null;
        }

        System.out.println("Demande de dette");
        List<Article> articles = articleService.selectAll();
        articleView.liste(articles);

        boolean continuer = true;
        while (continuer) {
            System.out.println("Entrez le libellé de l'article:");
            String reponse = scanner.nextLine();

            Article article = articles.stream()
                    .filter(a -> a.getLibelle().equalsIgnoreCase(reponse))
                    .findFirst()
                    .orElse(null);

            if (article != null) {
                DetailDebtRequest detailDebt = new DetailDebtRequest();
                System.out.println("Entrez la quantité de l'article:");
                int qte = scanner.nextInt();

                if (qte > 0 && qte <= article.getQte_stock()) {
                    detailDebt.setQte(qte);
                    detailDebt.setPrix(article.getPrix() * qte);
                    debtRequest.setTotalAmount(detailDebt.getPrix() + debtRequest.getTotalAmount());
                    detailDebt.setArticle(article);

                    // IMPORTANT : Ne PAS insérer DetailDebtRequest ici
                    debtRequest.addDetailDebt(detailDebt);
                    System.out.println("Article ajouté : " + article.getLibelle());
                } else {
                    System.out.println("Quantité invalide.");
                }
            } else {
                System.out.println("Aucun article avec le libellé \"" + reponse + "\" trouvé.");
            }

            scanner.nextLine(); // Consommer le newline
            System.out.println("Voulez-vous ajouter un autre article ? (oui/non):");
            String choix = scanner.nextLine();

            if (!choix.equalsIgnoreCase("oui")) {
                continuer = false;
            }
        }

        debtRequest.setClient(client);
        debtRequest.setStatus("en cours");
        debtRequest.setDate(new Date());

        // NOUVEAU : Insérer DebtRequest en premier
        int debtRequestId = detteRequestServ.insertWithId(debtRequest, 0);
        System.out.println("ID de la demande de dette : " + debtRequestId);
        // Ensuite, insérer les DetailDebtRequest avec l'ID généré
        for (DetailDebtRequest detailDebt : debtRequest.getDetailDebts()) {
            detailDebt.setId(debtRequestId);
            detailDebt.setDebtRequest(debtRequest);
            detailDebtRequestService.insert(detailDebt);
        }

        return debtRequest;
    }

    public void handleDebtRequest() {
        liste(detteRequestServ.selectAll());
        System.out.println("Veuillez saisir L'id de la demande de dette");
        int id = scanner.nextInt();
        System.out.println("Voulez vous valider (oui/non)");
        String reponse = scanner.nextLine();
        DebtRequest detteRequest = detteRequestServ.getDebtRequestById(id);
        if (detteRequest == null) {
            System.out.println("Cette demande n'existe pas");
            return;
        }
        if (reponse.equalsIgnoreCase("oui")) {
            detteRequestServ.toggleStatus(id, "valider");
            Debt debt = new Debt();
            debt.setClient(detteRequest.getClient());
            debt.setDate(new Date());
            debt.setRemainingMount(detteRequest.getTotalAmount());
            for (DetailDebtRequest detailDebtRequest : detteRequest.getDetailDebts()) {
                DetailDebt detailDebt = new DetailDebt();
                detailDebt.setArticle(detailDebtRequest.getArticle());
                detailDebt.setQte(detailDebtRequest.getQte());
                detailDebt.setPrix(detailDebtRequest.getPrix());
                detailDebt.setDebt(debt);
                debt.addDetailDebt(detailDebt);
            }
            debtServ.insert(debt);
        } else {
            detteRequestServ.toggleStatus(id, "refuser");
        }
    }

}