About Media Search API
=================

Media Search API  


## Features
Functionalities Media Search API provides are:
- Search media (albums and books) avaialable via iTunes Store Search API and Google Volumes API


## Libraries used
- Java 8
- Spring Boot 2
- Spring Boot Actuator
- Spring WebFlux
- Springfox OAS 3
- SLF4J 


# Installation

## Prerequisites 
JDK 1.8
Maven 3.3


## Installation steps
Build the application with the following maven command:

 `mvn clean install`.

This command will run tests along with the build. To omit test use the following maven command:

`mvn clean install -DskipTests`

## Build artifact
`media-search-api-${project.version}.jar`

## Environment variables
The following environment variable should be set:
JAVA_HOME=path/to/jdk

## Running
`java -jar  media-search-api-${project.version}.jar`

# Deployment 


## Health page
Health status of the whole application could be found at:
`http://[hostname]:8080/actuator/health`
Health status of upstream services could be found at:
`http://[hostname]:8080/actuator/health/clients`

## Swagger UI
Media Search API documentation could be found at:
`http://[hostname]:8080/swagger-ui/`

## Metrics
Endpoint statistics for all classes annotated with @RestController in Media Search application could be found at:
`http://[hostname]:8080/actuator/metrics/http.client.requests`
Endpoint statistics for upstream services could be found at:

iTunes Search API:
`http://[hostname]:8080/actuator/metrics/client.itunes.requests`

Google Volumes API:
`http://[hostname]:8080/actuator/metrics/client.google.requests`

# Developers
@mlazic
