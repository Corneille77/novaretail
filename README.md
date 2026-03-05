# NovaRetail ETL (Spark + Java)

Projet: "Projet Finaux (Groupe) - Introduction du Big Data" (Tyrolium).
Objectif: Pipeline ETL Java/Spark depuis MySQL (JDBC) vers JSON partitionné.

Ressources de cours citées dans l'énoncé:
- "Apache Spark Introduction" — Julien Nauroy
- "Let's Watch — Introduction à Spark" — Dimitri Condoris

## Ce que fait le pipeline
1) Extraction: lecture JDBC de `novaretail_legacy.customer_transactions`
2) Nettoyage: suppression des lignes où `country` est NULL
3) Anonymisation: suppression de la colonne `customer_email`
4) Tri: par `country`, puis `purchase_amount` décroissant (dans chaque partition)
5) Load: export JSON en mode Overwrite + `.partitionBy("country")`

## Paramètres (par défaut)
- DB: jdbc:mysql://localhost:3306/novaretail_legacy?useSSL=false&serverTimezone=UTC
- Table: customer_transactions
- Output: ./output/datalake/customer_transactions_json

Tu peux surcharger via variables d'environnement:
- DB_URL, DB_USER, DB_PASS, DB_TABLE, OUTPUT_PATH

## Build
mvn -q -DskipTests package

## Run (fat-jar)
java -jar target/novaretail-etl-1.0.0.jar
