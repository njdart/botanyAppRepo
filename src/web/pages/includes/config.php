<?php
//database values
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "stevesshop";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);

// Connection error checking
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

?>