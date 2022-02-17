DROP DATABASE chatty;

CREATE DATABASE chatty;

USE chatty;

CREATE TABLE users(
	phone_number VARCHAR(50) PRIMARY KEY,
    display_name VARCHAR(50) NOT NULL,
    gender VARCHAR(1) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    picture VARCHAR(250),
    bio VARCHAR(250),
    user_password VARCHAR(250) NOT NULL,
    birth_date DATE NOT NULL,
    country_id INT NOT NULL,
    user_status_id INT NOT NULL DEFAULT 1
);

CREATE TABLE countries(
	country_id INT PRIMARY KEY AUTO_INCREMENT, 
    country_name VARCHAR(100)
    );
    
CREATE TABLE user_status(
	user_status_id INT PRIMARY KEY AUTO_INCREMENT, 
    user_status_name VARCHAR(20)
    );
    
CREATE TABLE group_chats (
	group_chat_id INT PRIMARY KEY AUTO_INCREMENT,
    group_chat_name VARCHAR(50) NOT NULL
    );
    
-- MANY TO MANY 
CREATE TABLE contacts(
	contact_phone_number VARCHAR(50) NOT NULL,
    contactee_phone_number VARCHAR(50) NOT NULL);

CREATE TABLE invitations(
	contact_phone_number VARCHAR(50) NOT NULL,
    contactee_phone_number VARCHAR(50) NOT NULL);
    
CREATE TABLE group_chats_users(
	group_chat_id INT NOT NULL,
	user_phone_number VARCHAR(50) NOT NULL
    );

-- CONSTRAINTS

ALTER TABLE users 
    ADD CONSTRAINT FOREIGN KEY(country_id) REFERENCES countries(country_id),
    ADD CONSTRAINT FOREIGN KEY(user_status_id) REFERENCES user_status(user_status_id) ;
    
    
ALTER TABLE contacts
    ADD CONSTRAINT FOREIGN KEY(contact_phone_number) REFERENCES users(phone_number),
    ADD CONSTRAINT FOREIGN KEY(contactee_phone_number) REFERENCES users(phone_number),
    ADD CONSTRAINT PRIMARY KEY(contact_phone_number, contactee_phone_number),
    ADD CONSTRAINT CHECK (contact_phone_number != contactee_phone_number);

ALTER TABLE invitations
    ADD CONSTRAINT FOREIGN KEY(contact_phone_number) REFERENCES users(phone_number),
    ADD CONSTRAINT FOREIGN KEY(contactee_phone_number) REFERENCES users(phone_number),
    ADD CONSTRAINT PRIMARY KEY(contact_phone_number, contactee_phone_number);
    
ALTER TABLE group_chats_users
    ADD CONSTRAINT FOREIGN KEY(group_chat_id) REFERENCES group_chats(group_chat_id),
    ADD CONSTRAINT FOREIGN KEY(user_phone_number) REFERENCES users(phone_number),
    ADD CONSTRAINT PRIMARY KEY(group_chat_id, user_phone_number);
    
-- ADD DATA 
INSERT INTO `chatty`.`countries` (`country_id`, `country_name`) VALUES ('1', 'Egypt');
INSERT INTO `chatty`.`countries` (`country_id`, `country_name`) VALUES ('2', 'Canada');

INSERT INTO `chatty`.`user_status` (`user_status_id`, `user_status_name`) VALUES ('1', 'Available');
INSERT INTO `chatty`.`user_status` (`user_status_id`, `user_status_name`) VALUES ('2', 'Away');
INSERT INTO `chatty`.`user_status` (`user_status_id`, `user_status_name`) VALUES ('3', 'Busy');

INSERT INTO `chatty`.`users` (`phone_number`, `display_name`, `gender`, `email`, `bio`, `user_password`, `birth_date`, `country_id`, `user_status_id`) VALUES ('11111111111', 'Mahmoud123', 'M', 'mahmoudfawzykhalil98@gmail.com', 'Amir al a7zan', '12345678', '1998-01-21', '1', '1');
INSERT INTO `chatty`.`users` (`phone_number`, `display_name`, `gender`, `email`, `bio`, `user_password`, `birth_date`, `country_id`, `user_status_id`) VALUES ('22222222222', 'Osama123', 'M', 'osama@gmail.com', 'soltan el a7zan', '12345678', '1999-05-06', '2', '2');
INSERT INTO `chatty`.`users` (`phone_number`, `display_name`, `gender`, `email`, `bio`, `user_password`, `birth_date`, `country_id`, `user_status_id`) VALUES ('33333333333', 'Salma123', 'F', 'salma.fayez@gmail.com', 'eh', '12345678', '2001-07-27', '1', '3');
INSERT INTO `chatty`.`users` (`phone_number`, `display_name`, `gender`, `email`, `bio`, `user_password`, `birth_date`, `country_id`, `user_status_id`) VALUES ('44444444444', 'Christine123', 'F', 'christine123@gmail.com', 'ya 3m ektb ay 7aga', '12345678', '1950-02-11', '2', '1');
INSERT INTO `chatty`.`users` (`phone_number`, `display_name`, `gender`, `email`, `bio`, `user_password`, `birth_date`, `country_id`, `user_status_id`) VALUES ('55555555555', 'Hafsa123', 'F', 'hafsa@gmail.com', 'ay 7aga', '12345678', '1977-11-22', '1', '1');

INSERT INTO `chatty`.`contacts` (`contact_phone_number`, `contactee_phone_number`) VALUES ('11111111111', '22222222222');
INSERT INTO `chatty`.`contacts` (`contact_phone_number`, `contactee_phone_number`) VALUES ('33333333333', '44444444444');
INSERT INTO `chatty`.`contacts` (`contact_phone_number`, `contactee_phone_number`) VALUES ('55555555555', '44444444444');

INSERT INTO `chatty`.`group_chats` (`group_chat_name`) VALUES ('shabeha');
INSERT INTO `chatty`.`group_chats` (`group_chat_name`) VALUES ('iti5000');

INSERT INTO `chatty`.`group_chats_users` (`group_chat_id`, `user_phone_number`) VALUES ('1', '11111111111');
INSERT INTO `chatty`.`group_chats_users` (`group_chat_id`, `user_phone_number`) VALUES ('1', '22222222222');
INSERT INTO `chatty`.`group_chats_users` (`group_chat_id`, `user_phone_number`) VALUES ('1', '55555555555');
INSERT INTO `chatty`.`group_chats_users` (`group_chat_id`, `user_phone_number`) VALUES ('2', '33333333333');
INSERT INTO `chatty`.`group_chats_users` (`group_chat_id`, `user_phone_number`) VALUES ('2', '44444444444');

INSERT INTO `chatty`.`invitations` (`contact_phone_number`, `contactee_phone_number`) VALUES ('11111111111', '33333333333');






