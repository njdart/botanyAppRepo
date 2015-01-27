<?php

	include 'config.php';	

	//Stops warnings
	error_reporting(E_ERROR);

	//Takes POST into variable
	$reserveID = (int)$_POST["reserveID"];

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

	//Query database records table for reserve_ids that match the in-putted reserveID
	$reserveQuery = $conn->query("SELECT * FROM botany_reserves WHERE reserve_id=$reserveID ");
	
	//Fetch variables and assign to $reserveFetch
	$reserveFetch = $reserveQuery->fetch_assoc();	
	
	//Assign database values to variables for array
	$LocationNameArray = $reserveFetch['location_name'];
	$LocationOSArray = $reserveFetch['location_os'];
	$DescriptionArray = $reserveFetch['description'];
	$ReserveIDArray = $reserveFetch['reserve_id'];

	//Fill array
	$reserve = array(
		'LocationName' => $LocationNameArray, 
		'LocationOS' => $LocationOSArray, 
		'Description' => $DescriptionArray, 
		'ReserveID' => $ReserveIDArray);

	//Decode array into JSON
	$json = json_encode($reserve);

	//Send JSON to HTTP 
	echo $json;

	//Close connection
	$conn->close();

?>
