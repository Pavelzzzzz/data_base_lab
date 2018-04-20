# 1. Создать пользователя базы данных по своей фамилии с номером варианта (Например, Ivanov5).
CREATE USER 'Krupenin15'@'localhost' IDENTIFIED BY 'Krupenin15';
# 2. Проверить, имеет ли ваше текущее имя входа доступ к БД.
# mysql -u Krupenin15 -p
# 3. Отозвать разрешение для Ivanov5 подключаться к БД.
#########
# 4. Удалить пользователя Ivanov5.
DROP USER 'Krupenin15'@'localhost';
# 5. Вывести отчет обо всех пользователях базы данных, утративших связь с именем входа.
SELECT User,Host FROM mysql.user;
# 6. Создать пользователя базы данных Ivanov5 в базе данных без сопоставления с ним имени входа.
############
# 7. Предоставьте одному имени входа все права на созданную базу данных, а второму права на обновление таблиц и создание процедур.
CREATE USER 'User1'@'localhost' IDENTIFIED BY 'User1';
GRANT ALL ON news_blog.* TO 'User1'@'localhost';
# ALL (admin) — пользователю, получившему данную привилегию,
# автоматически назначаются все права в рамках уровня привилегий
# (возможных привилегий в принципе, согласно контексту выдачи привилегий).
# Не назначается только привилегия GRANT OPTION в данном случае.
CREATE USER 'User2'@'localhost' IDENTIFIED BY 'User2';
GRANT UPDATE ON news_blog.* TO 'User2'@'localhost';
GRANT CREATE ROUTINE ON news_blog.* TO 'User2'@'localhost';
# CREATE ROUTINE — позволяет создать процедуру, которая является набором заготовленным набором SQL-команд.
# 8.  Предоставьте одному имени входа полный набор привилегий.
GRANT ALL ON news_blog.* TO 'Krupenin15'@'localhost';
# 9.  Проверьте, какими правами вы обладаете в момент текущего подключения к базе данных.
SHOW GRANTS FOR 'User2'@'localhost';
# 10. Создать новую роль в базе данных. Дать ей название. Создать и добавить к этой роли пользователя с вашим именем и номером варианта, например, Alex5.
CREATE ROLE 'Role1';
GRANT 'Role1' TO 'Krupenin15'@'localhost';
# 11. Проверить, принадлежит ли текущий пользователь к роли db_accessadmin.
SELECT CURRENT_ROLE();
# 12. Удалить пользователя Alex5 из созданной роли.
REVOKE 'Role1' FROM 'Krupenin15'@'localhost';
# 13. Создать схему, дать ей название. Назначить владельцем Alex5.
CREATE SCHEMA shema1;
GRANT ALL ON shema1.* TO 'Krupenin15'@'localhost';
GRANT GRANT OPTION ON shema1.* TO 'Krupenin15'@'localhost';
# 14. Создать в новой схеме таблицу и предоставить пользователю разрешение на обновление и выбор данных из таблицы.
CREATE TABLE `shema1`.`new_table` (
  `idnew_table` INT NOT NULL,
  `new_tablecol` VARCHAR(45) NULL,
  PRIMARY KEY (`idnew_table`));
GRANT SELECT, UPDATE ON `shema1`.`new_table` TO 'User2'@'localhost';
# 15. Объявить созданную схему схемой по умолчанию для Alex5. Получить информацию о схеме, а затем удалить ее.
SELECT table_name, table_type, engine
FROM information_schema.tables
WHERE table_schema = 'shema1'
ORDER BY table_name;
DROP SCHEMA shema1;
# 16. Разрешить пользователю Alex5 вставлять, удалять, обновлять и выбирать данные из произвольной таблицы вашей БД. Затем отозвать разрешение  выбирать данные. Затем запретить ему удаление.
GRANT INSERT, UPDATE, DROP ON data_base_lab.User TO 'User2'@'localhost';
REVOKE SELECT, DROP ON data_base_lab.User FROM 'User2'@'localhost';
# 17. Разрешить выполнять две на ваш выбор операции над столбцом произвольной таблицы в БД. Затем отозвать выданные привилегии.
GRANT TRIGGER, CREATE VIEW ON data_base_lab.User TO 'User2'@'localhost';
REVOKE TRIGGER, CREATE VIEW  ON data_base_lab.User FROM 'User2'@'localhost';

DROP USER 'Krupenin15'@'localhost';