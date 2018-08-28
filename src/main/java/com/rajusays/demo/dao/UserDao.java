package com.rajusays.demo.dao;

import java.util.List;

import com.rajusays.demo.to.UserTO;

public interface UserDao {
	public void create(UserTO userTO);

	public UserTO get(String userName);

	public void delete(String userName);

	public void update(String userName, UserTO userTO);

	public void followUser(String follower, String followee);
	
	public void unFollowUser(String follower, String followee);
	
	public List<UserTO> getFollowers(String followee);
	
	public List<UserTO> getFollowees(String follower);
	
	public List<UserTO> searchUser(String searchString);
}
