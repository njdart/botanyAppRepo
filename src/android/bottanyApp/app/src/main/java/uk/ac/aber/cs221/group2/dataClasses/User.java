package uk.ac.aber.cs221.group2.dataClasses;


public class User {

    private String name;
    private String phoneNumber;
    private String email;
    private String siteLocation;

    //Possible use?
    private double lat;
    private double lng;

    public User(String name,
                String phone,
                String email,
                String siteLoc){
        this.name = name;
        this.phoneNumber = phone;
        this.email = email;
        this.siteLocation = siteLoc;
    }
}
