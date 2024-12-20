package com.baf.views.impl;

import com.baf.data.entities.Article;
import com.baf.services.ArticleService;

public class ArticleView extends ViewImpl<Article> {
    private ArticleService articleServices;

    public ArticleView(ArticleService articleServices) {
        this.articleServices = articleServices;
    }

    public Article saisie() {
        Article article = new Article();
        boolean articleExist = true;
        do {
            System.out.println("Veuillez saisir le libelle");
            article.setLibelle(scanner.nextLine());
            articleExist = articleServices.selectByLibelle(article.getLibelle()) != null;
            if (articleExist) {
                System.out.println("Ooups !! Cet article exist deja");
                System.out.println("veuillez réessayer");
            }
          
        } while ( article.getLibelle() == "" || articleExist);
        System.out.println(articleExist);
        do {
            System.out.println("Veuillez saisir la quantite en stock");
            article.setQte_stock(scanner.nextInt());

        } while (article.getQte_stock() < 0);

        do {
            System.out.println("Veuillez saisir le prix");
            article.setPrix(scanner.nextInt());

        } while (article.getPrix() < 0);
        return article;
    }

    public void showAvailableArticles() {
        System.out.println("Liste des articles disponibles : ");
        articleServices.selectAll().forEach(article -> {
            if (article.getQte_stock() > 0)
                System.out.println(article.toString());
        });
    }

    public void updateQteStock() {
        scanner.nextLine();
        System.out.println("Veuillez saisir l'id de l'article");
        int id = scanner.nextInt();
        Article article = articleServices.selectById(id);
        if (article != null) {
            System.out.println("Veuillez saisir la nouvelle quantite en stock");
            int qte = scanner.nextInt();
            article.setQte_stock(qte);
            System.out.println("La quantite a ete mise a jour");
        } else {
            System.out.println("l'id: " + id);
            System.out.println("L'article n'existe pas");
        }
    }

    @Override
    public void show(Article data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
}
