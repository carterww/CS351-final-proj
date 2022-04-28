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
   - If you did not make the database name 'groupten' change the 'groupten' string every GRANT ALL PRIVILEGES ON groupten.*
     to your database name.
4) Now the database can be used for this application and the representatives can login.

Configuring the Java Application:
1) Access the class: com.groupten.data.DependentVars and change the url and database name to their respective values
   for your database.
   - If the database name is not 'groupten' and the 'users.sql' file was not adjusted prior to execution, then the
   'users.sql' file will not grant users priveleges and they cannot login.
2) A classpath must be set to the connectorj.jar file located in the 'libs' folder. This can be done easily through an
   IDE like Eclipse or IntelliJ (.iml file has classpath so if running in IntelliJ, this step shouldn't be required.
   - Your IDE likely has a way to do this, so search how to add a classpath to a jar file for your IDE.
   – If you are not using an IDE, and running through 'java' command in terminal, enter -classpath "..\libs\connectorj.jar;"
     as an argument passed to java.
   - For these reasons we recommend using IntelliJ because the classpath is already set in the .iml file provided.
3)
