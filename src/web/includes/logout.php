<?php
Session_start();
Session_destroy();
$temp = $_SERVER['HTTP_REFERER'];
list($temp, $pass, $yjuk) = explode('?',$temp,3);
header('location:'. $temp.'?msg='.urlencode(base64_encode("You have been successfully logged out!")));
?>