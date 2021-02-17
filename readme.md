
SupportSystem application : Micro-Service with in memory H2 database
Default port is 8080
Environment Variable :

eureka.client.serviceUrl.defaultZone
default value is : http://localhost:8762/eureka
server.port
default value is : 8080
This micro-service requires one eureka server to be up and running. While running SupportSystem container please use eureka.client.serviceUrl.defaultZone flag to pass the Netflix server URL

Swagger UI is available at http://localhost:8080/support/swagger-ui.html : please change the port to mapped host port.

On startup, this container creates the necessary tables in the database. Populates the table with initial data.

https://hub.docker.com/r/devakash/supportsystem
