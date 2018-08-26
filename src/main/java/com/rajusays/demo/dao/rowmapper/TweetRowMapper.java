package com.rajusays.demo.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rajusays.demo.to.TweetTO;
import com.rajusays.demo.to.UserTO;

public class TweetRowMapper implements RowMapper<TweetTO> {

	private static final String NAME = "name";
	private static final String USER_NAME = "user_name";
	private static final String TIME = "time";
	private static final String ID = "id";
	private static final String MESSAGE = "message";

	@Override
	public TweetTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TweetTO tweet = new TweetTO();
		tweet.setMessage(rs.getString(MESSAGE));
		tweet.setId(rs.getString(ID));
		tweet.setTime(rs.getTimestamp(TIME).toLocalDateTime());
		UserTO userTO = new UserTO();
		userTO.setUserName(rs.getString(USER_NAME));
		userTO.setName(rs.getString(NAME));
		tweet.setUser(userTO);
		return tweet;

	}

}
