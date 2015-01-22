<?php


//filter column
echo "<div id='filtering'>
<a href='add_specimen.php'>Add Specimen</a>
<h3 class='centered sortingheading'>Sorting</h3>";

//orderby form
echo"
<form action='plant_specimens.php' method='post'>
Order By
<select name='orderby'>
	<option value='name'>Name</option>
	<option value='price'>Price</option>
	<option value='description'>Description</option>
</select>
<select name='ordertype'>
	<option value='DESC'>DESC</option>
	<option value='ASC'>ASC</option>
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

