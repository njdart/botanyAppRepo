<?php
$url = 'users.aber.ac.uk/mta2/groupapi/getReserve.php';
$ch = curl_init($url);


$resID = $_GET['resID'];
	 $data = array(
	'reserveID' => $resID,
	);


// Form data string
$postString = http_build_query($data, '', '&');

// Setting our options
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, $postString);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

// Get the response
$response = curl_exec($ch);
// var_dump ($response);
curl_close($ch);
$objres = json_decode($response);
?>