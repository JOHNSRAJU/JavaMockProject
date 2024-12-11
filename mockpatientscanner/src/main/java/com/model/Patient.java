package com.model;

import java.util.Objects;

public class Patient {
	
	private int id;
	private String name;
	private int age;
	private double weight;
	private double height;
	private String description;
	private String status;
	private double bmi;
	
	public Patient(int id, String name,int age, double weight, double height, String description, String status) {
		super();
		this.id = id;
		this.name = name;
		this.age=age;
		this.weight = weight;
		this.height = height;
		this.description = description;
		this.status = status;
		this.bmi = calculateBMI(weight,height);
	}
	
	
	public Patient()
	{
		// TODO Auto-generated constrtor stub
		super();
	}
	
	private static double calculateBMI(double weight2, double height2) 
	{
		// TODO Auto-generated method stub
		if(height2 < 0)
		{
			System.out.println("height cant be less than zero");
		}
		else if(weight2<0)
		{
			System.out.println("weight cant be less than zero");
		}
		
			return (weight2*10000)/(height2*height2);
		
	}

	
	
	
	public double getBmi() {
		return bmi;
	}


	public void setBmi(double bmi) {
		this.bmi = bmi;
	}


	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", weight=" + weight + ", height=" + height
				+ ", description=" + description + ", status=" + status + ", bmi=" + bmi + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(age, bmi, description, height, id, name, status, weight);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return age == other.age && Double.doubleToLongBits(bmi) == Double.doubleToLongBits(other.bmi)
				&& Objects.equals(description, other.description)
				&& Double.doubleToLongBits(height) == Double.doubleToLongBits(other.height) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(status, other.status)
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
	}
	
	

	

}
