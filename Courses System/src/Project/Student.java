package Project;

import java.util.*;

public class Student extends User implements Function, java.io.Serializable {
	//initialize variable
	private ArrayList<Course> regCourse = new ArrayList<Course>();
	
	//default constructor
	public Student() {
		super();
	}
	
	//constructor for new student
	public Student(String un, String pw, String fn, String ln) {
		super(un, pw, fn, ln);
	}
	
	//getter and setter
	public ArrayList<Course> getRC() {
		return regCourse;
	}
	
	public void SetRC(ArrayList<Course> rc) {
		regCourse = rc;
	}
	
	//Print all courses
	public void printAllCourse(ArrayList<Course> AC) {
		for(Course c:AC)
			System.out.println(c);
	}
	
	//Print all courses that are not full
	public void printMatchCourse(ArrayList<Course> AC) {
		for(Course c:AC) {
			if (!c.ifFull())
				System.out.println(c);
		}
	}
	
	//Register on a course
	public void RegCourse(Course c) {
		regCourse.add(c);
		c.setCNS(c.getCNS()+1);
		ArrayList<String> curRegStudent = c.getCRS();
		curRegStudent.add(getFN() + " " + getLN());
		c.setCRS(curRegStudent);
	}
	
	//Withdraw from a course
	public void witCourse(Course c) {
		regCourse.remove(c);
		c.setCNS(c.getCNS()-1);
		ArrayList<String> curRegStudent = c.getCRS();
		curRegStudent.remove(getFN() + " " + getLN());
		c.setCRS(curRegStudent);
	}
}
