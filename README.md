# Microservice PoC

## Introduction

The idea behind this project is to PoC an integrated microservice architecture using common patterns:
* [Database per Service pattern](https://microservices.io/patterns/data/database-per-service.html)
* Containerized (Docker and Docker Compose)
* [API Gateway](https://microservices.io/patterns/apigateway.html)/Façade pattern/[API composition](https://microservices.io/patterns/data/api-composition.html) (implemented with Spring Zuul)
* Publish/Subscribe pattern (based on events, implemented with Kafka)
* [CQRS pattern](https://microservices.io/patterns/data/cqrs.html)
* Service Discovery (implemented with Eureka)
* Observability (Spring Boot Admin/Actuator)

## Components schema

![schema](https://raw.githubusercontent.com/t-keller/microservices-poc/master/schema/schema.png)

## Components

### gdpr-service

This REST API is responsible for the CRUD on two entities:
1. /consents: to expose with GDPR consents
2. /treatments: to expose the treatments linked to a consent (i.e. newsletter, marketing campaigns)

The reference used to identity a user is coming from the user-service.

This API also broadcasts events for consent creation, i.e.

```javascript
{
  "type": "CREATE",
  "consent": {
    "id": 4,
    "personId": "1",
    "treatment": {
      "id": 1
    },
    "optin": false
  }
}
```

### user-service

This REST API is responsible for the CRUD on a user database:
1. /users: to expose the users

This API also broadcasts events for user creation, i.e.

```javascript
{
  "type": "CREATE",
  "user": {
    "id": 1,
    "firstname": "Thomas",
    "lastname": "K"
  }
}
```

... and deletion:

```javascript
{
  "type": "DELETE",
  "user": {
    "id": 1
  }
}
```

### user-gdpr-service

This API doesn't have a dedicated datastore but implement a "facade pattern" to expose the routes from gdpr-service and user-service:
1. /consents
2. /treatments
3. /users

Moreover, it implements a dedicated route (/consents-full) to enrich the consents with the username (enrichment/aggregatation pattern)

### user-gdpr-cqrs

This app subscribes to the topics "consent" and "user" to replicate locally the objects and expose an aggregated route:
1. /consents 

### eureka-server

This app implements a service registry thanks to Eureka. The services periodically register with this app in order to maintain an up-to-date service registry.

### admin-server

This app exposes an admin console (Spring Boot Admin) in order to observe the microservices (metrics, configuration etc)
The services are discovered thanks to Eureka

## Launch the PoC

First step is to build the services:

```
mvn clean package
```

Remove the potential previous created containers:

```
sudo docker-compose rm -f
```

And then build & launch the docker-compose:

```
sudo docker-compose up --build
```

