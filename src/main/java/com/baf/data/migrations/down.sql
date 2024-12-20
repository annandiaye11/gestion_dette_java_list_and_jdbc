-- Fichier de migration pour supprimer les tables PostgreSQL (down)

-- Suppression des tables dépendantes en premier
DROP TABLE IF EXISTS "public"."DetailDebtRequest" CASCADE;
DROP TABLE IF EXISTS "public"."DebtRequest" CASCADE;
DROP TABLE IF EXISTS "public"."Payment" CASCADE;
DROP TABLE IF EXISTS "public"."DetailDebt" CASCADE;
DROP TABLE IF EXISTS "public"."Debt" CASCADE;
DROP TABLE IF EXISTS "public"."Article" CASCADE;
DROP TABLE IF EXISTS "public"."Client" CASCADE;
DROP TABLE IF EXISTS "public"."User" CASCADE;
