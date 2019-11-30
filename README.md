# Microservice PoC

## Introduction

The idea behind this project is to PoC an integrated microservice architecture using common patterns (API aggregation, event-driven architecture...)

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

## To-do

1. Implement an OAuth2 security scheme
2. Add support for Lombok
3. Fix the startup error of "gdpr-service"