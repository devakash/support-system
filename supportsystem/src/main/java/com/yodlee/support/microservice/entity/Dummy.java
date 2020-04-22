package com.yodlee.support.microservice.entity;

import java.io.Serializable;


public class Dummy implements Serializable {
	private static final long serialVersionUID = 1L;

	public String name;
	public String add;
	public Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString()
	{
		return "{ "+name+""+add+""+age+" }";
	}

}