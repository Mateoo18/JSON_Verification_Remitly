# JSON File Verification Tool

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [How to run](#How-to-run)
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
3. Build the project using your preferred build tool (e.g. Maven ). The IDE `IntelliJ IDEA` should suggest to "Load Maven Project" and then click "Load Project"
4. Run the tests to ensure everything is working correctly. To run the test you need to open Maven window on the right site in IntelliJ and then click Lifecycle -> test
5. Run the program in the Main class clicking the green arrow at the of the screen
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
