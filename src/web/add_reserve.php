<?php
include "includes/header.php";
?>
	<div id='content-boxes'>
		<h1 class='indent'>Add Reserve</h1>
		<h2>Please enter the data for a new reserve</h2>
		
		<form class='add-reserve' name='addReserve' action='add_reserve.php' method='POST'>
	
		Location Name:<br>
		<input type='text' id='locationName' name='LocationName' /><br><br>
	
		OS Grid Reference:<br>
		<input type='text' id='osReference' name='OSReference' /><br><br>
			
		Description:<br>
		<input type='text' id='Description' name='Description' /><br><br>
		
		<input type='submit' />
		</form>
	</div>
	
	<?php
	$reserve=array(
	'LocationName' => $_POST['LocationName'],
	'LocationOS' => $_POST['OSReference'],
	'Description' => $_POST['Description']
	);
	
	$json = json_encode($reserve);
	
	$ch=curl_init('http://users.aber.ac.uk/mta2/groupapi/addReserve.php');
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, array('reserve' => $json));
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$response=curl_exec($ch);
	curl_close($ch);
	
	include "includes/footer.php";
	?>