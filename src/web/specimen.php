<?php
$title = "Specimen";
include "/includes/record_curl.php";
include "/includes/header.php";
include "/includes/img_curl.php";
?>

<!--Googlemap popup-->
<div id="mapPopUp">
	<div class="popupBoxWrapper">
		<div class="popupBoxContent">
			<div id="googleMap" style="width:500px;height:380px;"></div>
			<p>Click <a href="javascript:void(0)" onclick="toggle_visibility('mapPopUp');">here</a> to the map!</p>
		</div>
	</div>
</div>
<!--Googlemap end-->

<?php
// right section

//record navigation
$idp = $id - 1;
echo "<a href = 'specimen.php?id=" . $idp . "'>Previous</a>";
$idn = $id + 1;
echo "<a href = 'specimen.php?id=" . $idn . "'>Next</a>";
//end record navigation

//start content boxes
//right section start
echo "<div id='content-boxes'>
	<h1 class='indent'>Welcome</h1>
	<h2>Basic layout, no functionality. Will show specific specimin</h2>
	<div id='right-section'>";

//delete button
		if (isset($_SESSION['pass'])){
			echo "<form action = 'includes/delete_curl.php' method= 'GET' name = 'deleteSpecimen'> 
				<button class = 'deleteSpecimen' onclick='confirmation()' type='submit' name='id' value=" . $id . "> delete</button> 
			</form>";
		}
//end error button
		
//if no image, assigns default image
		echo"<script>
			$(document).ready(function(){
				$('.image').error(function(){
					$(this).attr('src', './images/default_image.png');
				});
			});
		</script>";
//end script
		
		echo "<p>specimen</p><img class='image' src='" . $responsep . "' alt='default'/>";
		echo "<p>scene</p><img class='image' src='" . $responsepp . "' alt='default'/>";

//where link to show map on pop up
		echo "<a href='javascript:void(0)' onclick='toggle_visibility('mapPopUp');'>Where to find</a>
	</div>";
// right section end

// left section
//verticle table 
	echo "<div id='left-section'>";
		echo "<table>";
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
					echo $object->Abundance;
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
</div>";
// left section end

include '/includes/footer.php';
?>
