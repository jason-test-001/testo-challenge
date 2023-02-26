# Before Start Server
### 1.start Mysql database,change the db url、username、password config in the path: src/main/resources/application.yml
###  2.run SQL to DB from the path  :src/main/resources/sql/testo challenge.SQL

# Start Server
### Run: src/main/java/com/testo/TestoChallengeApplication.java

# Test Server logic
###  1. how to get short url ?
        VisitorTest.java 、 CommonTest.java 、AdminTest.java all of then has "shortUrl" method to access.
        if the database has record ,it will return directly and update table short_url.
        if the database has no record,it will generate one to save DB and return.
        this interface has not permission control.

### 2. how to control the permission ?
        case 1 : user not login, please see VisitorTest.java "queryAllRecord" and "queryUserRecord" test method.
                 neither method has permission to access.
                 
        case 2:  common user login, please see CommonTest.java
                 can ony access  "queryUserRecord" method, it will search it's own record.
                 have no permission to access "queryAllRecord".

        case 3: admin user login, please see AdminTest.java
                 can not only access "queryUserRecord" to query admin's own statistic,
                 but also can access "queryAllRecord" to query all statistic.
# Base Framework
springboot、spring：main framework 
mybatis：DB operator
shiro：define role and permission
mysql: database