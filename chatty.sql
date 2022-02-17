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
    	group_chat_name VARCHAR(50) NOT NULL,
	picture VARCHAR(250)
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
-- COUNTRIES
Insert INTO `chatty`.`countries` (country_name) VALUES ('China');
Insert INTO `chatty`.`countries` (country_name) VALUES ('India');
Insert INTO `chatty`.`countries` (country_name) VALUES ('United States');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Indonesia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Brazil');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Pakistan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Nigeria');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Bangladesh');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Russia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Japan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Mexico');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Philippines');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Vietnam');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Ethiopia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Egypt');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Germany');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Iran');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Turkey');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Democratic Republic of the Congo');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Thailand');
Insert INTO `chatty`.`countries` (country_name) VALUES ('France');
Insert INTO `chatty`.`countries` (country_name) VALUES ('United Kingdom');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Italy');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Burma');
Insert INTO `chatty`.`countries` (country_name) VALUES ('South Africa');
Insert INTO `chatty`.`countries` (country_name) VALUES ('South Korea');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Colombia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Spain');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Ukraine');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Tanzania');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Kenya');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Argentina');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Algeria');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Poland');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Sudan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Uganda');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Canada');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Iraq');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Morocco');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Peru');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Uzbekistan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Saudi Arabia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Malaysia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Venezuela');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Nepal');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Afghanistan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Yemen');
Insert INTO `chatty`.`countries` (country_name) VALUES ('North Korea');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Ghana');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Mozambique');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Taiwan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Australia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Ivory Coast');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Syria');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Madagascar');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Angola');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Cameroon');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Sri Lanka');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Romania');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Burkina Faso');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Niger');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Kazakhstan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Netherlands');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Chile');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Malawi');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Ecuador');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Guatemala');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Mali');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Cambodia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Senegal');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Zambia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Zimbabwe');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Chad');
Insert INTO `chatty`.`countries` (country_name) VALUES ('South Sudan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Belgium');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Cuba');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Tunisia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Guinea');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Greece');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Portugal');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Rwanda');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Czech Republic');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Somalia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Haiti');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Benin');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Burundi');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Bolivia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Hungary');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Sweden');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Belarus');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Dominican Republic');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Azerbaijan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Honduras');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Austria');
Insert INTO `chatty`.`countries` (country_name) VALUES ('United Arab Emirates');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Switzerland');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Tajikistan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Bulgaria');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Hong Kong (China)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Serbia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Papua New Guinea');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Paraguay');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Laos');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Jordan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('El Salvador');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Eritrea');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Libya');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Togo');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Sierra Leone');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Nicaragua');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Kyrgyzstan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Denmark');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Finland');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Slovakia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Singapore');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Turkmenistan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Norway');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Lebanon');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Costa Rica');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Central African Republic');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Ireland');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Georgia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('New Zealand');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Republic of the Congo');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Palestine');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Liberia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Croatia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Oman');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Bosnia and Herzegovina');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Puerto Rico');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Kuwait');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Moldov');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Mauritania');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Panama');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Uruguay');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Armenia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Lithuania');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Albania');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Mongolia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Jamaica');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Namibia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Lesotho');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Qatar');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Macedonia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Slovenia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Botswana');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Latvia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Gambia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Kosovo');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Guinea-Bissau');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Gabon');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Equatorial Guinea');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Trinidad and Tobago');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Estonia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Mauritius');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Swaziland');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Bahrain');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Timor-Leste');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Djibouti');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Cyprus');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Fiji');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Reunion (France)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Guyana');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Comoros');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Bhutan');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Montenegro');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Macau (China)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Solomon Islands');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Western Sahara');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Luxembourg');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Suriname');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Cape Verde');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Malta');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Guadeloupe (France)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Martinique (France)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Brunei');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Bahamas');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Iceland');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Maldives');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Belize');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Barbados');
Insert INTO `chatty`.`countries` (country_name) VALUES ('French Polynesia (France)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Vanuatu');
Insert INTO `chatty`.`countries` (country_name) VALUES ('New Caledonia (France)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('French Guiana (France)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Mayotte (France)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Samoa');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Sao Tom and Principe');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Saint Lucia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Guam (USA)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Curacao (Netherlands)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Saint Vincent and the Grenadines');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Kiribati');
Insert INTO `chatty`.`countries` (country_name) VALUES ('United States Virgin Islands (USA)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Grenada');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Tonga');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Aruba (Netherlands)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Federated States of Micronesia');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Jersey (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Seychelles');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Antigua and Barbuda');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Isle of Man (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Andorra');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Dominica');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Bermuda (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Guernsey (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Greenland (Denmark)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Marshall Islands');
Insert INTO `chatty`.`countries` (country_name) VALUES ('American Samoa (USA)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Cayman Islands (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Saint Kitts and Nevis');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Northern Mariana Islands (USA)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Faroe Islands (Denmark)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Sint Maarten (Netherlands)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Saint Martin (France)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Liechtenstein');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Monaco');
Insert INTO `chatty`.`countries` (country_name) VALUES ('San Marino');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Turks and Caicos Islands (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Gibraltar (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('British Virgin Islands (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Aland Islands (Finland)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Caribbean Netherlands (Netherlands)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Palau');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Cook Islands (NZ)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Anguilla (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Wallis and Futuna (France)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Tuvalu');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Nauru');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Saint Barthelemy (France)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Saint Pierre and Miquelon (France)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Montserrat (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Saint Helena, Ascension and Tristan da Cunha (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Svalbard and Jan Mayen (Norway)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Falkland Islands (UK)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Norfolk Island (Australia)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Christmas Island (Australia)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Niue (NZ)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Tokelau (NZ)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Vatican City');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Cocos (Keeling) Islands (Australia)');
Insert INTO `chatty`.`countries` (country_name) VALUES ('Pitcairn Islands (UK)');
-- COUNTRIES

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






