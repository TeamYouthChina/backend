# YouthChina-Backend
[![CircleCI](https://circleci.com/gh/TeamYouthChina/backend.svg?style=svg)](https://circleci.com/gh/TeamYouthChina/backend)

Backend of YouthChina based on Spring framework.

 Copy `.properties.example` file and change the extension into `.properties`

 Fill the properties in those files.

## Docker
### MySQL
You can use `docker-compose.yml` to start a docker service called `youthchina-mysql`, which would initialize with `data.sql`

To start services
```
docker-compose up -d
```

To end services
```
docker-compose down -v 
```
