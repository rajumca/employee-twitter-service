package com.rajusays.demo.dao;

import java.util.List;

import com.rajusays.demo.to.TweetTO;


public interface FeedDao {
List<TweetTO> getFeed(String userName);
List<TweetTO> getUserTweets(String userName);
public boolean createTweet(TweetTO tweet);
public boolean deleteTweet(int id);


}
