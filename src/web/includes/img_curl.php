<?php
$urlp = 'users.aber.ac.uk/mta2/groupapi/getResource.php';
$chp = curl_init($urlp);
$datap = array(
	'resourceID' => $object->SpecimenPhoto,
);

$postStringp = http_build_query($datap, '', '&');

curl_setopt($chp, CURLOPT_POST, 1);
curl_setopt($chp, CURLOPT_POSTFIELDS, $postStringp);
curl_setopt($chp, CURLOPT_RETURNTRANSFER, true);

$responsep = curl_exec($chp);

curl_close($chp);

$urlpp = 'users.aber.ac.uk/mta2/groupapi/getResource.php';
$chpp = curl_init($urlpp);
$datapp = array(
	'resourceID' => $object->ScenePhoto,
);

$postStringpp = http_build_query($datapp, '', '&');

curl_setopt($chpp, CURLOPT_POST, 1);
curl_setopt($chpp, CURLOPT_POSTFIELDS, $postStringpp);
curl_setopt($chpp, CURLOPT_RETURNTRANSFER, true);

$responsepp = curl_exec($chpp);

curl_close($chpp);
?>