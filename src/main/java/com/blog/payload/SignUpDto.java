package com.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignUpDto {
	@NotEmpty
    @Size(min = 2,message = "name should start with atleast 2 character")
	private String name;
	@NotEmpty
	@Size(min =2,message = "username should start with 2 latters")
	private String username;
	@Email(message = "manadarity to used an the @gmail.com")
	private String email;
	@NotEmpty
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
