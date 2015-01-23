
<?php
$url = 'users.aber.ac.uk/mta2/groupapi/getSpecimen.php';
$ch = curl_init($url);
$data = array(
	'specimenID' => 194,
	
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

$a= $object->SpecimenID;

echo($a);
var_dump($a);
// foreach($object as $specimens)
	// {

	// // var_dump ($specimens);

	// $speciesName = $specimens->SpecimenName;
	// $locationName = $specimens->LocationName;
	// $userName = $specimens->UserName;
	// $timeStamp = $specimens->Timestamp;
	// echo $speciesName;
	// echo "<br />";
	// echo $locationName;
	// echo "<br />";
	// echo $userName;
	// echo "<br />";
	// echo $userName;
	// echo "<br />";
	// echo $timeStamp;
	// echo "<br />";
	// }

?>
