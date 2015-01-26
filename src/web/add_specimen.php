<?php
	$title = "Add";
	// include 'includes/config.php';
	include "header.php";
?>
	<div id='content-boxes'>
		<h1 class='indent'>Add Record</h1>
		<h2>Please enter the details in the form below</h2>
		
		<div id='left-section'>
			<form enctype="multipart/form-data" class='add-record' name='formMain' action='add_specimen.php' method='POST'>
				Username:<br>
				<input type='text' id='UserName' name='UserName' /><br><br>

				Phone:<br>
				<input type='text' id='UserPhone' name='UserPhone' /><br><br>

				Email:<br>
				<input type='text' id='UserEmail' name='UserEmail' /><br><br>

				Location Name:<br>
				<input type='text' id='LocationName' name='LocationName' /><br><br>

				Location OS:<br>
				<input type='text' id='LocationOS' name='LocationOS' /><br><br>

				Species Name:<br>
				<input type='text' id='SpeciesName' name='SpeciesName'/><br><br>

				Location Latitude:<br>
				<input type='text' id='LocationLatitude' name='LocationLatitude' /><br><br>

				Location Longitude:<br>
				<input type='text' id='LocationLongitude' name='LocationLongitude' /><br><br>

				Abundance:<br>
				<input type='text' id='Abundance' name='Abundance' /><br><br>

				Comment:<br>
				<input type='text' id='Comment' name='Comment' /><br><br>
				
				
			
			
		</div>
	</div>
	
	
	
	<input type='hidden' name='MAX_FILE_SIZE' value='10000000' />
	Choose a Site image to upload: <input name='resource[]' type='file' /><br><br />	

	<input type='hidden' name='MAX_FILE_SIZE' value='10000000' />
	Choose a specimen image to upload: <input name='resource[]' type='file' /><br><br />
	<input id='test2' type='submit' value='upload1'><br>
	</form>
	
	
	<?php
	var_dump($_POST);
	echo $_FILES['resource']['error'];
	print("<pre>");
	var_dump($_FILES);
	print("</pre>");
	if ( isset($_FILES['resource']) ) {
	 $POST_DATA = array(
	   'resource' =>  new CurlFile($_FILES['resource']['tmp_name'][0])
	   
 	);
	}
	else{echo "NO!!!!";}
	if ( isset($_FILES['resource']) ) {
	 $POST_DATA2 = array(
	   'resource' =>  new CurlFile($_FILES['resource']['tmp_name'][1])
	   
 	);
	}
	else{echo "NO!!!!";}

	
	//move_uploaded_file($_FILES["uploadedfile"], $resource);
	$submit='Submit';

	$timestamp=time();

	
	
	print("<pre>");

	print("<br />");

	
	
	
	$ch=curl_init('http://users.aber.ac.uk/mta2/groupapi/addResource.php');
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $POST_DATA);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$photoSpecimen=curl_exec($ch);
	curl_close($ch);
 
	var_dump($photoSpecimen);
	echo $photoSpecimen;
	$ch=curl_init('http://users.aber.ac.uk/mta2/groupapi/addResource.php');
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $POST_DATA2);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$photoScene=curl_exec($ch);
	curl_close($ch);
	var_dump($photoScene);
	
	$specimen=array(
		'SpeciesName' => $_POST['SpeciesName'],
		'LocationLatitude' => (float)$_POST['LocationLatitude'],
		'LocationLongitude' => (float)$_POST['LocationLongitude'],
		'Abundance' => (int)$_POST['Abundance'],
		'Comment' => $_POST['Comment'],
		'SpecimenPhoto' => "$photoSpecimen",
		'ScenePhoto' => "$photoScene");


	$record=array(
		'UserName' => $_POST['UserName'],
		'UserPhone' => $_POST['UserPhone'],
		'UserEmail' => $_POST['UserEmail'],
		'ReserveID' => $_POST['LocationName'],
		
		'Specimens' => array($specimen),
		'Timestamp' => $timestamp);


	$json = json_encode($record);

	echo $json;

	$url='http://users.aber.ac.uk/mta2/groupapi/addRecord.php';

	$ch=curl_init($url);
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, array('record' => $json));
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$response=curl_exec($ch);
	curl_close($ch);
	print("</pre>");
	include 'footer.php';
	echo"done";

?>
