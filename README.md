## SQS Local Camel

This is a project to test the usage of Apache Camel and Amazon SQS in Local Stack. 


To run the PoC run: 

`./gradlew clean test`

This will start the local stack with SQS listening in a random port (bound with the container port 4576).

You need to have: 

- `docker` and `docker-compose` available.
