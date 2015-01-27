<?php 
	
	include 'config.php';		

	//Stops warnings
	error_reporting(E_ERROR);
	
	//Decodes the JSON into PHP readable
	$record = json_decode($_POST["record"]);
	// var_dump($record);
	// var_dump($_POST);

	//If data is wrong, error code 400
	if(!checkRecord($record))
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
	$UserName = $conn->real_escape_string($record->UserName);
	$UserPhone = $conn->real_escape_string($record->UserPhone);
	$UserEmail = $conn->real_escape_string($record->UserEmail);
	$ReserveID = $conn->real_escape_string($record->ReserveID);
	$RecordTime = $conn->real_escape_string($record->Timestamp);
	$Specimens = $record->Specimens;

	//Checks if any variables are empty
	if(empty($UserName) || empty($UserPhone) || empty($UserEmail) || empty($ReserveID) || empty($RecordTime) 
		|| empty($Specimens))
	{
		http_response_code(400);
		die('A field is empty');
	}
	
	//Selects the Database
	$conn->select_db('msh4');
	
	//Begin transaction
	$conn->autocommit(FALSE);	

	//Query to inserts JSON user data into Database
	$insertUser = "INSERT INTO botany_users (user_name, user_phone, user_email)
		VALUES ('$UserName', '$UserPhone', '$UserEmail')";
		
	
	//Runs the user query
	$conn->query($insertUser);

	//Check if specimen exists in DB
	if($conn->affected_rows < 1)
	{
		http_response_code(500);
		die('Record was not added. Bad user.');	
	}



	//Grabs the automatically incremented User ID
	$userID = $conn->insert_id;
	

	//Query to insert JSON record data into Database
	$insertRecord = "INSERT INTO botany_records (user_id, time_stamp, reserve_id)
	VALUES ($userID, $RecordTime, $ReserveID)";
	
	//Runs the record query
	$conn->query($insertRecord);

	//Check if specimen exists in DB
	if($conn->affected_rows < 1)
	{
		http_response_code(500);
		die('Record was not added. Bad record.');	
	}

	//Grabs the automatically incremented Record ID
	$recordID = $conn->insert_id;
	
	//Loops through Specimens array and pulls JSON variables into PHP variables
	foreach($Specimens as $specimen)
	{
		$SpeciesName = $conn->real_escape_string($specimen->SpeciesName);
		$Latitude = $conn->real_escape_string($specimen->LocationLatitude);
		$Longitude = $conn->real_escape_string($specimen->LocationLongitude);
		$Abundance = $conn->real_escape_string($specimen->Abundance);
		$Comment = $conn->real_escape_string($specimen->Comment);
		$ScenePhoto = $conn->real_escape_string($specimen->ScenePhoto);
		$SpecimenPhoto = $conn->real_escape_string($specimen->SpecimenPhoto);

		//Checks Latitude and Longitude are within real world values
		if($Latitude > 90 || $Latitude < -90 || $Longitude > 180 || $Longitude < -180)
		{
			http_response_code(400);
			die('Latitude or Longitude is out of bounds. Latitude must be between -90 and 90. Longitude must be between -180 and 180');
		}

		//Checks if any variables (except $Comment) are empty
		if(empty($SpeciesName) || empty($Latitude) || empty($Longitude) || empty($Abundance))
		{
			http_response_code(400);
			die('A field is empty');
		}
		//Query to insert JSON specimen data into Database
		$insertSpecimens = "INSERT INTO botany_specimens (record_id, species_name, 
			latitude, longitude, abundance, comment, scene_photo, specimen_photo) 
			VALUES ($recordID, '$SpeciesName', $Latitude, $Longitude, $Abundance, 
			'$Comment', $ScenePhoto, $SpecimenPhoto);";

		//Runs the specimen query
		$conn->query($insertSpecimens);
		//Check if specimen exists in DB
		if($conn->affected_rows < 1)
		{
			http_response_code(500);
			die('Record was not added. Bad specimen.');	
		}
	}
	
	//Commit transaction
	$conn->commit();	

	//Prints recordID for use by Database
	echo $recordID;
	
	//Close connection
	$conn->close();

	//Function to check if all specimen data exists
	//@param Takes Specimen variables
	//@return Returns boolean
	function checkSpecimen($LocalSpecimen)
	{
		$valid = property_exists($LocalSpecimen, 'SpeciesName') 
			&& property_exists($LocalSpecimen, 'LocationLatitude')
			&& property_exists($LocalSpecimen, 'LocationLongitude') 
			&& property_exists($LocalSpecimen, 'Abundance')
			&& property_exists($LocalSpecimen, 'Comment')
			&& property_exists($LocalSpecimen, 'ScenePhoto') 
			&& property_exists($LocalSpecimen, 'SpecimenPhoto');
		
		return $valid;
	}
	
	//Function to check if all record data exists, using checkSpecimen()
	//@param Takes Record variables
	//@return Returns boolean
	function checkRecord($LocalRecord)
	{
		$valid = property_exists($LocalRecord, 'UserName')
			&& property_exists($LocalRecord, 'UserPhone')
			&& property_exists($LocalRecord, 'ReserveID')
			&& property_exists($LocalRecord, 'Specimens')
			&& property_exists($LocalRecord, 'Timestamp');
			
		foreach($LocalRecord->Specimens as $specimen)
		{
			$valid = $valid && checkSpecimen($specimen);
		}
		return $valid;
	}

?>
