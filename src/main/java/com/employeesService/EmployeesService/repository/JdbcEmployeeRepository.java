package com.employeesService.EmployeesService.repository;

import com.employeesService.EmployeesService.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcEmployeeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Employee save(Employee employee) {
        String sql = "Insert into employees(emp_no, birth_date,first_name, last_name, gender, hire_date) values (:empNo,:birthDate, :firstName, :lastName, cast(:gender as gender), :hireDate) " +
                "on conflict (emp_no) do update set birth_date = :birthDate, first_name= :firstName, last_name= :lastName, gender= cast(:gender as gender), hire_date= :hireDate";
        MapSqlParameterSource paramSource = new MapSqlParameterSource("empNo", employee.getEmpNo())
                .addValue("birthDate", employee.getBirthDate())
                .addValue("firstName", employee.getFirstName())
                .addValue("lastName", employee.getLastName())
                .addValue("gender", employee.getGender().toString())
                .addValue("hireDate", employee.getHireDate());
        namedParameterJdbcTemplate.update(sql, paramSource);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = namedParameterJdbcTemplate.query("select * from employees", new JdbcEmployeeRepository.EmployeeRowMapper());
        return employees;
    }

    @Override
    public Optional<Employee> findById(Long emptNo) {
        String sql = ("select * from employees where emp_no= :empNo");
        MapSqlParameterSource paramSource = new MapSqlParameterSource("empNo", emptNo);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, paramSource, new JdbcEmployeeRepository.EmployeeRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Long empNo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource("empNo", empNo);
        namedParameterJdbcTemplate.update("delete from employees where emp_no= :empNo", paramSource);
    }

    private static final class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setEmpNo(rs.getLong("emp_no"));
            employee.setBirthDate(rs.getObject("birth_date", LocalDate.class));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            employee.setGender(Employee.Gender.valueOf(rs.getString("gender")));
            employee.setHireDate(rs.getObject("hire_date", LocalDate.class));
            return employee;
        }
    }
}
