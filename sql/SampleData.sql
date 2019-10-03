
select * from employees_table;
select * from reimb_table;
select * from reimb_reciepts_table;

INSERT INTO employees_table(user_id,manager,first_name,last_name,passwd,email)
VALUES ('asdf134',false,'Engel','Sanders','1234','eee@555.com');

INSERT INTO employees_table(user_id,manager,first_name,last_name,passwd,email)
VALUES ('dfsg45',false,'Will','Fengel','zxcv','rret@ett.com');

INSERT INTO employees_table(user_id,manager,first_name,last_name,passwd,email)
VALUES ('ronson24',true,'Ronald','Wolson','wetty3','asf@fft.net');

INSERT INTO employees_table(user_id,manager,first_name,last_name,passwd,email)
VALUES ('jkrow8',false,'Justin','Rolling','egjwoi','hairy@pots.net');

INSERT INTO employees_table(user_id,manager,first_name,last_name,passwd,email)
VALUES ('whois',true,'Qino','Sanchez','qwer','aaa@eee.com');

--drop table reimb_reciepts_table;
--drop table reimb_table;
--drop table employees_table;


--SELECT reimb_employee.reimb_id, reimb_manager.reimb_status, 
--	reimb_manager.reimb_balance, reimb_manager.mgr_user_id_fk, 
--	reimb_employee.emp_user_id_fk
--	FROM reimb_employee INNER JOIN reimb_manager 
--	ON reimb_employee.reimb_id = reimb_manager.reimb_id_fk
--	WHERE emp_user_id_fk = 'dfsg45';

--SELECT reimb_employee.reimb_id, reimb_manager.reimb_status, 
--	reimb_manager.reimb_balance, reimb_manager.mgr_user_id_fk, 
--	reimb_employee.emp_user_id_fk
--	FROM reimb_employee INNER JOIN reimb_manager 
--	ON reimb_employee.reimb_id = reimb_manager.reimb_id_fk
--	WHERE reimb_employee.emp_user_id_fk = 'jkrow8';

INSERT INTO reimb_table(reimb_id,reimb_status,reimb_balance,mgr_user_id_fk,emp_user_id_fk)
     VALUES('342rf','PENDING',20.00,'whois','asdf1345');
INSERT INTO reimb_reciepts_table(reimb_id_fk,reciept_img_path)
     VALUES('342rf','c:/blah/eee.jpg');

--INSERT INTO reimb_employee(reimb_id_fk,emp_user_id_fk,emp_fk,reciept_img_path)
--	   VALUES('034jg0j','jkrow8','c:/blah/hahah.jpg');
INSERT INTO reimb_table(reimb_id,reimb_status, reimb_balance,mgr_user_id_fk,emp_user_id_fk)
     VALUES('034jg0j','PENDING',80.00,'ronson24','jkrow8');

INSERT INTO reimb_reciepts_table(reimb_id_fk,reciept_img_path)
     VALUES('034jg0j','c:/blah/oh-look-another.jpg');

--UPDATE reimb_manager SET reimb_status = true WHERE mgr_user_id_fk = 'whois';
--update employees_table set employee_or_manager = true where work_id = 'asdf134';
--
--INSERT INTO reimb_manager(reimb_id,reimb_status,
--			reimb_balance,mgr_user_id_fk,mgr_fk)
--		VALUES('35r3o2jr','PENDING',20.00,'whois',false);
--INSERT INTO reimb_employee(reimb_id_fk,emp_user_id_fk,emp_fk,reciept_img_path)
--	VALUES('35r3o2jr','asdf134',true,'c:/blah/examplelel.png');		

SELECT * FROM reimb_table;
    
INSERT INTO reimb_table(reimb_id, reimb_status, reimb_balance, mgr_user_id_fk, emp_user_id_fk)
     VALUES (           'dfsg45', 'APPROVED',   80.10,         'whois',        'jkrow8');

INSERT INTO reimb_table(reimb_id,   reimb_status, reimb_balance, mgr_user_id_fk, emp_user_id_fk)
     VALUES (           'ranson24', 'PENDING',   80.33,         'whois',        'jkrow8');

-- 2 for testing for now

--delete from reimb_manager where reimb_id = 'PL9q1oYz';

-- TODO: figure out what this was trying to do...
SELECT reimb_reciepts_table.reciept_img_path FROM reimb_reciepts_table_2
INNER JOIN reimb_table ON reimb_table.reimb_id = reimb_reciepts_table.reimb_id_fk 
WHERE reimb_table.emp_user_id_fk = 'asdf134';

CREATE TABLE reimb_reciepts_table_2 (

        reimb_id_fk VARCHAR(8) NOT NULL,
        reciept_img_path VARCHAR(122) PRIMARY KEY,
        
        amount NUMERIC(8,2) DEFAULT 0.00,
        
        added_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

        FOREIGN KEY (reimb_id_fk) REFERENCES reimb_table(reimb_id)

);

INSERT INTO reimb_reciepts_table_2(reimb_id_fk, reciept_img_path) VALUES ('034jg0j', 'csomething');
INSERT INTO reimb_reciepts_table_2(reimb_id_fk, reciept_img_path, amount) VALUES ('034jg0j', 'csomething2', 9.09);
INSERT INTO reimb_reciepts_table_2(reimb_id_fk, reciept_img_path, amount) VALUES ('034jg0j', 'csomething55', 0.01);
INSERT INTO reimb_reciepts_table_2(reimb_id_fk, reciept_img_path, amount) VALUES ('034jg0j', 'csomething3', 1.11); 
INSERT INTO reimb_reciepts_table_2(reimb_id_fk, reciept_img_path, amount) VALUES ('ranson24', 'csomething44', 1.11); 


select * from employees_table;
select * from reimb_table;
select * from reimb_reciepts_table;

-- Gets the sum of the reiceipts for a reimbursement set '034jg0j' -- was reimb_table.reimb_balance
-- TODO: edit reimb_table.reimb_balance in the table creation, see notes around that about editing it.
SELECT sum(amount) FROM reimb_reciepts_table_2 WHERE reimb_id_fk='034jg0j';

