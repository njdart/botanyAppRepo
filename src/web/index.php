<?php
$title = "Home";
include "includes/header.php";

// first top boxes
echo "<div id='content-boxes' class='top-index'><br>
	<p class='main twocolumns'><span class='welcome'>Welcome</span> It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
</div>";
// first box ends

// simple search bar
echo "<form class='simple-search' action='plant_specimens.php' method='post'>
	<input type='text' id='search' size='50' class='simpletext' name='search' placeholder='Enter the plant you want to find' value=''/>
	<input type='submit' class='searchbutton' src='images/search.png' name='submitsearch' alt='Search'/>
</form>";
// end simple search bar

// bottom box
echo "<div id='content-boxes' class='bottom-index'>
	<p class='main twocolumns'>It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
	
	<p>The information provided on this and other pages by Group 2, is under our own personal responsibility and not that of Aberystwyth University. Similarly, any opinions expressed are my own and are in no way to be taken as those of A.U.
 
I declare that the contents of this site are entirely our own work. 
</p>
</div>";
// bottom box end

include "includes/footer.php";
?>
