<?php


	include 'config.php';	

	//Stops warnings
	error_reporting(E_ERROR);
	
	//Decodes the JSON into PHP readable
	$recordID = $_POST["recordID"];

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
	$recordQuery = $conn->query("SELECT DISTINCT location_name, location_os FROM botany_records");
	
	//Create array for Specimens
	$records = array();
	
	//Fill array
	while($row = $recordQuery ->fetch_assoc())
	{
		array_push($records, array(
		//Records table
		'LocationName' => $row['location_name'],
		'LocationOS' => $row['location_os']));
	}
	
	//Decode array into JSON
	$json = json_encode($records);

	//Send JSON to HTTP 
	echo $json;



?>
