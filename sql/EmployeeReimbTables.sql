-- Shows all tables
--SELECT table_name FROM information_schema.tables 
--WHERE table_schema = 'project-1-csbr';

-- TODO: set it to auto generate ID numbers...

-- Drop tables generally should be in reverse creation order (assuming it was made it the right order)

DROP TABLE reimb_reciepts;
DROP TABLE reimb_table;
DROP FUNCTION is_manager(usr_id_loc varchar);
DROP TABLE employees_table;


--CREATE DATABASE ReimbursementProgram;


CREATE table employees_table (

        user_id VARCHAR(20) PRIMARY KEY, -- Worker's username
        manager BOOLEAN, -- Employee: False, Manager: True
        first_name VARCHAR(20) NOT NULL,
        last_name VARCHAR(20) NOT NULL,
        passwd varchar(50) NOT NULL, -- using 'password' for a column name causes my IDE to highilght it like it's a keyword, to be safe using another name
        email VARCHAR(40) NOT NULL,
        street_address VARCHAR(50) NULL,
        city VARCHAR(20) NULL,
        country VARCHAR(50) NULL

);


CREATE OR REPLACE FUNCTION is_manager(user_id_loc VARCHAR) RETURNS BOOLEAN 
AS
$$
        SELECT manager FROM employees_table WHERE user_id = user_id_loc;
$$ LANGUAGE SQL;

-- possible statuses: not-submitted, submitted-pending, 
-- 		submitted-approved-not-reimbursed, 
--		submitted-approved-reimbursed, submitted-not-approved
-- one of: not_submitted(13), 
--		submitted(9), pending(7), approved(8), 
--		unapproved(10), reimbursed(10), 
--		withdrawn(9) (if user decides to cancel submission)
-- longest one is 13 characters
-- ideas for others?
-- separate into separate booleans or combine like this?


CREATE TABLE reimb_table (

        reimb_id VARCHAR(8) PRIMARY KEY,
        reimb_status VARCHAR(13) NOT NULL, 
        reimb_balance NUMERIC CHECK(reimb_balance >= 0.0),
        mgr_user_id_fk VARCHAR(20) NOT NULL CHECK(is_manager(mgr_user_id_fk) = true),
        emp_user_id_fk VARCHAR(20) NOT NULL CHECK(is_manager(emp_user_id_fk) = false),

        FOREIGN KEY (emp_user_id_fk) REFERENCES 
        employees_table(user_id),
        FOREIGN KEY (mgr_user_id_fk) REFERENCES 
        employees_table(user_id)

);


CREATE TABLE reimb_reciepts_table (

        reimb_id_fk VARCHAR(8) NOT NULL,
        reciept_img_path VARCHAR(122) PRIMARY KEY,
        FOREIGN KEY (reimb_id_fk) REFERENCES reimb_table(reimb_id)

);

