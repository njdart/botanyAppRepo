public class Visit{

    /**
     * Returns the visit Date when the user logged in
     * https://docs.oracle.com/javase/7/docs/api/java/sql/Date.html
     * Fulfils requirement FR2
     */
    public Date getVisitDate();


    /**
     * Returns the visit Time when the user logged in
     * https://docs.oracle.com/javase/7/docs/api/java/sql/Time.html
     * Fulfils requirement FR2
     */
    public Time getVisitTime();


    /**
     * Returns a list of all specimens entered so far
     * Filfils FR3
     */
    public Specimen[] getSpecimen();

    /**
     * Adds a specimens to the visit
     * Fulfils FR3
     */
    public void addSpecimen(Specimen s);

}