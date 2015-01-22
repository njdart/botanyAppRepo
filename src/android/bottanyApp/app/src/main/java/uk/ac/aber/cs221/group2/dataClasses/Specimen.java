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

	
	private String Latitude;
	private String Longitude;
	private AbundanceEnum Abundance;
	private String Comment;
	private String ScenePhotoURI;
	private String SpecimenPhotoURI;
	
	
	/**
	 * Set the approximate Specimen latitude
	 */
	public void setLatitude(String a){
		Latitude = a;
	}
	
	/**
	 * Set the approximate Specimen longitude
	 */
	public void setLongitude(String a){
		Longitude = a;
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
		Comment = a;
	}
	
	/**
	 * Set the scene photo URI
	 */
	public void setScenePhotoURI(String a){
		ScenePhotoURI = a;
	}
	
	/**
	 * Set the Specimen photo URI
	 */
	public void setSpecimenPhotoURI(String a){
		SpecimenPhotoURI = a;
	}
	
	/**
	 * Gets the approximate Specimen longitude
	 */
	public String getLatitude() {
		return Latitude;
	}

	/**
	 * Gets the approximate Specimen latitude
	 */
	public String getLongitude() {
		return Longitude;
	}

	/**
	 * Gets the abundance rating
	 */
	public AbundanceEnum getAbundance() {
		return Abundance;
	}

	/**
	 * returns the free text comment made by the user
	 */
	public String getComment() {
		return Comment;
	}

	/**
	 * returns a URI to the scene photo if one is provided
	 */
	public String getScenePhotoURI() {
		return ScenePhotoURI;
	}

	/**
	 * returns a URI to the Specimen photo if one is provided
	 */
	public String getSpecimenPhotoURI() {
		return SpecimenPhotoURI;
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
