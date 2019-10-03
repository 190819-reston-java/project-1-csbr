-- Shows all tables
--SELECT table_name FROM information_schema.tables 
--WHERE table_schema = 'project-1-csbr';

-- TODO: set it to auto generate ID numbers?...

--CREATE DATABASE ReimbursementProgram;

-- Drop tables generally should be in reverse creation order (assuming it was made in the right order)

--DROP TABLE reimb_reciepts_table;
--DROP TABLE reimb_table;
--DROP FUNCTION is_manager(usr_id_loc varchar);
--DROP TABLE employees_table;





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
-- submitted can be pending, all pending has been submitted
-- longest one is 13 characters
-- ideas for others, or these renamed?
-- separate into separate booleans or combine like this?
-- TODO: Put these down as a constraint for this column

 -- for round when third number is '0' for round, and not '0' for turncate
 
-- CHECK(is_manager(mgr_user_id_fk) = false) -- removed this constraint, a manager can do this as an employeee
-- However a manager can not self aprove their own stuff 

-- TODO: set reimb_id as SERIAL or something like this, I remember Carlos saying no to this idea...
-- TODO: remove balance from reimb_table and add amount to reimb_reciepts_table -- adjust java code, queries, etc.. as necessary
CREATE TABLE reimb_table (

        reimb_id VARCHAR(8) PRIMARY KEY,
        reimb_status VARCHAR(13) NOT NULL CHECK(reimb_status IN ('NOTSUBMITTED','PENDING', 'APPROVED', 'DENIED')),
        reimb_balance NUMERIC(8,2) CHECK(reimb_balance >= 0.0),
        mgr_user_id_fk VARCHAR(31) CHECK(mgr_user_id_fk IS NULL OR is_manager(mgr_user_id_fk) = TRUE ),
        emp_user_id_fk VARCHAR(31) NOT NULL,
        
        submited_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
        proval_time   TIMESTAMP WITH TIME ZONE DEFAULT NULL,

        FOREIGN KEY(emp_user_id_fk) REFERENCES employees_table(user_id),
        FOREIGN KEY(mgr_user_id_fk) REFERENCES employees_table(user_id),
        CHECK (mgr_user_id_fk <> emp_user_id_fk)

);

-- TODO: add something like the reimb_balance NUMERIC(8,2) CHECK(reimb_balance >= 0.0), from above, and REMOVE from Above
CREATE TABLE reimb_reciepts_table (

        reimb_id_fk VARCHAR(8) NOT NULL,
        reciept_img_path VARCHAR(122) PRIMARY KEY,
        
        added_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

        FOREIGN KEY (reimb_id_fk) REFERENCES reimb_table(reimb_id)

);

SELECT * FROM reimb_reciepts_table;
SELECT * FROM reimb_table;
SELECT * FROM employees_table;

--SELECT * FROM reimb_reciepts_table JOIN reimb_table ON reimb_id_fk=reimb_id;
