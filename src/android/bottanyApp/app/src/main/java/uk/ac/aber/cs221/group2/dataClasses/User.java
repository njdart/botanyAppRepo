package uk.ac.aber.cs221.group2.dataClasses;

public class User {

	private String ForeName;
	private String LastName;
	private String PhoneNumber;
	private String Email;

	/**
	 * sets the users forename
	 */
	public void setForeName(String a){
		ForeName = a;
	}
	
	/**
	 * sets the users lastname
	 */
	public void setLastName(String a){
		LastName = a;
	}
	
	/**
	 * sets the users phonenumber
	 */
	public void setPhoneNumber(String a){
		LastName = a;
	}
	
	/**
	 * sets the users email
	 */
	public void setEmail(String a){
		LastName = a;
	}


	 /**
     * Returns the users first name
     */
    public String getUserForeName() {
		return ForeName;
	}

    /**
     * Returns the users last name
     */
    public String getUserLastName() {
		return LastName;
	}

    /**
     * Returns the users phone number
     * Formatted accordingly (Eg 07...)
     */
    public String getUserPhoneNumber() {
		return PhoneNumber;
	}

    /**
     * Returns the users email
     */
    public String getUserEmail() {
		return Email;
	}
}
