package com.yangcy.ehcache.bean;

public class Dog {
	private short age;
	private String name;
	private float weight;
	
	
	public Dog(short age, String name, float weight) {
		super();
		this.age = age;
		this.name = name;
		this.weight = weight;
	}
	public short getAge() {
		return age;
	}
	public void setAge(short age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	
}
