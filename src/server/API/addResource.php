<?php 
	
	//Stops warnings
	//error_reporting(E_ERROR);
	
	//Decodes the JSON into PHP readable from POST
	$resource = $_FILES["resource"];
	
	//Returns success code for successful data and connects to Database
	http_response_code(200);
	$conn = new mysqli('db.dcs.aber.ac.uk', 'msh4', 'password');
	
	//Checks Database connection and returns error code 500 to show server error, then end the script
	if($conn->connect_errno)
	{
		http_response_code(500);
		die('Could not connect: ' . $mysqli->connect_error);
	}
	
	//Selects the Database
	$conn->select_db('msh4');
	
	//Query to insert resource into Database 
	$insertResource = "INSERT INTO botany_resources () VALUES ()";
	
	//Execute query
	$conn->query($insertResource);
	
	//Get resource ID
	$resourceID = $conn->insert_id;
	
	//Defines the upload path for file
	$uploadPath = './DBPictures/' . $resourceID;
	
	//Move file from temporary location to defined storage; error code 500 if can't
	if(!move_uploaded_file($resource['tmp_name'], $uploadPath)) {
		http_response_code(500);
		return;
	}
	
	//Echo resource ID for use by Database
	echo $resourceID;
?>