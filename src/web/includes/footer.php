<!--Doesn't need to be changed-->
<div id="footer-bar">
	<img class="footer-center" src="images/footer-logo.png"  alt="Logo"/>
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
		var agree=confirm("Are you sure you want to delete this file?");
if (agree)
     return true ;
else
     return false ;
}
	

	// -->

	</script>
	
<!-- ********************************************** -->
<!-- JavaScript at the bottom for fast page loading -->
<!-- ********************************************** -->

	<!-- Grab Google CDN's jQuery, fall back to local if offline -->
  		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
  		<script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.1.min.js"><\/script>')</script>
        
        
	<!-- FancyBox -->
		<script src="js/fancybox/jquery.fancybox.js"></script>
		<script src="js/fancybox/jquery.fancybox-buttons.js"></script>
		<script src="js/fancybox/jquery.fancybox-thumbs.js"></script>
        <script src="js/fancybox/jquery.easing-1.3.pack.js"></script>
		<script src="js/fancybox/jquery.mousewheel-3.0.6.pack.js"></script>
        
        <script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox();
	});
</script>
        

        
        <script type="text/javascript">
        	$(document).ready(function() {
				$(".various").fancybox({
					maxWidth	: 800,
					maxHeight	: 600,
					fitToView	: true,
					width		: 800,
					height		: 400,
					autoSize	: true,
					closeClick	: false,
					openEffect	: 'elastic',
					closeEffect	: 'none'
				});
			});
		</script>
		<script>
		$(document).ready(function() {
	$(".fancybox").fancybox({
		openEffect	: 'none',
		closeEffect	: 'none'
	});
});
</script>
	</div>
</div>
</body>
</html>