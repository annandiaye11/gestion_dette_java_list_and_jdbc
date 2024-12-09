package com.baf.views.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baf.data.entities.Article;
import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;
import com.baf.data.entities.DetailDebt;
import com.baf.services.ArticleService;
import com.baf.services.DebtRequestServ;
import com.baf.services.DetailDebtService;

public class DebtRequestView extends ViewImpl<DebtRequest> {

    private ClientView clientView;
    private ArticleView articleView;
    private ArticleService articleService;
    private DebtRequestServ detteRequestServ;
    private DetailDebtService detailDebtService;
    public DebtRequestView(ArticleView articleView, ArticleService articleService, ClientView clientView, DebtRequestServ detteRequestServ, DetailDebtService detailDebtService) {
        this.articleView = articleView;
        this.articleService = articleService;
        this.clientView = clientView;
        this.detteRequestServ = detteRequestServ;
        this.detailDebtService = detailDebtService;
    }

    public int subMenuFilter() {
        System.out.println("1. Afficher les demandes de dette en cours");
        System.out.println("2. Afficher les demandes annulées");
        System.out.println("0. Retour");
        return scanner.nextInt();
    }

    // @Override
    // public void liste(List<DebtRequest> detteRequests) {
    // if (detteRequests.isEmpty()) {
    // System.out.println("Aucune demande de dette trouvée.");
    // return;
    // }
    // detteRequests.forEach(detteRequest ->
    // System.out.println(detteRequest.toString()));
    // }

    @Override
    public DebtRequest saisie() {
        DebtRequest debtRequest = new DebtRequest();
        System.out.println("Indentifie toi");
        Client client = clientView.findClientByTel();
        if (client == null) {
            System.out.println("Client non trouvé");
            return null;
        }

        System.out.println("Demande de dette");
        // Affichage des articles disponibles
        System.out.println("Les articles de la dette:");
        List<Article> articles = articleService.selectAll();
        articleView.liste(articles);
        boolean continuer = true;

        while (continuer) {
            System.out.println("Entrez le libellé de l'article:");
            String reponse = scanner.nextLine();

            // Recherche de l'article correspondant au libellé
            Article article = articles.stream()
                    .filter(a -> a.getLibelle().equalsIgnoreCase(reponse))
                    .findFirst()
                    .orElse(null);

            if (article != null) {
                DetailDebt detailDebt = new DetailDebt();
                System.out.println("Entrez la quantité de l'article:");
                int qte = scanner.nextInt();
                if (qte > 0 && qte <= article.getQteStock()) {
                    detailDebt.setQte(qte);
                    detailDebt.setPrix(article.getPrix() * qte);
                    debtRequest.setTotalAmount(detailDebt.getPrix() + debtRequest.getTotalAmount());
                    detailDebt.setArticle(article);
                    debtRequest.addDetailDebt(detailDebt);
                    detailDebt.setDebtRequest(debtRequest); 
                    detailDebtService.insert(detailDebt);
                    System.out.println("Article ajouté : " + article.getLibelle());
                } else {
                    System.out.println("Quantité invalide.");
                }

            } else {
                System.out.println("Aucun article avec le libellé \"" + reponse + "\" trouvé.");
            }

            // Demander si l'utilisateur veut ajouter un autre article
            scanner.nextLine();
            System.out.println("Voulez-vous ajouter un autre article ? (oui/non):");
            String choix = scanner.nextLine();
            if (!choix.equalsIgnoreCase("oui")) {
                continuer = false;
            }
        }

        // Affichage des articles sélectionnés
        // System.out.println("Articles sélectionnés :");
        // debtRequest.getDetailDebts().forEach(detailDebt -> System.out.println("- " + detailDebt.getArticle().getLibelle()));
        debtRequest.setClient(client);

        debtRequest.setStatus("en cours");
        debtRequest.setDate(new Date());
        // System.out.println(debtRequest.toString());
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
        } else {
            detteRequestServ.toggleStatus(id, "refuser");
        }
    }

}