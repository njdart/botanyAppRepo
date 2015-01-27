<?php
include "includes/config.php";
session_save_path($CONFIG["session"]);
include "includes/header.php";

$url = $CONFIG["api"] . '/addReserve.php';
if(!isset($_SESSION['pass'])){
header('Location:plant_specimens.php');
}
?>
	<div id='content-boxes'>
		<h1 class='indent'>Add Reserve</h1>
		<p>Please enter the data for a new reserve</p>
		
		<form class='add-reserve' name='addReserve' action='add_reserve.php' method='POST'>
	<table class='adda'>
		<tr><th>Location Name</th>
		<td><input type='text' id='locationName' name='LocationName' required='true'/></td></tr>
		<tr><th>OS Grid Reference</th>
		<td><input type='text' id='osReference' name='OSReference' required='true'/></td></tr>
		<tr>	
		<th>Description:</th>
		<td><input type='text' id='Description' name='Description' required='true'/></td></tr>
		
		<tr><td><input type='submit' /></td></tr>
		</table>
		</form>
	</div>
	
	<?php
	$reserve=array(
	'LocationName' => $_POST['LocationName'],
	'LocationOS' => $_POST['OSReference'],
	'Description' => $_POST['Description']
	);
	
	$json = json_encode($reserve);
	
	$ch=curl_init($url);
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, array('reserve' => $json));
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$response=curl_exec($ch);
	curl_close($ch);
	include "includes/footer.php";
	?>
