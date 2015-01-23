<?php
$title = "Plants";
include "/includes/header.php";

include "/includes/specimens_json.php";

// main section

echo "<div id='content-boxes-dblist'>
	<h1>Plant Database</h1>";

// Advance form start

echo "<form class='advanced-search' action='plant_specimens.php' method='post'>
		<input type='submit' name='submitadvsearch' class='advancedsearchbutton' src='images/search.png' alt='Search' />
		<input type='text' size='50' id='search' class='simpletext' name='search' placeholder='Enter the plant you want to find' value=''/>
		<select name='searchfield'>
			<option value='id'>ID</option>
			<option value='name'>name</option>
			<option value='price'>price</option>
			<option value='description'>description</option>
		</select>
	</form>";

// Advance form end

echo "<table>
<tr>
    <th>Specimen Name</th>
    <th>Location</th>
    <th>User Name</th>
    <th>Date</th>
  </tr>";

foreach($object as $specimens)
	{
	$id = $specimens->SpecimenID;
	$speciesName = $specimens->SpecimenName;
	$locationName = $specimens->LocationName;
	$userName = $specimens->UserName;
	$timeStamp = $specimens->Timestamp;
	$specphoto = $specimens->SpecimenPhoto;
	echo "<tr>
		<td>" . $speciesName . "</td>
		<td>" . $locationName . "</td>
		<td>" . $userName . "</td>
		<td>" . $timeStamp . "</td>
		<td>
			<form action = 'Specimen.php' method= 'GET' name = 'getSpecimen'> 
				<button class = 'viewSpecimen' type='submit' name='id' value=" . $id . "> View</button> 
			</form>
		</td>
	</tr>";
	}

echo "</table>
</div>";

// main section end

include '/includes/footer.php';
?>