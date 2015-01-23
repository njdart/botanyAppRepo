<?php
$urlp = 'users.aber.ac.uk/mta2/groupapi/getResource.php';
$chp = curl_init($urlp);
$datap = array(
	'resourceID' => $object->SpecimenPhoto,
	'resourceID' => $object->ScenePhoto,
);

// Form data string

$postStringp = http_build_query($datap, '', '&');

// Setting our options

curl_setopt($chp, CURLOPT_POST, 1);
curl_setopt($chp, CURLOPT_POSTFIELDS, $postStringp);
curl_setopt($chp, CURLOPT_RETURNTRANSFER, true);

// Get the response

$responsep = curl_exec($chp);

// echo $response;

curl_close($chp);

// echo $response;






$urlpp = 'users.aber.ac.uk/mta2/groupapi/getResource.php';
$chpp = curl_init($urlpp);
$datapp = array(
	'resourceID' => $object->ScenePhoto,
);

// Form data string

$postStringpp = http_build_query($datapp, '', '&');

// Setting our options

curl_setopt($chpp, CURLOPT_POST, 1);
curl_setopt($chpp, CURLOPT_POSTFIELDS, $postStringpp);
curl_setopt($chpp, CURLOPT_RETURNTRANSFER, true);

// Get the response

$responsepp = curl_exec($chpp);

// echo $response;

curl_close($chpp);

// echo $response;
?>
