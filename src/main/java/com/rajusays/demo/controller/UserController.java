package com.rajusays.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajusays.demo.dao.UserDao;
import com.rajusays.demo.to.UserTO;
import com.rajusays.demo.to.UsersResponseTO;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

//	private static final String SEARCH = "search";
	@Autowired
	private UserDao userDao;

	@GetMapping
	public UsersResponseTO searchUser(@RequestParam String userName) {
		UsersResponseTO responseTO = new UsersResponseTO();
			responseTO.setUsers(userDao.searchUser(userName));
		return responseTO;
	}

	@GetMapping("{userName}")
	public UsersResponseTO getUser(@PathVariable(name = "userName") String userName) {
		UsersResponseTO responseTO = new UsersResponseTO();
		responseTO.setUsers(Collections.singletonList(userDao.get(userName)));
		return responseTO;
	}

	@PostMapping
	public void createUser(@RequestBody UserTO userTO) {
		userDao.create(userTO);
	}

	@PutMapping("{userName}")
	public void updateUser(@PathVariable String userName, @RequestBody UserTO userTO) {
		userDao.update(userName, userTO);
	}

	@DeleteMapping("{userName}")
	public void deleteUser(String userName) {
		userDao.delete(userName);
	}

	@PostMapping("/{followee}/follow")
	public void followUser(@RequestParam String follower, @PathVariable String followee) {
		userDao.followUser(follower, followee);
	}

	@DeleteMapping("/{followee}/follow")
	public void unfollowUser(@RequestParam String follower, @PathVariable String followee) {
		userDao.unFollowUser(follower, followee);
	}

	@GetMapping("{followee}/followers")
	public List<String> getFollowers(@PathVariable String followee) {
		return userDao.getFollowers(followee);
	}

	@GetMapping("{follower}/followees")
	public List<String> getFollowees(@PathVariable String follower) {
		return userDao.getFollowees(follower);
	}
}
