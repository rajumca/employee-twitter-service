package com.rajusays.demo.to;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class UserTO implements Serializable{

	private static final long serialVersionUID = 80372818155026852L;
	private String userName;
	private String name;
	private LocalDate dateOfBirth;
	private String city;
	private String state;
	private List<String> followers;
	private List<String> followees;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<String> getFollowers() {
		return followers;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

	public List<String> getFollowees() {
		return followees;
	}

	public void setFollowees(List<String> followees) {
		this.followees = followees;
	}



}
