# Stairwell
Calculates the minimum amount of strides having an array of flights and a step-rate stride int

Build:
- mvn3 clean package builds a war artifact
- deploy to a Jboss instance (I used version 7 for my example)

Consume:
- send a post request with a body parameter data = {base64 json} with the format {"flights": [4,9,8,11,14],"steps" : 2}
- example: POST to http://localhost:8080/staircase/webapi/stairwell data=eyJmbGlnaHRzIjogWzQsOSw4LDExLDcsMjAsMTRdLCJzdGVwcyIgOiAyfQ== ({"flights": [4,9,8,11,7,20,14],"steps" : 2}) returns {"minStrides":50}

Example with curl:
- curl -X POST -d "data=eyJmbGlnaHRzIjogWzQsOSw4LDExLDcsMjAsMTRdLCJzdGVwcyIgOiAzfQ==" http://localhost:8080/staircase/webapi/stairwell
- representing data={"flights": [4,9,8,11,7,20,14],"steps" : 3}

