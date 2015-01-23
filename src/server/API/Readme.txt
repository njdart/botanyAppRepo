Hi James

You suck lots of sucky sucking suckily suckiness, Mr. Sucker Suckington of the sucky town Suckingham, Suckingshire, Suck Kingdom. 

DO NOT FUCKING CHANGE THIS.
@Copyright Max Atkins && Maxim Harizanov



Look at commandName.php to explore how it works (with beautiful comments)

Chrome has a great extension to play with API: Postman

Use the API from my server: users.aber.ac.uk/mta2/groupapi/commandName.php

Updates:

config.php file added to protect sensitive data from online publications.

Available Commands:

addRecord.php (Encode a JSON called "record")
addResource.php (Send a JPEG called "resource")
getRecord.php (Decode the JSON) -- Not for use
getResource.php (eg ID: 13)
getSpecimen.php (Decode the JSON. POST name = specimenID) 
getSpecimens.php (Decode the JSON. All specimens are in array. POST order = ascending/descending. POST method = speciesName/locationName/userName/timeStamp
							       POST searchValue = user input, POST searchColumn = speciesName/locationName/userName)
removeSpecimen.php (POST specimenID. POST password = "admin".)
getLocations.php (No POST data.)


To do:

addSpecimen.php -- Gone
modifySpecimen.php -- Not required
getRecords.php -- Gone
modifyRecord.php -- Gone
removeRecord.php -- Gone

