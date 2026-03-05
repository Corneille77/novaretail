-- NovaRetail legacy mock data
CREATE DATABASE IF NOT EXISTS novaretail_legacy;
USE novaretail_legacy;

DROP TABLE IF EXISTS customer_transactions;
CREATE TABLE customer_transactions (
  transaction_id   INT PRIMARY KEY,
  customer_name    VARCHAR(120) NOT NULL,
  customer_email   VARCHAR(180) NOT NULL,
  customer_age     INT NOT NULL,
  country          VARCHAR(80) NULL,
  purchase_amount  DOUBLE NOT NULL,
  clearance_level  INT NOT NULL
);

-- 20 lignes dont plusieurs country = NULL (données corrompues)
INSERT INTO customer_transactions (transaction_id, customer_name, customer_email, customer_age, country, purchase_amount, clearance_level) VALUES
(1,  'Lina Martin',      'lina.martin@example.com',     28, 'France',     129.90, 2),
(2,  'Noah Bernard',     'noah.bernard@example.com',    34, 'France',     349.00, 3),
(3,  'Sara Diallo',      'sara.diallo@example.com',     22, 'Belgium',    59.99,  1),
(4,  'Hugo Petit',       'hugo.petit@example.com',      41, NULL,         79.50,  2),
(5,  'Maya Lopez',       'maya.lopez@example.com',      30, 'Spain',      499.90, 4),
(6,  'Ibrahim Koné',     'ibrahim.kone@example.com',    27, 'Spain',      189.00, 2),
(7,  'Emma Moreau',      'emma.moreau@example.com',     25, 'Germany',    15.99,  1),
(8,  'Lucas Dupont',     'lucas.dupont@example.com',    38, 'France',     999.99, 5),
(9,  'Inès Benali',      'ines.benali@example.com',     29, NULL,         24.00,  1),
(10, 'David Rossi',      'david.rossi@example.com',     33, 'Italy',      89.90,  2),
(11, 'Awa Traoré',       'awa.traore@example.com',      26, 'Belgium',    259.00, 3),
(12, 'Chloé Garnier',    'chloe.garnier@example.com',   31, 'France',     49.90,  1),
(13, 'Karim Haddad',     'karim.haddad@example.com',    45, 'Germany',    199.99, 4),
(14, 'Nora Svensson',    'nora.svensson@example.com',   24, 'Sweden',     74.20,  2),
(15, 'Adam Kowalski',    'adam.kowalski@example.com',   36, 'Poland',     120.00, 2),
(16, 'Sofia Alvarez',    'sofia.alvarez@example.com',   28, 'Spain',      39.95,  1),
(17, 'Yasmine El Amrani','yasmine.elamrani@example.com',32, NULL,         310.10, 3),
(18, 'Tom van Dijk',     'tom.vandijk@example.com',     40, 'Netherlands',210.00, 4),
(19, 'Luca Bianchi',     'luca.bianchi@example.com',    23, 'Italy',      650.00, 5),
(20, 'Mila Novak',       'mila.novak@example.com',      35, 'Poland',     5.50,   1);

-- Vérifications rapides
-- SELECT COUNT(*) FROM customer_transactions;
-- SELECT COUNT(*) FROM customer_transactions WHERE country IS NULL;
