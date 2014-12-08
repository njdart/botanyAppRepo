<?php
$db_username = '';
$db_password = '';
$db_name = '';
$db_host = '';
$mysql = new mysql($db_host, $db_username, $db_password,$db_name);

if (!$mysql) {
header('Location: dberror.php');
}
?>