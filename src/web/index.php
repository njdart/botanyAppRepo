<?php
include "includes/config.php";
session_save_path($CONFIG["session"]);
$title = "Home";
include "includes/header.php";

// first top boxes
?>

<div id='content-boxes' class='top-index'><br>
	<p class='main twocolumns'><span class='welcome'>Welcome</span> This website is for making and viewing records for botanists to see how plants are spreading in there reserves. You can use the RESERVES page to view all the reserves currently availably, and from here you can view all the records associated with that reserve. The PLANTS page allows you to see all the records stored on the database, and both search and sort the records to your choosing. you can also view any of the records that are displayed and view where the records was make with the ‘where to find’ button. you can add new records to the database by going to the ADD SPECIMENS page where you can enter all the information for the record and upload both a site and a specimen photo.</p>
</div>

<form class='simple-search' action='plant_specimens.php' method='get'>
	<input type='text' id='search' size='50' class='simpletext' name='species' placeholder='Enter the plant you want to find' value=''/>
	<input type='submit' class='searchbutton' src='images/search.png' alt='Search'/>
</form>

<div id='content-boxes' class='bottom-index'>
	<p class='main twocolumns'>to find out more about how we made this then you can go to the ABOUT page where we explain what we did. As it is unlikely you would have access to a computer in a field there is a companion app for android devices. This app allows you to add records on the go and comes with some added benefits, such as using the GPS built into the phone rather than entering the longitude and latitude manually. The app also has the list of latin names stored into the app so it will auto fill you entry. The 	use of the camera will allow you to take the photos from within the app so you don’t need to take then in advance and search throw your phone to find them.</p>
</div>
<?php 
include "includes/footer.php";
?>