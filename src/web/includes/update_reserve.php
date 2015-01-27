<?php
$url = 'users.aber.ac.uk/mta2/groupapi/getSpecimen.php';
$ch = curl_init($url);

// if (!isset($_GET['id']) || empty($_GET['id'])){
	// header('Location: plant_specimens.php');
	// exit(); 
// }

$resID = '3';
$data = array(
	'reserveID' => $id,
);

$postString = http_build_query($data, '', '&');

curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, $postString);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

$response = curl_exec($ch);

curl_close($ch);
$object = json_decode($response);



// if (!is_object($object)){
	// header('Location: plant_specimens.php');
// }
?>