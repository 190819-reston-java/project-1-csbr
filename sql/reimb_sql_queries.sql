


SELECT * FROM employees_table;
SELECT * FROM reimb_table;
SELECT * FROM reimb_reciepts_table;

-- Employee queries

-- View "034jg0j" reimb_table info, a particular user, replace "034jg0j" with varible
SELECT * FROM reimb_table WHERE reimb_id = 	'034jg0j';
--'NOTSUBMITTED','PENDING', 'APPROVED', 'DENIED'
SELECT * FROM reimb_table  WHERE reimb_id = '034jg0j' AND reimb_status='NOTSUBMITTED';
SELECT * FROM reimb_table  WHERE reimb_id = '034jg0j' AND reimb_status='PENDING';
SELECT * FROM reimb_table  WHERE reimb_id = '034jg0j' AND reimb_status='APPROVED';
SELECT * FROM reimb_table  WHERE reimb_id = '034jg0j' AND reimb_status='DENIED';
-- Reimbursements that have been replied to
SELECT * FROM reimb_table  WHERE reimb_id = '034jg0j' AND reimb_status='DENIED' OR reimb_status='APPROVED';

-- Manager queries

-- View all reimbursemnt info
SELECT * FROM reimb_table;
-- View all of a type
SELECT * FROM reimb_table  WHERE reimb_status='PENDING';
SELECT * FROM reimb_table  WHERE reimb_status='APPROVED';
SELECT * FROM reimb_table  WHERE reimb_status='DENIED';

-- VIEW ALL employeees
--SELECT * FROM employees_table; -- don't do, shows password column
SELECT user_id, first_name, last_name FROM employees_table;
SELECT user_id, first_name, last_name, email, street_address, city, country FROM employees_table;
