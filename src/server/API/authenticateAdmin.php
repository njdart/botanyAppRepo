<?php
	
	include 'config.php';
	
	//Takes POST variable for password
	$password = $_POST["password"];
	
	//Checks if password is correct and returns true
	if(!strcmp($password, $CONFIG['adminPassword']))	
	{
		echo "true";
	}
	else
	{
		echo "false";
	}
	
?>
