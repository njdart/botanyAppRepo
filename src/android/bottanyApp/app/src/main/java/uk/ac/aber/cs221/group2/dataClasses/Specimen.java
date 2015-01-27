package uk.ac.aber.cs221.group2.dataClasses;

public class Specimen {
    public Specimen(String name, Double latitude, Double longitude, AbundanceEnum abundance, String comment, String scenePhotoURI, String specimenPhotoURI, int visitId, int userId) {
        System.out.println("Making specimen with user id " + userId + " And visit id " + visitId);
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.abundance = abundance;
        this.comment = comment;
        this.scenePhotoURI = scenePhotoURI;
        this.specimenPhotoURI = specimenPhotoURI;
        this.userId = userId;
        this.visitId = visitId;
    }

    /**
	 * Abundance scale
	 * Fulfills FR4
	 */
	
	
	public enum AbundanceEnum {
        DOMINANT ,
        ABUNDANT ,
        FREQUENT,
        OCCASIONAL ,
        RARE;

    }

    private String name;
	private Double latitude;
	private Double longitude;
	private AbundanceEnum abundance;
	private String comment;
	private String scenePhotoURI;
	private String specimenPhotoURI;
    private int visitId;
    private int userId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    private Visit visit;
    private User user;


    /**
     * Gets the name of the userId
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the userId
     */
    public void setName(String name) {
        this.name = name;
    }

	/**
	 * Set the approximate Specimen latitude
	 */
	public void setLatitude(Double a){
		latitude = a;
	}
	
	/**
	 * Set the approximate Specimen longitude
	 */
	public void setLongitude(Double a){
		longitude = a;
	}
	
	/**
	 * Set the approximate Specimen Abundance
	 */
	public void setAbundance(AbundanceEnum a){
		abundance = a;
	}
	
	/**
	 * Set the comment
	 */
	public void setComment(String a){
		comment = a;
	}
	
	/**
	 * Set the scene photo URI
	 */
	public void setScenePhotoURI(String a){
		scenePhotoURI = a;
	}
	
	/**
	 * Set the Specimen photo URI
	 */
	public void setSpecimenPhotoURI(String a){
		specimenPhotoURI = a;
	}
	
	/**
	 * Gets the approximate Specimen longitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * Gets the approximate Specimen latitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * Gets the abundance rating
	 */
	public AbundanceEnum getAbundance() {
		return abundance;
	}

	/**
	 * returns the free text comment made by the userId
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * returns a URI to the scene photo if one is provided
	 */
	public String getScenePhotoURI() {
		return scenePhotoURI;
	}

	/**
	 * returns a URI to the Specimen photo if one is provided
	 */
	public String getSpecimenPhotoURI() {
		return specimenPhotoURI;
	}

    public int getUserId(){
        return userId;
    }

    public int getVisitId(){
        return visitId;
    }

	/**
	 * takes a Specimen photo.
	 * Opens up the camera and allows the userId to take a photo rather than
	 * asking for the location of one
	 */
	public void takeSpecimenPhoto(){}

	/**
	 * takes a Specimen photo
	 * Opens up the camera and allows the userId to take a photo rather than
	 * asking for the location of one
	 */
	public void takeScenePhoto(){}
}
