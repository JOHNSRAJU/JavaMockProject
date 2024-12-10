package model;

import java.time.LocalDate;
import java.util.Date;


import controller.Controller;

public class Patient{
	private int id;
	private String name;
	private LocalDate dob;
	private double weight;
	private double height;
	private double bmi;
	private String description;
	private Status status;
	public Patient(int id, String name, LocalDate dob, double weight, double height, String description) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.weight = weight;
		this.height = height;
		this.description = description;
		this.bmi = Controller.calculateBMI(weight, height);
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
				+ "id=" + id + "\' name=" + name + "\' dob=" + dob + "\' weight=" + weight + "\' height=" + height
				+ "\' description=" + description + "\'}";
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
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
	
}
