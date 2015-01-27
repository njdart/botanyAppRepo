<?php 
	
	include 'config.php';		

	//Stops warnings
	error_reporting(E_ERROR);
	
	//Decodes the JSON into PHP readable
	$specimen = json_decode($_POST["specimen"]);
	//$specimenID = $_POST["specimenID"];

	//If data is wrong, error code 400
	if(!checkSpecimen($specimen))
	{
		http_response_code(400);
		return;
	}
	
	//Returns success code for successful data and connects to Database
	http_response_code(200);
	$conn = new mysqli($CONFIG['dbname'], $CONFIG['username'], $CONFIG['password']);
	
	//Checks Database connection and returns error code 500 to show server error, then end the script
	if($conn->connect_errno)
	{
		http_response_code(500);
		die('Could not connect: ' . $mysqli->connect_error);
	}
	
	//Pull JSON variables into PHP variables
	$SpecimenID = $conn->real_escape_string($specimen->SpecimenID);
	$SpeciesName = $conn->real_escape_string($specimen->SpeciesName);
	$Latitude = $conn->real_escape_string($specimen->LocationLatitude);
	$Longitude = $conn->real_escape_string($specimen->LocationLongitude);
	$Abundance = $conn->real_escape_string($specimen->Abundance);
	$Comment = $conn->real_escape_string($specimen->Comment);
	$ScenePhoto = $conn->real_escape_string($specimen->ScenePhoto);
	$SpecimenPhoto = $conn->real_escape_string($specimen->SpecimenPhoto);
	
	//Selects the Database
	$conn->select_db('msh4');
	
	//Begin transaction
	$conn->autocommit(FALSE);
	
	//Checks Latitude and Longitude are within real world values
	if($Latitude > 90 || $Latitude < -90 || $Longitude > 180 || $Longitude < -180)
	{
		http_response_code(400);
		die('Latitude or Longitude is out of bounds. Latitude must be between -90 and 90. Longitude must be between -180 and 180');
	}

	//Checks if any variables (except $Comment) are empty
	if( empty($SpecimenID) || empty($SpeciesName) || empty($Latitude) || empty($Longitude) || empty($Abundance))
	{
		http_response_code(400);
		die('A field is empty');
	}

	if(empty($ScenePhoto) && !empty($SpecimenPhoto))
	{
		$updateSpecimen = "UPDATE botany_specimens SET species_name='$SpeciesName', 
		latitude='$Latitude', longitude='$Longitude', abundance='$Abundance', 
		comment='$Comment', specimen_photo='$SpecimenPhoto' 
		WHERE specimen_id = $SpecimenID";
	}
	else if(empty($SpecimenPhoto) && !empty($ScenePhoto))
	{
		$updateSpecimen = "UPDATE botany_specimens SET species_name='$SpeciesName', 
		latitude='$Latitude', longitude='$Longitude', abundance='$Abundance', 
		comment='$Comment', scene_photo='$ScenePhoto' 
		WHERE specimen_id = $SpecimenID";
	}
	else if(empty($ScenePhoto) && empty($SpecimenPhoto))
	{
		$updateSpecimen = "UPDATE botany_specimens SET species_name='$SpeciesName', 
		latitude=$Latitude, longitude=$Longitude, abundance=$Abundance, 
		comment='$Comment'
		WHERE specimen_id = $SpecimenID";
	}
	else
	{
		//Query to insert JSON specimen data into Database
		$updateSpecimen = "UPDATE botany_specimens SET species_name='$SpeciesName', 
			latitude=$Latitude, longitude=$Longitude, abundance=$Abundance, 
			comment='$Comment', scene_photo='$ScenePhoto', specimen_photo='$SpecimenPhoto' 
			WHERE specimen_id = $SpecimenID";
	}	
	//Runs the specimen query

	$conn->query($updateSpecimen);
	//Check if specimen exists in DB
	if($conn->affected_rows < 1)
	{
		http_response_code(500);
		die('Specimen was not updated. Bad specimen.');	
	}

	//Commit transaction
	$conn->commit();	

	//Prints recordID for use by Database
	echo $specimenID;
	
	//Close connection
	$conn->close();

	//Function to check if all specimen data exists
	//@param Takes Specimen variables
	//@return Returns boolean
	function checkSpecimen($LocalSpecimen)
	{
		$valid = //property_exists($LocalSpecimen, 'SpecimenID')
			 property_exists($LocalSpecimen, 'SpeciesName') 
			&& property_exists($LocalSpecimen, 'LocationLatitude')
			&& property_exists($LocalSpecimen, 'LocationLongitude') 
			&& property_exists($LocalSpecimen, 'Abundance')
			&& property_exists($LocalSpecimen, 'Comment')
			&& property_exists($LocalSpecimen, 'ScenePhoto') 
			&& property_exists($LocalSpecimen, 'SpecimenPhoto');
		
		return $valid;
	}

?>
