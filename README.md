
# IPSNR

IP Checker is a Java program that allows you to check an IP address against various websites to get detailed information about the IP address. 
The program uses threading to concurrently check the IP address on multiple websites, and utilizes Selenium's WebDriver to navigate and extract information from the websites. 
The program also uses an EnumMap to map state abbreviations to their full names, and a REST API to convert country codes to their full names.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.


### Prerequisites

To run this program, you will need to have the following installed on your machine:

* Java 8 or above
* Selenium WebDriver
* Maven


### Installing

* Clone the project to your local machine using the following command:

```bash
  git clone https://github.com/<dmahida2002>/IPChecker.git
```

* Navigate to the project's root directory:

```bash
  cd IPChecker
```

* Build the project using maven:

```bash
  mvn clean install
```

* Run the program:

```bash
  java -jar target/IPChecker-1.0-SNAPSHOT.jar
```

### Features

* Concurrent execution of IP checking using threading
* Utilizes Selenium's WebDriver to navigate and extract information from websites
* Maps state abbreviations to their full names using an EnumMap
* Converts country codes to their full names using a REST API


## Classes

### ThreadingIPCheck

The ThreadingIPCheck class is a child of the Thread class, which allows for concurrent execution of IP checking. 
It takes in an IP address and uses it to check against websites specified in websiteList. 
It implements a method run() that calls webOne() or webTwo() depending on the value of currentWebsite. 
It also implements a method setChecker(int i) that sets the currentWebsite to the passed in value, and a method numOfChecks() 
that returns the number of websites in websiteList.

### Main

The main method of the program creates a new instance of the Command class and passes in an IP address to be checked. 
It then calls the checkIP() method on the Command instance and prints the result of the getIPDetails() method.

### DetailedCompression

The DetailedCompression class is responsible for receiving, comparing and analyzing the results of the IP check from different websites. 
It has several private static fields: set, firstSet, secondSet, finalIPData and resources. 
The set field is an integer that keeps track of the number of sets of data that have been received. 
The firstSet and secondSet are arrays of strings that hold the details of the IP check from different websites. 
The finalIPData is an array of strings that holds the analyzed results of the IP check. The resources is an instance of the Resources class.

### Command

The Command class is the main class that contains the main methods that are used to check an IP address and retrieve the details of the IP address. 
It contains a private static variables IP, ISP, country, state, innerRegion, risk which holds the details of the IP address.

The class has several methods including:

* The constructors, which allows creating an instance of the Command class and passing in an IP address to check.
* The checkIP method, which is used to check the IP address by instantiating an object of the ThreadingIPCheck class and creating multiple threads using the Thread class.
* The setIPDetails method, which is used to set the values of the private variables with the details of the IP address.
* The getIPDetails method, which is used to retrieve the details of the IP address in the form of a String.
* The getters, which are used to get the values of the private variables.

The Command class is the backbone of the IP checker program. It utilizes the functionality of other classes such as ThreadingIPCheck, DetailedCompression, Resources, and IPInfoCollector to check and retrieve information about an IP address. 
It also uses Selenium's WebDriver to navigate to a website that provides information about an IP address and extracts the necessary information.
The class has several methods: the constructor, toReceiver, analize, and getIPData. The constructor initializes the set field to 0. 
The toReceiver method is used to receive the details of the IP check from different websites and assigns them to firstSet or secondSet. 
The analize method is used to compare and analyze the results of the IP check from different websites and stores them in finalIPData. 
The getIPData method is used to retrieve the final results of the IP check.

### Resources

The Resources class is a utility class that provides several useful methods for the IP checker program. These methods are:

1. getFullCountryName(String countryCode): This method is used to convert a country code to its full name by sending a request to a REST API. It takes a single string parameter, the country code, and returns a string containing the full name of the country.
2. nordContAbr(String nordCountry): This method is used to extract the abbreviation of the country name from the output of the NordVPN website. It takes a single string parameter, the NordVPN country string, and returns a string containing the abbreviation of the country name.
3. similarity(String s1, String s2): This method is used to calculate the Jaccard similarity between two given strings. It takes two string parameters, s1 and s2, and returns a double value representing the Jaccard similarity between the two strings.

In addition to these methods, the Resources class may also contain other utility methods that are used by the IP checker program. 
The class is designed to be used as a singleton, meaning that only one instance of the class should be created at any given time, and that instance should be shared among all classes that need to use the utility methods provided by the class.

### BehaviorsForNordVPN

The BehaviorsForNordVPN class is responsible for interacting with the NordVPN website in order to retrieve information about a specific IP address. 
It uses Selenium WebDriver to navigate and interact with the website. The class contains several methods that allow for the setting up of the WebDriver, entering an IP address, clicking the 'Check' button on the website, and extracting the details such as ISP, country, state and inner region from the website.
The setupWebDriver() method is used to initialize and configure the Selenium WebDriver, ensuring that it is properly set up and ready for use. 
The enterIP() method is used to enter a specific IP address into the NordVPN website's search bar, so that the website can perform a lookup on the provided IP.

The clickCheck() method is used to simulate the clicking of the 'Check' button on the NordVPN website, which initiates the lookup of the entered IP address. 
The extractDetails() method is used to extract the details of the IP address from the website, such as ISP, country, state, and inner region. 
These details are then stored in an array, which can be accessed through the finish() method.

This class is useful for retrieving detailed information about a specific IP address from the NordVPN website, and it makes use of Selenium WebDriver to automate the process of interacting with the website. 
This allows for efficient and accurate retrieval of IP address details, and makes the process of IP address lookup much more streamlined.

### BehaviorsForIPQual

The BehaviorsForIPQual class is responsible for gathering information about a specific IP address from a website using Selenium's WebDriver. 
It navigates to a website that provides information about IP addresses and extracts the necessary information such as ISP, country, state, and inner region. 
The class also uses an EnumMap to map state abbreviations to their full names.

The class contains methods for setting up the WebDriver, entering an IP address, navigating to the website, and extracting the details from the website. 
The extracted information is stored in an array, and can be accessed through the finish method. This class is an important part of the overall IP checker program, as it is responsible for collecting the necessary information about the IP address being checked.


### Future Plans

* Implement JFrame or better GUI: The current implementation of the program is purely command line based. Adding a graphical user interface (GUI) using Java's JFrame or a more advanced GUI library such as JavaFX will greatly improve the user experience and make the program more accessible to a wider audience.
* IP Checker for VPN and bots: One of the main use cases for the program is to check if an IP address is associated with a VPN or bot. In the future, the program can be enhanced to specifically check for these types of IP addresses and provide a score or rating based on the results.
* Organize data provided from checker: The current implementation of the program provides a lot of information about an IP address, but it may be overwhelming for some users. In the future, the program can be enhanced to better organize and present the information in a more user-friendly way. This can include adding charts, graphs, or other visualizations to better convey the data.
* Adding more website to check IP
* Adding more functionalities like IP reputation check, Geo location check, etc
* Integrating with a database to store results of checker and to retrieve previous check results.

Overall, these future plans will greatly improve the functionality and usability of the program and make it a valuable tool for a wide range of users.


### Conclusion

In summary, this IP checker program utilizes various functionalities such as threading, OOP, API, and Selenium to check an IP address against websites specified in websiteList and extract the necessary information. 
It also utilizes a EnumMap to map state abbreviations to their full names. 
The program is able to analyze and compare the results of the IP check from different websites and stores them in finalIPData.
