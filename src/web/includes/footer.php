<!--Doesn't need to be changed-->
<div id="footer-bar">
	<a class="footer-left" href="sitemap.php">Site Map</a>
	<img class="footer-center" src="images/footer-logo.png"  alt="Logo"/>
	<a href='about.php' class='download'>Download App</a>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#orderby").change(function(){
				$( "#orderby option:selected").each(function(){
                    $(".box").hide();
					if($(this).attr("value")=="speciesName"){
						$(".speciesName").show();
					}
					if($(this).attr("value")=="locationName"){
						$(".locationName").show();
					}
					if($(this).attr("value")=="userName"){
						$(".userName").show();
					}
					if($(this).attr("value")=="timeStamp"){
						$(".timeStamp").show();
					}
					if($(this).attr("value")=="abundance"){
						$(".abundance").show();
					}
				});
			});
		});
	</script>
	<script type="text/javascript">
	<!--
	function confirmation() {
		var answer = confirm("Delete Specimen?")
		if (answer){
			alert("Specimen <?php echo $id ?> has been deleted")
			window.location = "http://www.google.com/";
		}else{
			alert("Thanks for sticking around!")
		}
	}

	// -->

	</script>
	</div>
</div>
</body>
</html>