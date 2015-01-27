
<?php

	$title = "Add";
	include 'includes/edit_specimens_curl.php';

	include 'includes/img_curl.php';
	include "includes/header.php";
var_dump ($_GET['id']);
$id=$_GET['id'];
		
		$SpecimenPhoto=$object->SpecimenPhoto;
		$ScenePhoto=$object->ScenePhoto;
		
	?>
	<div id='content-boxes'>
		<h1 class='indent'>Add Record</h1>
		<h2>Please enter the details in the form below</h2>
		<div id='left-section'>
			<form enctype="multipart/form-data" class='add-record' name='formMain' action='edit_specimen.php' method='POST'>
<table>
<thead>
<tr>
<th>Location Name</th>
<td><input type='text' id='LocationName' name='LocationName' value='<?php echo $object->LocationName;?>'/></td>
</tr>
<tr>
<th>Location OS</th>
<td><input type='text' id='LocationOS' name='LocationOS' value='<?php echo $object->LocationOS;?>'/></td>
</tr>
<tr>
<th>Specimen Name</th>
<td><input type='text' id='SpeciesName' name='SpeciesName' value='<?php echo $object->SpeciesName;?>'/></td>
</tr>
<tr>
<th>Latitude</th>
<td><input type='text' id='LocationLatitude' name='LocationLatitude' value='<?php echo $object->LocationLatitude;?>'/></td>
</tr>
<tr>
<th>Longitude</th>
<td><input type='text' id='LocationLongitude' name='LocationLongitude' value='<?php echo $object->LocationLongitude;?>'/></td>

</tr>
<tr>
<th>Abundence</th>
<td><input type='number' placeholder='Enter 1-5' id='Abundance' name='Abundance' value='<?php echo $object->Abundance;?>'/></td>

</tr>
<tr>
<th>Comment</th>
<td><input type='text' id='Comment' name='Comment' value='<?php echo $object->Comment;?>'/></td>
</tr>
</table>

			

				
				<script>
$(document).ready(function()
{
    $('.image').error(function(){
        $(this).attr('src', './images/default_image.png');
    });
});
</script>		 
		<a class='fancybox' href='<?php echo $responsep ;?>' rel='gallery1'><img class='image' src='<?php echo $responsep;?>' alt='default'/></a>
				</td><td>
<a class='fancybox' href='<?php echo $responsepp ;?>' rel='gallery1'><img class='image' src='<?php echo $responsepp ;?>' alt='default'/></a>
				</td>
</tr>
			
	<input type='hidden' name='MAX_FILE_SIZE' value='10000000' />
	Choose a Site image to upload: <input name='resource[]' type='file' /><br><br />	

	<input type='hidden' name='MAX_FILE_SIZE' value='10000000' />
	Choose a specimen image to upload: <input name='resource[]' type='file' /><br><br />
	<input id='test2' type='submit' value='upload1'><br>
	</form>
			
		</div>
	</div>
	
	
	
	
	
	<?php
	if(isset($_POST['LocationName'])){
	if ( isset($_FILES['resource']) ) {
	 $POST_DATA = array(
	   'resource' =>  new CurlFile($_FILES['resource']['tmp_name'][0])
	   
 	);
	}
	if ( isset($_FILES['resource']) ) {
	 $POST_DATA2 = array(
	   'resource' =>  new CurlFile($_FILES['resource']['tmp_name'][1])
	   
 	);
	}
   
	
	//move_uploaded_file($_FILES["uploadedfile"], $resource);
	$submit='Submit';

	$timestamp=time();

	
	


	
	
	
	$ch=curl_init('http://users.aber.ac.uk/mta2/groupapi/addResource.php');
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $POST_DATA);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$photoSpecimen=curl_exec($ch);
	curl_close($ch);
 
	$ch=curl_init('http://users.aber.ac.uk/mta2/groupapi/addResource.php');
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $POST_DATA2);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$photoScene=curl_exec($ch);
	curl_close($ch);
	
	$specimen=array(
	
		'SpeciesName' => $_POST['SpeciesName'],
		'LocationLatitude' => (float)$_POST['LocationLatitude'],
		'LocationLongitude' => (float)$_POST['LocationLongitude'],
		'Abundance' => (int)$_POST['Abundance'],
		'Comment' => $_POST['Comment'],
		'SpecimenPhoto' => $SpecimenPhoto,
		'SpecimenID' => $id,
		'ScenePhoto' => $ScenePhoto);
		

	// $record=array(
		// 'UserName' => $_POST['UserName'],
		// 'UserPhone' => $_POST['UserPhone'],
		// 'UserEmail' => $_POST['UserEmail'],
		// 'ReserveID' => $_POST['LocationName'],
		
		// 'Specimens' => array($specimen),
		// 'Timestamp' => $timestamp);


	$json = json_encode($specimen);
var_dump($json);
	$url='http://users.aber.ac.uk/mta2/groupapi/updateSpecimen.php';

	$ch=curl_init($url);
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, array('specimen' => $json));
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$response=curl_exec($ch);
	curl_close($ch);
	
	}
	include 'includes/footer.php';

?>

