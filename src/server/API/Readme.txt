Hi James

You suck lots of sucky sucking suckily suckiness, Mr. Sucker Suckington of the sucky town Suckingham, Suckingshire, Suck Kingdom. 

Yours suckily,
Sucky Emperor Suckerustus
Suckpire of Suck Kingdom

DO NOT FUCKING CHANGE THIS.
@Copyright Max Atkins && Maxim Harizanov



Look at commandName.php to explore how it works (with beautiful comments)

Chrome has a great extension to play with API: Postman

Use the API from my server: users.aber.ac.uk/mta2/groupapi/commandName.php

Updates:

config.php file added to protect sensitive data from online publications.
getSpecimens.php can now order/search by abundance and can paginate with start row and range of rows
Added sanitisation to all API commands
Added more Database protection with transactions and connection closing
Added resource location protection with config
Added validation on Lat and Long and empty fields
Added authenticateAdmin command. Added includes to relevant places
Added addReserve and removeReserve commands.
Added updateSpecimen command and updateReserve command.
Added getReserve command.

Available Commands:

addRecord.php (Encode a JSON called "record")
addResource.php (Send a JPEG called "resource")
getRecord.php (Decode the JSON) -- Not for use
getResource.php (eg ID: 13)
getSpecimen.php (Decode the JSON. POST name = specimenID) 
getSpecimens.php (Decode the JSON. All specimens are in array. POST order = ascending/descending,
							       POST method = speciesName/locationName/userName/timeStamp/abundance,
							       POST value = user input, POST column = speciesName/locationName/userName/abundance/reserveID,
							       POST start = starting row, POST range = number of rows to display)
removeSpecimen.php (POST specimenID. POST password = ask Max.)
getLocations.php (No POST data.)
authenticateAdmin.php (POST password = admin password, will return true or false)
addReserve.php (Encode a JSON called "reserve")
removeReserve.php (POST reserveID    POST password = ask Max)
getReserves.php (No POST data.)
updateReserve.php (POST = reserveID of reserve to update. POST reserve = all reserve fields.)
updateSpecimen.php (POST specimenID = ID of specimen to update. POST specimen = all specimen fields.)
getReserve.php (POST reserveID = ID of reserve to get.)


To do:

Anything people ask.

