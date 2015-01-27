<?php
include "includes/config.php";
session_save_path($CONFIG["session"]);
include "includes/header.php";
include "includes/get_reserve.php";
include "includes/config.php";
$url = $CONFIG["api"] . '/updateReserve.php';
if(!isset($_SESSION['pass'])){
header('Location:plant_specimens.php');
}
?>

<div id='content-boxes'>
		<h1 class='indent'>Edit Reserve</h1>
		<p>Please enter the data for a new reserve</p>
		
		<form class='edit_reserve' name='editreserve' action='edit_reserve.php' method='POST'>
	    <table class='adda'>
		<input type='hidden' id='resID' name='resID' value='<?php echo $_GET['resID'];?>'/><br><br>
		<tr><th>Location Name</th>
		<td><input type='text' id='LocationName' name='LocationName' value='<?php echo $objres->LocationName;?>' required='true'/></td></tr>
		<tr><th>OS Grid Reference</th>
		<td><input type='text' id='osReference' name='OSReference' value='<?php echo $objres->LocationOS;?>' required='true'/></td></tr>
		<tr>	
		<th>Description:</th>
		<td><input type='text' id='Description' name='Description' value='<?php echo $objres->Description;?>' required='true'/></td></tr>
		
		<tr><td><input type='submit' /></td></tr>
		</table>
		
		</form>
	</div>
	
	<?php

	if(isset($_POST['LocationName'])){
	$reserve=array(
	'ReserveID' => $_POST['resID'],
	
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
	header('Location: reserves.php');
	}
	include "includes/footer.php";
	?>
