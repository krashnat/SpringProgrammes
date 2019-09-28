package com.bridgelabz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bridgelabz.model.PasswordUpdate;
import com.bridgelabz.model.StudentLogin;
import com.bridgelabz.model.StudentRegistration;

@Component
public class StudentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//method for stores data to database
	public boolean register(StudentRegistration registration) {
		//sql query
		String sql = "INSERT INTO student (name, email, mobno, password)" + " VALUES ('" + registration.getName()
				+ "','" + registration.getEmail() + "','" + registration.getMobno() + "', '"
				+ registration.getPassword() + "')";
		int result= jdbcTemplate.update(sql);
		if(result>0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		

	}
	//method fetch the data from database
	public boolean login(StudentLogin login) {
          //sql query
		String sql = "select * from student where email='" + login.getEmail() + "' and password='" + login.getPassword()
				+ "'";
		List<StudentRegistration> users = jdbcTemplate.query(sql, new RowMapper<StudentRegistration>()

		{
			public StudentRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentRegistration student = new StudentRegistration();
				student.setEmail(rs.getString("email"));
				student.setPassword(rs.getString("password"));
				return student;
			}
		});

		if (users.size() > 0) {

			System.out.println("inside lgoinmethod :-" + users.get(0).getEmail());
			System.out.println("inside lgoinmethod :-" + users.get(0));
			return true;
		} else

			return false;

	}
		//method to update password
	public int updatePassword(PasswordUpdate information) {
		String sql = "update student set password='" + information.getNewPassword() + "' where email='"
				+ information.getEmail() + "'";
		return jdbcTemplate.update(sql);

	}

}
