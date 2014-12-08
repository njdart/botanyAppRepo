<?php
$title = "Plants";
// include 'includes/config.php';
include "header.php";

//filter column
echo "<div id='filtering'>
<a href='add_specimen.php'>Add Specimen</a>
<h3 class='centered sortingheading'>Sorting</h3>
<form class='filtering' action='#' method='post'>
Plant type<br>
<select name='genreoption'>
<option value='Rock'>Rock</option>
<option value='Pop'>Pop</option>
<option value='Other'>Other</option>
<option value='World'>World</option>
</select><br>
Flowering time<br>
<select name='genreoption'>
<option value='Rock'>Rock</option>
<option value='Pop'>Pop</option>
<option value='Other'>Other</option>
<option value='World'>World</option>
</select><br>
Flower color<br>
<select name='genreoption'>
<option value='Rock'>Rock</option>
<option value='Pop'>Pop</option>
<option value='Other'>Other</option>
<option value='World'>World</option>
</select><br>
Fruit color<br>
<select name='genreoption'>
<option value='Rock'>Rock</option>
<option value='Pop'>Pop</option>
<option value='Other'>Other</option>
<option value='World'>World</option>
</select><br>
Landscape use<br>
<select name='genreoption'>
<option value='Rock'>Rock</option>
<option value='Pop'>Pop</option>
<option value='Other'>Other</option>
<option value='World'>World</option>
</select><br>
Hardiness zone<br>
<select name='genreoption'>
<option value='Rock'>Rock</option>
<option value='Pop'>Pop</option>
<option value='Other'>Other</option>
<option value='World'>World</option>
</select><br>
Location<br>
<select name='genreoption'>
<option value='Rock'>Rock</option>
<option value='Pop'>Pop</option>
<option value='Other'>Other</option>
<option value='World'>World</option>
</select><br>
<input type='submit' name='submitfilter' value='Filter' />
</form>
</div>";

//main section
echo "<div id='content-boxes-dblist'>
<h1>Welcome</h1>
<a href='edit_specimen.php'>Edit specimen page</a>";

//Advance form start
echo"<form class='advanced-search' action='#' method='post'>
<input type='image' class='advancedsearchbutton' src='images/search.png' alt='Search' />
<input type='text' size='50' class='simpletext' name='search' placeholder='Enter the plant you want to find' value=''/>
<select name='searchfield'>
<option value='title'>Track Title</option>
<option value='artist'>Artist</option>
<option value='album'>Album</option>
<option value='genre'>Genre</option>
</select>
</form>";
//Advance form end


//where the code for calling the database and the handles etc. 
echo"<p class='main twocolumns'>It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
</p>
</div>";
//main section end




include 'footer.php';
?>