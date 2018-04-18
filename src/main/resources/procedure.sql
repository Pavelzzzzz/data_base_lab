DELIMITER //

CREATE PROCEDURE `addNewUser` (IN email VARCHAR(100),
  IN firstName VARCHAR(100),
  IN lastName VARCHAR(100))
BEGIN

  INSERT INTO `data_base_lab`.`User` (`EMAIL`, `FIRST_NAME`, `LAST_NAME`) VALUES (email, firstName, lastName);
  SELECT * FROM `data_base_lab`.`User`
    WHERE `data_base_lab`.`User`.`EMAIL`= email;
END//

CALL `data_base_lab`.`addNewUser`('ty', '2', '3' );