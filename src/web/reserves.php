<?php
include "includes/config.php";
session_save_path($CONFIG["session"]);
$title = "Reserves";
include "includes/header.php";
include "includes/reserves_curl.php";
?>

<div id='content-boxes-dblist-res'>
	<h1>Plant Database</h1>
	<a class='butdesa' href='add_reserve.php'>Add Reserve</a>
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
?>	
	<script>
			$(document).ready(function(){
				$('.image').error(function(){
					$(this).attr('src', './images/default_image.png');
				});
			});
		</script>
	<tr>
		<td> <?php echo $reserveName;?></td>
		<td><?php echo $reserveLocation ;?></td>
		<td><?php echo $description ;?></td>
		<?php
		if (isset($_SESSION['pass'])){
			?>
			
		<td><form action = 'includes/resdelete_curl.php' method= 'GET' name = 'deleteSpecimen'> 
				<button class = 'butdes deleteReserve' onclick='confirmation()' type='submit' name='resid' value="<?php echo $resID; ?>">Delete</button> 
			</form></td>
			<td>
			
			<form action = 'edit_reserve.php' method= 'GET' name = 'deleteSpecimen'> 
				<button class = 'butdes ' type='submit' name='resID' value="<?php echo $resID; ?>">Edit Reserve</button> 
			</form>
			
			</td>
			
			
			<?php
		}
		?>
		<td>
		
		<form action = 'plant_specimens.php' method= 'GET' name = 'getReserve'> 
			<input type='hidden' name='resID' id='resID' value='<?php echo $resID;?>'>
				<input type='submit' class='butdes' value='View' > 
			</form>
		</td>
	</tr>
	<?php
	}
	
	?>

</table>
</div>

<?php
	 

include 'includes/footer.php';
?>
