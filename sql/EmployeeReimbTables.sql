CREATE DATABASE ReimbursementProgram;

CREATE table employees_table (

	usr_id VARCHAR(20) PRIMARY KEY, -- Worker's username
	emp_or_mgr BOOLEAN, -- Employee: False, Manager: True
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	password varchar(50) NOT NULL,
	email VARCHAR(40) NOT NULL,
	street_address VARCHAR(50) NULL,
	city VARCHAR(20) NULL,
	country VARCHAR(50) NULL
	
);

CREATE OR REPLACE FUNCTION emp_or_mgr(usr_id_loc VARCHAR) RETURNS BOOLEAN 
AS
$$
	SELECT emp_or_mgr FROM employees_table WHERE usr_id = usr_id_loc;
$$ LANGUAGE SQL;

CREATE TABLE reimb_table (

	reimb_id VARCHAR(8) PRIMARY KEY,
	reimb_status VARCHAR(10) NOT NULL,
	reimb_balance NUMERIC CHECK(reimb_balance >= 0.0),
	mgr_usr_id_fk VARCHAR(15) NOT NULL CHECK(emp_or_mgr(mgr_usr_id_fk) = true),
	emp_usr_id_fk VARCHAR(15) NOT NULL CHECK(emp_or_mgr(emp_usr_id_fk) = false),
	
	FOREIGN KEY (emp_usr_id_fk) REFERENCES 
	employees_table(usr_id),
	FOREIGN KEY (mgr_usr_id_fk) REFERENCES 
	employees_table(usr_id)
	
);

CREATE TABLE reimb_reciepts (

	reimb_id_fk VARCHAR(8) NOT NULL,
	reciept_img_path VARCHAR(100) PRIMARY KEY,
	FOREIGN KEY (reimb_id_fk) REFERENCES reimb_table(reimb_id)

);

