-- Fichier de migration pour PostgreSQL

-- Création de la table des utilisateurs
CREATE TABLE "public"."User" (
    id SERIAL PRIMARY KEY,
    email TEXT NOT NULL UNIQUE,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL, -- Par exemple: "Admin", "Boutiquier", "Client"
    is_active BOOLEAN DEFAULT TRUE
);

-- Création de la table des clients
CREATE TABLE "public"."Client" (
    id SERIAL PRIMARY KEY,
    surname TEXT NOT NULL,
    telephone TEXT NOT NULL UNIQUE,
    address TEXT NOT NULL,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    user_id INT UNIQUE, -- Lien vers un utilisateur
    FOREIGN KEY (user_id) REFERENCES "public"."User"(id) ON DELETE SET NULL
);

-- Création de la table des articles
CREATE TABLE "public"."Article" (
    id SERIAL PRIMARY KEY,
    libelle TEXT NOT NULL,
    qte_stock INT NOT NULL DEFAULT 0,
    prix INT NOT NULL
);

-- Création de la table des dettes
CREATE TABLE "public"."Debt" (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    mount NUMERIC(10, 2) NOT NULL,
    amount_paid NUMERIC(10, 2) DEFAULT 0,
    remaining_amount NUMERIC(10, 2) NOT NULL,
    is_achievied BOOLEAN DEFAULT FALSE,
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES "public"."Client" (id) ON DELETE CASCADE
);

-- Création de la table des détails de dettes
CREATE TABLE "public"."DetailDebt" (
    id SERIAL PRIMARY KEY,
    quantity INT NOT NULL,
    prix NUMERIC(10, 2) NOT NULL,
    article_id INT NOT NULL,
    debt_id INT NOT NULL,
    FOREIGN KEY (debt_id) REFERENCES "public"."Debt"(id) ON DELETE CASCADE,
    FOREIGN KEY (article_id) REFERENCES "public"."Article"(id) ON DELETE CASCADE
);

-- Création de la table des paiements
CREATE TABLE "public"."Payment" (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    amount NUMERIC(10, 2) NOT NULL,
    debt_id INT NOT NULL,
    FOREIGN KEY (debt_id) REFERENCES "public"."Debt"(id) ON DELETE CASCADE
);

-- Création de la table des demandes de dette
CREATE TABLE "public"."DebtRequest" (
    id SERIAL PRIMARY KEY,
    totalAmount INT NOT NULL,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    status TEXT NOT NULL DEFAULT 'En Cours', -- "En Cours", "Annuler", "Valider"
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES "public"."Client"(id) ON DELETE CASCADE
);

-- Création de la table des détails des demandes de dette
CREATE TABLE "public"."DetailDebtRequest" (
    id SERIAL PRIMARY KEY,
    quantity INT NOT NULL,
    prix INT NOT NULL,
    debt_request_id INT NOT NULL,
    article_id INT NOT NULL,
    FOREIGN KEY (debt_request_id) REFERENCES "public"."DebtRequest"(id) ON DELETE CASCADE,
    FOREIGN KEY (article_id) REFERENCES "public"."Article"(id) ON DELETE CASCADE
);

-- Contraintes supplémentaires pour garantir l'intégrité des données
ALTER TABLE "public"."Debt" ADD CONSTRAINT chk_remaining_amount CHECK (remaining_amount >= 0);
ALTER TABLE "public"."Payment"  ADD CONSTRAINT chk_payment_amount CHECK (amount > 0);
ALTER TABLE "public"."DetailDebt"  ADD CONSTRAINT chk_quantity CHECK (quantity > 0);
ALTER TABLE "public"."DetailDebtRequest"  ADD CONSTRAINT chk_request_quantity CHECK (quantity > 0);
