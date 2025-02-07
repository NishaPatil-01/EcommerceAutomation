# Ecommerce Automation TestNG

### This is a complete project where an [E-commerce site](http://automationpractice.com/) site is automated by writing test suites using selenium-webdriver and TestNg as testing framework

The following key modules/pages are automated:


- **SignUp**
- **Login**
- **Search**
- **Cart**
- **Checkout**</br>

Key test cases(total **51**) are written for each module and test suites created including the positive and negative test cases.</br>A state-transition flow of test-cases are designed and run like a user buying a product from an e-commerce site.</br>
For failed test cases it will take a screenshot aswell at the point of failure.

### Technology: </br>

- Tool: Selenium Webdriver
- IDE: Intellij IDEA
- Build tool: Gradle
- Language: Java
- Testing Framework : TestNG

### Prerequisite: </br>

- Need to install jdk 11, gradle and allure
- Configure Environment variable for jdk 11, gradle and allure
- Clone this project and unzip it
- Open the project folder
- Double click on "build.gradle" and open it through IntellIJ IDEA
- Let the project build successfully
- Click on "Terminal" and run the automation scripts

### Run the Automation Script by the following command

```java
gradle clean test
```

- Selenium will open the browser and start automating.
- After automation to view allure report , give the following commands:

```java
allure generate allure-results --clean -o allure-report
allure serve allure-results
```
