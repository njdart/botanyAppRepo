<?php
include "includes/header.php";
include "includes/get_reserve.php";

?>

<div id='content-boxes'>
		<h1 class='indent'>Add Reserve</h1>
		<h2>Please enter the data for a new reserve</h2>
		
		<form class='edit_reserve' name='editreserve' action='edit_reserve.php' method='POST'>
	    
		<input type='hidden' id='resID' name='resID' value='<?php echo $_GET['resID'];?>'/><br><br>
		
		Location Name:<br>
		<input type='text' id='LocationName' name='LocationName' value='<?php echo $objres->LocationName;?>'/><br><br>
	
		OS Grid Reference:<br>
		<input type='text' id='osReference' name='OSReference' value='<?php echo $objres->LocationOS;?>' /><br><br>
			
		Description:<br>
		<input type='text' id='Description' name='Description' value='<?php echo $objres->Description;?>' /><br><br>
		
		<input type='submit' />
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
	var_dump ($json);
	
	$ch=curl_init('http://users.aber.ac.uk/mta2/groupapi/updateReserve.php');
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, array('reserve' => $json));
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$response=curl_exec($ch);
	var_dump($response);
	curl_close($ch);
	header('Location: reserves.php');
	}
	include "includes/footer.php";
	?>