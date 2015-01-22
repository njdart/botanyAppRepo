<?php

	//Stops warnings
	//error_reporting(E_ERROR);

	//Takes POST into variable
	$resourceID = $_POST["resourceID"];

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
	
	//Query database records table for record_ids that match the in-putted recordID
	$resourceQuery = $conn->query("SELECT * FROM botany_resources WHERE resource_id=$resourceID ");

	//If there is more than one result, return error code 500 and end the script
	if($resourceQuery->num_rows != 1) 
	{
		echo "Cant read resource";
		http_response_code(500);
		return;
	}

	//Send data reading to start
	$resourceQuery->data_seek(0);

	//Get record from database
	$resource = $resourceQuery->fetch_assoc();

	//Assign record variables to PHP variables
	$resourceID = (int)$resource['resource_id'];

	
	//COPIED
	$downloadPath = 'http://users.aber.ac.uk/mta2/groupapi/DBPictures/' . $resourceID;
        /*if (file_exists($downloadPath)) {

            http_response_code(200);
            header("Content-Type: application/octet-stream");
            header("Content-Transfer-Encoding: Binary");
            header("Content-Length:".filesize($downloadPath));
            header("Content-Disposition: attachment; filename='$resourceID'");
            readfile($downloadPath);
            die();        
        } else {
	    http_response_code(500);
            die("Error: File not found.");
        } */

	echo $downloadPath;

?>
