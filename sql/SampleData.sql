insert into employees_table(user_id,manager,first_name,last_name,passwd,email)
values ('asdf134',false,'Engel','Sanders','1234','eee@555.com');

insert into employees_table(user_id,manager,first_name,last_name,passwd,email)
values ('dfsg45',false,'Will','Fengel','zxcv','rret@ett.com');

insert into employees_table(user_id,manager,first_name,last_name,passwd,email)
values ('ronson24',true,'Ronald','Wolson','wetty3','asf@fft.net');

insert into employees_table(user_id,manager,first_name,last_name,passwd,email)
values ('jkrow8',false,'Justin','Rolling','egjwoi','hairy@pots.net');

insert into employees_table(user_id,manager,first_name,last_name,passwd,email)
values ('whois',true,'Qino','Sanchez','qwer','aaa@eee.com');

--drop table reimb_reciepts;
--drop table reimb_table;
--drop table employees_table;

select * from employees_table;
select * from reimb_table;
select * from reimb_reciepts_table;

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

insert into reimb_table(reimb_id,reimb_status,
			reimb_balance,mgr_user_id_fk,emp_user_id_fk)
		values('342rf','PENDING',20.00,'whois','asdf134');
INSERT INTO reimb_reciepts(reimb_id_fk,reciept_img_path)
	VALUES('342rf','c:/blah/eee.jpg');

--INSERT INTO reimb_employee(reimb_id_fk,emp_user_id_fk,emp_fk,reciept_img_path)
--	VALUES('034jg0j','jkrow8','c:/blah/hahah.jpg');
insert into reimb_table(reimb_id,reimb_status,
			reimb_balance,mgr_user_id_fk,emp_user_id_fk)
		values('034jg0j','PENDING',80.00,'ronson24','jkrow8');

INSERT INTO reimb_reciepts_table(reimb_id_fk,reciept_img_path)
	VALUES('034jg0j','c:/blah/oh-look-another.jpg');

--UPDATE reimb_manager SET reimb_status = true WHERE mgr_user_id_fk = 'whois';
--update employees_table set employee_or_manager = true where work_id = 'asdf134';
--
--insert into reimb_manager(reimb_id,reimb_status,
--			reimb_balance,mgr_user_id_fk,mgr_fk)
--		values('35r3o2jr','PENDING',20.00,'whois',false);
--INSERT INTO reimb_employee(reimb_id_fk,emp_user_id_fk,emp_fk,reciept_img_path)
--	VALUES('35r3o2jr','asdf134',true,'c:/blah/examplelel.png');		


--delete from reimb_manager where reimb_id = 'PL9q1oYz';
	
SELECT reimb_reciepts.reciept_img_path FROM reimb_reciepts 
INNER JOIN reimb_table ON reimb_table.reimb_id = reimb_reciepts.reimb_id_fk 
WHERE reimb_table.emp_user_id_fk = 'asdf134';


	