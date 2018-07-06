INSERT INTO USERS(login, first_name, name, job) VALUES ('gpereira', 'Guillaume', 'Pereira', 'Scrum Master');
INSERT INTO USERS(login, first_name, name, job) VALUES ('gvaneecloo', 'Guillaume', 'Vaneecloo', 'Architecte');
INSERT INTO USERS(login, first_name, name, job) VALUES ('mbah', 'Bah', 'Madjou', 'Le nouveau');
INSERT INTO USERS(login, first_name, name, job) VALUES ('jbricout', 'Jérémy', 'Bricout', 'Glandeur');

INSERT INTO TODO (text, id_user) SELECT 'Préparer la prochaine rétro', id FROM USERS WHERE login = 'gpereira';
INSERT INTO TODO (text, id_user) SELECT 'Préparer des ice breakers', id FROM USERS WHERE login = 'gpereira';
INSERT INTO TODO (text, id_user) SELECT 'Aller au sport', id FROM USERS WHERE login = 'gpereira';
INSERT INTO TODO (text, id_user) SELECT 'Aller chercher les enfants', id FROM USERS WHERE login = 'gpereira';
INSERT INTO TODO (text, id_user) SELECT 'Mettre en place Rancher', id FROM USERS WHERE login = 'gvaneecloo';
INSERT INTO TODO (text, id_user) SELECT 'Préparer la transition Spring', id FROM USERS WHERE login = 'gvaneecloo';
INSERT INTO TODO (text, id_user) SELECT 'Monter en compétence sur ADV', id FROM USERS WHERE login = 'mbah';
INSERT INTO TODO (text, id_user) SELECT 'Formation sécurité', id FROM USERS WHERE login = 'mbah';
INSERT INTO TODO (text, id_user) SELECT 'Baby', id FROM USERS WHERE login = 'jbricout';
INSERT INTO TODO (text, id_user) SELECT 'Sieste', id FROM USERS WHERE login = 'jbricout';
INSERT INTO TODO (text, id_user) SELECT 'Baby', id FROM USERS WHERE login = 'jbricout';
INSERT INTO TODO (text, id_user) SELECT 'Café', id FROM USERS WHERE login = 'jbricout';
INSERT INTO TODO (text, id_user) SELECT 'Baby', id FROM USERS WHERE login = 'jbricout';