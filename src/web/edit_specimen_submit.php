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
		'SpecimenPhoto' => $_POST['ScenePhoto'],
		'SpecimenID' => $_POST['SpecimenID'],
		'ScenePhoto' => $_POST['ScenePhoto']);


	// $record=array(
		// 'UserName' => $_POST['UserName'],
		// 'UserPhone' => $_POST['UserPhone'],
		// 'UserEmail' => $_POST['UserEmail'],
		// 'ReserveID' => $_POST['LocationName'],
		
		// 'Specimens' => array($specimen),
		// 'Timestamp' => $timestamp);

		

	$json = json_encode($specimen);
	var_dump(json);
	$url='http://users.aber.ac.uk/mta2/groupapi/updateSpecimen.php';

	$ch=curl_init($url);
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, array('specimen' => $json));
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$response=curl_exec($ch);
	curl_close($ch);
	
	
//header( 'Location: plant_specimens.php' ) ;

?>