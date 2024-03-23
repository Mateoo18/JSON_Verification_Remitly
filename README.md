# JSON File Verification Tool

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [How to run](#How-to-run)
* [Fixing problems](#Fixing-problems)
* [Usage](#usage)
* [Room for Improvement](#room-for-improvement)
* [Contact](#contact)

## General Information
The JSON File Verification Tool is a Java application designed to verify **AWS::IAM::Role Policy** files for specific criteria, such as ensuring that field "Resource"  does not contain single **asterisk(*)**.

## Technologies Used
- Java
- JUnit
- JSON library (e.g., org.json)
- Maven

## Features
- Read JSON file from a specified path
- Verify the content of the JSON file against predefined criteria
- Provide feedback on whether the file meets the verification criteria

## How to run
To set up the project locally, follow these steps:
1. Clone the repository to your local machine.
2. Ensure you have Java installed on your system.
3. Make sure you have updated your JDK
4. Build the project using your preferred build tool (e.g. Maven ). The IDE `IntelliJ IDEA` should suggest to "Load Maven Project" and then click "Load Project"
5. Run the tests to ensure everything is working correctly. To run the test you need to go to the classes called **DataLoaderTest** and **DataWriterTest**. Next you need to click on the green button next to the name of the class. Another way is to open Maven window on the right site in IntelliJ and then click Lifecycle -> test.
6. Run the program in the Main class clicking the green arrow at the of the screen
## Fixing problems
Some problems that might have occured:
1. First, if you have problem with Maven always Reload Maven Project clicking the refresh button in Maven window.
2. If Maven has an error after compiling erase this code in **pom.xml**:
` <build>
   <pluginManagement>
   <plugins>
   <plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-surefire-plugin</artifactId>
   <version>3.2.5</version>
   </plugin>
   </plugins>
   </pluginManagement>
   </build>`
3. If you have a problem about the **version**. Make sure you are using JDK 18 or higher.
4. If you have another problem, please write to me on e-mail *mateusz.nowak.203@gmail.com*

## Usage
To use the JSON File Verification Tool, follow these steps:
1. Provide the path to the JSON file you want to verify.
2. Run the verification process.
3. Review the output to see if the file passed or failed the verification.

## Project Status
The project is for now finished. Functionality for reading and verifying JSON files has been implemented. 

## Room for Improvement
Areas for improvement include:
- Adding support for more complex verification rules
- Providing options for custom verification criteria
- Improving performance for large JSON files

## Contact
For questions or feedback, please contact the project maintainer at mateusz.nowak.203@gmail.com
