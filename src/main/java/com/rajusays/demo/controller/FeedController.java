package com.rajusays.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajusays.demo.dao.FeedDao;
import com.rajusays.demo.dao.UserDao;
import com.rajusays.demo.to.FeedResponseTO;
import com.rajusays.demo.to.FeedTO;
import com.rajusays.demo.to.TweetRequestTO;
import com.rajusays.demo.to.TweetTO;
import com.rajusays.demo.to.UserTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class FeedController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private FeedDao twitterDao;

	@GetMapping("feed")
	public FeedResponseTO getFeed(@RequestParam String follower) {

		FeedTO feed = new FeedTO();
		feed.setTweets(twitterDao.getTweets(follower));
		UserTO user = userDao.get(follower);
		feed.setUser(user);
		feed.setId(user.getUserName());
		FeedResponseTO feedRespone = new FeedResponseTO();
		feedRespone.setFeed(feed);
		return feedRespone;
	}

	@GetMapping("feed/user/{followee}")
	public List<TweetTO> getUserTweets(@PathVariable String followee, @RequestParam String follower) {
		return twitterDao.getUserFeed(followee);
	}

	@PostMapping("tweets")
	public void tweet(@RequestBody TweetRequestTO tweet) {
		twitterDao.createTweet(tweet.getTweet());
	}

	@DeleteMapping("tweets/{id}")
	public void delete(@PathVariable int id) {
		twitterDao.deleteTweet(id);
	}

}
