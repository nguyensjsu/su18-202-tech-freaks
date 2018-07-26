# Project Journal

|Name | Detail|
|---|---|
| Harini Balakrishnan | Daily Scrum |

## Daily Scrum Report

**Jul 17th**

- What tasks did I work on / complete?

  - Created the business logic for Adding Cards.
  - Unit Test cases for AddCard and MyCards classes in the library.

- What am I planning to work on next?

  - Connect the business logic to MySQL database.
  - Allow multiple cards and set one as active in the database.

- What tasks are blocked waiting on another team member?

  - Installing MySQL and establishing connection from Eclipse

----


**Jul 18th**

- What tasks did I work on / complete?

  - Connected the business logic to MySQL database.
  - Allowing multiple cards entries and the user can set one of the card as active.

- What am I planning to work on next?

  - Design the wire-frames for my part.
  - Create CRUD operations with MySQL database.

- What tasks are blocked waiting on another team member?

  - Pencil is difficult. Using other tool to design wire-frames.

----


**Jul 19th**

- What tasks did I work on / complete?

  - Designed the wire-frames for my part.
  - Created table Card in the MySQL database.

- What am I planning to work on next?

  - Write code to perform CRUD operations with MySQL database.
  - Add validations for each data entry to the database.
  - Export the project as a JAR file.

- What tasks are blocked waiting on another team member?

  - Had MySQL issues while trying to establish the connection. Resolved. But took half of my day.

----


**Jul 20th**

- What tasks did I work on / complete?

  - Created CRUD operations with MySQL database.
  - Added validations to the database columns.
  - Exported the business logic as a JAR file.

- What am I planning to work on next?

  - Implement REST APIs that calls the classes and methods from the JAR file.
  - Test the RESTful functionalities using Advance Chrome REST API tool.


- What tasks are blocked waiting on another team member?

  - Merge the library code to form a single JAR file
  - Access the methods and classes from the JAR library without adding the entire project folder.

----


**Jul 21st**

- What tasks did I work on / complete?

  - Created Dynamic Web Project in Eclipse with Tomcat server.
  - Install JAX-RS API jar files to the library
  - Add the Domain Component JAR file to the library and import the package.

- What am I planning to work on next?

  - Implement REST APIs that calls the library classes and methods to perform the business logic.
  - Test the RESTful functionalities using Advance Chrome REST API tool.
  - Begin Android user interface for Add Card, MyCards and Settings Screen.

- What tasks are blocked waiting on another team member?

  - Had issues with Maven project in Eclipse and so switched to Dynamic Web Project.
  - Access the methods and classes from the JAR library without adding the entire project folder. Still working on this issue.

----


**Jul 22nd**

- What tasks did I work on / complete?
  - Installed Springboot and setup new project
  - Configured AWS RDS with database and necessary tables.
  - Implemented REST APIs that calls the library classes and methods.

- What am I planning to work on next?
  - Modify the database credentials and test REST API functionalities with AWS RDS 
  - Add the AWS RDS credential details in env file.

- What tasks are blocked waiting on another team member?
  - Eclipse dynamic web application required lots of dependencies. So switched to SpringBoot for the controller program.

----


**Jul 23rd**

- What tasks did I work on / complete?
  - Modified the database credentials with AWS RDS credentails 
  - Implement 5 REST APIs and tested using Advance Chrome REST API.
  - Started User Interface with basic Android simulator.

- What am I planning to work on next?
  - Complete the userinterface with buttons and list view.
  - Call the REST API and integrate the UI view with controller 
  - Test the AddCard functionality flow


- What tasks are blocked waiting on another team member?
  - Had MySQL JDBC connector issue. Resolved by upgrading to JRE 9.0 version and add the JAR file to classpath

----

**Jul 24th**
- What tasks did I work on / complete?
  - Created android application basic setup.
  - Designed the customized navigation bar and action bar.
  - Resolved teammate's issue and supported another teammate to connect STS controller to the AWS RDS.

- What am I planning to work on next?
  - Design MyCards page and AddCard page.
  - Integrate UI with REST API.

- What tasks are blocked waiting on another team member?
  - Need to work on EC2 deployment which can be done only if all the REST APIs are added and tested. 
  - Waiting for User authntication API and Order API.

----
**Jul 25th**
- What tasks did I work on / complete?
  - Designed MyCards and AddCard screens.
  - Integrated REST API response with the UI.
  - Set navigation bottom custom layout and customized action bar.

- What am I planning to work on next?
  - Design AllCards Screen and GET all cards JSON add it to ListView.
  - Design the Settings screen with basic navigation and action bar.

- What tasks are blocked waiting on another team member?
  - Awaiting for teammates to complete their REST API parts. 
  - Need to deploy the controller to AWS EC2 and get EC2 urls in the Andorid application. 

----

## XP Core Values

### Communication

> Out team does a video chat for 20 minutes once in three days to check our individual progress status.
Everyone is given 5 minutes to explain the above three questions. If one of us needs help, we book for a pair programming video session to resolve issues and clarify doubts.

> I maintain the GitHub project board to tag someone in my team to get help in a particular task. I also specify their name if my code is depended on their code.

### Simplicity

> I plan to implement the basic necessary pages with necessary methods for API calls. Then I have preponed my deadlines three days prior to work on the Extra Credit part of the project.

> Everyone of my team will be implementing a JAR file and set of APIs. We have decided to merge the controller to link our dependencies at the controller level and have a clean development with less/no merge conflicts.


### Courage

> Each of us decided to learn every part of this project and gain hands-on experience with front-end, back-end, database, testing. So that, when we merge our code we will know what and how things work in our project very well. It also helps us to understand and help each other.

> I'm working on Add Card API and so I design the wire-frame, write unit test cases and code the business logic. I also learned to export the project as a JAR file and implement the REST APIs.

### Respect

> Everyone in this team is specialized in a specific domain that when combined together helps us to build this project 100%. We don't proceed with any decision unless everyone shares their opinion on the floor.

----
