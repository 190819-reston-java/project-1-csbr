CREATE DATABASE ReimbursementProgram;

CREATE TABLE work_table (

	work_id VARCHAR(20) UNIQUE, -- Worker's username
	employee_or_manager BOOLEAN NOT NULL, -- Employee: True, Manager: False
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	acc_password varchar(50) NOT NULL,
	acc_email VARCHAR(40) NOT NULL,
	PRIMARY KEY (work_id, employee_or_manager)
);

CREATE TABLE contact_addresses (

	work_id_fk VARCHAR(15) UNIQUE,
	address VARCHAR(50) NOT NULL,
	city VARCHAR(20) NOT NULL,
	country VARCHAR(20) NOT NULL,
	state VARCHAR(15) NULL,
	zipcode VARCHAR(5) NULL,

	FOREIGN KEY (work_id_fk) REFERENCES 
	work_table(work_id)

);

CREATE TABLE reimbursements (

	reimb_id varchar(8) PRIMARY KEY,
	reimb_status BOOLEAN NOT NULL,
	pending BOOLEAN,
	reimb_balance NUMERIC CHECK(reimb_balance >= 0.0),
	work_mgr_id_fk VARCHAR(15) NULL,
	mgr_fk BOOLEAN CHECK(mgr_fk = false),

	FOREIGN KEY (work_emp_id_fk, emp_fk) REFERENCES 
	work_table(work_id, employee_or_manager),
	FOREIGN KEY (work_mgr_id_fk, mgr_fk) REFERENCES 
	work_table(work_id, employee_or_manager)
	
);

CREATE TABLE reimb_employee (

	reimb_id_fk varchar(8) NOT NULL,
	work_emp_id_fk VARCHAR(15) NOT NULL,
	emp_fk BOOLEAN CHECK(emp_fk = true),
	reciept_img_path VARCHAR(100) NOT NULL,
	FOREIGN KEY (work_emp_id_fk, emp_fk) REFERENCES 
	work_table(work_id, employee_or_manager),
	FOREIGN KEY (reimb_id_fk) REFERENCES reimbursements(reimb_id)

);
