# Project Journal

|Name | Detail|
|---|---|
| Supreetha Ganapathi | Daily Scrum |

## Daily Scrum Report

**Jul 17th**

- What tasks did I work on / complete?

> As per the discussion with team i was assigned with payment Api to work on.
> Completed setting up the environment like eclipse and mysql.
> Created a project structure to implement business logic.
> Started filling the Excel sheet to track the work done.

- What am I planning to work on next?

> Create DAO class/model class for payment.
> Create Database table for persisting payment details.
> Connect to the DB from the Java application and implement payment api logic to save and retreive payment details from the table.

- What tasks are blocked waiting on another team member?

 > No blockage for me for my next tasks.
-------------
**Jul 18th**

- What tasks did I work on / complete?

> Created payment model class and a service class and implemented the business logic to save and retreive data from DB.
> Created a payment table in DB and Successfully connected to Mysql DB from java application and was able to save and get the payment details from the table.
> Filled the excel sheet for burndown chart.

- What am I planning to work on next?

> Export the paymnet business logic class as .jar file.
> Create a springboot application and import the above .jar file as a exterbal library.

- What tasks are blocked waiting on another team member?

> No blockage for my next tasks.

-------------

**Jul 19th**

- What tasks did I work on / complete?

> I was trying to import .jar file as maven dependancy to my spring boot application. Facing some issue to  import.
> Filled the excel sheet for burndown chart.
- What am I planning to work on next?

> Resolve the dependancy error in pom.xml file.
> Write a controller class to map Payment rest api to the end point method.
> Use the library of business logic in the controller class.

- What tasks are blocked waiting on another team member?

> Getting error while installing the jar file to local repo. Error message "Plugin execution not covered by lifecycle configuration.
> Updated in git project as issue. 

-------------

**Jul 20th**

- What tasks did I work on / complete?

> Resolved the maven dependancy error and was able to import the jar file.
> Implemented the Rest end point for payment api in controller class.
> Filled the excel sheet for burndown chart.

- What am I planning to work on next?

> Hitting the rest end point from the Postman rest client tool.
> Check if the library works fine.

- What tasks are blocked waiting on another team member?

> Need order details and card id details from order table and card table.

-------------

**Jul 21st**

- What tasks did I work on / complete?

> I was trying call Rest api from postman rest client, but getting error.

- What am I planning to work on next?

> Resolve the error and call rest endpoint from postman.
> Need to figure out how to get card id and order details from card and order apis.

- What tasks are blocked waiting on another team member?

> Need to understand how order api and card api is implemented and how to use them to get my required data.
-------------

**Jul 22nd**
- What tasks did I work on / complete?
> Resolved the issue with hitting rest end point from the postman.
> Explored UI wire frame designing tools.
> Created a AWS RDS Mysql DB and connected to the DB and tested the connection from java code.
- What am I planning to work on next?
> Merge the controller class code to get card id and order details to make payment.
- What tasks are blocked waiting on another team member?
> Once I get api or method to get card and order information I will update payment method to use those information.
-------------
**Jul 23nd**
- What tasks did I work on / complete?
> Tried to solve connection error while connecting to AWS RDS db.
> Resolved merge conflict in the controller class and merged my branch with the master.
> Added new api for getting payment details using ID.

- What am I planning to work on next?
> Merge the controller class code to get card id and order details to make payment.
> Prepare wireframes for payment details using Balamiq tool.
- What tasks are blocked waiting on another team member?
> Card details and order details needed to complete payment process.
-------------
**Jul 24th**
- What tasks did I work on / complete?
> Merged the controller codes but getting merge conflicts.
> Made changed to call addcard api and order api.
> Added new api for getting payment details using ID.

- What am I planning to work on next?
> Merge the controller class code to get card id and order details to make payment.
> Prepare wireframes for payment details using Balamiq tool.
- What tasks are blocked waiting on another team member?
> Need to figure our how to set the url inside the url for rest end pints calls to other apis.
-------------
## XP Core Values
> We as a team decided to incorporate values of extreme programming as much as possible while developing our team project.
### Simplicity

> We decided to keep our project as simple as possible considering the time limit we have.

> We thought of starting with the base functionality that is expected in the team project.Since we did our individual project we were familer with the starbucks business logic part, so we decided to take up that as a our first task and then move to the functionalities for extra credit.

> We started taking step by step development. I was assigned to implement payment API, and I started with building a Model class for Payments and then built classes upon that.
   
> Once I had business logic part ready, I made it as jar file and used it in the rest API handler methods.

 
### Communication

> Since my team members are in different places, and one member will be out country, we decided to meet using zoom conferences for scrum meetings and also to discuss about our tasks.

> Other than Zoom calls we also had quick meetings after the classes to discuss about the issues and tried to resolve it.

> We maintained git project track board to track our todo tasks , in progress task and the completed tasks.

> Git project track board helped me to report the issues I faced and let the team member know the issue I am stuck with.


### Courage

> We were new to REST API based programming and decided to help each other and get the project done.

> We were transparent on our work and issues, so that all have an idea what is happening over all as a team.

> We had only 2 weeks of time to design and implement our project and we decided to give our best on this project and adopt to the situation and make changes to the plan according the situation.

### Respect

> We respected all team members opinion on how to proceed in the project.

> Each one had their own level of expertise, we are helping each other to solve the issues and get the development going. 

### Feedback

> I pushed to git our working code base to our branch in git.

>  Then raised pull request to merge to master, so that other team members gives feedback on my code and merge the code to master.

----
