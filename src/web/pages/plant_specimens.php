<?php
$title = "Plants";
include 'includes/config.php';
include "header.php";
include 'includes/filter.php';

//main section
echo "<div id='content-boxes-dblist'>
<h1>Plant Database</h1>";




//Advance form start
echo"<form class='advanced-search' action='plant_specimens.php' method='post'>
<input type='submit' name='submitadvsearch' class='advancedsearchbutton' src='images/search.png' alt='Search' />
<input type='text' size='50' id='search' class='simpletext' name='search' placeholder='Enter the plant you want to find' value=''/>
<select name='searchfield'>
<option value='id'>ID</option>
<option value='name'>name</option>
<option value='price'>price</option>
<option value='description'>description</option>
</select>
</form>";
//Advance form end


//simple search query
if(isset($_POST['submitsearch'])){
	$search= htmlspecialchars(stripslashes($_POST['search']));
	$sql = "SELECT * FROM products WHERE name like ('%$search%') ORDER BY $order $ordertype";
	echo "Your search criteria: ". $search;

//advanced search query
}else if(isset($_POST['submitadvsearch'])){
	$search= htmlspecialchars(stripslashes($_POST['search']));
	$searchfield= htmlspecialchars(stripslashes($_POST['searchfield']));
	echo "You have searched for: ". $search ." from ".$searchfield;
	$sql = "SELECT * FROM products WHERE $searchfield like ('%$search%') ORDER BY $order $ordertype";
}else{

//default query
	$sql = "SELECT * FROM products ORDER BY $order $ordertype";
}
$result = mysqli_query($conn, $sql);
echo"
<table><th>ID</th><th>Name</th><th>Price</th><th>Description</th>";

if (mysqli_num_rows($result) > 0) {
	while($row = mysqli_fetch_assoc($result)) {
		echo "<tr>";
		echo "<td>". $row['id'] ."</td>";
		echo "<td>". $row['name'] ."</td>";
		echo "<td>". $row['price'] ."</td>";
		echo "<td>". $row['description'] ."</td>";
		echo "</tr>";
	}
	echo"</table>";
}



echo"
</div>";
//main section end
include 'footer.php';
?>