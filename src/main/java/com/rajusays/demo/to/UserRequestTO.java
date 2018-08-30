package com.rajusays.demo.to;

import java.io.Serializable;

public class UserRequestTO implements Serializable {

	private static final long serialVersionUID = -1583359182592154435L;
	private UserTO user;

	public UserTO getUser() {
		return user;
	}

	public void setUser(UserTO user) {
		this.user = user;
	}

}
