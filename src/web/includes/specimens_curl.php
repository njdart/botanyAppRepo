<?php
$url = 'users.aber.ac.uk/mta2/groupapi/getSpecimens.php';
$ch = curl_init($url);


if (isset($_POST['submitsearch'])){
	$data = array(
		'value' => $_POST['search'],
		'column' => 'speciesName',
		'order' => 'ascending',
		'method' => 'speciesName'
	);
}elseif (isset($_POST['resID'])){
	$data = array(
		'value' => $_POST['resID'],
		'column' => 'resourceID',
		'order' => 'ascending',
		'method' => 'speciesName'
	);
}
elseif (isset($_POST['submitadvsearch'])){
	$data = array(
		'value' => $_POST['search'],
		'column' => $_POST['searchfield'],
		'order' => 'ascending',
		'method' => 'speciesName'
	);
}elseif (isset($_POST['submitdateorder'])){
	$data = array(
		'value' => '',
		'column' => '',
		'order' => $_POST['dateorder'],
		'method' => 'timeStamp'
	);
}
elseif (isset($_POST['submitorder'])){
	$data = array(
		'value' => '',
		'column' => '',
		'order' => $_POST['ordertype'],
		'method' => $_POST['orderby']
	);
}else{
	$data = array(
	
		'order' => "ascending",
		'method' => "speciesName",
	);
}

// Form data string
$postString = http_build_query($data, '', '&');

// Setting our options
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, $postString);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

// Get the response
$response = curl_exec($ch);
curl_close($ch);
$object = json_decode($response);
?>