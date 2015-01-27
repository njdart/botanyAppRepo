<?php
include "includes/config.php";
session_save_path($CONFIG["session"]);
include "test.php";

$title = "Edit Specimen";
include "includes/header.php";

include "includes/record_curl.php";
include "includes/config.php";

if (!isset($_SESSION['pass']))
	{
	header('Location:plant_specimens.php');
	}

?>
	<div id='content-boxes'>
		<h1 class='indent'>Edit Specimen</h1>
		<p>Please enter the details in the form below</p>
			<form enctype="multipart/form-data" class='edit-specimen' name='formMain' action='edit_specimen.php' method='POST'> 
	
	<div id='right-add-section'>
	<table class='addpictures'>
	<tr>
						<th>Scene Photo</th>
						<td><input type='hidden' name='MAX_FILE_SIZE' value='10000000' />Choose a Site image to upload: <input name='resource[]' type='file' /></td>
					</tr><br>
					<tr>
						<th>Specimen Photo</th>
						<td><input type='hidden' name='MAX_FILE_SIZE' value='10000000' />Choose a specimen image to upload: <input name='resource[]' type='file' /></td>
					</tr>
					<tr>
					<th></th>
						<td><input id='test2' type='submit' value='upload1'></td>
					</tr>
				</table>
	
	
	
	
		</div>
		<div id='left-add-section'>
			<table class='add'>
				<tr>
				<td><input type='hidden' id='ID' name='SpecimenID' value='<?php echo $_GET['id'];?>'></td>
			
				</tr>
				<tr>
				<th>Species Name</th>
				<td><input type='text' id='SpeciesName' name='SpeciesName' value='<?php
echo $object->SpeciesName; ?>'required='true'/></td>
			
				</tr>
				<tr>
				<th>Latitude</th>
				<td><input type='text' id='LocationLatitude' name='LocationLatitude' value='<?php
echo $object->LocationLatitude; ?>'required='true'/></td>
			
				</tr>
				<tr>
				<th>Longitude</th>
				<td><input type='text' id='LocationLongitude' name='LocationLongitude' value='<?php
echo $object->LocationLongitude; ?>'required='true'/></td>
			
				</tr>
				<tr>
				<th>Abundance</th>
				<td><input type='number' id='Abundance' name='Abundance' value='<?php
echo $object->Abundance; ?>'required='true'/></td>
			
				</tr>
				<tr>
				<th>Comment</th>
				<td><input type='text' id='Comment' name='Comment' value='<?php
echo $object->Comment; ?>'required='true'/></td>
			
				</tr>
				</table>
				

			
	<?php


?>
	</form>
			
		</div>
	</div>
	
	
	
	
	
	<?php
include 'includes/footer.php';

// sets the selected image files as a resource

if (isset($_FILES['resource']))
	{
	$POST_DATA = array(
		'resource' => new CurlFile($_FILES['resource']['tmp_name'][0])
	);
	}

if (isset($_FILES['resource']))
	{
	$POST_DATA2 = array(
		'resource' => new CurlFile($_FILES['resource']['tmp_name'][1])
	);
	}

$timestamp = time(); //gets the timestamp in unix time

// uploads the first image file

$ch = curl_init($CONFIG["api"] . '/addResource.php');
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, $POST_DATA);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$photoSpecimen = curl_exec($ch);
curl_close($ch);

// uploads the second image file

$ch = curl_init($CONFIG["api"] . '/addResource.php');
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, $POST_DATA2);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$photoScene = curl_exec($ch);
curl_close($ch);

if (isset($_POST['SpeciesName']))
	{

	// creates an array for specimen from the form data provided by the user

	$specimen = array(
		'SpecimenID' => $_POST['SpecimenID'],
		'SpeciesName' => $_POST['SpeciesName'],
		'LocationLatitude' => (float)$_POST['LocationLatitude'],
		'LocationLongitude' => (float)$_POST['LocationLongitude'],
		'Abundance' => (int)$_POST['Abundance'],
		'Comment' => $_POST['Comment'],
		'SpecimenPhoto' => "$photoSpecimen",
		'ScenePhoto' => "$photoScene"
		
	);
	$json = json_encode($specimen); //creates a record as a json object
	$url = $CONFIG["api"] . '/updateSpecimen.php';

	// sends the json object to the server

	$ch = curl_init($url);
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, array(
		'specimen' => $json
	));
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$response = curl_exec($ch);
	curl_close($ch);
	}

?>
