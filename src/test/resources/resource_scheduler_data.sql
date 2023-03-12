/**************  person ***************/
INSERT INTO person (first_name, middle_name, last_name, email, phone, status,addr1, addr2, city, state, province, postal_code, country, billing) 
	VALUES('Aaron','Paul','Hamilton','anahamilton@msn.com','6232038107', 'Active','14561 W SHERIDAN ST', '','GOODYEAR', 'AZ','ARIZONA','85395','UNITED STATES','Y');
INSERT INTO person (first_name, middle_name, last_name, email, phone, status,addr1, addr2, city, state, province, postal_code, country, billing)  
	VALUES('Joel','Mark','Hamilton','joelhamilton@gmail.com','2187601861', 'Active', '27308 420TH ST NW', '','LAPORTE', 'MN','MINNESOTA','56461','UNITED STATES','Y');
INSERT INTO person (first_name, middle_name, last_name, email, phone, status,addr1, addr2, city, state, province, postal_code, country, billing) 
	VALUES('Shawn','','Hamilton','hammer6077@gmail.com','3208284119', 'Active', '146 16TH AVE S', '','ST CLOUD', 'MN','MINNESOTA','56301','UNITED STATES','Y');
INSERT INTO person (first_name, middle_name, last_name, email, phone, status,addr1, addr2, city, state, province, postal_code, country, billing)  
	VALUES('Diane','','Hamilton','mommahammi','7632423300', 'Active', '1013 RIVER AVE S', '','SAUK RAPIDS', 'MN','MINNESOTA','56379','UNITED STATES','Y');
INSERT INTO person (first_name, middle_name, last_name, email, phone, status,addr1, addr2, city, state, province, postal_code, country, billing) 
	VALUES('Matt','Karl','Pezzelle','pezellem@gmail.com','6029892130', 'Active', '11945 W MADISON ST', '','AVONDALE', 'AZ','ARIZONA','85323','UNITED STATES','Y');
INSERT INTO person (first_name, middle_name, last_name, email, phone, status,addr1, addr2, city, state, province, postal_code, country, billing) 
	VALUES('Stephen','','Hemming','coachhemming@gmail.com','gmail.com', 'Active', '8834 W ASTER DR', '','PEORIA', 'AZ','ARIZONA','85381','UNITED STATES','Y');
commit;

/*********************** rezource ******************/
INSERT INTO rezource (name, description, rezource_type, rezourcer_id, schedule_type, regular_rate, start_time, end_time, status, state, postal_code) 
	VALUES('Hamilton Horseshoing and Hoofcare','Ferrier service for Northern Minnesota','Service', 2, 'Hourly','50','08:00:00','17:00:00', 'Active', 'MN', '56461');
INSERT INTO rezource (name, description, rezource_type, rezourcer_id, schedule_type, regular_rate, start_time, end_time, status,addr1, addr2, city, state, province, postal_code, country, billing) 
	VALUES('Crown King Cottage','3 bedroom 2 bath vacation rental in Crown King, AZ','Place', 1, 'Daily','200','15:00:00','12:00:00', 'Active',
    '6708 ASH DR', '','CROWN KING', 'AZ','ARIZONA','86343','UNITED STATES','N');
INSERT INTO rezource (name, description, rezource_type, rezourcer_id, schedule_type, regular_rate, start_time, end_time, status,addr1, addr2, city, state, province, postal_code, country, billing) 
	VALUES('19 foot Tracker John Boat','19 Foot tracker John Boat for rent in Northern Minnesota','Thing', 3, 'Daily','50','08:00:00','17:00:00', 'Active',
	'146 16TH AVE S', '','ST CLOUD', 'MN','MINNESOTA','56301','UNITED STATES','Y');
commit;

/************* address *****************/
INSERT INTO address (addr1, addr2, city, state, province, postal_code, country, billing, delivery, person_id) 
	VALUES ('14561 W SHERIDAN ST', '','GOODYEAR', 'AZ','ARIZONA','85395','UNITED STATES','Y','Y',1);
INSERT INTO address (addr1, addr2, city, state, province, postal_code, country, billing, delivery, person_id) 
	VALUES ('27308 420TH ST NW', '','LAPORTE', 'MN','MINNESOTA','56461','UNITED STATES','Y','Y',2);
INSERT INTO address (addr1, addr2, city, state, province, postal_code, country, billing, delivery, person_id) 
	VALUES ('146 16TH AVE S', '','ST CLOUD', 'MN','MINNESOTA','56301','UNITED STATES','Y','Y',3);
INSERT INTO address (addr1, addr2, city, state, province, postal_code, country, billing, delivery, rezource_id, person_id) 
	VALUES ('6708 ASH DR', '','CROWN KING', 'AZ','ARIZONA','86343','UNITED STATES','N','N', 2,1);
INSERT INTO address (addr1, addr2, city, state, province, postal_code, country, billing, delivery, person_id) 
	VALUES ('11945 W MADISON ST', '','AVONDALE', 'AZ','ARIZONA','85323','UNITED STATES','Y','N',5);    
INSERT INTO address (addr1, addr2, city, state, province, postal_code, country, billing, delivery, person_id) 
	VALUES ('8834 W ASTER DR', '','PEORIA', 'AZ','ARIZONA','85381','UNITED STATES','Y','Y',6);     
commit;

/**************** reservation *******************/
INSERT INTO reservation (date_from, date_to, start_time, end_time, rezource_id, scheduler_id, status) 
	VALUES ('2023-05-21','2023-05-23','15:00:00','12:00:00',2,4, 'Requested');
INSERT INTO reservation (date_from, date_to, start_time, end_time, rezource_id, scheduler_id, status) 
	VALUES ('2023-03-11','2023-03-12','08:00:00','17:00:00',3,3, 'Accepted');  
INSERT INTO reservation (date_from, date_to, start_time, end_time, rezource_id, scheduler_id, status) 
	VALUES ('2023-03-10','2023-03-10','08:00:00','09:00:00',1,6, 'Accepted'); 
commit;	