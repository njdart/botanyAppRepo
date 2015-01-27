<?php
	$title = "Add";
	include 'includes/edit_specimens_curl.php';
	include 'includes/img_curl.php';
	include "includes/header.php";

	?>
	<div id='content-boxes'>
		<h1 class='indent'>Add Record</h1>
		<h2>Please enter the details in the form below</h2>
		
		<div id='left-section'>
			<form enctype="multipart/form-data" class='add-record' name='formMain' action='edit_specimen.php' method='POST'>
	Location Name:<br>
				<input type='text' id='LocationName' name='LocationName' value='<?php echo $object->LocationName;?>'/><br><br>

				Location OS:<br>
				<input type='text' id='LocationOS' name='LocationOS' value='<?php echo $object->LocationOS;?>'/><br><br>

				Species Name:<br>
				<input type='text' id='SpeciesName' name='SpeciesName' value='<?php echo $object->SpeciesName;?>'/><br><br>

				Latitude:<br>
				<input type='text' id='LocationLatitude' name='LocationLatitude' value='<?php echo $object->LocationLatitude;?>'/><br><br>

				Longitude:<br>
				<input type='text' id='LocationLongitude' name='LocationLongitude' value='<?php echo $object->LocationLongitude;?>'/><br><br>

				Abundance:<br>
				<input type='number' placeholder='Enter 1-5' id='Abundance' name='Abundance' value='<?php echo $object->Abundance;?>'/><br><br>

				Comment:<br>
				<input type='text' id='Comment' name='Comment' value='<?php echo $object->Comment;?>'/><br><br>
				
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

	
	
	print("<pre>");

	print("<br />");

	
	
	
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
		'SpecimenPhoto' => "5",
		'SpecimenID' => "3",
		'ScenePhoto' => "6");


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
	echo "hello";
var_dump($response);
	curl_close($ch);
	include 'includes/footer.php';

?>
