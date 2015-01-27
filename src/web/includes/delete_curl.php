<?php
include "config.php";
session_save_path($CONFIG["session"]);
session_start();

$url = $CONFIG["api"] . '/removeSpecimen.php';
$ch = curl_init($url);

$data = array(
	'specimenID' => $_GET['id'],
	'password' => $_SESSION['pass']
);

$postString = http_build_query($data, '', '&');

curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, $postString);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

$response = curl_exec($ch);

$http_status = curl_getinfo($ch, CURLINFO_HTTP_CODE);

if ($http_status == 200){
	header('Location: ' . $_SERVER['HTTP_REFERER']);
}else{
	header('Location: ' . $_SERVER['HTTP_REFERER']);
}

curl_close($ch);
?>
