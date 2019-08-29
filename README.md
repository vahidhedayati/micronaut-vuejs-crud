Micronaut backend vuejs frontend application - with security
---

```
	users.add(addUser("admin","password", "Alan", "Wollenstein"));
		users.add(addUser("susan","password", "Susan", "Jones"));
		users.add(addUser("bill","password", "Billy", "Smith"));
		users.add(addUser("ben","password", "Benjamin", "Thomas"));

```

Above usenames and passwords can login and they all get `ROLE_ADMIN`, 
refer to 2nd video and `DataLoader.java` in `Gateway` application

### Same project with no security:
git clone https://github.com/vahidhedayati/micronaut-vuejs-crud -b default 




Sample to get h2 database working taken from [this guide](https://guides.micronaut.io/micronaut-data-access-jpa-hibernate/guide/index.html)

Demo of this project on Youtube
-----------
###### [Micronaut vuejs crud Part 1 ](https://www.youtube.com/watch?v=Lhg8p9zI8xA)
###### [Micronaut vuejs crud authentication Part 2](https://www.youtube.com/watch?v=bD4sDz1PuEQ)


The discussions on the video around missing aspects of CRUD should all now be fixed -
> The user object as shown below is now linked to the updateUserId which is a Long value of actual update userId
 
when list is received by Gateway gatewayController uses flatMap and connects to userClient picks up User object based on that Id.
Gateway has a modified HotelModel object which has User updateUser with getter and setter that is set by the flatMap function.
User at front end then sees the list as well as which user that did the update. 
  
>Frontend validation 
(hotels [add a hotel](https://github.com/vahidhedayati/micronaut-vuejs-crud/blob/master/frontend/src/components/hotel/HotelForm.vue#L9-L144) )
 / backend validation (gateway  / [HotelSaveCommand](https://github.com/vahidhedayati/micronaut-vuejs-crud/blob/master/gateway/src/main/java/gateway/adaptors/models/implementation/HotelSaveCommand.java#L22-L28) )
 
   
![user object binds to instance list](https://raw.githubusercontent.com/vahidhedayati/micronaut-vuejs-crud/master/docs/binding-to-userObject-for-listing-using-flatMap-inGateWayController.png)

![backend validation](https://raw.githubusercontent.com/vahidhedayati/micronaut-vuejs-crud/master/docs/backend-validation.png)


![Hotel name translated using static/translations/he.json](https://raw.githubusercontent.com/vahidhedayati/micronaut-vuejs-crud/master/docs/translations-backend-codes-translated.png)

![Hotel name translated using static/translations/he.json](https://raw.githubusercontent.com/vahidhedayati/micronaut-vuejs-crud/master/docs/translations-frontend-error-codes-translated.png)



Running app
----

##### Run Consul

To run first either install consul locally and run `./consul agent dev`

or if you have installed docker simply run `sudo docker run -p 8500:8500 consul`
 
 
 

##### To start all applications in one session run:

```
./gradlew frontend:start backend:run gateway:run userbase:run --parallel


# Advanced: 
# When running on linux a process for node hangs on which also keeps jvms active - killing node kills all other jvms hanging off
# this is all in 1 line to kill if found and start apps

kill -9 $(netstat -pln 2>/dev/null |grep LISTEN|grep node|awk '{print $7}'|awk -F"/" '{print $1}'); ./gradlew frontend:start backend:run gateway:run userbase:run --parallel



```

The above will launch 1 instance of frontend vuejs site running on `localhost:3000` 
and a backend micronaut site running on port `localhost:{random}` a gateway micronaut app running on port 
`localhost:8080` 


###Security test 
```
curl -X "POST" "http://localhost:8080/login" -H 'Content-Type: application/json; charset=utf-8' -d '{ "username": "admin", "password": "password" }'
```
You should see something like this
```
{"username":"admin","roles":["ROLE_ADMIN"],"access_token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsIm5iZiI6MTU2NzA4OTQxNywicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpc3MiOiJnYXRld2F5IiwiZXhwIjoxNTY3MDkzMDE3LCJpYXQiOjE1NjcwODk0MTd9.JxtuOp7KycOO_pERoBf3DTm-qS6VBzl36THtLs89ig4","refresh_token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsIm5iZiI6MTU2NzA4OTQxNywicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpc3MiOiJnYXRld2F5IiwiaWF0IjoxNTY3MDg5NDE3fQ.fYW-rEx8el7_C52oL35SxkcKVEAh5Tl5GikHHdImdO4","token_type":"Bearer","expires_in":3600}
```


##### frontend changed to resemble a grails vuejs site: will start up on port 3000
```
c:\xxx\micronaut\hotel-info>gradlew frontend:start

To manually start app using npm or yarn run:

micro-projects/hotel-info/frontend$ npm run dev   

micro-projects/hotel-info/frontend$ yarn run dev


```


##### To run Backend: will currently launch and bind to port {random}  - mutiple instances can be started

```
c:\xxxx\micronaut\hotel-info>gradlew backend:run --stacktrace

```


##### To run gateway app : will currently launch and bind to port  8080 - testing single instance only currently

```
c:\xxxx\micronaut\hotel-info>gradlew gateway:run --stacktrace

```


##### Testing gateway -> backend:

```bash

curl "http://localhost:8080/list?name=&offset=1"
[{"id":7,"code":"MARL","name":"Mariott International - London Bridge","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":13,"code":"STWL","name":"Starwood Hotels - London Bridge","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":19,"code":"ACGL","name":"Accor Group - London Bridge","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},{"roomType":"TRIP","price":85.00,"stockTotal":200},
{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":25,"code":"CHIL","name":"Choice Hotels - London Bridge",
"hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},{"roomType":"DOUB","price":65.00,"stockTotal":200},
{"roomType":"TWIN","price":65.00,"stockTotal":200},{"roomType":"TRIP","price":85.00,"stockTotal":200},
{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":31,"code":"BEWL","name":"Best Western - London Bridge",
"hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},{"roomType":"DOUB","price":65.00,"stockTotal":200},
{"roomType":"TWIN","price":65.00,"stockTotal":200},{"roomType":"TRIP","price":85.00,"stockTotal":200},
{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":37,"code":"CARL","name":"Carlson - London Bridge","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":43,"code":"HILI","name":"Hilton - Islington","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":49,"code":"MARI","name":"Mariott International - Islington","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":55,"code":"STWI","name":"Starwood Hotels - Islington","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},{"roomType":"TRIP","price":85.00,"stockTotal":200},
{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":61,"code":"ACGI","name":"Accor Group - Islington","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]}]

curl "http://localhost:8080/1"
{"id":1,"code":"HILL","name":"Hilton - London Bridge",
"hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},
{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},
{"roomType":"FAM","price":95.00,"stockTotal":200}]}

```

##### Cors issue attempting to hit vuejs site to a core micronaut java controller:

```
webpack-internal:///./node_modules/babel-loader/lib/index.js!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/components/Pagination.vue:99  thi1 0
:3000/#/hotel:1 Failed to load http://localhost:8080/list?name=&offset=0: No 'Access-Control-Allow-Origin' header is present on the requested resource. Origin 'http://localhost:3000' is therefore not allowed access.
webpack-internal:///./src/services/HotelService.js:48 XMLHttpRequest
:3000/#/hotel:1 Failed to load http://localhost:8080/list?name=: No 'Access-Control-Allow-Origin' header is present on the requested resource. Origin 'http://localhost:3000' is therefore not allowed access
```

 `No 'Access-Control-Allow-Origin' header is present on the requested resource.` is typically an issue when attempting to hit a java micronaut app.
 
 To complete this a new `gateway app` will be added after this push which talks to http clients of core backend java micronaut apps.   

Port changed in application.yml of backend to `${random.port}` port 8080 will become the `gateway site`

To create gateway site:

`mn create-app gateway`

Gateway basics added as part of last push for things to work consul now is required:


Here is a very basic sample of site loading up micronaut backend app via gateway app through to vuejs frontend app.

![basics working](https://raw.githubusercontent.com/vahidhedayati/micronaut-vuejs-crud/master/docs/working-with-pagination-vuejs.png)

![save search demo](https://raw.githubusercontent.com/vahidhedayati/micronaut-vuejs-crud/master/docs/save-search-demo.png)

