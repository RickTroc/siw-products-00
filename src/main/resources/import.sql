
INSERT INTO fornitore (id, nome, indirizzo) VALUES  (nextval('hibernate_sequence'), 'GIGI inc.', 'via di qua 35');
INSERT INTO fornitore (id, nome, indirizzo) VALUES  (nextval('hibernate_sequence'), 'ABC Company', 'via delle Rose 10');
INSERT INTO fornitore (id, nome, indirizzo) VALUES  (nextval('hibernate_sequence'), 'XYZ Corporation', 'via del Sole 22');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (nextval('hibernate_sequence'), 'Fornitore D', 'Indirizzo D');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (nextval('hibernate_sequence'), 'Fornitore E', 'Indirizzo E');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (123, 'Fornitore F', 'Indirizzo F');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (7, 'Fornitore G', 'Indirizzo G');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (8, 'Fornitore H', 'Indirizzo H');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (9, 'Fornitore I', 'Indirizzo I');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (10, 'Fornitore J', 'Indirizzo J');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (11, 'Fornitore K', 'Indirizzo K');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (12, 'Fornitore L', 'Indirizzo L');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (13, 'Fornitore M', 'Indirizzo M');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (14, 'Fornitore N', 'Indirizzo N');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (15, 'Fornitore O', 'Indirizzo O');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (16, 'Fornitore P', 'Indirizzo P');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (17, 'Fornitore Q', 'Indirizzo Q');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (18, 'Fornitore R', 'Indirizzo R');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (19, 'Fornitore S', 'Indirizzo S');
INSERT INTO fornitore (id, nome, indirizzo) VALUES (20, 'Fornitore T', 'Indirizzo T');


INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (1, 'Cacciavite', 19.99, 'Descrizione del Prodotto A');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (2, 'Spazzolino', 29.99, 'Descrizione del Prodotto B');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (3, 'Padella', 14.99, 'Descrizione del Prodotto C');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (4, 'Prodotto D', 24.99, 'Descrizione del Prodotto D');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (5, 'Prodotto E', 39.99, 'Descrizione del Prodotto E');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (6, 'Prodotto F', 9.99, 'Descrizione del Prodotto F');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (7, 'Prodotto G', 34.99, 'Descrizione del Prodotto G');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (8, 'Prodotto H', 17.99, 'Descrizione del Prodotto H');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (9, 'Prodotto I', 49.99, 'Descrizione del Prodotto I');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (10, 'Prodotto J', 7.99, 'Descrizione del Prodotto J');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (11, 'Prodotto K', 29.99, 'Descrizione del Prodotto K');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (12, 'Prodotto L', 14.99, 'Descrizione del Prodotto L');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (13, 'Prodotto M', 54.99, 'Descrizione del Prodotto M');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (14, 'Prodotto N', 19.99, 'Descrizione del Prodotto N');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (15, 'Prodotto O', 64.99, 'Descrizione del Prodotto O');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (16, 'Prodotto P', 12.99, 'Descrizione del Prodotto P');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (17, 'Prodotto Q', 44.99, 'Descrizione del Prodotto Q');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (18, 'Prodotto R', 21.99, 'Descrizione del Prodotto R');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (19, 'Prodotto S', 74.99, 'Descrizione del Prodotto S');
INSERT INTO prodotto (id, nome, prezzo, descrizione) VALUES (20, 'Prodotto T', 9.99, 'Descrizione del Prodotto T');


INSERT INTO users(id, email, name, surname) VALUES(1,"a@a.a","a","a");
INSERT INTO credentials(id, user_id, password, role, username) values(1, 1, "$2a$10$08vwI9dUQW1MdcSsfIe3mu.Ls2so743alvZC4dhnm9oMmpIrRm5ni", "ADMIN", "a");
