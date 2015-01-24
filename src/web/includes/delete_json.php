<?php
session_start();
$url = 'users.aber.ac.uk/mta2/groupapi/removeSpecimen.php';
$ch = curl_init($url);

$data = array(
	'specimenID' => $_GET['id'],
	'password' => $_SESSION['pass']

);

// Form data string

$postString = http_build_query($data, '', '&');

// Setting our options

curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, $postString);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

// Get the response

$response = curl_exec($ch);

// echo $response;
$http_status = curl_getinfo($ch, CURLINFO_HTTP_CODE);
if($http_status == 200){
header('Location: ' . $_SERVER['HTTP_REFERER']);
}else{
header('Location: ' . $_SERVER['HTTP_REFERER']);
}



curl_close($ch);

// echo $response;



?>