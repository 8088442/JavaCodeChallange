# Java code challenge

This development is made as a proof of concept, does not have deep analysis of project domain and based solely on 
description in the attached [JVM Coding Challenge.docx](JVM%20Coding%20Challenge.docx) file.

As a result, we have working prototype code with a basic accordance to SOLID, DRY, KISS and such approaches.
It contains not valuable amount of Unit tests and hase poor covering.

## How to build
For building the project you need only local Docker (such as Docker Desctop for MAC or Win) and optionally gradle.
To build, 
1. Check, that local Docker is running
2. open the **bash** terminal and type: ``gradle clean build jibDockerBuild``
If you don't have gradle installed on your computer, you can use gradlew (gradle wrapper), which is also works.

As a result we will have both microservice compiled, wrapped into docker image and pushed to local docker.

## How to run
For running microservices, you need to start both of microservices and PostgreSQL in your local Docker.
You also must have access to a	[https://www.adidas.co.uk/api/products/](https://www.adidas.co.uk/api/products/)

To run:
1. just start docker-compose with the file [docker-compose.yaml](docker-compose.yaml) included in project. Simply do:
```shell
docker-compose up -d
```

As a result we will have 3 components running in docker-compose environment
1. PostgreSQL DB
2. Review microservice
3. Product microservice

## How to test
At last, you can test project with predefined scenario.
Just run the bash script [test.sh](test.sh), included in project
```shell
./test.sh
```

If for some reason it has no apropriate permitions to start, make it executable by:
```shell
 chmod a+x test.sh
```