<?php
include "includes/config.php";
session_save_path($CONFIG["session"]);
	$title = "Add Specimen";
	include "includes/header.php";
	include "test.php";

	if(!isset($_SESSION['pass'])){
header('Location:plant_specimens.php');
}
	?>
	<div id='content-boxes'>
		<h1 class='indent'>Add Specimen</h1>
		<p>Please enter the details in the form below</p>
		
			<form enctype="multipart/form-data" class='add-record' name='formMain' action='add_specimen.php' method='POST'>
			<div id='right-add-section'>
				<table class='addpictures'>
					<tr><br>	
						<th>Scene Photo</th>
						<td><input type='hidden' name='MAX_FILE_SIZE' value='10000000' />Choose a Site image to upload:<br> <input name='resource[]' type='file' /></td>
					</tr>
					<tr>
						<th>Specimen Photo</th>
						<td><input type='hidden' name='MAX_FILE_SIZE' value='10000000' />Choose a specimen image to upload: <input name='resource[]' type='file' /></td>
					</tr>
					<tr>
					<th></th>
						<td><input id='test2' type='submit' value='Confirm Edit'></td>
					</tr>
				</table>
			</div>
		<div id='left-add-section'>
				<table class='add'>
					<tr >
						<th>Username<th>
							<td><input type='text' id='UserName' name='UserName' required='true'/></td>
					</tr>
					<tr>	
						<th>Phone<th>
							<td><input type='text' id='UserPhone' name='UserPhone' /></td>
					</tr>
					<tr>	
						<th>Email<th>
							<td><input type='text' id='UserEmail' name='UserEmail' /></td>
					</tr>	
						<th>Reserve<th>
							<!--<td><input type='text' id='LocationName' name='LocationName' /></td>-->
							<td><select name = 'locid'><?php
									foreach($array as $option){
									echo "<option value='".$option[0]."'>".$option[1]."</option>";
									}
									
									
									?>
									</select>
									</td>
				
					<tr>	
						<th>Species Name<th>
							<td><input type='text' id='SpeciesName' name='SpeciesName'/></td>
					</tr>
					<tr>	
						<th>Latitude<th>
							<td><input type='text' id='LocationLatitude' name='LocationLatitude' /></td>
					</tr>
					<tr>	
						<th>Longitude<th>
							<td><input type='text' id='LocationLongitude' name='LocationLongitude' /></td>
					</tr>
					<tr>	
						<th>Abundence<th>
							<td>
							<select id='Abundance' name='Abundance'>
								<option class='optionwidth' value='1'>Rare</option>
								<option class='optionwidth' value='2'>Occasional</option>
								<option class='optionwidth' value='3'>Frequent</option>
								<option class='optionwidth' value='4'>Abundant</option>
								<option class='optionwidth' value='5'>Dominant</option>
							</select>
							</td>
					</tr>
					<tr>	
						<th>Comment<th>
						<td><input type='text' id='Comment' name='Comment' /></td>
					</tr>
				</table>
		</div>
			</form>
	</div>
	
	<?php
	include 'includes/footer.php';
	if ( isset($_FILES['resource']) ) {
		$POST_DATA = array(
		   'resource' =>  new CurlFile($_FILES['resource']['tmp_name'][0])
		   
		);
	
		$ch=curl_init('http://users.aber.ac.uk/mta2/groupapi/addResource.php');
		curl_setopt($ch, CURLOPT_POST, 1);
		curl_setopt($ch, CURLOPT_POSTFIELDS, $POST_DATA);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		$photoSpecimen=curl_exec($ch);
		curl_close($ch);
	} else { $photoSpecimen = -1; }
	if ( isset($_FILES['resource']) ) {
		$POST_DATA2 = array(
		   'resource' =>  new CurlFile($_FILES['resource']['tmp_name'][1])
		   
		);
		$ch=curl_init('http://users.aber.ac.uk/mta2/groupapi/addResource.php');
		curl_setopt($ch, CURLOPT_POST, 1);
		curl_setopt($ch, CURLOPT_POSTFIELDS, $POST_DATA2);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		$photoScene=curl_exec($ch);
		curl_close($ch);
	} else { $photoScene = -1; }

	
	//move_uploaded_file($_FILES["uploadedfile"], $resource);
	$submit='Submit';

	$timestamp=time();

	
	


	
	
 

	if(!$photoSpecimen) $photoSpecimen = -1;
	if(!$photoScene) $photoScene = -1;
	
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
		'ReserveID' => (int)$_POST['locid'],
		
		'Specimens' => array($specimen),
		'Timestamp' => $timestamp);


	$json = json_encode($record);
	var_dump($json);

	$url='http://users.aber.ac.uk/mta2/groupapi/addRecord.php';

	$ch=curl_init($url);
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, array('record' => $json));
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$response=curl_exec($ch);
	curl_close($ch);
	var_dump($response);

	?>
