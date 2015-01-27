<?php
$title = "Reserves";
include "includes/header.php";
include "includes/reserves_curl.php";
include "includes/specimens_curl.php";
include "includes/filter.php";
?>
<div id='content-boxes-dblist'>
	<h1>Reserve Database</h1>
<br>
	<form class='advanced-search' action='plant_specimens.php' method='post'>
		<!-- <input type='submit' name='submitadvsearch' class='advancedsearchbutton' src='images/search.png' alt='Search' /> -->
		<input id='search' type='text' size='50'  class='simpletext' name='search' placeholder='Enter the plant you want to find' value=''/>
		<select id='plant-search-type' name='searchfield'>
			<option value='speciesName'>Species Name</option>
			<option value='locationName'>Location</option>
			<option value='userName'>User</option>
		</select>
	</form>
    

    <table id='results'>
	<a name="top"></a>
        <thead><tr>
			<th style="width: 25%;">Specimen Name</th>
			<th style="width: 25%;">Reserve</th>
			<th style="width: 20%;">User Name</th>
			<th style="width: 15%;">Date</th>
			<th style="width: 15%;">Abundance</th>
            <th style="width: 10%;"></th>
		</tr></thead>
<tr>
	<td></td>


</tr>		
        
	</table>
	</div>
     <?php
	 echo $_POST['resID'];
include 'includes/footer.php';
?>
