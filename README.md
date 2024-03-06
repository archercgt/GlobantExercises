# GlobantExercises
Training Spring Boot and AWS Lamba exercises for becoming familiar with the concepts.

## Description of the projects

### sqsLambdaFunction
In this project AWS SNS service generates messages that are later taken and stored in a queue by the AWS SQS service . These SQS messages work as triggers of a AWS Lambda function, which extracts the original SNS messages and print them in the console.

### sqsLambdaSdk
This project does exactly the same that sqsLambdaFunction project, but uses AWS SDK for Java instead of Spring Cloud.

### api_challenge-master
This project is a solution to the challenge described in https://gist.github.com/fredjean/bfdaf53c6baa547e9c16

### api_challenge_graphql
This project is a solution to the challenge described in https://gist.github.com/fredjean/bfdaf53c6baa547e9c16 but also includes integration with GraphQL for the queries.

### twoSumLeetCode
This project is a solution to the challenge twoSum problem described in https://leetcode.com/problems/two-sum/description/

### introGraphql
Project created following the guide in https://spring.io/guides/gs/graphql-server#initial

### twoSum2Pointers
Solution for the twoSum problem using two pointers. IMPORTANT!!! This solution only works if the input array is sorted.

## Used tools
sqsLambdaFunction project was created using Spring Boot 3.2.2 and amazon-corretto-17.0.10.7.1.

sqsLambdaSdk was creaated using Spring Boot 2.7.17 and amazon-corretto-17.0.10.7.1. 

api_challenge-master was created with Spring Boot 2.7.17 and Oracle OpenJDL 1.8.0_111.

api_challenge_graphql was created with Spring Boot 2.7.17 and amazon-corretto-17.0.10.7.1. 

twoSumLeetCode was created with amazon-corretto-17.0.10.7.1.

twoSum2Pointers was created with amazon-corretto-17.0.10.7.1.

introGraphql was created with Spring Boot 3.2.3 and amazon-corretto-17.0.10.7.1.

## How to implement api_challenge-master project
Once you have the project running, you can access the next Endpoints using Imsonia https://insomnia.rest/ or a similar application for testing RestAPIs:

### Rest API

- List all available dog pictures grouped by breed (Get method must be used):  
  http://localhost:8080/api/list  
 
- List all available dog pictures of a particular breed (Get method must be used):  
  http://localhost:8080/api/list/{breed}  
  Breed value can be labrador, pug, retriever or yorkie.  
 
- User creation (Put method must be used):  
  http://localhost:8080/api/users  
  Request Headers: id, name  
  Example: id  12345  name John

- List users (Get method mus be used):  
  http://localhost:8080/api/users  
  Get method mus be used
  
- Add additional information to a dog's picture (Patch method must be used):  
  http://localhost:8080/api/info  
  Request Headers: url, info  
  Example url http://i.imgur.com/SAJJ1oH.png info Dog with a smile

- Get additional information from a dog's picture (Get method must be used):  
  http://localhost:8080/api/info  
  Request Headers: url  
  Example url http://i.imgur.com/SAJJ1oH.png

- Vote up and down a dog's picture (Patch method must be used):  
  http://localhost:8080/api/vote  
  Request Headers: url, vote, userId  
  Example url http://i.imgur.com/SAJJ1oH.png vote up userId 12345  
  Vote value can be up or down.

### GraphQL
The method PUT must be set in Insomnia and all queries mut point to http://app.test:8080/graphql.

Examples:  
- List all available dog pictures grouped by breed:  
query {  
    listPictures {  
        url  
	breed  
	votesUp  
	votesDown  
    }  
}  
 
- List all available dog pictures of a particular breed:  
query {  
    listByBreed(breed: "labrador") {  
        url  
        breed  
        votesUp  
        votesDown  
    }  
}  
 
- User creation:  
mutation {  
    createUser(id: 98765, fullName: "John Doe") {  
        answer  
    }  
}  

- List users:  
query {  
    listUsers {  
        id  
	fullName  
    }  
}  
  
- Add additional information to a dog's picture:  
mutation {  
    addInfo(url: "http://i.imgur.com/VzFTsGg.png", info: "Dog with spots") {  
        answer  
    }  
}  

- Get additional information from a dog's picture:  
query {  
    getInfo(url: "http://i.imgur.com/SAJJ1oH.png") {  
        answer  
    }  
}  

- Vote up and down a dog's picture:  
mutation {  
    vote(url: "http://i.imgur.com/hBFRUuW.png", vote: "up", userId: 98765) {  
        answer  
    }  
}
