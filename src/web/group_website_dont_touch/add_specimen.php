<?php
$title = "Add";
// include 'includes/config.php';
include "header.php";

echo "<div id='content-boxes'>
<h1 class='indent'>Add Record</h1>
<h2>Please enter the details in the form below</h2>
<div id='right-section'>";
//if no picture is attatched, use default image2wbmp
echo"<img class='image' src='images/default_image.png'  alt='default'/>
<br>
<a href='AddResource.php'>Upload Image</a>
<br><br><br>


</div>
<div id='left-section'>
<form class='add-record' action='add_specimen.php' method='GET'>
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

<input type='submit' id='Submit' name='Submit' />
</form>
</div>
</div>";

$submit='Submit';

$timestamp=time();

$specimen=array(
	'SpeciesName' => $_GET['SpeciesName'],
	'LocationLatitude' => (float)$_GET['LocationLatitude'],
	'LocationLongitude' => (float)$_GET['LocationLongitude'],
	'Abundance' => (int)$_GET['Abundance'],
	'Comment' => $_GET['Comment'],
	'SpecimenPhoto' => "0",
	'ScenePhoto' => "0");
	
	

$record=array(
	'UserName' => $_GET['UserName'],
	'UserPhone' => $_GET['UserPhone'],
	'UserEmail' => $_GET['UserEmail'],
	'LocationName' => $_GET['LocationName'],
	'LocationOS' => $_GET['LocationOS'],
	'Specimens' => array($specimen),
	'Timestamp' => $timestamp);

$json = json_encode($record);
print("<pre>");
var_dump ($specimen);

print("<br />");
var_dump ($record);

var_dump($json);

$url='http://users.aber.ac.uk/mta2/groupapi/addRecord.php';

$ch=curl_init($url);
var_dump ($ch);
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, array('record' => $json));
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$response=curl_exec($ch);
curl_close($ch);
var_dump($response);
print("</pre>");
include 'footer.php';
?>
