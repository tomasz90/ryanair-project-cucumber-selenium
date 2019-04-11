# myproject

This mini project shows simple test scenario of booking flight on ryanair site.
Test has to check if proper error message is displayed when user books flight with invalid payment data.

### Languages, frameworks
To develop this simple project I used Java, Selenium Webdriver, Cucumber, Maven, JUnit.

### Run Test
To run test all you have to do is run "Runner.java" file.

### Features
* Depending on used OS, Runner should find right driver binary. I tried to use WebDriverManager instead however it had problems with downloading Webdrivers(not sure if it was problem with my configuration, anyway to not waste my time, decided to do it on my own).

* In resources I placed few properties file every file is responsible for different configuration: 
driver configuration, logger configuration, test data and output data.

* I implemented simple mechanism to capture screenshot whenever test fails
