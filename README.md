# Microservice PoC

## Introduction

The idea behind this project is to poc an integrated microservice architecture using as many pattern as possible (CQRS, event-driven architecture...)

## Components

### gdpr-service

This REST API is responsible for the CRUD on two entities:
1. /consents: to expose with GDPR consents
2. /treatments: to expose the treatments linked to a consent (i.e. newsletter, marketing campaigns)

The reference used to identity a user is coming from the user-service.

### user-service

This REST API is responsible for the CRUD on a user database:
1. /users: to expose the users

### user-gdpr-service

This API doesn't have a dedicated datastore but implement a "facade pattern" to expose the routes from gdpr-service and user-service:
1. /consents
2. /treatments
3. /users

Moreover, it implements a dedicated route (/consents-full) to enrich the consents with the username (enrichment/aggregatation pattern)

## To-do

1. Finish the CQRS implementation (proper event objects, replication)
2. Add support for Lombok
3. Implement a OAuth2 security scheme