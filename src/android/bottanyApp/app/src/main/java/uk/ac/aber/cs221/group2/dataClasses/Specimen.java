package uk.ac.aber.cs221.group2.dataClasses;

public class Specimen {
	/**
	 * Abundance scale
	 * Fulfills FR4
	 */
	
	
	
	
	public enum AbundanceEnum {
		DOMINANT,
		ABUNDANT,
		FREQUENT,
		OCCASIONAL,
		RARE,
	}

	
	private String latitude;
	private String longitude;
	private AbundanceEnum abundance;
	private String comment;
	private String scenePhotoURI;
	private String specimenPhotoURI;
	
	
	/**
	 * Set the approximate Specimen latitude
	 */
	public void setLatitude(String a){
		latitude = a;
	}
	
	/**
	 * Set the approximate Specimen longitude
	 */
	public void setLongitude(String a){
		longitude = a;
	}
	
	/**
	 * Set the approximate Specimen Abundance
	 */
	public void setAbundance(String a){
		/*Abundance = a;*/
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
	public String getLatitude() {
		return latitude;
	}

	/**
	 * Gets the approximate Specimen latitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * Gets the abundance rating
	 */
	public AbundanceEnum getAbundance() {
		return abundance;
	}

	/**
	 * returns the free text comment made by the user
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

	/**
	 * takes a Specimen photo.
	 * Opens up the camera and allows the user to take a photo rather than
	 * asking for the location of one
	 */
	public void takeSpecimenPhoto(){}

	/**
	 * takes a Specimen photo
	 * Opens up the camera and allows the user to take a photo rather than
	 * asking for the location of one
	 */
	public void takeScenePhoto(){}
}
