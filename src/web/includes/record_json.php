<?php
$url = 'users.aber.ac.uk/mta2/groupapi/getSpecimen.php';
$ch = curl_init($url);
if (!isset($_GET['id']) || empty($_GET['id'])) {
    header('Location: plant_specimens.php');
    exit(); // don't execute any code after it!
}
 
$id=$_GET['id'];

$data = array(
	'specimenID' => $id,
);

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
$lat = $object->LocationLatitude;
$long = $object->LocationLongitude;
 if (!is_object($object)) {
        header('Location: plant_specimens.php');
    }
?>
