<?php
	
	include 'config.php';	
	
	//Stops warnings
	error_reporting(E_ERROR);
	
	//Takes POST into variable
	$specimenID = $_POST["specimenID"];
	$password = $_POST["password"];

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
	
	//Check if password is authorised
		if($password == $CONFIG['adminPassword'])	
	{
		//Query database specimens table for specimens that match the in-putted recordID
		$specimenQuery = $conn->query("DELETE FROM botany_specimens WHERE specimen_id = $specimenID ");

		//Check if specimen exists in DB
		if($conn->affected_rows < 1)
		{
			http_response_code(400);
			die('Specimen does not exist');	
		}
	}
	else
	{
		http_response_code(401);
		die('Invalid password.');
	}

	//Close connection
	$conn->close();

?>
