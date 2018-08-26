package com.rajusays.demo.to;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TweetTO implements Serializable{


	private static final long serialVersionUID = 108508451215501787L;
	private String id;
	private String message;
	private LocalDateTime time;
	private UserTO user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public UserTO getUser() {
		return user;
	}

	public void setUser(UserTO user) {
		this.user = user;
	}

	

}
