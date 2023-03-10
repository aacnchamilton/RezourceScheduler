DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS rezource;
DROP TABLE IF EXISTS person;

CREATE TABLE person (
  person_id int unsigned NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(100) NOT NULL, 
  middle_name VARCHAR(100),
  last_name VARCHAR(45) NOT NULL,
  email VARCHAR (320) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  country_code VARCHAR(5),
  status enum('Active','Terminated','Inactive','NotVerified') NOT NULL,
  PRIMARY KEY (person_id),
  UNIQUE KEY (email)
);

CREATE TABLE rezource (
	rezource_id int unsigned NOT NULL AUTO_INCREMENT,
	name VARCHAR(200) NOT NULL,
	description VARCHAR(1000) NOT NULL,
	rezource_type enum('Service', 'Place', 'Thing') NOT NULL,
	rezourcer_id int unsigned NOT NULL,
	schedule_type enum('Hourly', 'Daily', 'Monthly') NOT NULL,
	regular_rate decimal(9,2) NOT NULL,
	travel_rate decimal(9,2),
	deposit decimal(9,2),
	cleaning_fee decimal(9,2),
	emerg_rate decimal(9,2),
	weekend_rate decimal(9,2),
	start_time TIME NOT NULL,
	end_time TIME NOT NULL,
	bedroooms int unsigned,
	bathrooms int unsigned,
	size_in_ft int unsigned,
	people int unsigned,
	beds int unsigned,
	style int unsigned,
	manufacturer VARCHAR(200),
	model varchar(200),
	year int unsigned,
	key_words VARCHAR(200),
	status enum('Active','Terminated','Inactive','NotVerified') NOT NULL,
	PRIMARY KEY (rezource_id),
	UNIQUE KEY (name, rezourcer_id),
	CONSTRAINT FK_PersonRezource FOREIGN KEY (rezourcer_id) REFERENCES person(person_id) ON DELETE CASCADE
);

CREATE TABLE address (
	address_id int unsigned NOT NULL AUTO_INCREMENT,
	addr1 VARCHAR(200) NOT NULL,
	addr2 VARCHAR(200) NOT NULL,
	city VARCHAR(200) NOT NULL,
	state VARCHAR(2),
	province VARCHAR(200),
	postal_code VARCHAR(10) NOT null,
	country VARCHAR(60) NOT NULL,
	billing char(1),
	delivery char(1),
	rezource_id int unsigned,
	person_id int unsigned NOT NULL,
	PRIMARY KEY (address_id),
	UNIQUE KEY (addr1, addr2, postal_code, country, person_id),
	CONSTRAINT FK_RezourceAddress FOREIGN KEY (rezource_id) REFERENCES rezource(rezource_id),
	CONSTRAINT FK_PersonAddress FOREIGN KEY (person_id) REFERENCES person (person_id) ON DELETE CASCADE
);

CREATE TABLE reservation (
	reservation_id int unsigned NOT NULL AUTO_INCREMENT,
	date_from DATE NOT NULL,
	date_to DATE NOT NULL,
	start_time TIME NOT NULL,
	end_time TIME NOT NULL,
	travel_time INT unsigned,
	rezource_id int unsigned NOT NULL,
	scheduler_id int unsigned NOT NULL,
	address_id int unsigned NOT NULL,
	num_of_people int unsigned,
	special_requests varchar(1000),
	status enum('Requested','Accepted','Cancelled','Completed','NotSubmitted') NOT NULL,
	PRIMARY KEY (reservation_id),
	CONSTRAINT FK_RezourceReservation FOREIGN KEY (rezource_id) REFERENCES rezource(rezource_id),
	CONSTRAINT FK_SchedulerReservation FOREIGN KEY (scheduler_id) REFERENCES person(person_id),
	CONSTRAINT FK_AddressReservation FOREIGN KEY (address_id) REFERENCES address(address_id)
);