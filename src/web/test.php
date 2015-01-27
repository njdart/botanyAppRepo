<?php
include "includes/config.php";
session_save_path($CONFIG["session"]);
$url = $CONFIG["api"] . '/getReserves.php';
$ch = curl_init($url);

$id = 2;
$data = array(
	'specimenID' => $id,
);

$postString = http_build_query($data, '', '&');

curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

$response = curl_exec($ch);

$http_status = curl_getinfo($ch, CURLINFO_HTTP_CODE);
$object= json_decode($response);

$array = Array();
foreach($object as $value ){
array_push($array, Array($value->ReserveID,
					     $value->LocationName));
}


// foreach($j);

// curl_close($ch);
?>

