Attempt to get micronaut to work on windows 10 Home Edition
---


Please refer to different pushes  with some lines of instructions added here

##Installing micronaut
[Used micronaut 1.1.2 (current)](https://github.com/micronaut-projects/micronaut-core/releases/download/v1.1.2/micronaut-1.1.2.zip)

Once downloaded, unzip and place into a folder

![add mn_home](https://raw.githubusercontent.com/vahidhedayati/hotel-info/master/docs/modify_path_add_mn_home_bin.png)


![export path mn_home/bin](https://raw.githubusercontent.com/vahidhedayati/hotel-info/master/docs/export_MN_HOME.png)



open a `cmd` prompt and run :

```
mn init
```
This will initialiase and set up micronaut locally for usage.


After checking part 2

inside this app we run

mn create-app backend



## Import into eclipse -
 
Download eclipse  - https://www.eclipse.org/downloads/  - Currently Eclipse IDE 201903 


Set up above project to work under Eclipse:
 
> https://docs.micronaut.io/latest/guide/index.html
#### Configuring Eclipse IDE > Micronaut project into Eclipse using either Gradle BuildShip

### Eclipse  Version: 2019-03 (4.11.0) provides Gradle Buildship out of the box.


```
C:\xxx\micronaut\hotel-info>gradlew eclipse

BUILD SUCCESSFUL in 1s
5 actionable tasks: 5 executed
```

Not quite sure how you import, attempted File /  import / Gradle / and followed the wizard. 
Project has opened up in Eclipse.  


##Setting up frontend - push 6 
Once a folder called  `frontend` is manually created : `settings.gradle` defines the other application as frontend.

[package.json](https://github.com/asc-lab/micronaut-microservices-poc/blob/master/web-vue/package.json) was copied over from an existing sample project running micronaut-vuejs

#### Installing npm on windos
[download nodejs](https://nodejs.org/en/). By default this provides npm once installed

```
\hotel-info\frontend>npm install
npm WARN deprecated joi@14.3.1: This module has moved and is now available at @hapi/joi. Please update your dependencies as this version is no longer maintained an may contain bugs and security issues.
```




#### Commit 12: Vuejs - site is now working a new branch has been made called `basic_template` 

This contains a very basic vuejs configured app as well as a very basic micronaut 1.1.2 application none of which are doing any actual work as yet - just the core project so far - can be used to model custom data model from this point - some basic fab hotel stuff put into vuejs app that would need to change to whatever the company is.
 



#### Commit 14 - issues found with app starting / build.gradle - fixed a new branch has been made called `basic_template_fixed`

#### Commit 15 - adds `Hotel` `HotelRooms` domain classes to `backend` app. A variet of screen shots taken showing app working in both eclipse and intellij community edition. Eclipse needs additional setting updates which can be found under docs folder in form of screen shots. application.properties reverted to application.yml and `org.h2.Driver` added to application.yml 
Following added to main build.gradle:
```
   compile "io.micronaut.configuration:micronaut-hibernate-jpa"
   compile "io.micronaut.configuration:micronaut-jdbc-hikari"
   runtime "com.h2database:h2"
```

Running app
----

To start both run:

```
./gradlew frontend:start backend:run --parallel
```


To start  Frontend: You will also need to install Yarn for windows - set up yarn

``` 
Variable name:  YARN_HOME
Variable Value: C:\Program Files (x86)\Yarn

Add path value %YARN_HOME%\bin
```

New instructions: (frontend changed to resemble a grails vuejs site)
```
c:\xxx\micronaut\hotel-info>gradlew frontend:start

To manually start app using npm or yarn run:

micro-projects/hotel-info/frontend$ npm run dev   

micro-projects/hotel-info/frontend$ yarn run dev


```


To run Backend:

```
c:\xxxx\micronaut\hotel-info>gradlew backend:run --stacktrace



> Task :backend:compileJava
Note: Creating bean classes for 5 type elements

> Task :backend:run
16:51:43,762 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback-test.xml]
16:51:43,762 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback.groovy]
16:51:43,762 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Found resource [logback.xml] at [file:/C:/workbenches/micronaut/hotel-info/backend/build/resources/main/logback.xml]
16:51:43,883 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - debug attribute not set
16:51:43,891 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - About to instantiate appender of type [ch.qos.logback.core.ConsoleAppender]
16:51:43,901 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - Naming appender as [STDOUT]
16:51:43,903 |-INFO in ch.qos.logback.core.joran.action.NestedComplexPropertyIA - Assuming default type [ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
16:51:43,984 |-INFO in ch.qos.logback.core.ConsoleAppender[STDOUT] - Enabling JANSI WindowsAnsiOutputStream for the console.
16:51:43,984 |-WARN in ch.qos.logback.core.ConsoleAppender[STDOUT] - Failed to create WindowsAnsiOutputStream. Falling back on the default stream. ch.qos.logback.core.util.DynamicClassLoadingException: Failed to instantiate type org.fusesource.jansi.WindowsAnsiOutputStream
     ..
16:51:43,984 |-INFO in ch.qos.logback.classic.joran.action.RootLoggerAction - Setting level of ROOT logger to INFO
16:51:43,984 |-INFO in ch.qos.logback.core.joran.action.AppenderRefAction - Attaching appender named [STDOUT] to Logger[ROOT]
16:51:43,984 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - End of configuration.
16:51:43,994 |-INFO in ch.qos.logback.classic.joran.JoranConfigurator@482f8f11 - Registering current configuration as safe fallback point

16:51:45.752 [main] INFO  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Starting...
16:51:45.937 [main] INFO  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Start completed.
16:51:45.974 [main] INFO  org.hibernate.Version - HHH000412: Hibernate Core {5.4.0.Final}
16:51:45.990 [main] INFO  org.hibernate.cfg.Environment - HHH000206: hibernate.properties not found
16:51:46.206 [main] INFO  o.h.validator.internal.util.Version - HV000001: Hibernate Validator 6.0.13.Final
16:51:46.238 [main] INFO  o.h.v.i.engine.ConfigurationImpl - HV000002: Ignoring XML configuration.
16:51:46.491 [main] INFO  o.h.annotations.common.Version - HCANN000001: Hibernate Commons Annotations {5.1.0.Final}
16:51:46.676 [main] INFO  org.hibernate.dialect.Dialect - HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
16:51:47.240 [main] INFO  org.hibernate.tuple.PojoInstantiator - HHH000182: No default (no-argument) constructor for class: backend.domain.Hotel (class must be instantiated by Interceptor)
Hibernate: create table hotel (id bigint not null, code varchar(255), name varchar(255), primary key (id))
Hibernate: create table hotel_rooms (type varchar(31) not null, id bigint not null, price decimal(19,2), room_type varchar(255), stock_total bigint, hotel_id bigint, primary key (id))
Hibernate: create sequence hibernate_sequence start with 1 increment by 1
Hibernate: alter table hotel_rooms add constraint FKrxmhxdnj4fjesel7s5aw9yx0e foreign key (hotel_id) references hotel
16:51:47.757 [main] INFO  o.h.e.t.j.p.i.JtaPlatformInitiator - HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
 working on HILL
Hibernate: call next value for hibernate_sequence
 working on MARL
Hibernate: call next value for hibernate_sequence
 working on STWL
Hibernate: call next value for hibernate_sequence
 working on ACGL
Hibernate: call next value for hibernate_sequence
 working on CHIL
Hibernate: call next value for hibernate_sequence
 working on BEWL
Hibernate: call next value for hibernate_sequence
 working on CARL
Hibernate: call next value for hibernate_sequence
 working on HILI
Hibernate: call next value for hibernate_sequence
 working on MARI
Hibernate: call next value for hibernate_sequence
 working on STWI
Hibernate: call next value for hibernate_sequence
 working on ACGI
Hibernate: call next value for hibernate_sequence
 working on CHII
Hibernate: call next value for hibernate_sequence
 working on BEWI
Hibernate: call next value for hibernate_sequence
 working on CARI
Hibernate: call next value for hibernate_sequence
 working on BEWK
Hibernate: call next value for hibernate_sequence
 working on STWK
Hibernate: call next value for hibernate_sequence
 working on ACGK
Hibernate: call next value for hibernate_sequence
 working on CHIK
Hibernate: call next value for hibernate_sequence
 working on BEWK
Hibernate: call next value for hibernate_sequence
 working on CARK
Hibernate: call next value for hibernate_sequence
 working on BEWW
Hibernate: call next value for hibernate_sequence
 working on STWW
Hibernate: call next value for hibernate_sequence
 working on ACGW
Hibernate: call next value for hibernate_sequence
 working on CHIW
Hibernate: call next value for hibernate_sequence
 working on BEWW
Hibernate: call next value for hibernate_sequence
 working on CARW
Hibernate: call next value for hibernate_sequence
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
Hibernate: insert into hotel (code, name, id) values (?, ?, ?)
16:51:49.636 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 5642ms. Server Running: http://localhost:8081
<=========----> 75% EXECUTING [1m 26s]
```

```
c:\xxx\micronaut\hotel-info>mn create-app backend1 --features=groovy



```

Fixed issue now app starts - groovy app backend1 added not working returning back to java app for now
