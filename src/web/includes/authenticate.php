<?php
$url = 'users.aber.ac.uk/mta2/groupapi/authenticateAdmin.php';
$ch = curl_init($url);
echo $_POST['pass'];
$data = array(
	'password' => $_POST['pass']

);

// Form data string

$postString = http_build_query($data, '', '&');

// Setting our options

curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, $postString);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

// Get the response

$response = curl_exec($ch);

var_dump ($response);

// echo $response;
// if($response == true){
		

// }else{
// echo "Wrong";
// }



curl_close($ch);

// echo $response;



?>