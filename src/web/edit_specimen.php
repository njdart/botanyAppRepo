<?php
$title = "Edit";
include "/includes/header.php";

// container start
echo "<div id='content-boxes'>
	<h1 class='indent'></h1>
	<h2>Basic layout, no functionality</h2>";

// right section start
	echo "<div id='right-section'>";

		echo "<img class='image' src='images/default_image.png'  alt='default'/><br>
			<a href='#'>Upload Image</a>
			<a href='#'>Save</a>
			<a href='#'>Cancel</a>
	</div>";
	// right section end

	// left section start
	echo "<div id='left-section'>
		<form class='edit-record' action='edit_record.php' method='post'>
			Scientific name: <input type='text' id='scientific' name='scientific' value='existing values  from db'/><br /><br />
			Pronunciation: <input type='text' id='pronunciation' name='pronunciation' value='existing values from db' /><br /><br />
			Common name: <input type='text' id='common' name='common' value='existing values  from db'/><br /><br />
			Family name: <input type='text' id='family' name='family' value='existing values  from db'/><br /><br />
			Plant type: <input type='text' id='plant' name='plant' value='existing values  from db'/><br /><br />
			Habit: <input type='text' id='habit' name='habit' value='existing values  from db'/><br /><br />
			Form: <input type='text' id='form' name='form' value='existing values  from db'/><br /><br />
			Origin: <input type='text' id='origin' name='origin' value='existing values  from db' /><br /><br />
			Location: <input type='text' id='location' name='location' value='existing values  from db' /><br /><br />
		</form>
	</div>
</div>";
// left ends

include '/includes/footer.php';
?>
