# Project Journal

|Name | Detail|
|---|---|
| Rupal Martin | Daily Scrum |

## Daily Scrum Report

**Jul 17th**
<<<<<<< HEAD
=======
- First Meeting (17 Jul 2018)
- We discussed our current capacity and decided to split the API to one team member each.
- I brushed my java skills by going through youtube videos on how to create maven project,using git, using PostMan for REST API.
- I also downloaded starbucks app to get a deeper understanding.
- I also created task for my part and mentioned in To_Do
- I also created rough Google sheets for monitoring the hours spent.
>>>>>>> b15f4d5fc4800d27b0ac735bd854308951161c96

- What tasks did I work on / complete?

> As per the discussion with team I was assigned with user authentication Api to work on.
> Completed setting up the environment like Intelij and mysql as local DB.
> Created a maven project structure to implement business logic.
> Started filling the Excel sheet to track the work done.

- What am I planning to work on next?

> Create DAO class/model class for user authentication.
> Create Database and user and cards table for persisting user authentication details.
> Connect to the DB from the Java application and implement user authentication api logic to save and retreive user authentication details from the table.

- What tasks are blocked waiting on another team member?

 > No blockage for me for my next tasks.
-------------
**Jul 18th**
<<<<<<< HEAD
=======
- Meeting (19 Jul 2018)
- Discussed the class names and the methods to implement and validate for all 4 API
>>>>>>> b15f4d5fc4800d27b0ac735bd854308951161c96

- What tasks did I work on / complete?

> Created user authentication model class and a service class and implemented the business logic to save and retreive data from DB.
> Created a user table in DB and Successfully connected to Mysql DB from maven java application and was able to save and get the user authentication details from the table.
> Filled the excel sheet for burndown chart.

- What am I planning to work on next?

> Export the user authentication business logic class as .jar file.
> import the above .jar file as a exterbal library.

- What tasks are blocked waiting on another team member?

> No blockage for my next tasks.

-------------

**Jul 19th**

- What tasks did I work on / complete?

> I was trying to import .jar file as maven dependancy. Facing some issue to  import.
> Filled the excel sheet for burndown chart.
- What am I planning to work on next?

> Resolve the dependancy error in pom.xml file.
> Write a controller class to map user authentication rest api to the cards.
> Use the library of business logic in the controller class.

- What tasks are blocked waiting on another team member?

> No blockage for my next tasks.

-------------

**Jul 20th**
<<<<<<< HEAD
=======
- Meeting (20 Jul 2018)
- Decided to create 4 different jar files to reduce dependency among others.
>>>>>>> b15f4d5fc4800d27b0ac735bd854308951161c96

- What tasks did I work on / complete?

> Implemented the Rest end point for user authentication api in controller class.
> Filled the excel sheet for burndown chart.

- What am I planning to work on next?

> Hitting the rest end point from the Postman rest client tool.
> Check if the library works fine.

- What tasks are blocked waiting on another team member?

> No blockage for my next tasks.


-------------

**Jul 21st**
<<<<<<< HEAD
=======
- Meeting (21 Jul 2018)
- Team mutually decided to create one jar instead of 4 different jar files for smoth implementation.
>>>>>>> b15f4d5fc4800d27b0ac735bd854308951161c96

- What tasks did I work on / complete?

> Mapped user authentication and card id details from user table and card table.
> I was trying call Rest api from postman rest client, but getting error.

- What am I planning to work on next?

> connect to the rest endpoint from postman.
> Need to figure out how to get card id and order details from card and order apis.

- What tasks are blocked waiting on another team member?

> Need to understand how user authentication api and card api is implemented and how to use them to get my required data.
-------------

**Jul 22nd**
<<<<<<< HEAD
=======

- Meeting (22 Jul 2018)
- Discussed Status updates of individual team members.
- Decided to create a single controller class instead of 4 controller class.

>>>>>>> b15f4d5fc4800d27b0ac735bd854308951161c96
- What tasks did I work on / complete?
- What am I planning to work on next?
- What tasks are blocked waiting on another team member?
-------------
