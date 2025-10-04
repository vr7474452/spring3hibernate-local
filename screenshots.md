<img width="1491" height="511" alt="image" src="https://github.com/user-attachments/assets/7a19a10d-680b-4de6-94e4-88a831ef70a9" />



```
Plugins

Pipeline: Stage View – visualize pipeline stages.
 
Blue Ocean – modern UI for pipeline visualization.
```

### Tools
<img width="1382" height="607" alt="image" src="https://github.com/user-attachments/assets/4c69a4f8-a04d-4e6c-a258-54a2f91716ee" />

<img width="1331" height="645" alt="image" src="https://github.com/user-attachments/assets/2ec8aabb-d0d2-46ba-b658-bbbcdb839756" />

### 

<img width="1582" height="594" alt="image" src="https://github.com/user-attachments/assets/166dfc26-9b23-4a6a-ab38-fbb159acc2d5" />


```
Terminal History of Jenkins Slave
ubuntu@ip-172-31-16-215:~$ history
    1  sudo apt install openjdk-21-jdk -y
    2  sudo apt update -y
    3  sudo apt upgrade -y
    4  sudo apt install openjdk-21-jdk -y
    5  java --version
    6  curl -sO http://15.207.235.234:8080/jnlpJars/agent.jar
    7  java -jar agent.jar -url http://15.207.235.234:8080/ -secret 291190355252f73dff450520eb67d8c2902507975e3f1400d33d1b7fa3a03de8 -name slave3 -webSocket -workDir "/home/ubuntu"
    8  history
ubuntu@ip-172-31-16-215:~$
ubuntu@ip-172-31-16-215:~$ curl -sO http://15.207.235.234:8080/jnlpJars/agent.jar
ubuntu@ip-172-31-16-215:~$ java -jar agent.jar -url http://15.207.235.234:8080/ -secret 291190355252f73dff450520eb67d8c2902507975e3f1400d33d1b7fa3a03de8 -name slave3 -webSocket -workDir "/home/ubuntu"
ubuntu@ip-172-31-16-215:~$ history
    1  java --version
    2  sudo apt install openjdk-11-jdk -y
    3  java --version
    4  sudo update-alternatives --config java
    5  sudo apt install maven
    6  mvn --version
    7  history
ubuntu@ip-172-31-16-215:~$
```

```
Terminal history of Jenkins Master
ubuntu@ip-172-31-27-30:~$ history
    1  sudo cat /var/lib/jenkins/secrets/initialAdminPassword
    2  java --version
    3  sudo apt install openjdk-11-jdk
    4  sudo update-alternative --config java
    5  sudo update-alternatives --config java
    6  sudo apt install update
    7  sudo apt-get update
    8  sudo apt install maven
    9  maven --version
   10  mvn -v
   11  sudo update-alternatives --config java
   12  find /var/lib/jenkins/ -name "*.war"
   13  sudo find /var/lib/jenkins/ -name "*.war"
   14  # What Java & Maven are Maven actually using?
   15  mvn -v
   16  java -version
   17  javac -version
   18  echo "$JAVA_HOME"
   19  which mvn && readlink -f "$(which mvn)"
   20  which java && readlink -f "$(which java)"
   21  history | tail -n 25
   22  history
ubuntu@ip-172-31-27-30:~$
```

