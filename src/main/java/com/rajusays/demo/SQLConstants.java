package com.rajusays.demo;

public final class SQLConstants {

	private SQLConstants() {
	};

	public static final String INSERT_USER = "INSERT INTO twitter.user (user_name, name, date_of_birth, city, state) VALUES (:user_name, :name, :date_of_birth, :city, :state)";

	public static final String SELECT_USER = "SELECT user_name, name, date_of_birth, city, state FROM twitter.user where user_name = :user_name";
	public static final String UPDATE_USER = "UPDATE twitter.user SET user_name=:user_name, name=:name, date_of_birth=:date_of_birth, city=:city, state=:state WHERE user_name=:old_user_name";
	public static final String DELETE_USER = "DELETE FROM twitter.user WHERE user_name = :user_name";

	public static final String SELECT_FEED = "SELECT tweet.id, tweet.message, tweet.time, tweet.user_name, tweet_user.name FROM twitter.tweet as tweet, twitter.user as tweet_user WHERE  tweet_user.user_name=tweet.user_name AND (tweet.user_name in (select followee from twitter.followers where follower=:user_name) OR tweet.user_name=:user_name) order by time desc";
	public static final String SELECT_TWEET = "SSELECT tweet.id, tweet.message, tweet.time, tweet.user_name, tweet_user.name FROM twitter.tweet as tweet, twitter.user as tweet_user WHERE  tweet_user.user_name=tweet.user_name AND tweet.user_name=:user_name order by time desc";
	public static final String INSERT_TWEET = "INSERT INTO twitter.tweet (id, message, time, user_name) VALUES (nextval('serial'),:message, :time, :user_name)";
	public static final String UPDATE_TWEET = "UPDATE twitter.tweet SET message=:message, time=:time WHERE id=:id";
	public static final String DELETE_TWEET = "DELETE FROM twitter.tweet WHERE id=:id";
	public static final String FOLLOW_USER = "INSERT INTO twitter.followers(follower, followee) VALUES (:follower, :followee)";
	public static final String UNFOLLOW_USER = "DELETE FROM twitter.followers WHERE follower=:follower AND followee=:followee";

	public static final String SEARCH_USER = "SELECT user_name, name, date_of_birth, city, state FROM twitter.\"user\" where upper(name) like :search_string or upper(user_name) like :search_string;";

	public static final String SELECT_FOLLOWERS = "select follower from twitter.followers where followee= :followee";
	public static final String SELECT_FOLLOWEES = "select followee from twitter.followers where follower=:follower";

}
