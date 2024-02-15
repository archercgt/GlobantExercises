# GlobantExercises
Training Spring Boot AWS Lamba exercises

sqsLambdaFunction project was created using Spring Boot 3.2.2 and amazon-corretto-17.0.10.7.1.

api_challenge-master was created with Spring Boot 2.7.17 and Oracle OpenJDL 1.8.0_111.

sqsLambdaSdk was creaated using Spring Boot 2.7.17 and amazon-corretto-17.0.10.7.1. 

Endpoints of the project:
- List all of the available dog pictures grouped by breed:
  http://localhost:8080/api/list
  Get method must be used.
  
- List all of the available dog pictures of a particular breed:
  http://localhost:8080/api/list/{breed}
  Breed can be labrador, pug, retriever or yorkie.
  Get method must be used.

- User's creation:
  http://localhost:8080/api/users
  Request Headers: id, name   Example: id  12345  name John
  Put method must be used.

- List users
  http://localhost:8080/api/users
  Get method mus be used.
  
- Vote up and down a dog picture:
  http://localhost:8080/api/vote
  Request Headers: url, vote, userId    Example url http://i.imgur.com/SAJJ1oH.png vote up userId 12345
  Vote can be up or down.
  Patch method must be used.
  
- Add additional information to a dog's picture:
  http://localhost:8080/api/info
  Request Headers: url, info    Example url http://i.imgur.com/SAJJ1oH.png info Dog with a smile
  Patch method must be used.

- Get additional information form a dog's picture:
  http://localhost:8080/api/info
  Request Headers: url    Example url http://i.imgur.com/SAJJ1oH.png
  Get method must be used.
