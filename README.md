# course-registration-system
A class project from Data Structure Course

### Basic Description 
It is a simple class registration system developed with java object oriented programming method. 

#### Account Types
The system allows two kinds of user: **Admin** and **Student**. The admin accounts are created in the **MainSystem** file, and only admins have the ability to add new students to the system. (for the convenience of testing, I also hardcoded some student accounts in the **MainSystem** file. 

#### Possible Actions
The Admin accounts are allowed to do those actions below:
- Create a new course
- Delete a course
- Edit a course
- Display information for a given course (by course ID)
- Register a student
- View all courses
- View all courses that are Full
- Write to a file the list of courses that are Full
- View the names of the students being registered in a specific course
- View the list of courses that a given student is being registered on
- Sort (from the course with the most student to least)
- Logout

The Student accounts are allowed to do those actions below:
- View all courses that are available
- View all courses that are NOT Full
- Register on a course
- Withdraw from a course
- View all courses you have registered in
- Logout

#### Data Management
When the system is started for the first time, it will input all the course information from the **MyUniversityCourses.csv** file. When an account logs out from the system, which will terminate the program at the current version, all the modified data will be serialized and stored locally in **Course.ser** and **Student.ser**. It means that all the updates and changes will be saved automatically. Therefore, when the system is restarted next time, all the data can be deserialized and used in the system.



### Download Tips
If you want to download the project to try or mess around with it, here are some tips:
- Make sure the **MyUniversityCourses.csv** file is under the same directory as the **Project** folder.
- **MainSystem** contains the main method. 


