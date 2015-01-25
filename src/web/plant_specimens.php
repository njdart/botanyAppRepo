<?php
$title = "Plants";
include "includes/header.php";
include "includes/specimens_curl.php";
include "includes/filter.php";

// main section
echo "<div id='content-boxes-dblist'>
	<h1>Plant Database</h1>";

// Advance form start
	echo "<form class='advanced-search' action='plant_specimens.php' method='post'>
		<input type='submit' name='submitadvsearch' class='advancedsearchbutton' src='images/search.png' alt='Search' />
		<input type='text' size='50' id='search' class='simpletext' name='search' placeholder='Enter the plant you want to find' value=''/>
		<select name='searchfield'>
			<option value='speciesName'>Species Name</option>
			<option value='locationName'>Location</option>
			<option value='userName'>User</option>
		</select>
	</form>";
// Advance form end

//table starts
	echo "<table>";
//headings
		echo"<tr>
			<th>Specimen Name</th>
			<th>Location</th>
			<th>User Name</th>
			<th>Date</th>
			<th>Abundance</th>
			<th></th>
		</tr>";
		
//loop round all specimens
		$abundances = array( 1 => "Rare", 2 => "Occassional", 3 => "Frequent",
                             4 => "Abundant", 5 => "Dominant" );
		foreach($object as $specimens){
			$id = $specimens->SpecimenID;
			$speciesName = $specimens->SpecimenName;
			$locationName = $specimens->LocationName;
			$userName = $specimens->UserName;
			$timeStamp = date('Y-m-d', $specimens->Timestamp);
			$abundance = $abundances[$specimens->Abundance];
			echo "<tr>
				<td>" . $speciesName . "</td>
				<td>" . $locationName . "</td>
				<td>" . $userName . "</td>
				<td>" . $timeStamp . "</td>
				<td>" . $abundance . "</td>
				<td>
					<form action = 'specimen.php' method= 'GET' name = 'getSpecimen'> 
						<button class = 'viewSpecimen' type='submit' name='id' value=" . $id . "> View</button> 
					</form>
				</td>
			</tr>";
		}
//end loop
	echo "</table>
</div>";
// main section end

include 'includes/footer.php';
?>