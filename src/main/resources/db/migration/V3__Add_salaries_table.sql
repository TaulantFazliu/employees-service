CREATE TABLE salaries
(
    emp_no    INTEGER NOT NULL,
    salary    INTEGER NOT NULL,
    from_date DATE    NOT NULL,
    to_date   DATE,
    PRIMARY KEY (emp_no, from_date)
);
ALTER TABLE salaries
    ADD CONSTRAINT fkEmployNumber FOREIGN KEY (emp_no) REFERENCES employees (emp_no);

CREATE INDEX emp_no ON salaries (emp_no);
