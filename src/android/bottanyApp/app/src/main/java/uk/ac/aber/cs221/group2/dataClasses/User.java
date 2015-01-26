package uk.ac.aber.cs221.group2.dataClasses;

public class User {

    public static User CurrentUser;

    private String name;
	private String phoneNumber;
	private String email;

    public User(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
