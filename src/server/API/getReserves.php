<?php

	include 'config.php';	

	//Stops warnings
	error_reporting(E_ERROR);

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
	$reservesQuery = $conn->query("SELECT * FROM botany_reserves");
	
	//Create array for Specimens
	$reserves = array();
	
	//Fill array
	while($row = $reservesQuery->fetch_assoc())
	{
		array_push($reserves, array(
		'LocationName' => $row['location_name'],
		'LocationOS' => $row['location_os'],
        'Description' => $row['description'],
        'ReserveID' => $row['reserve_id']));
	}
	
	//Decode array into JSON
	$json = json_encode($reserves);

	//Send JSON to HTTP 
	echo $json;

	//Close connection
	$conn->close();

?>
