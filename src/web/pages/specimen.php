<?php
$title = "Specimen";
// include 'includes/config.php';
include "header.php";
?>
<!--where the maps stored-->
<div id="mapPopUp">
			<div class="popupBoxWrapper">
				<div class="popupBoxContent">
<div id="googleMap" style="width:500px;height:380px;"></div>
					<p>Click <a href="javascript:void(0)" onclick="toggle_visibility('mapPopUp');">here</a> to the map!</p>
					
				</div>
			</div>
		</div>


<?php
// right section
echo "<div id='content-boxes'>
<h1 class='indent'>Welcome</h1>
<h2>Basic layout, no functionality. Will show specific specimin</h2>
<div id='right-section'>
<a href='edit_specimen.php'>Edit</a>
<a href='#'>Remove</a>
<br><br>";
//if no picture is attatched, use default image
echo"<img class='image' src='images/default_image.png'  alt='default'/>
<br>";
?>
<!--where link to show map on pop up-->
<p> <a href="javascript:void(0)" onclick="toggle_visibility('mapPopUp');">Where to find</a> </p>
<?php
echo "</div>";
//right section end

//left section 
echo"<div id='left-section'>
<p>Veniam adipisci ei duo, ut melius inimicus partiendo qui? Ipsum fuisset invidunt in quo, ut has mutat dicunt eligendi, noluisse patrioque sed ea! Suas solet appareat mea cu, vim porro mazim at. Mei omnium nostrud ea, ad ius altera quodsi adversarium, dicam scripserit id eum? Nec vocent eloquentiam consectetuer no, pro at voluptua adipisci perpetua!

Aperiri graecis nam ad, corpora quaestio eleifend ad usu. Recteque expetendis qui cu, aeterno molestie mnesarchum qui an, id vim veritus appareat? Sed oporteat torquatos definitiones id, ius et saperet explicari. Vide putent te mel, eu vix dicta audire? Detraxit imperdiet neglegentur ex cum, ea nam omnis persius persequeris, in melius omnium eum? Id vel diam quidam iuvaret, rebum ridens insolens ne qui. At similique reformidans usu, saepe mediocrem ea vix.

Cu sea sint mundi aliquid, exerci assentior eu eum. Cu mei impedit tacimates temporibus, ad eos audiam torquatos! Torquatos consequuntur eum in, mei id congue eirmod tincidunt. Nam tota justo ocurreret ad, his audiam impedit vituperata ex. Tale aliquam expetenda cu nam, tation pertinacia adipiscing eum ne.</p>
</div>
</div>";
//left section end




include 'footer.php';
?>