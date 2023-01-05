# Random-Image-REST-API
Made a spring boot REST API that parses through a directory and creates an REST endpoint for each folder and a request at said endpoint gives a random image from that folder.

## Setup
Add folders with images in them in the src/main/resourcesstatic/api directory. Each folder will automatically be mapped to an endpoint. Use the application.properties file to configure bucket4j for throttling requests.
