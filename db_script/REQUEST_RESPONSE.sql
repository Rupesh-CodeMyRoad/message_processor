SET FOREIGN_KEY_CHECKS = 0;
CREATE DATABASE IF NOT EXISTS xgile_subs_manager;
USE xgile_subs_manager;

DROP TABLE IF EXISTS request_response;
CREATE TABLE request_response
(
    request_response_id bigint       NOT NULL AUTO_INCREMENT,
    sub_reference_id    varchar(255) NOT NULL,
    request            MEDIUMTEXT  NOT NULL,
    response            MEDIUMTEXT,
    status              boolean      NULL,
    PRIMARY KEY (request_response_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

SET FOREIGN_KEY_CHECKS = 1;
commit;