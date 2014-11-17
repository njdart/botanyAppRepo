public class Specimen{

	/**
	 * Abundance scale
	 * Fulfills FR4
	 */
	public Enum AbundanceEnum {
		DOMINANT,
		ABUNDANT,
		FREQUENT,
		OCCASIONAL,
		RARE,
	}

	/**
	 * Gets the approximate specimen longitude
	 */
	public String getLatitude();

	/**
	 * Gets the approximate specimen latitude
	 */
	public String getLongitude();

	/**
	 * Gets the abundance rating
	 */
	public AbundanceEnum getAbundance();

	/**
	 * returns the free text comment made by the user
	 */
	public String getComment();

	/**
	 * returns a URI to the scene photo if one is provided
	 */
	public String getScenePhotoURI();

	/**
	 * returns a URI to the specimen photo if one is provided
	 */
	public String getSpecimenPhotoURI();

	/**
	 * takes a specimen photo.
	 * Opens up the camera and allows the user to take a photo rather than
	 * asking for the location of one
	 */
	public void takeSpecimenPhoto();

	/**
	 * takes a specimen photo
	 * Opens up the camera and allows the user to take a photo rather than
	 * asking for the location of one
	 */
	public void takeScenePhoto();
}