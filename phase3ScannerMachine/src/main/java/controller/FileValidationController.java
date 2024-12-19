package controller;

import java.io.File;

public class FileValidationController {
	//importing
	public static boolean isValidJsonFile(File file) {
		return file != null &&file.getName().toLowerCase().endsWith(".json");
		
	}
	//export
	public static File exportValidation(File file) {
		if(file != null && !file.getName().toLowerCase().endsWith(".json")) {
			return new File(file.getAbsoluteFile()+".json");
		}
		return file;
	}

}
