<p align="center">
<img src="https://uploads-ssl.webflow.com/6097e0eca1e87557da031fef/609859a191abe5d64b17fed3_Patika%20logo-p-500.png" width="140"/>
<img src="https://cdn03.ciceksepeti.com/Themes/Ciceksepeti/Assets/images/ciceksepeti-logo-146.png?v=3.1.1.38564" alt="mobile-gif" width="150"/>
</p>

This project is an integration test project developed for ```signin``` and ```signup``` endpoints.


# Introduction

![image](https://user-images.githubusercontent.com/55894683/149918039-93696226-5da8-43d0-9028-052d72df9ae9.png)

- It is a integration test for signin and signup endpoints.
- The user should not sign up with the existing email.
- The user should not sign in with an incorrect email or password.
- The 'email' and 'password' fields are required to sing up.
- The 'email' must be valid, 'password' must be at least 8 and maximum 20 characters to sign up.

<br>

<img src="report/restassured.png" alt="restassured" width="200"/>

## Technologies Used

- **Java**
- **Rest Assured**

Rest Assured enables you to test REST APIs using java libraries and integrates well with Maven. It has very efficient matching techniques, so asserting your expected results is also pretty straight forward. Rest Assured has methods to fetch data from almost every part of the request and response no matter how complex the JSON structures are. You should have better knowledge about Java/BDD for working in REST-Assured.

- **TestNG**

TestNG is a testing framework used with Selenium WebDriver. JUnit is similar. But it has additional functions and features like parallel test execution, parameterization and more. In TestNG, all tests can run independently of each other. Generates a report. 

- **Jackson**

Jackson is a very popular and efficient java based library to serialize or map java objects to JSON and vice versa. This tutorial will teach you basic and advanced Jackson library API features and their usage in a simple and intuitive way.

- **Loj4j**

Log4j is a reliable, fast and flexible logging framework (APIs) written in Java, which is distributed under the Apache Software License. 

<br>

## Running the tests

- Clone the project:

```sh
git clone https://github.com/fatossgorur/ciceksepeti-patika-graduation-project.git
```
- Use ```mvn test``` code in terminal or click the run button on the ide to run the test.

- After running the project, you can access the log records from the ```Log4j``` > ```log4j-application``` file.


## Step 1

#### Instructions

```baseURL= "https://bootcampapi.techcs.io/api/fe/v1/authorization"```

+ | Description  |
  |---------------------------|
  | 	Control sign in request body |
    +  ```Assert.assertTrue(email.contains("@"))```
    + ```Assert.assertNotNull(email)``` 
    + ```Assert.assertTrue(password.length() <= 20)``` 
    + ```Assert.assertTrue(password.length() >= 8)``` 
    + ```Assert.assertNotNull(password)``` 
  

+ | Endpoint | Responses Code | Description |
  |----------|----------------|---------------------------|
  | /signin | 200             | 	User successfully signed-in |
    +  ```Assert.assertEquals(response.getAccess_token(), accessToken)```
    + ```Assert.assertNotNull(accessToken)``` 

+ | Endpoint | Responses Code | Description |
  |----------|----------------|---------------------------|
  | /signin | 401            | 		User submitted a wrong email or password |
    +  ```Assert.assertEquals(response.getStatusCode(), statusCode)```
    + ```Assert.assertEquals(response.getMessage(), message)``` 


## Step 2

```baseURL= "https://bootcampapi.techcs.io/api/fe/v1/authorization"```

#### Instructions

+ | Description  |
  |---------------------------|
  | 	Control sign up request body |
    +  ```Assert.assertTrue(email.contains("@"))```
    + ```Assert.assertNotNull(email)``` 
    + ```Assert.assertTrue(password.length() <= 20)``` 
    + ```Assert.assertTrue(password.length() >= 8)``` 
    + ```Assert.assertNotNull(password)``` 
  

+ | Endpoint | Responses Code | Description |
  |----------|----------------|---------------------------|
  | /signup | 200             | 	User successfully signed-up |
    +  ```Assert.assertEquals(response.getAccess_token(), accessToken)```
    + ```Assert.assertNotNull(accessToken)``` 

+ | Endpoint | Responses Code | Description |
  |----------|----------------|---------------------------|
  | /signup | 409            | 	User already signed-up |
    +  ``` Assert.assertEquals(response.getStatusCode(), statusCode)```
    + ```Assert.assertEquals(response.getMessage(), message)``` 
    + ```Assert.assertEquals(response.getError(), error)```
    + ```Assert.assertNotNull(statusCode)```
    + ```Assert.assertNotNull(message)```
    + ```Assert.assertNotNull(error)```
    + ```Assert.assertEquals((int) statusCode, 409)```
    +  ```Assert.assertTrue(message.contains("User already exist!"))```
    + ```Assert.assertTrue(error.contains("Conflict"))```

+ | Endpoint | Responses Code | Description |
  |----------|----------------|---------------------------|
  | /signup | 400            | 	Email and password should not be empty for signed-up |
    +  ```Assert.assertEquals(response.getStatusCode(), statusCode)```
    + ```Assert.assertEquals(response.getMessage(), message)``` 
    + ```Assert.assertEquals(response.getError(), error)```
    + ```Assert.assertNotNull(statusCode)```
    + ```Assert.assertNotNull(message)```
    + ```Assert.assertNotNull(error)```
    + ```Assert.assertEquals((int) statusCode, 400)```
    +  ```Assert.assertTrue(message.contains("email should not be empty"))```
    + ```Assert.assertTrue(error.contains("Bad Request"))```
   


<br>

# Results

  ![image](https://user-images.githubusercontent.com/55894683/149933067-937d032a-08d9-4ca7-a9c2-d4e457860275.png)






