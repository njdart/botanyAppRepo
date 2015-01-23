<?php
$title = "Add";

// include 'includes/config.php';

include "/includes/header.php";

echo "<div id='content-boxes'>
<h1 class='indent'>Welcome</h1>
<h2>Basic layout, no functionality</h2>
<div id='right-section'>";

// if no picture is attatched, use default image2wbmp

echo "<img class='image' src='images/default_image.png'  alt='default'/>
<br />
<a href='#'>Upload Image</a>
<br /><br /><br />
<a href='#'>Save</a>
<a href='#'>Cancel</a>


</div>
<div id='left-section'>
<form class='edit-record' action='edit_record.php' method='post'>
<!--value will have some kind of php code that will get the value of the specific specimen in the specific field. -->
Scientific name: <input type='text' id='scientific' name='scientific'/><br /><br />
Pronunciation: <input type='text' id='pronunciation' name='pronunciation' /><br /><br />
Common name: <input type='text' id='common' name='common' /><br /><br />
Family name: <input type='text' id='family' name='family' /><br /><br />
Plant type: <input type='text' id='plant' name='plant' /><br /><br />
Habit: <input type='text' id='habit' name='habit' /><br /><br />
Form: <input type='text' id='form' name='form' /><br /><br />
Origin: <input type='text' id='origin' name='origin' /><br /><br />
Location: <input type='text' id='location' name='location' /><br /><br />
</form>
</div>
</div>";
include '/includes/footer.php';

?>
