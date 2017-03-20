package handler.service;

import java.io.Serializable;

public class People implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String location;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	
}
