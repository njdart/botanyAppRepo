<?php
session_start(); ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta name="description" content=""/> 
		<meta name="keywords" content=""/> 
		<meta name="author" content="Botany App group Web team"/> 
		<meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
		<title><?php echo $title; ?></title>
		<link href="css/style.css?v=<?=time();?>" rel='stylesheet' type='text/css'/>
		<link href="css/table.css?v=<?=time();?>" rel='stylesheet' type='text/css'/>
		<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
	   <link rel="stylesheet" href="css/fancybox/jquery.fancybox-buttons.css">
        <link rel="stylesheet" href="css/fancybox/jquery.fancybox-thumbs.css">
        <link rel="stylesheet" href="css/fancybox/jquery.fancybox.css">
		<!--google maps javascript code-->
		<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
		

		<link rel="shortcut icon" type="image/ico" href="images/favicon.ico"/>


		<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
	</head>	
		<body>
			<header class="header-bar">
<?php
				if (!isset($_SESSION['pass'])){
					echo "<form class='pass' action='includes/authenticate.php' method='POST'>Password:
						<input type='text'  id='pass' class='passinput' name='pass' class='pass'/>
						<input type='submit' class='passsubmit' id='submit' name='submitpass'/>
					</form>";
				}else{
					echo "<a class='pass' href = 'includes/logout.php'>Logout</a>";
				}
				echo"<img class='logo' href = 'index.php' src='images/main_logo.png'  alt='logo'/>";

?>
		</header>
			<!--Doesn't need to be changed-->
		<div id='container'>
			<nav>
				<ul>
					<li><a href='index.php'>Home</a></li>
					<li><a href='reserves.php'>Reserves</a></li>
					<li><a href='plant_specimens.php'>Plants</a></li>
					<li><a href='add_specimen.php'>Add Specimens</a></li>
					<li><a href='about.php'>About</a></li>
				</ul>
			</nav>
<?php
				
				if (isset($_GET['msg'])){
					echo '<br><p class="messages">' . base64_decode(urldecode($_GET['msg'])) . '</p>';
				}

				if (isset($_GET['loginmsg'])){
					echo '<br><p class="messages">' . base64_decode(urldecode($_GET['loginmsg'])) . '</p>';
				}

				if (isset($_GET['errormsg'])){
					echo '<br><p class="messages">' . base64_decode(urldecode($_GET['errormsg'])) . '</p>';
				}
				?>