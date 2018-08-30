package com.rajusays.demo.dao;

import static com.rajusays.demo.SQLConstants.DELETE_USER;
import static com.rajusays.demo.SQLConstants.FOLLOW_USER;
import static com.rajusays.demo.SQLConstants.INSERT_USER;
import static com.rajusays.demo.SQLConstants.SEARCH_USER;
import static com.rajusays.demo.SQLConstants.SELECT_FOLLOWEES;
import static com.rajusays.demo.SQLConstants.SELECT_FOLLOWERS;
import static com.rajusays.demo.SQLConstants.SELECT_USER;
import static com.rajusays.demo.SQLConstants.UNFOLLOW_USER;
import static com.rajusays.demo.SQLConstants.UPDATE_USER;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rajusays.demo.dao.rowmapper.UserRowMapper;
import com.rajusays.demo.exception.UserAlreadyExistsException;
import com.rajusays.demo.to.UserTO;

@Repository
public class UserDaoImpl implements UserDao {

	private static final String PERCENTILE = "%";
	private static final String SEARCH_STRING = "search_string";
	private static final String FOLLOWEE = "followee";
	private static final String FOLLOWER = "follower";
	private static final String OLD_USER_NAME = "old_user_name";
	private static final String STATE = "state";
	private static final String CITY = "city";
	private static final String DATE_OF_BIRTH = "date_of_birth";
	private static final String NAME = "name";
	private static final String USER_NAME = "user_name";
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void create(UserTO userTO) {
		checkIfUserNameAlreadyExists(userTO);
		namedParameterJdbcTemplate.update(INSERT_USER, prepareCreateUserParams(userTO));
	}

	public void delete(String userName) {
		namedParameterJdbcTemplate.update(DELETE_USER, prepareUserNameParamSource(userName));
	}

	public void update(String userName, UserTO userTO) {
		MapSqlParameterSource prepareCreateUserParams = prepareCreateUserParams(userTO);
		prepareCreateUserParams.addValue(OLD_USER_NAME, userName);
		namedParameterJdbcTemplate.update(UPDATE_USER, prepareCreateUserParams);
	}

	public UserTO get(String userName) {
		UserTO userTO = null;
		try {
			userTO = namedParameterJdbcTemplate.queryForObject(SELECT_USER, prepareUserNameParamSource(userName),
					new UserRowMapper());
		} catch (EmptyResultDataAccessException extception) {
			return null;
		}
		userTO.setFollowees(getFollowees(userName));
		userTO.setFollowers(getFollowers(userName));
		return userTO;

	}
	
	@Override
	public List<UserTO> searchUser(String searchString) {
		String modifiedSearchString = prepareSearchString(searchString);
		List<UserTO> users = namedParameterJdbcTemplate.query(SEARCH_USER,
				new MapSqlParameterSource(SEARCH_STRING, modifiedSearchString), new UserRowMapper());
		for(UserTO userTO:users) {
			userTO.setFollowees(getFollowees(userTO.getUserName()));
			userTO.setFollowers(getFollowers(userTO.getUserName()));
		}
		return users;
	}

	
	@Override
	public void followUser(String follower, String followee) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue(FOLLOWER, follower);
		parameterSource.addValue(FOLLOWEE, followee);
		namedParameterJdbcTemplate.update(FOLLOW_USER, parameterSource);
	}

	@Override
	public void unFollowUser(String follower, String followee) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue(FOLLOWER, follower);
		parameterSource.addValue(FOLLOWEE, followee);
		namedParameterJdbcTemplate.update(UNFOLLOW_USER, parameterSource);

	}

	@Override
	public List<UserTO> getFollowers(String followee) {

		return namedParameterJdbcTemplate.query(SELECT_FOLLOWERS, new MapSqlParameterSource(FOLLOWEE, followee),
				new UserRowMapper());
	}

	@Override
	public List<UserTO> getFollowees(String follower) {
		return namedParameterJdbcTemplate.query(SELECT_FOLLOWEES, new MapSqlParameterSource(FOLLOWER, follower),
				new UserRowMapper());
	}

	private Map<String, String> prepareUserNameParamSource(String userName) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(USER_NAME, userName);
		return paramMap;
	}

	private MapSqlParameterSource prepareCreateUserParams(UserTO userTO) {

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue(USER_NAME, userTO.getUserName());
		parameterSource.addValue(NAME, userTO.getName());
		if(userTO.getDateOfBirth()==null) {
			userTO.setDateOfBirth(LocalDate.of(1990, 01, 01));
		}
		parameterSource.addValue(DATE_OF_BIRTH, userTO.getDateOfBirth());
		parameterSource.addValue(CITY, userTO.getCity());
		parameterSource.addValue(STATE, userTO.getState());
		return parameterSource;
	}

	private void checkIfUserNameAlreadyExists(UserTO userTO) {
		if (get(userTO.getUserName()) != null) {
			throw new UserAlreadyExistsException();
		}
	}


	private String prepareSearchString(String searchString) {
		searchString = searchString.toUpperCase();
		String modifiedSearchString = PERCENTILE + searchString + PERCENTILE;
		return modifiedSearchString;
	}

}
