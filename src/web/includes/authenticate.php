<?php
session_start();
$url = 'users.aber.ac.uk/mta2/groupapi/authenticateAdmin.php';
$ch = curl_init($url);
$loginmsg="";
$errormsg="";
if (array_key_exists('submitpass', $_POST)){
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
	header('location:' . $temp . '?loginmsg=' . urlencode(base64_encode("You have logged in")));
}else{
	session_destroy();
	header('location:' . $temp . '?errormsg=' . urlencode(base64_encode("Login Incorrect")));
}

curl_close($ch);
?>
