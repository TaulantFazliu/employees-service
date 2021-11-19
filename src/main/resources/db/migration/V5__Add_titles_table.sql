CREATE TABLE titles
(
    emp_no    INTEGER     NOT NULL,
    title     VARCHAR(50) NOT NULL,
    from_date DATE        NOT NULL,
    to_date   DATE,
    PRIMARY KEY (emp_no, title, from_date)
);
ALTER TABLE titles
    ADD CONSTRAINT fk_employee_no FOREIGN KEY (emp_no) REFERENCES employees (emp_no);
CREATE INDEX emp_no ON titles (emp_no);
