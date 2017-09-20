package model;

import java.sql.ResultSet;

public class Admin {
	private long id;
	private String identity;
	private String password;
	private ResultSet info;
	
	public ResultSet getInfo() {
		return info;
	}
	public void setInfo(ResultSet info) {
		this.info = info;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
