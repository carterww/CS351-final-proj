# CS351-final-proj
CS351-001 Final Project Spring 2022
By: Ali Al-Raisi
    Carter Williams
    Jacob (Jake) Ogorek
    Ashlyn Thrift
        
USERNAME : 'rafaelcampos15'
PASSWORD : raflol

USERNAME : 'megangradey30'
PASSWORD : meglol

USERNAME : 'huitian45'
PASSWORD : 'huilol'

USERNAME : 'janetsefton60'
PASSWORD : 'janlol'

USERNAME : 'alial-raisi85'
PASSWORD : 'alilol'

USERNAME : 'carterwilliams69'
PASSWORD : 'cheese'

The usernames, passwords, and privileges can be adjusted in the users.sql file, but these are the defaults.

System Requirements:
Java SE 14 or newer (only tested on 14 and 15)
MYSQL DBMS

Installation/Configuration:

Creating the database:
1) Create a new database in mySQL named 'groupten.'
2) Use the database dump file, 'grouptendump.sql' to add the tables to the database you just created.
   - This can be done in the terminal/cmd with the command mysql -u [user] -p groupten <"[dir]\grouptendump.sql"
     where dir is the directory to the file.
   - The terminal/cmd may have to be opened as an administrator and execute this as the root or root equivalent user.
3) Now add the existing user's privileges by doing the same thing but with the 'users.sql' file but the database name
   after -p can be left off. Here is how you can configure these users to your liking:
   - If you would like to adjust the representative's username or password it can be done.
   - '?'@'localhost' the ? represents the username in the CREATE USER statements so change that string.
   - IDENTIFIED BY '?'; the ? represents the password in the CREATE USER statements so change that string.
4) Now the database can be used for this application and the representatives can login and execute the queries.

Configuring the Java Application:
1) Access the class: com.groupten.data.DependentVars and change the url and database name to their respective values
   for your database.
   - If the database name is not 'groupten' and the 'users.sql' file was not adjusted prior to execution, then the
   'users.sql' file will not grant users priveleges and they cannot login.
2) A classpath must be set to the connectorj.jar file located in the 'lib' folder. This can be done easily through an
   IDE like Eclipse or IntelliJ (.iml file has classpath so if running in IntelliJ, this step shouldn't be required.
   - See https://www.geeksforgeeks.org/how-to-add-jar-file-to-classpath-in-java/ for more information
   - Your IDE likely has a way to do this, so search how to add a classpath to a jar file for your IDE.
   – If you are not using an IDE, and running through 'java' command in terminal, enter -classpath "..\libs\connectorj.jar;"
     as an argument.
   - For these reasons we recommend using IntelliJ and Eclipse because the classpath is already set in the .iml file provided.
3) Run the Main class in 'com.groupten.app.Main' to start the application!

Common Problems:
If setup is done incorrectly, these are some common issues and fixes.

- Unable to login. No suitable driver found for jdbc:mysql://localhost:3306/groupten
  This means your classpath to the connectorj.jar file provided in the 'lib' folder is not set up properly. There are many
  ways this can be fixed listed in number 2 of section 'Configuring the Java Application.' If it is still not working,
  check https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-binary-installation.html out for additional help.

- Cannot login as a representative.
  If the users.sql file was not executed, the existing users will not be able to login. Make sure you ran the file as a
  root user or root equivalent user.

- Cannot create a user after adding a representative.
  This means a representative was added to the rep table, but no user was added to login as. This stems from the task
  being run as a user with no CREATE USER or GRANT privileges. Make sure you logged into the database as a representative
  or root user.