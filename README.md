# GlobantExercises
Training Spring Boot AWS Lamba exercises

sqsLambdaFunction project was created using Spring Boot 3.2.2 and amazon-corretto-17.0.10.7.1.

api_challenge-master was created with Spring Boot 2.7.17 and Oracle OpenJDL 1.8.0_111.
Endpoints of the project:
- List all of the available dog pictures grouped by breed:
  http://localhost:8080/api/list Get method must be used.
- List all of the available dog pictures of a particular breed:
  http://localhost:8080/api/list/{breed}  Breeds can be labrador, pug, retriever or yorkie. Get method must be used.
- Vote up and down a dog picture:
  http://localhost:8080/api/vote?url={url}&vote={vote} Vote can be up or down. Patch method must be used.
- The details associated with a dog picture:
  http://localhost:8080/api/info?url={url} Get method must be used.
  http://localhost:8080/api/info?url={url}&info={description} Patch method must be used.
