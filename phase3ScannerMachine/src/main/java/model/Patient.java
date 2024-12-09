package model;

import java.util.Objects;

public class Patient{
	private int id;
	private String name;
	private int age;
	private double weight;
	private double height;
	private double bmi;
	private String description;
	private Status status;
	public Patient(int id, String name, int age, double weight, double height, String description) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.description = description;
		this.bmi = calculateBMI(weight, height);
		this.status = Status.NEW;
	}
	public Patient() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getWeight() {
		return weight;
	}

	public double getHeight() {
		return height;
	}

	public String getDescription() {
		return description;
	}
	

	@Override
	public String toString() {
		return "Patient \'{"
				+ "id=" + id + "\' name=" + name + "\' age=" + age + "\' weight=" + weight + "\' height=" + height
				+ "\' description=" + description + "\'}";
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
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
				&& Objects.equals(name, other.name) && status == other.status
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
	}
	private static double calculateBMI(double weight,double height) {
		if(height<0) {
			throw new IllegalArgumentException("Height should greater than Zero");
		}
		else if(weight<0) {
			throw new IllegalArgumentException("Weight should greater than Zero");
		}
		return Math.round((weight*10000)/(height*height));
	}
	
}
