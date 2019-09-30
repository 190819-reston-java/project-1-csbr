-- Shows all tables
--SELECT table_name FROM information_schema.tables 
--WHERE table_schema = 'project-1-csbr';

-- TODO: set it to auto generate ID numbers?...

-- Drop tables generally should be in reverse creation order (assuming it was made it the right order)

 DROP TABLE reimb_reciepts_table;
 DROP TABLE reimb_table;
 DROP FUNCTION is_manager(usr_id_loc varchar);
 DROP TABLE employees_table;


--CREATE DATABASE ReimbursementProgram;


CREATE table employees_table (

        user_id VARCHAR(31) PRIMARY KEY, -- Worker's username
        manager BOOLEAN, -- Employee: False, Manager: True
        first_name VARCHAR(31) NOT NULL,
        last_name VARCHAR(31) NOT NULL,
        passwd varchar(50) NOT NULL, -- using 'password' for a column name causes my IDE to highilght it like it's a keyword, to be safe using another name
        email VARCHAR(31) NOT NULL,
        street_address VARCHAR(50) NULL,
        city VARCHAR(58) NULL, -- https://en.wikipedia.org/wiki/List_of_long_place_names
        country VARCHAR(56) NULL -- https://www.worldatlas.com/articles/what-is-the-longest-country-name-in-the-world.html

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
--		unapproved(10), reimbursed(10), denied(6)
--		withdrawn(9) (if user decides to cancel submission)
-- longest one is 13 characters
-- ideas for others, or these renamed?
-- separate into separate booleans or combine like this?

 -- for round when third number is '0' for round, and not '0' for turncate
 
CREATE TABLE reimb_table (

        reimb_id VARCHAR(8) PRIMARY KEY,
        reimb_status VARCHAR(13) NOT NULL,
        reimb_balance NUMERIC(8,2) CHECK(reimb_balance >= 0.0),
        mgr_user_id_fk VARCHAR(31) NOT NULL CHECK(is_manager(mgr_user_id_fk) = true),
        emp_user_id_fk VARCHAR(31) NOT NULL CHECK(is_manager(emp_user_id_fk) = false),
        
        submited_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
        proval_time TIMESTAMP WITH TIME ZONE DEFAULT NULL,

        FOREIGN KEY (emp_user_id_fk) REFERENCES 
        employees_table(user_id),
        FOREIGN KEY (mgr_user_id_fk) REFERENCES 
        employees_table(user_id)

);


CREATE TABLE reimb_reciepts_table (

        reimb_id_fk VARCHAR(8) NOT NULL,
        reciept_img_path VARCHAR(122) PRIMARY KEY,
        
        added_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

        FOREIGN KEY (reimb_id_fk) REFERENCES reimb_table(reimb_id)

);

