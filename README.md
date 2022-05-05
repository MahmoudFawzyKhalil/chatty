#📚 BookEcommerce
This is a Book Ecommerce system using rest API java

>A Web Book Store application built using Rest Api and JPA as a project at the Information Technology Institute. 

# 🏛 Architecture
This software project was built using a layered architecture. The following diagram demonstrates an example use case that goes through all the layers. 

# ⚙ Features
* Registration and login and logout
* Online Book Shopping 
* Mangae product (ADD UPDATE DELTE EDIT)
* Manage Cart (add a product to cart, remove a product from the cart)
* Make order

# ⚙ Technologies used
* Maven
* JPA
* Rest API
* ORM
* Bean Validation (Hibernate Validator)
* MapStruct
* MySQL

# 🛠 How to run
**Maven**
* command to deploy project `mvn clean package tomcat7:redeploy`
* Run the `mvn install` phase from the project directory to create the shaded executable jars in the `target/` directory
* Run the jars using the `java -jar` command

**MySQL**
* Create a database user using MySQL Commandline 8.0 using the following commands:
```sql
CREATE USER 'bookyuser'@'localhost' IDENTIFIED BY 'P@$$word';
GRANT ALL PRIVILEGES ON booky . * TO 'bookyuser'@'localhost';
FLUSH PRIVILEGES;
```

# 👷‍♀️ Contributors
* [Hafsa Mohamed](https://github.com/hafsamohamed)

