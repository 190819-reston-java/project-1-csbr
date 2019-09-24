insert into work_table(work_id,employee_or_manager,first_name,last_name,acc_password,acc_email)
values ('asdf134',true,'Engel','Sanders','1234','eee@555.com');

insert into work_table(work_id,employee_or_manager,first_name,last_name,acc_password,acc_email)
values ('dfsg45',true,'Will','Fengel','zxcv','rret@ett.com');

insert into work_table(work_id,employee_or_manager,first_name,last_name,acc_password,acc_email)
values ('whois',false,'Qino','Sanchez','qwer','aaa@eee.com');

--drop table reimb_employee;
--drop table reimbursements;
--drop table contact_addresses;
--drop table work_table;

select * from work_table;
select * from reimbursements;
select * from reimb_employee;

insert into reimb_manager(reimb_id_fk,reimb_status,
			reimb_balance,work_mgr_id_fk,mgr_fk)
		values('342rf','PENDING',20.00,'whois',false);
insert into reimb_employee(reimb_id,work_emp_id_fk,emp_fk,reciept_img_path)
	values('342rf','dfsg45',true,'c:/blah/eee.jpg');

UPDATE reimb_manager SET reimb_status = false WHERE work_mgr_id_fk = 'whois';
update work_table set employee_or_manager = false where work_id = 'asdf134';

