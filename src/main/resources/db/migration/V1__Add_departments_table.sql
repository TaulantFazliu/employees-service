create table departments
(
    dept_no   char(4) primary key not null,
    dept_name varchar(40)         not null
);
create index dept_name on departments (dept_name);