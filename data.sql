USE youthchina;

CREATE TABLE user
(
    id int PRIMARY KEY AUTO_INCREMENT,
    username varchar(128),
    password varchar(128)
);