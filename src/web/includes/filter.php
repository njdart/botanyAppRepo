<?php
// filter column
echo "<div id='filtering'>
	<form action='plant_specimens.php' method='post'>
		
		<p>Order By</p>
		<select class='orderby' id='orderby' name='orderby'>
			<option value='speciesName'>Species Name</option>
			<option value='locationName'>Location</option>
			<option value='userName'>User</option>
			<option value='timeStamp'>Date</option>
			<option value='abundance'>Abundance</option>
		</select>

		<select name='ordertype' id='ordertype' class='ordertype'>
			<option value=''>Select an option</option>
			<option class='speciesName locationName userName box' value='ascending'>A-Z</option>
			<option class='speciesName locationName userName box' value='descending'>Z-A</option>
			<option class='timeStamp box' value='ascending'>Oldest</option>
			<option class='timeStamp box' value='descending'>Newest</option>
			<option class='abundance box' value='ascending'>Low-High</option>
			<option class='abundance box' value='descending'>High-Low</option>
		</select>
		<br>
		<br>
		<input type='submit' class='filter-submit' name='submitorder' value='Submit' />
	</form>";
	
echo "</div>";
?>
