package com.employeesService.EmployeesService.repository;

import com.employeesService.EmployeesService.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcDepartmentRepository implements DepartmentRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public JdbcDepartmentRepository(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public Department save(Department department) {
        String sql = "Insert into departments(dept_no, dept_name) values (:deptNo,:deptName) on conflict (demp_no) do update set dept_name = :deptName";
        MapSqlParameterSource paramSource = new MapSqlParameterSource("deptNo", department.getDeptNo()).addValue("deptName", department.getDeptName());
        this.namedJdbcTemplate.update(sql, paramSource);
        return department;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = this.namedJdbcTemplate.query("select * from departments", new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Optional<Department> findById(String deptNo) {
        String sql = "select * from departments where dept_no= :deptNo";
        MapSqlParameterSource paramSource = new MapSqlParameterSource("deptNo", deptNo);
        return Optional.ofNullable(this.namedJdbcTemplate.queryForObject(sql, paramSource, new DepartmentRowMapper()));
    }

    @Override
    public Optional<Department> findByIdWithEmployees(String deptNo) {
        return Optional.empty();
    }

    @Override
    public void deleteById(String deptNo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource("deptNo", deptNo);
        this.namedJdbcTemplate.update("delete from departments where dept_no= :deptNo", paramSource);
    }

    private static final class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();
            department.setDeptNo(rs.getString("dept_no"));
            department.setDeptName(rs.getString("dept_name"));
            return department;
        }
    }
}
