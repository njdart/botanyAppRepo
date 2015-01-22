import java.sql.Time;
import java.util.Date;


public class visit {
	
	
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
    public void addSpecimen(Specimen s);
    
}
