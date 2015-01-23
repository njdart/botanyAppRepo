<?php 
	
	include 'config.php';	
	
	//Stops warnings
	error_reporting(E_ERROR);
	
	//Decodes the JSON into PHP readable
	$specimen = json_decode($_POST["specimen"]);

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

	//Pull JSON variables into PHP variables
	$UserName = $record->UserName;
	$UserPhone = $record->UserPhone;
	$UserEmail = $record->UserEmail;
	$LocationName = $record->LocationName;
	$LocationOS = $record->LocationOS;
	$RecordTime = $record->Timestamp;
	$Specimens = $record->Specimens;












?>
