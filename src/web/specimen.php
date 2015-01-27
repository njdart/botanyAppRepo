<?php
$title = "Specimen";
include "includes/record_curl.php";
include "includes/header.php";
include "includes/img_curl.php";
if (!is_object($object)){
	header('Location: plant_specimens.php');
}
if (!isset($_GET['id']) || empty($_GET['id'])){
	header('Location: plant_specimens.php');
	exit(); 
}
$_SESSION['latlong']="$lat,$long";

?>


<?php
// right section

//record navigation
$idp = $id - 1;
echo "<a class='previous navigation' href = 'specimen.php?id=" . $idp . "'><<</a>";
$idn = $id + 1;
echo "<a class='next navigation' href = 'specimen.php?id=" . $idn . "'>>></a>";
//end record navigation
?>
<div id='content-boxes'>
	<h1 >Specimen </h1><br>
	<h2 class='indent'><?php echo $object->SpeciesName; ?></h2>
	<div id='right-section'>

		


		 <table class='centered ff'>
			<th>Specimen Photo</th>
			<th>Scene Image</th>
			<tr>
				<td>
				
		<script>
$(document).ready(function()
{
    $('.image').error(function(){
        $(this).attr('src', './images/default_image.png');
    });
});
</script>		 
		<a class='fancybox' href='<?php echo $responsep ;?>' rel='gallery1'><img class='image' src='<?php echo $responsep;?>' alt='default'/></a>
				</td><td>
<a class='fancybox' href='<?php echo $responsepp ;?>' rel='gallery1'><img class='image' src='<?php echo $responsepp ;?>' alt='default'/></a>
				</td>
</tr>
<tr>
<td>
		
<!--Googlemap popup-->
<a class="butdes various fancybox.iframe" href="includes/map.php">Where to find</a>
<!--Googlemap end-->
</td>

	<?php
		if (isset($_SESSION['pass'])){
		echo "<td>";
			echo "<form action = 'includes/delete_curl.php' method= 'GET' name = 'deleteSpecimen'> 
				<button class = 'butdes deleteSpecimen' onclick='confirmation()' type='submit' name='id' value=" . $id . ">Delete</button> 
			</form>";
echo"</td>";
echo"<td>
<a href='edit_specimens.php'>Edit Specimens</a>
</td>";

		}
echo "</tr>
</table>";
//end error button
echo"</div>";
// echo"</div>";
// right section end

// left section
//verticle table 
	echo "<div id='left-section'>";
		echo "<div id=spectable> ";
			echo "<table class='specimendetails'>";
				echo "<tr>";
					echo "<th>User Name</th>";
					echo "<td>";
						echo $object->UserName;
					echo "</td>";
				echo "</tr>";
				echo "<tr>";
					echo "<th>Location Name</th>";
					echo "<td>";
						echo $object->LocationName;
					echo "</td>";
				echo "</tr>";
				echo "<tr>";
					echo "<th>SpecimenID</th>";
					echo "<td>";
						echo $object->SpecimenID;
					echo "</td>";
				echo "</tr>";
				echo "<tr>";
					echo "<th>SpeciesName</th>";
					echo "<td>";
						echo $object->SpeciesName;
					echo "</td>";
				echo "</tr>";
				echo "<tr>";
					echo "<th>LocationOS</th>";
					echo "<td>";
						echo $object->LocationOS;
					echo "</td>";
				echo "</tr>";
				echo "<tr>";
					echo "<th>Abundance</th>";
					echo "<td>";
					 $abundances = array( 1 => "Rare", 2 => "Occassional", 3 => "Frequent",
                             4 => "Abundant", 5 => "Dominant" );
						echo $abundance = $abundances[$object->Abundance];
					echo "</td>";
				echo "</tr>";
				echo "<tr>";
					echo "<th>Date Submitted</th>";
					echo "<td>";
						echo date('d-m-Y', $object->Timestamp);
					echo "</td>";
				echo "</tr>";
				echo "<tr>";
					echo "<th>Time</th>";
					echo "<td>";
						echo date('h:i:s', $object->Timestamp);
					echo "</td>";
				echo "</tr>";
				echo "<tr>";
					echo "<th>Comment</th>";
					echo "<td>";
						echo $object->Comment;
					echo "</td>";
				echo "</tr>";
			echo "</table>";
	echo "</div>
	</div>
</div>";
// left section end

include 'includes/footer.php';
?>
