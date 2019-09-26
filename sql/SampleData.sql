insert into work_table(work_id,employee_or_manager,first_name,last_name,acc_password,acc_email)
values ('asdf134',true,'Engel','Sanders','1234','eee@555.com');

insert into work_table(work_id,employee_or_manager,first_name,last_name,acc_password,acc_email)
values ('dfsg45',true,'Will','Fengel','zxcv','rret@ett.com');

insert into work_table(work_id,employee_or_manager,first_name,last_name,acc_password,acc_email)
values ('ronson24',false,'Ronald','Wolson','wetty3','asf@fft.net');

insert into work_table(work_id,employee_or_manager,first_name,last_name,acc_password,acc_email)
values ('jkrow8',true,'Justin','Rolling','egjwoi','hairy@pots.net');

insert into work_table(work_id,employee_or_manager,first_name,last_name,acc_password,acc_email)
values ('whois',false,'Qino','Sanchez','qwer','aaa@eee.com');

drop table reimb_employee;
drop table reimb_manager;
drop table contact_addresses;
drop table work_table;

select * from work_table;
select * from reimb_manager;
select * from reimb_employee;

SELECT reimb_employee.reimb_id, reimb_manager.reimb_status, 
	reimb_manager.reimb_balance, reimb_manager.work_mgr_id_fk, 
	reimb_employee.work_emp_id_fk
	FROM reimb_employee INNER JOIN reimb_manager 
	ON reimb_employee.reimb_id = reimb_manager.reimb_id_fk
	WHERE work_emp_id_fk = 'dfsg45';

SELECT reimb_employee.reimb_id, reimb_manager.reimb_status, 
	reimb_manager.reimb_balance, reimb_manager.work_mgr_id_fk, 
	reimb_employee.work_emp_id_fk
	FROM reimb_employee INNER JOIN reimb_manager 
	ON reimb_employee.reimb_id = reimb_manager.reimb_id_fk
	WHERE reimb_employee.work_emp_id_fk = 'jkrow8';

insert into reimb_manager(reimb_id,reimb_status,
			reimb_balance,work_mgr_id_fk,mgr_fk)
		values('342rf','PENDING',20.00,'whois',false);
INSERT INTO reimb_employee(reimb_id_fk,work_emp_id_fk,emp_fk,reciept_img_path)
	VALUES('342rf','dfsg45',true,'c:/blah/eee.jpg');

INSERT INTO reimb_employee(reimb_id_fk,work_emp_id_fk,emp_fk,reciept_img_path)
	VALUES('034jg0j','jkrow8',true,'c:/blah/hahah.jpg');
INSERT INTO reimb_employee(reimb_id_fk,work_emp_id_fk,emp_fk,reciept_img_path)
	VALUES('034jg0j','jkrow8',true,'c:/blah/oh-look-another.jpg');

insert into reimb_manager(reimb_id,reimb_status,
			reimb_balance,work_mgr_id_fk,mgr_fk)
		values('034jg0j','PENDING',80.00,'ronson24',false);

UPDATE reimb_manager SET reimb_status = true WHERE work_mgr_id_fk = 'whois';
update work_table set employee_or_manager = true where work_id = 'asdf134';

insert into reimb_manager(reimb_id,reimb_status,
			reimb_balance,work_mgr_id_fk,mgr_fk)
		values('35r3o2jr','PENDING',20.00,'whois',false);
INSERT INTO reimb_employee(reimb_id_fk,work_emp_id_fk,emp_fk,reciept_img_path)
	VALUES('35r3o2jr','asdf134',true,'c:/blah/examplelel.png');		
	
	
	