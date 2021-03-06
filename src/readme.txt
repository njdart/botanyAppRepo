	 ______                                ______                         
	(____  \       _                      / _____)                        
	 ____)  ) ___ | |_  ____ ____  _   _ | /  ___  ____ ___  _   _ ____   
	|  __  ( / _ \|  _)/ _  |  _ \| | | || | (___)/ ___/ _ \| | | |  _ \  
	| |__)  | |_| | |_( ( | | | | | |_| || \____/| |  | |_| | |_| | | | | 
	|______/ \___/ \___\_||_|_| |_|\__  | \_____/|_|   \___/ \____| ||_/ 
                                      (____/                          |_|     

                       ______           _                
                      (_____ \         (_)           _   
                       _____) ____ ___  _  ____ ____| |_ 
                      |  ____/ ___/ _ \| |/ _  / ___|  _)
                      | |   | |  | |_| | ( (/ ( (___| |__
                      |_|   |_|   \____| |\____\____)\___)
                                     (__/                                             
================================================================================
================================================================================

DESCRIPTION
================================================================================
This is a group contribution that has resulted in a LAMP system comprising of an
Android Application, a back-end server with database and a website. This system 
is designed for botanists to keep a track of their plant recordings out in the 
field, and in the comfort of their own home on the web. These communicate in a 
professional and realistic way that protects the database from potential 
malicious entries. The Android Application and the Website do not communicate 
directly with each other or the database. Instead, they both communicate with a
Web API server that controls all data inputs. The Android Application sends data
from the field into the database, through the API, and the website receieves 
this data and has the ability to manage it.


MEMBERS
================================================================================
Group Leader                            -                       Alexander Barnes
Deputy Group Leader                     -                          Nicholas Dart
Technical Manager                       -                             Max Atkins

Android Leader                          -                          Nicholas Dart
Android Team:
                                        -                       Alexander Barnes

Website Leader                          -                           James O'hare
Website Team:
                                        -                         Daniel Wilcock
                                        -                         Sebastian Tuff

Server Leader                           -                        Maxim Harizanov
Server Team:
                                        -                             Max Atkins 	     

Testing Team:
                                        -                          Andrew Wilmot
                                        -                       William Aldridge

INSTALLATION
================================================================================

Android
--------------------------------------------------------------------------------
The Android application can be installed by running the "android.apk" file on 
any Android device Version 4.0+. To do this, you need to go to Settings -> 
Security -> Enable checkbox "Unknown Sources" to allow non-PlayStore 
applications to be installed. It runs its own local database(SQLite) to store 
unsent recordings and plant information and it receives and sends data to the 
server database. 
Usability:
           -Reserve name and latin names are auto-complete
           -When entering latin name, text box disappears at top, off screen
           -On record review page, long press to edit and delete records

Website
--------------------------------------------------------------------------------
The website does not need to be installed. Go to this URL to access it:
http://users.aber.ac.uk/daw54/group_website/
Features that involve editing or deleting data from the database requires a 
password. That password is: "g2n8A".
Links to Server API and Session storage are fixed. These need to be changed if
the server changes.
Majority of data in database is test data and so discrepencies in data are to 
be expected. For example, OS grid references and Latitude and Longitude data 
do not always match up on the map.
There is a config.php file which holds website configurations.

Server
--------------------------------------------------------------------------------
To create the exact, empty database we use for our system, you need to run the 
SQL table creation queries found in "src/server/table_queries.txt". The 
API command scripts are used by both the Android application and the website.
There is a config.php file which holds API configurations.
