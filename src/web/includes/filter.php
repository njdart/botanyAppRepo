<?php


//filter column
echo "<div id='filtering'>
<a href='add_specimen.php'>Add Specimen</a>
<h3 class='centered sortingheading'>Sorting</h3>



<form action='plant_specimens.php' method='post'>
Order By
<select id='orderby' name='orderby'>
	<option value='speciesName'>Species Name</option>
	<option value='locationName'>Location</option>
	<option value='userName'>User</option>
	<option value='timeStamp'>Date</option>
	<option value='abundance'>Abundance</option>
</select>

<select name='ordertype'>
	<option value=''>Select an option</option>
	<option class='speciesName locationName userName box' value='ascending'>A-Z</option>
	<option class='speciesName locationName userName box' value='descending'>Z-A</option>
	<option class='timeStamp box' value='ascending'>Oldest</option>
	<option class='timeStamp box' value='descending'>Newest</option>
	<option class='abundance box' value='ascending'>Low-High</option>
	<option class='abundance box' value='descending'>High-Low</option>
	
</select>

<input type='submit' name='submitorder' value='Submit' />
</form>
";




//order script
if(isset($_POST['submitorder'])){
	$order= htmlspecialchars(stripslashes($_POST['orderby']));
	$ordertype= htmlspecialchars(stripslashes($_POST['ordertype']));
	}else{
	$order='id';
	$ordertype='DESC';
	
}
	
	echo"

</div>";

?>

