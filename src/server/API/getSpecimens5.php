<?php
	// Filters : Species Name default. Site name, Username, Date ASC && DESC


	//Stops warnings
	error_reporting(E_ERROR);
	
	//Takes POST into variable
	$order = $_POST["order"]; //ascending or descending
	$method = $_POST["method"]; // speciesName, locationName, userName, timestamp
	
	//Switch statements to prevent SQL injection. Creates SQL code based on POSTS
	switch($method)
	{
		case "speciesName": 
			$methodName = "species_name";
			break;
		case "locationName":
			$methodName = "location_name";
			break;
		case "userName":
			$methodName = "user_name";
			break;
		case "timeStamp":
			$methodName = "time_stamp";
			break;
	default:
		$methodName = "species_name";
		break;
	}

	switch($order)
	{
		case "ascending":
			$orderName = "ASC";
			break;
		case "descending":
			$orderName = "DESC";
			break;
		default:
			$orderName = "ASC";
			break;
	}

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
	
	//Query database specimens table for specimens that match the in-putted recordID
	$specimenQuery = $conn->query("SELECT * FROM botany_specimens 
				       INNER JOIN botany_records ON botany_specimens.record_id = botany_records.record_id
				       INNER JOIN botany_users ON botany_records.user_id = botany_users.user_id
				       ORDER BY $methodName $orderName");
	
	//Create an array
	$specimens = array();
	
	//
	while($row = $specimenQuery ->fetch_assoc())
	{
		array_push($specimens, array(
		//Specimens table
		'SpecimenID' => (int)$row['specimen_id'],
		'SpecimenName' => $row['species_name'],
		'LocationLatitude' => (float)$row['latitude'],
		'LocationLongitude' => (float)$row['longitude'],
		'Abundance' => (int)$row['abundance'],
		'Comment' => $row['comment'],
		'ScenePhoto' => $row['scene_photo'],
		'SpecimenPhoto' => $row['specimen_photo'],
		//Records table
		'RecordID' => (int)$row['record_id'],
		'LocationName' => $row['location_name'],
		'Timestamp' => (int)$row['time_stamp'],
		'LocationOS' => $row['location_os'],
		//Users table
		'UserID' => (int)$row['user_id'],
		'UserName' => $row['user_name'],
		'UserPhone' => $row['user_phone'],
		'UserEmail' => $row['user_email']));
	}
	
	//Encode the $specimens array into JSON format
	$json = json_encode($specimens);
	
	//Print the JSON into HTTP response body
	echo $json;
?>