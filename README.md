
### Installation of springboot-cli using skdman

```
 sdk install springboot
 spring --version
```


should show:
```
Spring CLI v2.6.6
```

vi app.groovy

```
@RestController
class ThisWillActuallyRun {

    @RequestMapping("/")
    String home() {
        "Hello World!"
    }

}

```

Now run
`spring run app.groovy` and on browser goto `http://localhost:8080`


### Install maven
`sudo apt install maven`

`mvn -v`
Should show something like
```
Apache Maven 3.6.3
Maven home: /usr/share/maven
Java version: 14.0.2, vendor: Private Build, runtime: /usr/lib/jvm/java-14-openjdk-amd64
Default locale: en_GB, platform encoding: UTF-8
OS name: "linux", version: "5.4.0-105-generic", arch: "amd64", family: "unix"
```


#### 1. Install app via web 
Navigate to `https://start.spring.io/`

####2. Command line app creation using spring-cli

#### MavenApp
```
spring init -dependencies=web,thymeleaf MavenWebThymeLeaf.zip 
Using service at https://start.spring.io
Content saved to 'MavenWebThymeLeaf.zip'
```

#### Gradle app
```
spring init --build=gradle --java-version=1.8 --dependencies=web,thymeleaf --packaging=war GradleApplication.zip
Using service at https://start.spring.io
Content saved to 'GradleApplication.zip'
```

#### With postgres
```
spring init --build=gradle --java-version=1.8 --dependencies=web,thymeleaf,postgresql,mariadb --packaging=war GradleApplicationPostgres.zip --force
Using service at https://start.spring.io
Content saved to 'GradleApplicationPostgres.zip'
```

##Will use maven with Postgres
```
spring init --dependencies=web,thymeleaf,postgresql,mariadb  SpringBootPostgres.zip 
```


### Run the Application
To run the application, run the following command in a terminal window (in the complete) directory:

```
./gradlew bootRun
```

If you use Maven, run the following command in a terminal window (in the complete) directory:

```
./mvnw spring-boot:run
```


### Running with logging
```
#mvn
 
mvn spring-boot:run
-Dspring-boot.run.arguments=--logging.level.org.springframework=TRACE,--logging.level.com.example.SpringBootPostgres=TRACE
When working with Gradle, we can pass log settings through the command line. This will require setting the bootRun task.


#Gradle

./gradlew bootRun -Pargs=--logging.level.org.springframework=TRACE,--logging.level.com.example.SpringBootPostgres=TRACE
```


# ------
### Using Docker postgres:

```
sudo docker run --name=postgres-docker -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine

sudo docker ps

CONTAINER ID   IMAGE             COMMAND                  CREATED         STATUS         PORTS                                       NAMES
1d03444ba05d   postgres:alpine   "docker-entrypoint.s…"   4 seconds ago   Up 3 seconds   0.0.0.0:5432->5432/tcp, :::5432->5432/tcp   postgres-docker


sudo docker port  postgres-docker

5432/tcp -> 0.0.0.0:5432
5432/tcp -> :::5432

sudo docker exec -it 1d03444ba05d bin/bash
bash-5.1# psql -U postgres
psql (14.2)
Type "help" for help.
```

##-----------------------------

### Install postgresql manually (using above docker is easier) 
```
sudo apt install postgresql postgresql-contrib

sudo systemctl start postgresql.service
sudo -i -u postgres
createdb mytest
createuser --interactive
```
###
```
Enter name of role to add: mytest
Shall the new role be a superuser? (y/n) y
```

```
psql
ALTER USER mytest WITH PASSWORD 'mytest';
exit
```

Now ctrl d back to original account and 
 sudo vi /etc/postgresql/12/main/pg_hba.conf 
```
# local   all             postgres                                peer
local   all             postgres                                md5
local   all             mytest                                md5

# TYPE  DATABASE        USER            ADDRESS                 METHOD

# "local" is for Unix domain socket connections only
local   all             all                                     peer
```

Add the line for mytest as above and postgres also changed to md5 
save and quit
```
sudo service postgresql restart
sudo -u postgres
psql -U mytest -d mytest
```

This should now prompt for a password

let's stop postgres and use docker image instead

`sudo service postgresql stop`

### Some Postgres commands and tests:
```

psql -U postgres 

postgres=# \l
                                 List of databases
   Name    |  Owner   | Encoding |  Collate   |   Ctype    |   Access privileges   
-----------+----------+----------+------------+------------+-----------------------
 postgres  | postgres | UTF8     | en_US.utf8 | en_US.utf8 | 
 template0 | postgres | UTF8     | en_US.utf8 | en_US.utf8 | =c/postgres          +
           |          |          |            |            | postgres=CTc/postgres
 template1 | postgres | UTF8     | en_US.utf8 | en_US.utf8 | =c/postgres          +
           |          |          |            |            | postgres=CTc/postgres
(3 rows)

postgres=# create database mytest;
CREATE DATABASE
postgres=# \l
                                 List of databases
   Name    |  Owner   | Encoding |  Collate   |   Ctype    |   Access privileges   
-----------+----------+----------+------------+------------+-----------------------
 mytest    | postgres | UTF8     | en_US.utf8 | en_US.utf8 | 
 postgres  | postgres | UTF8     | en_US.utf8 | en_US.utf8 | 
 template0 | postgres | UTF8     | en_US.utf8 | en_US.utf8 | =c/postgres          +
           |          |          |            |            | postgres=CTc/postgres
 template1 | postgres | UTF8     | en_US.utf8 | en_US.utf8 | =c/postgres          +
           |          |          |            |            | postgres=CTc/postgres
(4 rows)


postgres=# \c mytest;
You are now connected to database "mytest" as user "postgres".
mytest=# \dt;
Did not find any relations.
mytest=# \d

mytest=# create table testing(id UUID not null, name varchar(250) not null);
CREATE TABLE
mytest=# \d
          List of relations
 Schema |  Name   | Type  |  Owner   
--------+---------+-------+----------
 public | testing | table | postgres
(1 row)

mytest=# create extension "uuid-ossp";
CREATE EXTENSION
mytest=# insert into  testing (id, name) values (uuid_generate_v4(), 'testing');
INSERT 0 1


mytest=# select * from testing;
                  id                  |  name   
--------------------------------------+---------
 a68c6dd6-acda-4f71-b537-d31dc03338e3 | testing
(1 row)

mytest=# 


mytest=# \q
bash-5.1# ctrl D 
exit

``` 
