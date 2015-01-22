<<<<<<< HEAD:src/android/bottanyApp/app/src/main/java/uk/ac/aber/cs221/group2/dataClasses/visit.java
import java.sql.Time;
import java.util.Date;


public class Visit {
	
	
	private Date VisitDate;
	private Time VisitTime;
    private String VisitName;

    public String getVisitName() {
        return VisitName;
    }

    public void setVisitName(String visitName) {

        VisitName = visitName;
    }
	
	
	
	/**
	 * set the visit date
	 */
	public void setVisitDate(Date a){
		VisitDate = a;
	}
	
	/**
	 * set the visit time
	 */
	public void setVisitTime(Time a){
		VisitTime = a;
	}
	
	 /**
     * Returns the visit Date when the user logged in
     * https://docs.oracle.com/javase/7/docs/api/java/sql/Date.html
     * Fulfils requirement FR2
     */
    public Date getVisitDate() {
		return VisitDate;
	}


    /**
     * Returns the visit Time when the user logged in
     * https://docs.oracle.com/javase/7/docs/api/java/sql/Time.html
     * Fulfils requirement FR2
     */
    public Time getVisitTime() {
		return VisitTime;
	}


    /**
     * Returns a list of all specimens entered so far
     * Filfils FR3
     */
    public Specimen[] getSpecimen() {
		return null;
		/*to be implimented later*/
	}

    /**
     * Adds a specimens to the visit
     * Fulfils FR3
     */
    public void addSpecimen(Specimen s);
    
}
=======
package uk.ac.aber.cs221.group2.dataClasses;

import java.sql.Time;
import java.util.Date;

public class Visit {
	
	
	private Date VisitDate;
	private Time VisitTime;
	
	
	
	/**
	 * set the visit date
	 */
	public void setVisitDate(Date a){
		VisitDate = a;
	}
	
	/**
	 * set the visit time
	 */
	public void setVisitTime(Time a){
		VisitTime = a;
	}
	
	 /**
     * Returns the visit Date when the user logged in
     * https://docs.oracle.com/javase/7/docs/api/java/sql/Date.html
     * Fulfils requirement FR2
     */
    public Date getVisitDate() {
		return VisitDate;
	}


    /**
     * Returns the visit Time when the user logged in
     * https://docs.oracle.com/javase/7/docs/api/java/sql/Time.html
     * Fulfils requirement FR2
     */
    public Time getVisitTime() {
		return VisitTime;
	}


    /**
     * Returns a list of all specimens entered so far
     * Filfils FR3
     */
    public Specimen[] getSpecimen() {
		return null;
		/*to be implimented later*/
	}

    /**
     * Adds a specimens to the visit
     * Fulfils FR3
     */
    public void addSpecimen(Specimen s){}
    
}
>>>>>>> origin/master:src/android/bottanyApp/app/src/main/java/uk/ac/aber/cs221/group2/dataClasses/Visit.java
