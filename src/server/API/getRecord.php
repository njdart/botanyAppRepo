<?php

	include 'config.php';

	//Stops warnings
	error_reporting(E_ERROR);
	
	//Decodes the JSON into PHP readable
	$recordID = (int)$_POST["recordID"];

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
	
	//Query database records table for record_ids that match the in-putted recordID
	$recordQuery = $conn->query("SELECT * FROM botany_records WHERE record_id=$recordID ");
	
	//If there is more than one result, return error code 500 and end the script
	if($recordQuery->num_rows != 1) 
	{
		http_response_code(500);
		return;
	}
	
	//Send data reading to start
	$recordQuery->data_seek(0);
	
	//Get record from database
	$record = $recordQuery->fetch_assoc();
	
	//Assign record variables to PHP variables
	$userID = (int)$record['user_id'];
	$locationName = $record['location_name'];
	$timestamp = (int)$record['time_stamp'];
	
	//Query database users table for user_ids that match the in-putted userID
	$userNameQuery = $conn->query("SELECT * FROM botany_users WHERE user_id = $userID ");
	
	//If there is more than one result, return error code 500 and end the script
	if($userNameQuery->num_rows != 1) 
	{
		http_response_code(500);
		return;
	}
	
	//Send data reading to start
	$userNameQuery->data_seek(0);
	
	//Get user from database
	$user = $userNameQuery->fetch_assoc();
	
	//Assign record variables to PHP variables
	$userName = $user['user_name'];
	$userPhone = $user['user_phone'];
	$userEmail = $user['user_email'];
	
	//Query database specimens table for specimens that match the in-putted recordID
	$specimensQuery = $conn->query("SELECT * FROM botany_specimens WHERE record_id = $recordID ");
	
	//Send data reading to start
	$specimensQuery->data_seek(0);
	
	//Declare Specimens array
	$specimens = array();
	
	//Get user from database
	while($row = $specimensQuery ->fetch_assoc())
	{
		array_push($specimens, array('SpeciesName' => $row['species_name'], 
									  'LocationLatitude' => $row['latitude'],
									  'LocationLongitude' => $row['longitude'],
									  'Abundance' => $row['abundance'],
									  'Comment' => $row['comment'],
									  'ScenePhoto' => $row['scene_photo'],
									  'SpecimenPhoto' => $row['specimen_photo']));
	}
	$lastRecord = array('UserName' => $userName,
						'UserPhone' => $userPhone,
						'UserEmail' => $userEmail,
						'LocationName' => $locationName,
						'Specimens' => $specimens,
						'Timestamp' => $timestamp);
						
	//Encode all data into JSON format
	$json = json_encode($lastRecord);
	
	//Print out JSON output for reading
	echo $json;
	
	//Close connection
	$conn->close();

?>
