<?php
	
	include 'config.php';	
	
	//Stops warnings
	//error_reporting(E_ERROR);
	
	//Takes POST into variable
	$reserveID = $_POST["reserveID"];
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
		$reserveQuery = $conn->query("DELETE FROM botany_reserves WHERE reserve_id = $reserveID ");
		//Check if reserve is removed from DB
		if($conn->affected_rows < 1)
		{
			http_response_code(400);
			die('Reserve does not exist');	
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
