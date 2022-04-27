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

Installation/Configuration:
Set url to database and database name in com.groupten.data.DependentVars to their respective values for the database
being used. If you would like the existing users to be given privileges to login and do queries, use the database name
'groupten.' Those users created in the dump will only be given access to that database.

Also, our MYSQL database dump will be included in the resources folder, so it can be created to have the
existing users and data in the database. To restore the database, create a new database named 'groupten.' Input the command
'mysql -u [user] -p groupten < '[dir]\grouptendump.sql'' in a terminal or command prompt. Then to add the users with
privileges on that database, run the same command, but with the users.sql file.That is all the setup required, now the
main class in the src folder can be compiled and run. Or the application can be used to create an executable jar file.

The ConnectorJ jar dependency is included in the libs folder, so there is no need to set the directory. If the ConnectorJ
is not working, check the CS351FinalProject.iml file under <CLASSES> for the directory ConnectorJ is set to.
