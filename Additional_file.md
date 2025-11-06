### docker installation
```
sudo reboot
uname -r
sudo apt autoremove -y
sudo apt remove docker docker-engine docker.io containerd runc -y
sudo apt update -y
sudo apt upgrade -y
sudo apt install ca-certificates curl gnupg lsb-release -y
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg

echo   "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] \
  https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt update -y
sudo apt install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y
sudo systemctl enable docker
sudo systemctl start docker
sudo usermod -aG docker $USER
newgrp docker
```
### Assignment2 Part 1
```
https://chatgpt.com/s/t_690c882f49e881918b32690c445e3a01
```

### Clean the docker environment
```
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi -f $(docker images -q)
docker network prune -f
docker volume prune -f
```
### git clone
```
git clone https://github.com/opstree/spring3hibernate.git
```

### Folder structure
```
spring3hibernate/
├── Dockerfile
├── docker-compose.yml
├── src/
│   └── main/resources/database.properties
└── nginx/
    ├── Dockerfile
    └── default.conf
```

### Spring application Dockerfile
```
FROM maven:3.3-jdk-8 as builder
COPY . /usr/src/mymaven/
WORKDIR /usr/src/mymaven/
RUN mvn clean install -DskipTests -Ddependency-check.skip=true
RUN mvn package -DskipTests -Ddependency-check.skip=true

FROM tomcat:7-jre7-alpine
MAINTAINER "opstree <opstree@gmail.com>"
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /usr/src/mymaven/target/Spring3HibernateApp.war /usr/local/tomcat/webapps/ROOT.war
WORKDIR /usr/local/tomcat/webapps/
EXPOSE 8080
```

### src/main/resources/database.properties
```
database.driver=com.mysql.jdbc.Driver
database.url=jdbc:mysql://mysql.okts.tk:3306/employeedb
database.user=root
database.password=password
hibernate.dialect=org.hibernate.dialect.MySQLDialect
hibernate.show_sql=true
hibernate.hbm2ddl.auto=update
upload.dir=c:/uploads
```

### docker-compose.yml
```
version: '3'
services:
  spring3hibernate.okts.tk:
    build:
      context: . 
      dockerfile: Dockerfile
    expose:
      - '8080'
    restart: always
    depends_on: 
      - mysql.okts.tk

  mysql.okts.tk:
    image: mysql:5.6
    restart: always
    expose:
      - '3306'
    environment:
       MYSQL_DATABASE: employeedb
       MYSQL_ROOT_PASSWORD: password
    volumes:
       - mysql_okts_tk:/var/lib/mysql

  ingress.okts.tk:
    build:
      context: nginx
      dockerfile: Dockerfile
    ports:
      - "80:80" 
    restart: always
    depends_on: 
      - spring3hibernate.okts.tk

volumes:
  mysql_okts_tk:

```

### 6️⃣ Nginx reverse proxy configuration
### nginx/Dockerfile
### nginx/default.conf

### Build and run everything
```
docker-compose down -v
docker-compose build
docker-compose up -d
```

### 8️⃣ Verify deployment
```
docker ps
```

```
🌍 9️⃣ Test access
✅ App: http://3.110.116.130/
❌ http://3.110.116.130:8080/
```

```
🧹 Optional cleanup / rebuild commands
docker-compose down -v
docker system prune -af
docker-compose build
docker-compose up -d
```

```
✅ Result:
You now have a production-like setup where:

Spring app builds automatically via Maven

MySQL runs in a linked container

Nginx ingress serves the app securely on port 80

All services are managed and orchestrated by Docker Compose
```
