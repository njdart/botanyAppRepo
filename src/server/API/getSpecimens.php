<?php

	include 'config.php';	

	//Stops warnings
	error_reporting(E_ERROR);
	
	//Takes POST into variable
	$searchValue = $_POST["value"];
	$searchColumn = $_POST["column"]; //speciesName, locationName, userName
    $search2Value = $_POST["value2"];
    $search2Column = $_POST["column2"]; //speciesName, locationName, userName
	$order = $_POST["order"]; //ascending or descending
	$method = $_POST["method"]; // speciesName, locationName, userName, timestamp
	$numericColumn = $numericMethod = false;
	$start = (int)$_POST["start"]; //number for row start
	$range = (int)$_POST["range"]; //how many records to display

	//Switch statements to prevent SQL injection. Creates SQL code based on POSTS
	switch($searchColumn)
	{
		case "speciesName":
			$columnName = "species_name";
			break;
		case "locationName":
			$columnName = "botany_reserves.location_name";
			break;
        case "reserveID":
            $columnName = "botany_records.reserve_id";
            $numericColumn = true;
            break;
		case "userName":
			$columnName = "user_name";
			break;
		case "abundance":
			$columnName = "abundance";
			$numericColumn = true;
			break;
		default:
			$columnName = "species_name";
			break;
	}
    switch($search2Column)
    {
        case "speciesName":
        $column2Name = "species_name";
        break;
        case "locationName":
        $column2Name = "botany_reserves.location_name";
        break;
        case "reserveID":
        $column2Name = "botany_records.reserve_id";
        $numeric2Column = true;
        break;
        case "userName":
        $column2Name = "user_name";
        break;
        case "abundance":
        $column2Name = "abundance";
        $numericColumn2 = true;
        break;
        default:
        $column2Name = "species_name";
        break;
    }
	switch($method)
	{
		case "speciesName": 
			$methodName = "species_name";
			break;
		case "locationName":
			$methodName = "botany_reserves.location_name";
			break;
		case "userName":
			$methodName = "user_name";
			break;
		case "timeStamp":
			$methodName = "time_stamp";
			break;
		case "abundance":
			$methodName = "abundance";
			$numericMethod = true;
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
	$conn = new mysqli($CONFIG['dbname'], $CONFIG['username'], $CONFIG['password']);

	//Escape special characters in search value
	$searchValue = $conn->real_escape_string($searchValue);

	//Checks Database connection and returns error code 500 to show server error, then end the script
	if($conn->connect_errno)
	{
		http_response_code(500);
		die('Could not connect: ' . $mysqli->connect_error);
	}
	
	//Selects the Database
	$conn->select_db('msh4');
	
	//Query database specimens table for specimens that match the in-putted recordID
	//Runs first if $searchValue has value, second if no value
    $queryText = "SELECT * FROM botany_specimens 
                    INNER JOIN botany_records ON botany_specimens.record_id = botany_records.record_id
                    INNER JOIN botany_users ON botany_records.user_id = botany_users.user_id 
                    INNER JOIN botany_reserves ON botany_records.reserve_id = botany_reserves.reserve_id "
                    . ($searchValue ? ($numericColumn ? " WHERE $columnName = $searchValue" : " WHERE $columnName LIKE '%$searchValue%'") : " ")
                    . ($search2Value ? ($numericColumn2 ? " AND $column2Name = $search2Value" : " AND $column2Name LIKE '%$search2Value%'") : " ")
                    . ($method ? " ORDER BY $methodName $orderName" : "")
                    . ($range ? " LIMIT $start, $range" : "");
	$specimenQuery = $conn->query($queryText);
	//Create array for Specimens
	$specimens = array();
	//Fill array
	while($row = $specimenQuery->fetch_assoc())
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
	
	//Decode array into JSON
	$json = json_encode($specimens);

	//Send JSON to HTTP 
	echo $json;

	//Close connection
	$conn->close();

?>
