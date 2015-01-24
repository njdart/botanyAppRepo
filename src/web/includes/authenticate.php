<?php
session_start();
$url = 'users.aber.ac.uk/mta2/groupapi/authenticateAdmin.php';
$ch = curl_init($url);

if (array_key_exists('submitpass', $_POST))
	{
	$_SESSION['pass'] = $_POST['pass'];
	}

echo $_SESSION['pass'];
$data = array(
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

if ($response == "true")
	{
	header('location:' . $_SERVER['HTTP_REFERER'] . '?loginmsg=' . urlencode(base64_encode("You have logged in")));
	}
  else
	{
	session_destroy();
	header('location:' . $_SERVER['HTTP_REFERER'] . '?errormsg=' . urlencode(base64_encode("Login Incorrect")));
	}

curl_close($ch);



?>