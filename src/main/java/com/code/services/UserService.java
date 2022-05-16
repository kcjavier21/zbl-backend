package com.code.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.code.model.User;

@Repository
public class UserService implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM users";
		List<User> listOfUsers = jdbcTemplate.query(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName") , 
						rs.getString("username"), rs.getString("email"), rs.getString("password"));
				return user;
			}
		});
		
		return listOfUsers;
	}

	@Override
	public User getSingleUser(int id) {
		String sql = "SELECT * FROM users WHERE id=" + id + ";";
		
		System.out.println(sql);
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User user = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName") , 
							rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("role"));
					return user;
				}
				
				return null;
			}
		});
	}
	
	@Override
	public User getUserByUsername(String username) {
		String sql = "SELECT * FROM users WHERE username = '" + username + "';";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User user = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName") , 
							rs.getString("username"), rs.getString("email"), rs.getString("password"));
					return user;
				}
				
				return null;
			}
		});
	}

	@Override
	public ResponseEntity<String> registerNewUser(User user) {
		String salt = BCrypt.gensalt(10);
		String encryptedPassword = BCrypt.hashpw(user.getPassword(), salt);
		
		String sql = "INSERT INTO users(firstName, lastName, username, email, password, role) VALUES('"+ user.getFirstName() 
		+ "','" + user.getLastName() + "','" + user.getUsername() + "','" + user.getEmail() + "','" + encryptedPassword + "','" + user.getRole() + "')";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("User has been successfully registered!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}

	@Override
	public ResponseEntity<String> updateUser(int id, User user) {
		String sql = "UPDATE teams " + 
				"SET firstName = '" + user.getFirstName() + "', lastName = '" + user.getLastName() + "', " +
					"username = '" + user.getUsername() + "', email = '" + user.getEmail() + "';" +
				"WHERE id = " + id + ";";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("User has been successfully updated!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}

	@Override
	public ResponseEntity<String> deleteUser(int id) {
		String sql = "DELETE FROM users WHERE id = " + id + ";";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("User has been successfully deleted!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}
}
