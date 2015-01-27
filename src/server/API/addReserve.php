<?php 
	
	include 'config.php';		

	//Stops warnings
	error_reporting(E_ERROR);
	
	//Decodes the JSON into PHP readable
	$reserve = json_decode($_POST["reserve"]);

	//If data is wrong, error code 400
	if(!checkReserve($reserve))
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
	$LocationName = $conn->real_escape_string($reserve->LocationName);
	$LocationOS = $conn->real_escape_string($reserve->LocationOS);
	$Description = $conn->real_escape_string($reserve->Description);

	//Checks if any variables are empty
	if(empty($LocationName) || empty($LocationOS))
	{
		http_response_code(400);
		die('A field is empty');
	}
	
	//Selects the Database
	$conn->select_db('msh4');
	
	//Begin transaction
	$conn->autocommit(FALSE);
	
	//Query to insert JSON reserve data into Database
	$insertReserve = "INSERT INTO botany_reserves (location_name, location_os, description) 
		VALUES ('$LocationName', '$LocationOS', '$Description');";

	//Runs the specimen query
	$conn->query($insertReserve);

	//Check if specimen exists in DB
	if($conn->affected_rows < 1)
	{
		http_response_code(500);
		die('Reserve was not added. Bad reserve.');	
	}

	//Grabs the automatically incremented User ID
	$reserveID = $conn->insert_id;

	//Commit transaction
	$conn->commit();	

	//Prints recordID for use by Database
	echo $reserveID;
	
	//Close connection
	$conn->close();

	//Function to check if all reserve data exists
	//@param Takes Reserve variables
	//@return Returns boolean
	function checkReserve($LocalReserve)
	{
		$valid = property_exists($LocalReserve, 'LocationName') 
			&& property_exists($LocalReserve, 'LocationOS')
			&& property_exists($LocalReserve, 'Description'); 
			
		
		return $valid;
	}

?>
