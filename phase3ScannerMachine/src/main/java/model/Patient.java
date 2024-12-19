package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import controller.AddEditPatientController;
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
		this.bmi = AddEditPatientController.calculateBMI(weight, height);
		this.status = Status.NEW;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bmi, description, dob, height, id, name, status, weight);
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
		return Double.doubleToLongBits(bmi) == Double.doubleToLongBits(other.bmi)
				&& Objects.equals(description, other.description) && Objects.equals(dob, other.dob)
				&& Double.doubleToLongBits(height) == Double.doubleToLongBits(other.height) && id == other.id
				&& Objects.equals(name, other.name) && status == other.status
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
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
