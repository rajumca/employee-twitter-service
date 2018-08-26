package com.rajusays.demo.to;

import java.util.List;

public class FeedTO {
private String id;

private List<TweetTO> tweets;

private UserTO user;

public List<TweetTO> getTweets() {
	return tweets;
}

public void setTweets(List<TweetTO> tweets) {
	this.tweets = tweets;
}

public UserTO getUser() {
	return user;
}

public void setUser(UserTO user) {
	this.user = user;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}




}
