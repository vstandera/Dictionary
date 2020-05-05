"# Dictionary" 

How to start the application:

1. Download https://www.docker.com/products/docker-desktop and install.
2. Download the docker-compose.yml and start docker-desktop.
3. run on your console command: docker-compose up
where you have downloaded the docker-compose.yml.
4. If are started docker-compose up the first time the mysql database may not be started in time. Quick fix 
Stop "Ctr + C" and start again "docker-compose up"
5. Download and install the Postman app https://www.postman.com/downloads/
6. Import to Postman project Dictionary.postman_collection2.json
7. All services are documented by swagger2 on address: http://localhost:8080/swagger-ui.html

#### Docker Commands
##### Start MySql Container (downloads image if not found)
``
docker run  --detach   --name di-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=dictionnarydb -e MYSQL_USER=vasekuser -e MYSQL_PASSWORD=vasekpass -d mysql
``

##### view all images
``
docker images
``

##### view all containers (running or not)
``
docker ps -a
``
##### Interact with Database (link to ec-mysql container) with mysql client
``
docker run -it --link di-mysql:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
``
##### Stop ec-mysql container
``
docker stop di-mysql
``
##### (ReStart) ec-mysql container
``
docker start di-mysql
``
##### Remove ec-mysql container (must stop it first)
``
docker rm di-mysql
``
##### Remove image (must stop and remove container first)
``
docker rmi mysql:latest


mvn clean install -Dmaven.test.skip=true

docker build -t dictionary:latest C:\Repository\DictionaryREPOLast

docker run --name dictionaryapp -p 8080:8080 --link di-mysql:mysql dictionary

mvn package -DskipTests docker:build 

docker commit di-mysql di-mysql-data   

docker tag 71e8efa827ed vstandera/main_repo:dictionaryapp

docker push vstandera/main_repo:dictionaryapp

// docker-compose
 
docker-compose build

docker-compose up -d

docker-compose stop

docker exec -it dictionary_dictionary-spring-boot-webapp_1  /bin/bash

docker cp dictionary_dictionary-spring-boot-webapp_1:/logs/dictionary.log logs.log

docker commit 4fcf8b55041aaaaa vstandera/main_repo:dictionary-mysql2
docker push vstandera/main_repo:dictionary-mysql2


########## start docker for React FE #########################
docker build -t web-dictionary:dev .


docker run \
    -it \
    --rm \
    -v ${PWD}:/app \
    -v /app/node_modules \
    -p 3001:3000 \
    -e CHOKIDAR_USEPOLLING=true \
    web-dictionary:dev

docker run \
    -it \
    --rm \
    -v ${PWD}:/app \
    -v /app/node_modules \
    -p 3000:3000 \
    -e CHOKIDAR_USEPOLLING=true \
    web-dictionary:dev
    
    
    npm install --- install define package to node-modules and to Dictionary/web-dictionary/package.json
    npm start --- start the app on port 3000
############################################################### 

Swagger-ui
address = http://localhost:8080/swagger-ui.html