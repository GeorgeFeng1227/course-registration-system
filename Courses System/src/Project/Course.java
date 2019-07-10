package Project;

import java.util.*;

public class Course implements java.io.Serializable, Comparable<Course>{
	//initial all the variable
	private String courseName;
	private String courseID;
	private int maxNumStu,curNumStu;
	private ArrayList<String> curRegStuName = new ArrayList<String>();
	private String insName;
	private int secNum;
	private String location;
	
	//Default Constructor
	public Course() {
		courseName = "";
		courseID = "";
		maxNumStu = curNumStu = 0;
		insName = "";
		secNum = 0;
		location = "";
	}

	//Constructor
	public Course(String cn, String cid, int mns, int cns, String in, int sn, String loc) {
		courseName = cn;
		courseID = cid;
		maxNumStu = mns; 
	    curNumStu = cns;
		insName = in;
		secNum = sn;
		location = loc;
	}
	
	//getters and setters
	
	public String getCN() {
		return courseName;
	}
	
	public void setCN(String cn) {
		courseName = cn;
	}
	
	public String getCID() {
		return courseID;
	}
	
	public void setCID(String cid) {
		courseID = cid;
	}
	
	public int getMNS() {
		return maxNumStu;
	}
	
	public void setMNS(int mns) {
		maxNumStu = mns;
	}
	
	public int getCNS() {
		return curNumStu;
	}
	
	public void setCNS(int cns) {
		curNumStu = cns;
	}
	
	public ArrayList<String> getCRS() {
		return curRegStuName;
	}
	
	public void setCRS(ArrayList<String> crs) {
		curRegStuName = crs;
	}
	
	public String getIN() {
		return insName;
	}
	
	public void setIN(String in) {
		insName = in;
	}
	
	public int getSN() {
		return secNum;
	}
	
	public void setSN(int sn) {
		secNum = sn;
	}
	
	public String getLOC() {
		return location;
	}
	
	public void setLOC(String loc) {
		location = loc;
	}
	
	// override compareTo method
	public int compareTo(Course c) {
		int compareCNS = ((Course) c).getCNS();
		return compareCNS - this.curNumStu;
	}
	
	//Check if the course is full
	public boolean ifFull() {
		if (curNumStu < maxNumStu)
			return false;
		else
			return true;
	}
	
	//Printing basic course info
	public String toString() {
		return "Course Name: " + courseName +"\nCourse ID: " + courseID + 
				"\nNumber of Students Registered: " + curNumStu + 
				"\nMaximum Number of Students: " + maxNumStu + "\n";
	}	
}
