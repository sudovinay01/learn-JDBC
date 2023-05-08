# learn-JDBC
 learn how to use JDBC APIs. 

## Requirements
### JAVA 8
https://www.oracle.com/in/java/technologies/javase/javase8-archive-downloads.html

### Database - MySQL Enterprise 8.0

https://www.mysql.com/downloads/enterprise/

### Driver - MySQL Enterprise JDBC Driver - Progress

https://www.progress.com/jdbc/mysql

### IDE - Eclipse 2020-06
https://www.eclipse.org/downloads/packages/release/2020-06/r/eclipse-ide-enterprise-java-developers

## Setup 
1. Install above mentioned requirements from given links.
2. Create a database using below command in MySQl. 
```
CREATE DATABASE <DATABASE NAME>;
```
Eg:
```
CREATE DATABASE jdbc;
```
3. clone the repo and import into eclipse as java project.
4. You may see build issues.
5. To solve build issues import the driver jar present at the installation location.
> C:/Program Files/Progress/DataDirect/Connect_for_JDBC_51/lib/mysql.jar
6. For importing jar go to `project propeties > java build path > libraries tab` click on `Add external JARs` and add above mentioned jar and  click on apply. Remove any pre-existing mysql.jar if present.
7. In `/learn-JDBC/src/_00_data/ConnectionData.java` update required MySQL database details. If installed using default settings updating USERNAME, PASSWORD and DATABASE_NAME constants will be fine.
8. Setup completed. 

## How to run
* `_00_data` this package is only used as utilities and it does not contains any java file with main.
* All other packages has different use cases of JDBC APIs.
Eg: `_01_connection._01_DriverManagerFullURI.java` show how to connect to database using full URI. 
* Running `_01_DriverManagerFullURI.java` as java application should complete without any error.
* Debug code to understand the api.
