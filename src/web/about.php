<?php
include "includes/config.php";
session_save_path($CONFIG["session"]);
$title = "About";
include "includes/header.php";
?>

<div id='content-boxes' class='top-index'>
		<p class='main onecolumn'><span class='about'>About</span>We are a small startup tech company that delivers bespoke software solutions, with a high standard of excellence and flexibility. A group of Aberystwyth Computer Science alumni, we integrate our clients into every step of the development process to make sure that you get the highest quality software for your investment. We believe that the current state of the industry is unacceptable, where the client may receive an unsuitable, unrecognisable, or unfinished application. Botany App is a groundbreaking application developed for Nigel Hardy, providing the capability or botanists to record and search plants either on the go through an Android App, or at home via the website. With the ability to store records locally until a connection can be established, it provides flexibility for those working in the field, however isolated they may be.</p>
</div>


<div id='content-boxes' class='bottom-index'>
		<p class='main onecolumns'><span class='downloadh'>Download</span>Botany App is a groundbreaking application developed for Nigel Hardy, providing the capability or botanists to record and search plants either on the go through an Android App, or at home via the website. With the ability to store records locally until a connection can be established, it provides flexibility for those working in the field, however isolated they may be.</p>
</div>
<?php
include "includes/footer.php";
?>

