![Chatty](https://user-images.githubusercontent.com/73137611/155671623-28ee713f-1778-4284-87f6-34a56fb4d26c.png)
>A desktop chatting application built using JavaFX and RMI as our first project at the Information Technology Institute. It takes full advantage of JavaFX's reactive programming model, using property binding to synchronize Models with Views.

# 🏛 Architecture
This software project was built using a layered architecture. The following diagram demonstrates an example use case that goes through all the layers. 

![image](https://user-images.githubusercontent.com/73137611/155808282-0a0ee1ab-9d35-4258-a953-c9ccad72f707.png)

# ⚙ Features
* Registration and login
* One to one and group chats
* File transfer
* Server statistics and management 
* Updating user profiles
* AIML chatbot

![Animation](https://user-images.githubusercontent.com/73137611/155806535-92e5a736-0b30-4e19-b5f1-371c91970736.gif)
![Animation2](https://user-images.githubusercontent.com/73137611/155806878-b4b497cd-5821-4420-9a9d-e95340aa4ff2.gif)


# 💻 How to run
**Maven**
* Download version 0.0.4.3 of the chatbot dependency from [here](https://code.google.com/archive/p/program-ab/downloads).
* Install it using the following maven command:
```
mvn install:install-file -Df`ile=Ab.jar -DgroupId=com.google -DartifactId=Ab -Dversion=0.0.4.3 -Dpackaging=jar
```
* Run the `mvn install` phase from the chatty-parent directory to create the shaded executable jars in the `target/` directory
* Run the jars using the `java -jar` command

**MySQL**
* Use the chatty.sql script to create the database using MySQL 8.0.28
* Create a database user using MySQL Commandline 8.0 using the following commands:
```sql
CREATE USER 'chattyuser'@'localhost' IDENTIFIED BY 'P@$$word';
GRANT ALL PRIVILEGES ON chatty . * TO 'chattyuser'@'localhost';
FLUSH PRIVILEGES;
```

# 👷‍♀️ Contributors
* [Ahmed Osama](https://github.com/AhmedOsama123)
* [Christine Nedaa](https://github.com/christinenedaa)
* [Hafsa Mohamed](https://github.com/hafsamohamed)
* [Mahmoud Fawzy](https://github.com/MahmoudFawzyKhalil)
* [Salma Fayez](https://github.com/salmafayez)
