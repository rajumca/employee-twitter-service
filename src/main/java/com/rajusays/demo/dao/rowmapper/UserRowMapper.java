package com.rajusays.demo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rajusays.demo.to.UserTO;

public class UserRowMapper implements RowMapper<UserTO> {
	private static final String DATE_OF_BIRTH = "date_of_birth";
	private static final String USER_NAME = "user_name";
	private static final String STATE = "state";
	private static final String CITY = "city";
	private static final String NAME = "name";

	@Override
	public UserTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserTO user = new UserTO();
		user.setName(rs.getString(NAME));
		user.setCity(rs.getString(CITY));
		user.setState(rs.getString(STATE));
		user.setUserName(rs.getString(USER_NAME));
		user.setDateOfBirth(rs.getDate(DATE_OF_BIRTH).toLocalDate());
		return user;
	}
}
