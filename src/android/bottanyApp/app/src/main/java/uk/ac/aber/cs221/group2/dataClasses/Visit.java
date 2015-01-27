package uk.ac.aber.cs221.group2.dataClasses;

import java.util.Calendar;

public class Visit {

    public static Visit CurrentVisit;

    private String visitName;
	private double visitDate;
	private String visitOS;
    private int id;
    private String description;

    public Visit(String name, String OSGridRef){
        visitName = name;
        visitOS = OSGridRef;
        visitDate = (double)Calendar.getInstance().get(Calendar.SECOND);
    }

    public String getVisitOS() {
        return visitOS;
    }

    public void setVisitOS(String visitOS) {
        this.visitOS = visitOS;
    }


    public String getVisitName() {
        return visitName;
    }

    public void setVisitName(String visitName) {
        this.visitName = visitName;
    }

	/**
	 * set the visit date
	 */
	public void setVisitDate(Double a){
		visitDate = a;
	}

	 /**
     * Returns the visit Date when the user logged in
     * https://docs.oracle.com/javase/7/docs/api/java/sql/Date.html
     * Fulfils requirement FR2
     */
    public double getVisitDate() {
		return visitDate;
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

    public int getVisitId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
