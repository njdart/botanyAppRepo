<?php
$url = 'users.aber.ac.uk/mta2/groupapi/getSpecimens.php';
$ch = curl_init($url);

$data = array(
    'value' => $_POST['value'],
    'column' => $_POST['column'],
    'order' => $_POST['order'],
    'method' => $_POST['method']
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
echo $response;
?>