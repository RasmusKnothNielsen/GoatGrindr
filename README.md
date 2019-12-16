# GoatGrindr

Dating app for serious goats

A Tinder/Grindr like app that offers 
- A carousel mode
- Swipe to like/dislike
- Match with new goats if both likes each other
- Admin Panel to delete goats and make goats admins
- Spring security to ensure integrity
- Bcrypt to hash passwords before they are being stored in the MySQL database


This web application was developed as an mandatory assignment on 3. semester of the AP in Computer Science from KEA, Denmark.

To run the application, an application.properties file is needed in the following folder:

```
src -> main -> resources
```

With the following text:

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/goats?serverTimezone=UTC
spring.datasource.username=
spring.datasource.password=
spring.main.allow-bean-definition-overriding=true
spring.profiles.active=dev
```
Remember to fill in the username and password of the corresponding MySQL database, you'd like to use.
In the above example, the database is called goats.

Test data for the database is provided in the following folder:
```
src -> main -> resources -> database
```

Choose database_mini.dml to initialise the database with minimal data.

The admin login is:
```
Username: Ferdinand
Password: Hunter2
```

Most of the users passwords are:
```
Hunter2
```


Developers involved:
- Niki Ryom Hansen
- Thomas Dahl
- Rasmus Knoth Nielsen

Have fun with GoatGrindr!