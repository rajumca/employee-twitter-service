package com.rajusays.demo.dao;

import static com.rajusays.demo.SQLConstants.DELETE_TWEET;
import static com.rajusays.demo.SQLConstants.INSERT_TWEET;
import static com.rajusays.demo.SQLConstants.SELECT_FEED;
import static com.rajusays.demo.SQLConstants.SELECT_TWEET;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rajusays.demo.dao.rowmapper.TweetRowMapper;
import com.rajusays.demo.to.TweetTO;

@Repository
public class FeedDaoImpl implements FeedDao {

	private static final String USER_NAME = "user_name";
	private static final String ID = "id";
	private static final String TIME = "time";
	private static final String MESSAGE = "message";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<TweetTO> getTweets(String userName) {
		return namedParameterJdbcTemplate.query(SELECT_FEED, new MapSqlParameterSource(USER_NAME, userName),
				new TweetRowMapper());
	}

	public List<TweetTO> getUserFeed(String userName) {
		return namedParameterJdbcTemplate.query(SELECT_TWEET, new MapSqlParameterSource(USER_NAME, userName),
				new TweetRowMapper());

	}

	public boolean createTweet(TweetTO tweet) {
		namedParameterJdbcTemplate.update(INSERT_TWEET, prepareParams(tweet));
		return true;
	}

	public boolean deleteTweet(int tweetId) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(ID, tweetId);
		namedParameterJdbcTemplate.update(DELETE_TWEET, paramSource);
		return false;
	}

	private MapSqlParameterSource prepareParams(TweetTO tweet) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(MESSAGE, tweet.getMessage());
		paramSource.addValue(TIME, LocalDateTime.now());
		paramSource.addValue(USER_NAME, tweet.getUser().getUserName());
		return paramSource;
	}

}