package Project;

import java.util.*;
import java.io.*;

public class Admin extends User implements Function {
	//default constructor
	public Admin() {
		super();
	}
	
	//constructor
	public Admin(String un, String pw, String fn, String ln) {
		super(un, pw, fn, ln);
	}
	
	//Print all courses
	public void printAllCourse(ArrayList<Course> AC) {
		for(Course c:AC)
			System.out.println(c);
	}
		
	//Print all courses that are full
	public void printMatchCourse(ArrayList<Course> AC) {
		for(Course c:AC) {
			if (c.ifFull())
				System.out.println(c);
		}
	}
	
	//Write a file for the list of courses that are Full
	public void writeFullCourse(ArrayList<Course> AC) {
		String fileName = "CoursesFull.txt";
		
		try{
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fileWriter);
		    
			for(Course c:AC) {
				if (c.ifFull()) {
					bw.write(c.toString());
					bw.newLine();
				}
			}
			
			bw.close();
		}

		catch (IOException exk) {
			System.out.println( "Error writing file '" + fileName + "'");
			exk.printStackTrace();
		}
	}
	
	//Print names of students in a course
	public void printStudentInCourse(ArrayList<Course> cList, String cID) {
		boolean found = false;
		Course selectC = new Course();
		for(Course c:cList) {
			if (cID.equals(c.getCID())) {
				found = true;
				selectC = c;
			}
		}	
		if (found) {
			for(String rsName:selectC.getCRS())
				System.out.println(rsName);
		} else
			System.out.println("Cannot find the course.");
	}
	
	//Print the list of courses a given student is being registered on
	public void printAllCoursesOfStudent(ArrayList<Student> sList, String fName, String lName) {
		boolean found = false;
		Student selectS = new Student();
		for(Student s:sList) {
			if (fName.equals(s.getFN()) && lName.equals(s.getLN())) {
				found = true;
				selectS = s;
			}
		}
		if (found) {
			for(Course cInfo:selectS.getRC())
				System.out.println(cInfo);
		} else
			System.out.println("Cannot find the Student.");
	}
	
	//sort
	public void sortCourse(ArrayList<Course> cList) {
		Collections.sort(cList);
	}
}
