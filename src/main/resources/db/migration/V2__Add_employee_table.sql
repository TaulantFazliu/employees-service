CREATE TABLE employees
(
    emp_no     INTEGER     NOT NULL PRIMARY KEY,
    birth_date DATE        NOT NULL,
    first_name VARCHAR(14) NOT NULL,
    last_name  VARCHAR(16) NOT NULL,
    gender     VARCHAR(1)  NOT NULL,
    hire_date  DATE        NOT NULL
);
