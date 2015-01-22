package uk.ac.aber.cs221.group2.dataClasses;

public class Visit {

    private String visitName;
	private Double visitDate;
	private String visitOS;

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
    public Double getVisitDate() {
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

}
