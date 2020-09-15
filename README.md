About Media Search API
=================

The Media Search API allows users to search for content within the iTunes Store and Google Books API. At this moment it is possible to search for albums and books only.

As the Media Search API should retrieve and process data from multiple sources (iTunes Store and Google Books) in order to achieve higher efficency it makes sense to perform these calls in parallel and to use a non-blocking client. By using Spring WebFlux and Spring WebClient it should be possible to achieve this.

With the help of Spring Boot Actuator, Media Search API provides health checks, metrics gathering, HTTP tracing etc. More on specific endpoints later in the document.

Errors (including timeouts) from any of the upstream services won't affect user's request - errors will be silenced and won't be propagated to the user and user will receive data only from available service (if there is any). In the case when both services are not available, the user would receive an empty result set.

This is my first project ever with Sping WebFlux and reactive  programming and although it was unusual to think in "reactive" way I had fun.


## Features
Functionalities Media Search API provides are:
- Search media (albums and books) avaialable via iTunes Store Search API and Google Volumes API


## Libraries used
#### API
- Java 8
- Spring Boot 2
- Spring Boot Actuator
- Spring WebFlux
- Springfox OAS 3
- SLF4J 

#### UI
- Angular 10
- Angular Material 10
- Node 12
- npm 6


# Installation

## Prerequisites 
JDK 1.8
Maven 3.3
Node 12 
npm 6

**Please note**: Node and npm will be installed automatically during the execution of `mvn clean install` command. 

## Installation steps
Build the application with the following maven command:

 `mvn clean install`.

This command will run tests along with the build. To omit test use the following maven command:

`mvn clean install -DskipTests`

## Build artifact
`media-search-api-${project.version}.jar`

Build artifact is to be found in the target directory of media-search-api module.

## Environment variables
The following environment variable should be set:
JAVA_HOME=path/to/jdk

## Running
To run the application, run the following command in a terminal window of the target directory:

`java -jar  media-search-api-${project.version}.jar`

or if you use Maven, run the following command:
`./mvnw spring-boot:run`

# Deployment 

## Home page

`http://[hostname]:8080/index.html`

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

## TODOs
- Global exception handling
- Proxy Caching so that upstream services are not overwhelmed with repeated requests?
- etc.

# Developers
@mlazic
