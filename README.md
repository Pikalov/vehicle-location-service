## Task

Vehicle geolocation. <br>
There is a large amount of vehicles (millions). Each vehicle is equipped with a GPS tracker. 
The tracker periodically sends vehicle geolocation to server (about every ten seconds). 
The task is to create a RESTful web service that will: 
<ol>
    <li> Receive current coordinates from vehicle GPS trackers; </li> 
    <li> On request return the list of vehicles that are located within a given rectangle on the map. 
        The rectangle coordinates are provided as the request params. </li>
</ol>

## Solution

According to the task, two REST-endpoints has been implemented:
<ol>
    <li> endpoint to provide upsert operation for vehicle and its location </li>
    <li> 
        endpoint to provide spatial search for all the vehicles located inside of given rectangle search area
        spanned between two points (order of points doesn't matter) specified as its longitude and latitude
    </li>
</ol>

To provide mapping between persistence entities and dto-s the solution using mapstruct has been implemented.
Such solution to map such simple entities might look as some kind of over-engineered solution, but
mapstruct is very useful in terms of scalability (changing the number of attributes to map mostly require
far less effort to map them as it automatically maps simple attributes such as id, name etc).

MongoDB has been chosen for implementing persistence layer of this application. MongoDB has mostly more performant 
search by spatial type of data then other databases (especially comparing to PostGIS) and convenient spring-data
with very useful spatial indexes. MongoDB's database, collection and index will be automatically created after the 
first start of application.

#### Pre-requisites
Maven should be installed.
MongoDB should be installed and be available on localhost with its default port 27017.

#### Run
Run the project by following command: `mvn spring-boot:run`
Rest endpoints are available by url: http://localhost:8080/vehicle-location-service/
Documented api is available on Springfox Swagger UI: http://localhost:8080/swagger-ui/