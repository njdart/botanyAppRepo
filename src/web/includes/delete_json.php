<?php
$url = 'users.aber.ac.uk/mta2/groupapi/removeSpecimen.php';
$ch = curl_init($url);
$_SESSION['pass']=$_POST['pass'];
$pass=$_SESSION['pass'];
$data = array(
	'specimenID' => $id,
	'password' => $pass

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
$http_status = curl_getinfo($ch, CURLINFO_HTTP_CODE;
if($http_status == 200){
	header('Location: plant_specimens.php');

}else{
echo "Wrong";
}



curl_close($ch);

// echo $response;



?>