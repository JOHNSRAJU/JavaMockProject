package controller;

import model.Database;
import model.Patient;
public class Controller {
		private Database db;
	public Controller() {
		db = new Database();
	}
	public Database getDb() {
		return db;
	}
}
