<?php
session_start(); ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta name="description" content=""/> 
		<meta name="keywords" content=""/> 
		<meta name="author" content="Botany App group Web team"/> 
		<meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
		 <title><?php
echo $title; ?></title>
		<link href="css/style.css" rel='stylesheet' type='text/css'/>
		<link href="css/typography.css" rel='stylesheet' type='text/css'/>
       <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
	  <!--google maps javascript code-->
	   <script src="http://maps.googleapis.com/maps/api/js">
</script>
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script>
var myCenter=new google.maps.LatLng(<?php
echo $lat; ?>,<?php
echo $long; ?>);
function initialize()
{
var mapProp = {
  center:myCenter,
  zoom:8,
  mapTypeId:google.maps.MapTypeId.ROADMAP
  };
var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
var marker=new google.maps.Marker({
  position:myCenter,
  icon:'images/mapicon.png'
  });
marker.setMap(map);
}
google.maps.event.addDomListener(window, 'load', initialize);
</script>
		<link rel="shortcut icon" type="image/ico" href="images/favicon.ico"/>
<script type="text/javascript">
			<!--
			    function toggle_visibility(id) {
			       var e = document.getElementById(id);
			       if(e.style.display == 'block')
			          e.style.display = 'none';
			         else
			          e.style.display = 'block';
			    }

			// -->

		</script>
		
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>


	</head>	
	<body>
		<header class="header-bar">
			<img class="logo" src="images/main_logo.png"  alt="logo"/>
			<form class='pass' action='includes/authenticate.php' method='POST'>
				<input type='text' id='pass' name='pass' class='pass'/>
				<input type='submit' id='pass' name='submitpass'/>
			</form>
		</header>
			<!--Doesn't need to be changed-->
	<div id='container'>
		<nav>
			<ul>
				<li><a href='index.php'>Home</a></li>
				<li><a href='plant_specimens.php'>Plants</a></li>
				<li><a href='add_specimen.php'>Add Specimens</a></li>
				<li><a href='about.php'>About</a></li>
			</ul>
		</nav>
