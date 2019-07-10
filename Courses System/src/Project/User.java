package Project;

public class User implements java.io.Serializable{
	//initial all the variable
	private String username, password, fName, lName;
	
	//Default Constructor
	public User() {
		username = password = fName = lName = "";
	}
	
	//Constructor
	public User(String un, String pw, String fn, String ln) {
		username = un;
		password = pw;
		fName = fn;
		lName = ln;
	}
	
	//getter and setter
	public String getFN() {
		return fName;
	}
	
	public String getLN() {
		return lName;
	}
	
	//check if input user name and password match the account info
	public boolean login(String inUN, String inPW) {
		if (username.equals(inUN) && password.equals(inPW))
			return true;
		else
			return false;
	}
	
	//check if input first name and last name match the account info
	public boolean nameCheck(String inFN, String inLN) {
		if (fName.equals(inFN) && lName.equals(inLN))
			return true;
		else
			return false;
	}
}
