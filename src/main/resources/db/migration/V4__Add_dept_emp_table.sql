CREATE TABLE dept_emp
(
    emp_no    INTEGER NOT NULL,
    dept_no   CHAR(4) NOT NULL,
    from_date DATE    NOT NULL,
    to_date   DATE,
    PRIMARY KEY (emp_no, dept_no)
);

ALTER TABLE dept_emp
    ADD CONSTRAINT fk_emp_name FOREIGN KEY (emp_no) REFERENCES employees (emp_no);
ALTER TABLE dept_emp
    ADD CONSTRAINT fk_dept_no FOREIGN KEY (dept_no) REFERENCES departments (dept_no);

CREATE INDEX dept_no ON dept_emp (dept_no);
CREATE INDEX emp_num ON dept_emp (emp_no);
