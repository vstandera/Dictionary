"# Dictionary" 

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
``
