package uk.ac.aber.cs221.group2.dataClasses;

public class User {

	private String foreName;
	private String lastName;
	private String phoneNumber;
	private String email;

	/**
	 * sets the users forename
	 */
	public void setForeName(String a){
		foreName = a;
	}
	
	/**
	 * sets the users lastname
	 */
	public void setLastName(String a){
		lastName = a;
	}
	
	/**
	 * sets the users phonenumber
	 */
	public void setPhoneNumber(String a){
		phoneNumber = a;
	}
	
	/**
	 * sets the users email
	 */
	public void setEmail(String a){
		email = a;
	}


	 /**
     * Returns the users first name
     */
    public String getUserForeName() {
		return foreName;
	}

    /**
     * Returns the users last name
     */
    public String getUserLastName() {
		return lastName;
	}

    /**
     * Returns the users phone number
     * Formatted accordingly (Eg 07...)
     */
    public String getUserPhoneNumber() {
		return phoneNumber;
	}

    /**
     * Returns the users email
     */
    public String getUserEmail() {
		return email;
	}
}
