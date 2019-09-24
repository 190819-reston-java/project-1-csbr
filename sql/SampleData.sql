insert into work_table(work_id,employee_or_manager,first_name,last_name,acc_password,acc_email)
values ('asdf134',true,'Engel','Sanders','1234','eee@555.com');

insert into work_table(work_id,employee_or_manager,first_name,last_name,acc_password,acc_email)
values ('dfsg45',true,'Will','Fengel','zxcv','rret@ett.com');

insert into work_table(work_id,employee_or_manager,first_name,last_name,acc_password,acc_email)
values ('whois',false,'Qino','Sanchez','qwer','aaa@eee.com');



insert into reimbursements(reimb_id,work_emp_id_fk,emp_fk,reimb_status,pending,
							reimb_balance,work_mgr_id_fk,mgr_fk) values
		('235gfg','asdf134',true,false,true,40.00,'whois',false);
insert into reimbursements(reimb_id,work_emp_id_fk,emp_fk,reimb_status,pending,
							reimb_balance,work_mgr_id_fk,mgr_fk) values
		('234gfg','asdf134',true,false,true,20.00,'whois',false);

select * from work_table;
select * from reimbursements;
select * from reimb_reciepts;


UPDATE reimbursements SET reimb_status = false WHERE work_mgr_id_fk = 'whois';
update work_table set employee_or_manager = false where work_id = 'asdf134';

insert into reimb_reciepts(reimb_id_fk,reciept_img_path) 
values ('235gfg','/users/blah/example.jpg');
insert into reimb_reciepts(reimb_id_fk,reciept_img_path) 
values ('235gfg','/users/blah/another_example.jpg');
-- this query doesn't work since there's no '233gfg' in the table.
insert into reimb_reciepts(reimb_id_fk,reciept_img_path) 
values ('233gfg','/users/blah/another_example.jpg');

delete from reimb_reciepts where reimb_id_fk = '235gfg';

drop table reimb_reciepts;
alter table reimb_reciepts 
