<!--Doesn't need to be changed-->
<div id="footer-bar">
<a class="footer-left" href="sitemap.php">Site Map</a>
<img class="footer-center" src="images/footer-logo.png"  alt="Logo"/>
<a href='about.php' class='download'>Download App</a>
<script type="text/javascript">
    $(document).ready(function(){
        $("#orderby").change(function(){
            $( "#orderby option:selected").each(function(){
                if($(this).attr("value")=="speciesName"){
                    $(".box").hide();
                    $(".speciesName").show();
                }
                if($(this).attr("value")=="locationName"){
                    $(".box").hide();
                    $(".locationName").show();
                }
                if($(this).attr("value")=="userName"){
                    $(".box").hide();
                    $(".userName").show();
                }
				if($(this).attr("value")=="timeStamp"){
                    $(".box").hide();
                    $(".timeStamp").show();
                }
				if($(this).attr("value")=="abundance"){
                    $(".box").hide();
                    $(".abundance").show();
                }
            });
        }).change();
    });
</script>
<script type="text/javascript">
<!--
function confirmation() {
	var answer = confirm("Delete Specimen?")
	if (answer){
		alert("Specimen <?php echo $id ?> has been deleted")
		window.location = "http://www.google.com/";
	}
	else{
		alert("Thanks for sticking around!")
	}
}
//-->
</script>
</div>
</div>
</body>
</html>