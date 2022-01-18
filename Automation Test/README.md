<p align="center">
<img src="https://uploads-ssl.webflow.com/6097e0eca1e87557da031fef/609859a191abe5d64b17fed3_Patika%20logo-p-500.png" width="140"/>
<img src="https://cdn03.ciceksepeti.com/Themes/Ciceksepeti/Assets/images/ciceksepeti-logo-146.png?v=3.1.1.38564" alt="mobile-gif" width="150"/>
</p>

This project is a automation test project developed for ```https://www.mizu.com``` and ```https://www.mizu.com/flowers```

- [Manual test cases for ```https://www.mizu.com/login```](https://github.com/fatossgorur/ciceksepeti-patika-graduation-project/blob/main/Automation%20Test/Login%20Test%20Cases.xlsx)

# Introduction

+ Automation test was written for 3 selected scenarios.
    +  Checking that 60 products are displayed on each page for the first 10 pages as you scroll down on ```https://www.mizu.com/flowers```
    +  Checking that the products on the listing page are listed in the correct price order by selecting sort: price high to low from the filter field on the ```https://www.mizu.com/flowers```
    +  Checking whether the links of all sub-categories under the ```https://www.mizu.com``` menu are broken

<br>

<img src="report/cucumber.png" alt="cucumber" width="200"/>

## Technologies Used

- **Java**
- **Selenium**

Selenium is a free (open-source) automated testing framework used to validate web applications across different browsers and platforms. 

- **Cucumber**

Cucumber is still the most popular scripting tool and is widely used in BDD development and automated tests along with Selenium, which provides the browser automation so the project can emulate user interactions with Web-UI.

- **TestNG**
 
TestNG is a testing framework used with Selenium WebDriver. JUnit is similar. But it has additional functions and features like parallel test execution, parameterization and more. In TestNG, all tests can run independently of each other. Generates a report. 

- **Log4j**

Log4j is a reliable, fast and flexible logging framework (APIs) written in Java, which is distributed under the Apache Software License. 

<br>

## Running the tests

- Clone the project:

```sh
git clone https://github.com/fatossgorur/ciceksepeti-patika-graduation-project.git
```
- To run all tests, use ```$ mvn test``` command in terminal or click button from ```TestRunner.java ``` 

- After running the project, you can access the log records from the ```Log4j``` > ```log4j-application``` file.


## Step 1

#### Instructions

```URL= "https://www.mizu.com/flowers"```

+ | Feature  |Scenario|
  |--------------|-------|
  | 	Product Count Test |Checking Count of Products on the Page|
    +  ```Assert.assertEquals(flowerPageUrl, key)```
    + ```Assert.assertEquals(productCount, value)``` 

With mapping, the links and the number of products in the links were kept.

![image](https://user-images.githubusercontent.com/55894683/149993988-8ebc05ca-8b6a-404b-9ce5-627e86bf6e9a.png)


## Step 2

#### Instructions

```URL= "https://www.mizu.com/flowers"```

+ | Feature  |Scenario|
  |--------------|-------|
  | 	Price Order Test |Checking Correct Price Sort with Filtering|
    + 
        ```
       if (!productsPage.checkPriceSort()) {
            Assert.fail("Not is decreasing order");
        }
        ``` 

All product prices on the search results page are listed. Checked if the order of the list elements is correct.

![image](https://user-images.githubusercontent.com/55894683/149994191-89d5d945-89f5-4afd-9afa-6e6f02ec3023.png)

## Step 3

#### Instructions

```URL= "https://www.mizu.com"```

+ | Feature  |Scenario|
  |--------------|-------|
  | 	Broken Links Test |Broken Links Validation|
    + 
    ``` 
    linkMap.forEach((key, value) -> {
            if (value >= 400) {
                Log4j.info(key + " : Broken Link -->" + " HTTP status code : " + value);

            } else {
                Log4j.info(key + " : Valid Link -->" + " HTTP status code : " + value);
            }
        });
   ``` 

Links and status codes of links are kept with mapping.

![image](https://user-images.githubusercontent.com/55894683/149994941-3d38ba94-51ec-45d4-89f9-ce9d385545be.png)
  
<br>

# Results

#### Intellij IDEA Result

![idereport](https://user-images.githubusercontent.com/55894683/149998095-3c3c346f-b410-4e95-af09-9da96e5bc8d2.png)


#### Cucumber Report

- Cucumber report path : ```tarrget > cucumber-reports > cucumber.html```
  
![cucumberreport](https://user-images.githubusercontent.com/55894683/149998083-1206cfb5-070e-4961-a214-7cb118c12516.png)







