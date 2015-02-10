<?php
include "config.php";
session_save_path($CONFIG["session"]);
session_start();
?>
<html>
<head>
<script src="http://maps.googleapis.com/maps/api/js"></script>
		<script>
var myCenter=new google.maps.LatLng(<?php echo $_SESSION['latlong'];?>);

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
  // icon:'images/mapicon.png'
  });

marker.setMap(map);
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>

</head>

<body>
<div id="googleMap" style="width:760px;height:380px;"></div>

<!--Googlemap end-->
</body>

</html>