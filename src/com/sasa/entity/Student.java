package com.sasa.entity;

public class Student {
	private String name;
	private String age;
	private String sex;
	private String home;

	@Override
	public String toString() {
		String mes = ("姓名：" + this.name + "，年龄：" + this.age + "，性别：" + this.sex
				+ "，家乡：" + this.home + "；");
		return mes;
	}

	public Student() {

	}

	public Student(String name, String age, String sex, String home) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.home = home;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}
}
