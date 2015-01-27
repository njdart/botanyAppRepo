<?php

	include 'config.php';
	
	//Stops warnings
	error_reporting(E_ERROR);
	
	//Takes POST into variable
	$specimenID = (int)$_POST["specimenID"];

	//Returns success code for successful data and connects to Database
	http_response_code(200);
	$conn = new mysqli($CONFIG['dbname'], $CONFIG['username'], $CONFIG['password']);
	
	//Checks Database connection and returns error code 500 to show server error, then end the script
	if($conn->connect_errno)
	{
		http_response_code(500);
		die('Could not connect: ' . $mysqli->connect_error);
	}
	
	//Selects the Database
	$conn->select_db('msh4');

	//Query database specimens table for specimens that match the in-putted recordID
	$specimenQuery = $conn->query("SELECT * FROM botany_specimens 
				       INNER JOIN botany_records ON botany_specimens.record_id = botany_records.record_id
				       INNER JOIN botany_users ON botany_records.user_id = botany_users.user_id
                       INNER JOIN botany_reserves ON botany_records.reserve_id = botany_reserves.reserve_id 
				       WHERE specimen_id = $specimenID ");

	//If there is more than one result, return error code 500 and end the script
	if($specimenQuery->num_rows != 1) 
	{
		http_response_code(500);
		return;
	}

	//Send data reading to start
	$specimenQuery->data_seek(0);
	
	$specimen = $specimenQuery->fetch_assoc();	
	
	//Specimens table
	$specimenID = (int)$specimen['specimen_id'];
	$specimenName = $specimen['species_name'];
	$latitude = (float)$specimen['latitude'];
	$longitude = (float)$specimen['longitude'];
	$abundance = (int)$specimen['abundance'];
	$comment = $specimen['comment'];
	$scenePhoto = $specimen['scene_photo'];
	$specimenPhoto = $specimen['specimen_photo'];
	//Records table
	$specimenRecordID = (int)$specimen['record_id'];
	$locationName = $specimen['location_name'];
	$timestamp = (int)$specimen['time_stamp'];
	$locationOS = $specimen['location_os'];
	//Users table
	$userID = (int)$specimen['user_id'];
	$userName = $specimen['user_name'];
	$userPhone = $specimen['user_phone'];
	$userEmail = $specimen['user_email'];
	
	//Assign all variables to an array for JSON encoding	
	$lastRecord = array('SpecimenID' => $specimenID,
			    'SpeciesName' => $specimenName,
		   	    'LocationLatitude' => $latitude, 
			    'LocationLongitude' => $longitude,
			    'Abundance' => $abundance,
			    'Comment' => $comment,
			    'ScenePhoto' => $scenePhoto,
			    'SpecimenPhoto' => $specimenPhoto,
			    'RecordID' => $specimenRecordID,
			    'LocationName' => $locationName,
			    'Timestamp' => $timestamp,
			    'LocationOS' => $locationOS,
			    'UserID' => $userID,
			    'UserName' => $userName,
			    'UserPhone' => $userPhone,
			    'UserEmail' => $userEmail);
			     	
	//Encode all data into JSON format
	$json = json_encode($lastRecord);
	
	//Print out JSON output for reading
	echo $json;

	//Close connection
	$conn->close();
	
?>
