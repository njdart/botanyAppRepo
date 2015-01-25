<?php
$title = "Plants";
include "/includes/header.php";
include "/includes/specimens_curl.php";
include "/includes/filter.php";
?>
<div id='content-boxes-dblist'>
	<h1>Plant Database</h1>"

	<form class='advanced-search' action='plant_specimens.php' method='post'>
		<!-- <input type='submit' name='submitadvsearch' class='advancedsearchbutton' src='images/search.png' alt='Search' /> -->
		<input id='search' type='text' size='50'  class='simpletext' name='search' placeholder='Enter the plant you want to find' value=''/>
		<select id='plant-search-type' name='searchfield'>
			<option value='speciesName'>Species Name</option>
			<option value='locationName'>Location</option>
			<option value='userName'>User</option>
		</select>
	</form>
    <p id="result-number">0 results</p>
    <button id="next-page">Next page</button>
    <button id="prev-page">Previous page</button>
    <table id='results'>
        <thead><tr>
			<th>Specimen Name</th>
			<th>Location</th>
			<th>User Name</th>
			<th>Date</th>
			<th>Abundance</th>
            <td>Details</th>
		</tr></thead>"
		
        <tbody><tr><td>Loading...</td></tr>
        </tbody>
        
	</table>
</div>;
<?php
include '/includes/footer.php';
?>

<script type="text/javascript">
    
    var lastValue = $("#search").val();
    var lastChange = $.now();
    var start = 0;
    var range = 20;
    var results = 0;
    $("#next-page").click(function() {
        if(results == range) {
            start += range;
            getSpecimens();
        }
    });
    $("#prev-page").click(function() {
        if(start > 0) {
            start -= range;
            getSpecimens();
        }
    });
    var convertAbundance = function(abundance) {
        switch(abundance) {
            case 1 : return "Rare";
            case 2 : return "Occassional";
            case 3 : return "Frequent";
            case 4 : return "Abundant";
            case 5 : return "Dominant";
        }
    }
    var convertTimestamp = function(timestamp) {
        var date = new Date(timestamp*1000);
        return date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    }
    var getSpecimens = function() {
        //update last value
        lastValue = $("#search").val();
        //do a post request with those arguments
        var post = $.post('includes/specimens_search.php',
        { "column": $("#plant-search-type").val(),
          "value" : $("#search").val(),
          "method": $("#orderby").val(),
          "order" : $("#ordertype").val(),
          "start" : start,
          "range" : range});
        //always need to empty the table
        post.always(function(result) {
            $("#results tbody").empty();
        });
        //load results into table
        post.done(function(result) {
            var specimens = jQuery.parseJSON(result);
            for(specimen of specimens){
                $("#results").append('<tr>' + 
                "<td>"+specimen.SpecimenName+"</td>" + 
                "<td>"+specimen.LocationName+"</td>" + 
                "<td>"+specimen.UserName+"</td>" + 
                "<td>"+convertTimestamp(specimen.Timestamp)+"</td>" + 
                "<td>"+convertAbundance(specimen.Abundance)+"</td>" + 
                "<td><a href='specimen.php?id=" + specimen.SpecimenID + "'>View</a></td></tr>");
            }
            results = specimens.length;
            $("#result-number").text(specimens.length + " results displayed. Maximum range: " + range + "; Offset: " + start);
            if(specimens.length < 1) {
                $("#results").append("<tr><td>Nothing found.</td></tr>");
            }
        });
        post.fail(function(result) {
            $("#results").append("<tr><td>Error loading.</td></tr>");
        });
    };
    var postFun = function() {
        //if the search value has changed, do the post
        if(lastValue != $("#search").val()) {
            getSpecimens();
        }
    };
    //check whether we need to post every 1s
    setInterval(postFun, 1000);
    
    //attach event handlers
    $("#plant-search-type").change(getSpecimens);
    $("#orderby").change(getSpecimens);
    $("#ordertype").change(getSpecimens);
    $("document").ready(getSpecimens);
</script>