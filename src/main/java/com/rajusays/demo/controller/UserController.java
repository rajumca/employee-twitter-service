package com.rajusays.demo.controller;

import java.util.Collections;

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
import com.rajusays.demo.to.UserRequestTO;
import com.rajusays.demo.to.UsersResponseTO;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

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
	public void createUser(@RequestBody UserRequestTO userRequest) {
		userDao.create(userRequest.getUser());
	}

	@PutMapping("{userName}")
	public void updateUser(@PathVariable String userName, @RequestBody UserRequestTO userRequest) {
		userDao.update(userName, userRequest.getUser());
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

}
