<?php
$title = "Reserves";
include "includes/header.php";
include "includes/reserves_curl.php";
?>

<div id='content-boxes-dblist-res'>
	<h1>Plant Database</h1>
	
	<table id='reserve'>
	<thead><tr>
	<th>Reserve name</th>
		<th>LocationOS</th>
		<th>Description</th>
		<th></th>
		<th></th>
		
	</thead></tr>
	<?php
	foreach($objres as $reserve)
	{
	$resID = $reserve->ReserveID;
	$reserveName= $reserve->LocationName;
	$reserveLocation = $reserve->LocationOS;
	$description = $reserve->Description;
	
	echo"<script>
			$(document).ready(function(){
				$('.image').error(function(){
					$(this).attr('src', './images/default_image.png');
				});
			});
		</script>
	<tr>
		<td>" . $reserveName . "</td>
		<td>" . $reserveLocation . "</td>
		<td>" . $description . "</td>";
		if (isset($_SESSION['pass'])){
			echo "<td><form action = 'includes/resdelete_curl.php' method= 'GET' name = 'deleteSpecimen'> 
				<button class = 'butdes deleteReserve' onclick='confirmation()' type='submit' name='resid' value=" . $resID . ">Delete</button> 
			</form></td>
			<td>
			
			<form action = 'edit_reserve.php' method= 'GET' name = 'deleteSpecimen'> 
				<button class = 'butdes ' type='submit' name='resID' value=" . $resID . ">Edit Reserve</button> 
			</form>
			
			</td>
			
			
			";
		}
		?>
		<td>
		<?php
			echo"<form action = 'plant_specimens.php' method= 'GET' name = 'getReserve'> 
			<input type='hidden' name='resID' id='resID' value='".$resID."'>
				<input type='submit' class='butdes' value='View' > 
			</form>
		</td>
	</tr>";
	
	}
	
	?>

</table>
</div>

<?php
	 

include 'includes/footer.php';
?>
