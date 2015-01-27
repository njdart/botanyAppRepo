<?php
include "config.php";
session_save_path($CONFIG["session"]);
session_start();
$url = $CONFIG["api"] . '/authenticateAdmin.php';
$ch = curl_init($url);
$loginmsg="";
$errormsg="";
if (array_key_exists('submitpass', $_POST)){
	echo"hello";
	$_SESSION['pass'] = $_POST['pass'];
}

$data = array(
	'password' => $_SESSION['pass']
);

$postString = http_build_query($data, '', '&');

curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, $postString);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

$response = curl_exec($ch);
$temp = $_SERVER['HTTP_REFERER'];
list($temp, $pass) = explode('?',$temp,2);

if ($response == "true"){
	header('location:' . $temp . '?loginmsg=' . urlencode(base64_encode("You have logged in")));//$_SESSION['pass'] = $_POST['pass'];
}else{
	session_destroy();
	header('location:' . $temp . '?errormsg=' . urlencode(base64_encode("Login Incorrect")));
}

curl_close($ch);
?>
