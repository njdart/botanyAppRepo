<?php
$title = "Home";
include "header.php";
//first top box
echo "<div id='content-boxes' class='top-index'>
<br>
<h1 class='indent'>Welcome</h1>
<p class='main twocolumns'>It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
</p>
</div>";
//first box ends


//search bar
echo "<form class='simple-search' action='plant_specimens.php' method='post'>
<input type='text' id='search' size='50' class='simpletext' name='search' placeholder='Enter the plant you want to find' value=''/>
<input type='submit' class='searchbutton' name='submitsearch' src='images/search.png' alt='Search' />
</form>";
//end search bar


//bottom box
echo "<div id='content-boxes' class='bottom-index'>
<p class='main twocolumns'>It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
</p>
</div>";

//bottom box end
include "footer.php";
?>