CREATE DATABASE ReimbursementProgram;

CREATE TABLE work_table (

	work_usr_id VARCHAR(20) PRIMARY KEY, -- Worker's username
	emp_or_mgr BOOLEAN, -- Employee: True, Manager: False
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	acc_password varchar(50) NOT NULL,
	acc_email VARCHAR(40) NOT NULL,
	address VARCHAR(50) NULL,
	city VARCHAR(20) NULL,
	country VARCHAR(50) NULL
	
);

CREATE TABLE reimb_table (

	reimb_id VARCHAR(8) PRIMARY KEY,
	reimb_status VARCHAR(10) NOT NULL,
	reimb_balance NUMERIC CHECK(reimb_balance >= 0.0),
	work_mgr_id_fk VARCHAR(15) NOT NULL,
	work_emp_id_fk VARCHAR(15) NOT NULL,
	mgr_fk BOOLEAN CHECK(mgr_fk = false),
	emp_fk BOOLEAN CHECK(emp_fk = true),
	
	FOREIGN KEY (work_emp_id_fk, emp_fk) REFERENCES 
	work_table(work_usr_id, emp_or_mgr),
	FOREIGN KEY (work_mgr_id_fk, mgr_fk) REFERENCES 
	work_table(work_usr_id, emp_or_mgr)
	
);

CREATE TABLE reimb_reciepts (

	reimb_id_fk VARCHAR(8) NOT NULL,
	reciept_img_path VARCHAR(100) PRIMARY KEY,
	FOREIGN KEY (reimb_id_fk) REFERENCES reimb_table(reimb_id)

);

