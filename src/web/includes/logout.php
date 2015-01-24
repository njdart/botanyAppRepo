<?php
Session_start();
Session_destroy();
header('location:'. $_SERVER['HTTP_REFERER'].'?msg='.urlencode(base64_encode("You have been successfully logged out!")));

?>