package com.javatpoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	@Id @GeneratedValue
	@Column(name="userid")
	private int userid;
	
	/*public User(int userid, String userName, String location) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.location = location;
	}*/
	@Column(name="userName")
	private String userName;
	
	@Column(name="location")
	private String location;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public User() {
		super();
	}
	
	

}
