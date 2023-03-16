This is DHT Chord P2P implemetation for photo share app.

Below are the steps need to perform on each node : 
Open the terminal
Locate the chord jar file using path as photoShare/Chord/out/artifacts/Chord_jar

To create a ring : 

#java -jar Chord.jar create <port>

To join existing ring : 

#java -jar Chord.jar join 8000 <existingNodeIP>:<port>

To execute the home servelet - make sure of having tomacat server install. 
Open the ChordUI from project in intelliJ
Configure the tomacat to execute the HomeServelet --> configure this by buidling artifact as web application -exploded.
Once done click on build project. And run the server.
