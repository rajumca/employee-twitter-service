package com.rajusays.demo.dao;

import java.util.List;

import com.rajusays.demo.to.TweetTO;


public interface FeedDao {
List<TweetTO> getTweets(String userName);
List<TweetTO> getUserFeed(String userName);
public boolean createTweet(TweetTO tweet);
public boolean deleteTweet(int id);


}
