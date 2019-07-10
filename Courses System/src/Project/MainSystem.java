package Project;

import java.util.*;
import java.io.*;

public class MainSystem {
	public static void main(String args[]) {
		//ArrayList of courses and student, and one admin object
		ArrayList<Course> courseList = new ArrayList<Course>();
		ArrayList<Student> studentList = new ArrayList<Student>();
		Admin ad = new Admin("Admin", "Admin001", "Advisor", "Advisor");
		
		
		//Loading files....
		File courseFile = new File("Course.ser");
		File studentFile = new File("Student.ser");
		
		if (courseFile.exists() && studentFile.exists()) {
			
			try {
				//deserialize courses objects
			    FileInputStream cfis = new FileInputStream("Course.ser");
			    ObjectInputStream cois = new ObjectInputStream(cfis);
			      
			    courseList = (ArrayList<Course>)cois.readObject();
			      
			    cois.close();
			    cfis.close();
			      
			    FileInputStream sfis = new FileInputStream("Student.ser");
			    ObjectInputStream sois = new ObjectInputStream(sfis);
			      
			    studentList = (ArrayList<Student>)sois.readObject();
			      
			    sois.close();
			    sfis.close();
			}
			catch(IOException ioe) {
			    ioe.printStackTrace();
			    return;
			}
			catch(ClassNotFoundException cnfe) {
			    cnfe.printStackTrace();
			    return;
			}
			
		} else {
			//read MyUniversityCourses.csv file and create courses
			
			String fileName = "MyUniversityCourses.csv";
			String line = null;
			try{
				FileReader fileReader = new FileReader(fileName);
				BufferedReader br = new BufferedReader(fileReader);
				
				String[] v;
				Course c;
				br.readLine(); //Skip the first line which is the category name 
				
				while((line = br.readLine()) != null) {
					v = line.split(",");
					c = new Course(v[0], v[1], Integer.parseInt(v[2]), Integer.parseInt(v[3]), 
									v[5], Integer.parseInt(v[6]), v[7]);
					courseList.add(c);
				}

				br.close();
			}

			catch(FileNotFoundException ex){
				System.out.println( "Unable to open file '" + fileName + "'");
				ex.printStackTrace();
			}

			catch (IOException ex) {
				System.out.println( "Error reading file '" + fileName + "'");
				ex.printStackTrace();
			}
			
			//add default student, which can be changed.
			Student s1 = new Student("Student", "Student001", "s1", "s1");
			Student s2 = new Student("Student", "Student001", "s2", "s2");
			Student s3 = new Student("Student", "Student001", "s3", "s3");
			Student s4 = new Student("Student", "Student001", "s4", "s4");
			Student s5 = new Student("Student", "Student001", "s5", "s5");
			studentList.add(s1);
			studentList.add(s2);
			studentList.add(s3);
			studentList.add(s4);
			studentList.add(s5);
		}
		
		//test
		/*for (Student s:studentList) {
			System.out.println(s.getFN() + " " + s.getLN());
		}
		*/
		
		//program begin
		Scanner scan = new Scanner(System.in);
		System.out.println("**Course Registration System**\n");
		
		//User login
		boolean foundUser = false;
		int userType = 0;
		String userIn1 = "";
		String userIn2 = "";
		Admin currentAdmin = new Admin();
		Student currentStu = new Student();
		
		while(!foundUser) {
			System.out.println("Please enter your username: ");
			userIn1 = scan.nextLine();
			System.out.println("Please enter your password: ");
			userIn2 = scan.nextLine();
			//check if the user is admin
			foundUser = ad.login(userIn1, userIn2);
			if (foundUser) {
				currentAdmin = ad;
				userType = 1;
			} 
				
			//check if the user is student
			ArrayList<Student> sameUNPW = new ArrayList<Student>();
			if (!foundUser) {
				for (Student s:studentList) {
					foundUser = s.login(userIn1, userIn2);
					if (foundUser) {
						sameUNPW.add(s);
						userType = 2;
					}		
				}
			}
			
			//find the right student among those who have the same username and password
			if (foundUser == true && userType == 2) {
				System.out.println("Please enter your first name: ");
				String userIn3 = scan.nextLine();
				System.out.println("Please enter your last name: ");
				String userIn4 = scan.nextLine();
				for (Student s:sameUNPW) {
					if (userIn3.equals(s.getFN()) && userIn4.equals(s.getLN())) {
						currentStu = s;
						foundUser = true;
						break;
					} else {
						foundUser = false;
					}
				}
			}
		}
		
		
		//admin section
		boolean logout = false;
		int selection = 0;
		if (userType == 1) {
			while (!logout) {
				System.out.println("Courses Management:");
				System.out.println("1.Create a new course\n"
						+ "2.Delete a course\n"
						+ "3.Edit a course\n"
						+ "4.Display information for a given course (by course ID)\n"
						+ "5.Register a student");
				System.out.println("Reports:");
				System.out.println("6.View all courses\n"
						+ "7.View all courses that are Full\n"
						+ "8.Write to a file the list of courses that are Full\n"
						+ "9.View the names of the students being registered in a specific course\n"
						+ "10.View the list of courses that a given student is being registered on\n"
						+ "11.Sort (from course with most student to least)\n"
						+ "12.Logout");
				System.out.println("Enter your Selection: ");
				selection = Integer.parseInt(scan.nextLine());
				if (selection == 1) {
					System.out.println("Creat a New Course");
					System.out.println("Course Name:");
					String cn = scan.nextLine();
					System.out.println("Course ID:");
					String cid = scan.nextLine();
					System.out.println("Maximum Number of Students:");
					int mns = Integer.parseInt(scan.nextLine());
					System.out.println("Course Instructor:");
					String ci = scan.nextLine();
					System.out.println("Section Number:");
					int sn = Integer.parseInt(scan.nextLine());
					System.out.println("Course Location:");
					String cl = scan.nextLine();
					courseList.add(new Course(cn, cid, mns, 0, ci, sn, cl));
					System.out.println("New course has been added.");
				} else if (selection == 2) {
					System.out.println("Delete a Course");
					Course foundC = null;
					System.out.println("Enter the course ID of the course that you want to delete:");
					String dcid = scan.nextLine();
					foundC = findCourse(dcid, courseList);
					if (foundC == null) {
						System.out.println("Fail to find the course.");
					} else {
						courseList.remove(foundC);
						System.out.println("Selected course has been removed.");
					}
				} else if (selection == 3) {
					System.out.println("Edit a course");
					System.out.println("Enter the course ID of the course that you want to edit:");
					String ecid = scan.nextLine();
					Course editC = findCourse(ecid, courseList);
					if (editC == null) {
						System.out.println("Fail to find the course.");
					} else {
						System.out.println("1.Course Name\n" 
								+ "2.Course ID\n" 
								+ "3.Instructor Name\n"
								+ "Enter a element you want to change:");
						int selection2 = Integer.parseInt(scan.nextLine());
						if (selection2 == 1) {
							System.out.println("Enter new course name: ");
							String ncn = scan.nextLine();
							editC.setCN(ncn);
							System.out.println("Selected course name has been changed");
						} else if (selection2 == 2) {
							System.out.println("Enter new course ID: ");
							String ncid = scan.nextLine();
							editC.setCID(ncid);
							System.out.println("Selected course ID has been changed");
						} else if (selection2 == 3) {
							System.out.println("Enter new course instructor: ");
							String nci = scan.nextLine();
							editC.setIN(nci);
							System.out.println("Selected course instructor has been changed");
						} else {
							System.out.println("Invaild Entry");
						}
					}
				} else if (selection == 4) {
					System.out.println("Display a course Information");
					Course foundC = null;
					System.out.println("Enter the course ID of the course that you want to display:");
					String disCID = scan.nextLine();
					foundC = findCourse(disCID, courseList);
					if (foundC == null) {
						System.out.println("Fail to find the course.");
					} else {
						System.out.println(foundC);
					}
				} else if (selection == 5) {
					System.out.println("Register a Student");
					System.out.println("Fisrt Name:");
					String fn = scan.nextLine();
					System.out.println("Last Name:");
					String ln = scan.nextLine();
					studentList.add(new Student("Student", "Student001", fn, ln));
					System.out.println("New Student has been added.");
				} else if (selection == 6) {
					System.out.println("View All Courses");
					currentAdmin.printAllCourse(courseList);
				} else if (selection == 7) {
					System.out.println("View All Courses That are FULL");
					currentAdmin.printMatchCourse(courseList);
				} else if (selection == 8) {
					System.out.println("Write File Contain Full Courses");
					currentAdmin.writeFullCourse(courseList);
				} else if (selection == 9) {
					System.out.println("Students of a Selected Course");
					System.out.println("Enter the course ID of the course that you want to display:");
					String sCID = scan.nextLine();
					currentAdmin.printStudentInCourse(courseList, sCID);
				} else if (selection == 10) {
					System.out.println("View Courses of a Student");
					System.out.println("Enter the first name of the student that you want to display: ");
					String sFN = scan.nextLine();
					System.out.println("Enter the last name of the student that you want to display: ");
					String sLN = scan.nextLine();
					ad.printAllCoursesOfStudent(studentList, sFN, sLN);
				} else if (selection == 11) {
					System.out.println("Sort Course (Most Student to Least");
					currentAdmin.sortCourse(courseList);
					System.out.println("Sort has been completed.");
				} else if (selection == 12){
					logout = true;
				} else {
					System.out.println("Invaild Entry.");
				}
			}
		}
		
		
		//Student section
		logout = false;
		selection = 0;
		if (userType == 2) {
			while (!logout) {
				System.out.println("Courses Management:");
				System.out.println("1.View all courses that are available\n"
						+ "2.View all courses that are NOT Full\n"
						+ "3.Register on a course\n"
						+ "4.Withdraw from a course\n"
						+ "5.View all courses you have registered in\n"
						+ "6.Logout");
				System.out.println("Enter your Selection: ");
				selection = Integer.parseInt(scan.nextLine());
				if (selection == 1) {
					System.out.println("View All Courses");
					(currentStu).printAllCourse(courseList);
				} else if (selection == 2) {
					System.out.println("View All Courses that are NOT FULL");
					(currentStu).printMatchCourse(courseList);
				} else if (selection == 3) {
					System.out.println("Register on a Course");
					System.out.println("Enter the course ID of the course that you want to register:");
					String rCID = scan.nextLine();
					Course rc = findCourse(rCID, courseList);
					if (rc == null) {
						System.out.println("Fail to find the course.");
					} else {
						currentStu.RegCourse(rc);
					    System.out.println("You have been register to the course.");
					}
				} else if (selection == 4) {
					System.out.println("Withdraw from a Course");
					System.out.println("Enter the course ID of the course that you want to withdraw:");
					String wCID = scan.nextLine();
					Course rc = findCourse(wCID, courseList);
					if (rc == null) {
						System.out.println("Fail to find the course.");
					} else {
						currentStu.witCourse(rc);
					    System.out.println("You have been removed from the course.");
					}
				} else if (selection == 5) {
					System.out.println("View all Courses You Have Registered");
					for (Course c:currentStu.getRC()) {
						System.out.println(c);
					}
				} else if (selection == 6) {
					logout = true;
				} else {
					System.out.println("Invaild Entry");
				}
			}
		}
		
		
		System.out.println("You have successfully logout.");
		
		//Serialization
		try {
			// serialize course
			FileOutputStream cfos = new FileOutputStream("Course.ser");
			ObjectOutputStream coos = new ObjectOutputStream(cfos);

			coos.writeObject(courseList);
			
			coos.close();
			cfos.close();
			
			//serialize student
			FileOutputStream sfos = new FileOutputStream("Student.ser");
			ObjectOutputStream soos = new ObjectOutputStream(sfos);

			soos.writeObject(studentList);
			
			soos.close();
			sfos.close();
			
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	//finding a course with course id
	public static Course findCourse(String cid, ArrayList<Course> cList) {
		Course fc = null;
		for (Course c : cList) {
			if (cid.equals(c.getCID())) {
				fc = c;
			}
		}	
		return fc;
	}
}
