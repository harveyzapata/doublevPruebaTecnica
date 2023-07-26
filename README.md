# doublevPruebaTecnica



## Quick start

To obtain the source code, just clone the repository at github
```
git clone https://github.com/harveyzapata/doublevPruebaTecnica.git
``` 

## Build

### Prerequisites
In order to build the application, you need java 8 and maven installed
```
$ mvn -version
Apache Maven 3.9.3 
Java version: 17
Docker
```
Run the following commands to create the PostgreSQL database and build the docker container
```
docker build -t pruebadvp .
docker compose up -d    

```
To the run application, just use maven
```
mvn spring-boot:run


  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.2)

2023-07-26T08:29:37.973-05:00  INFO 2396 --- [  restartedMain] com.example.DoubleV.DoubleVApplication   : No active profile set, falling back to 1 default profile: "default"
2023-07-26T08:29:37.993-05:00  INFO 2396 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2023-07-26T08:29:37.993-05:00  INFO 2396 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'


```
To validate the service:

Open the next url:

```
http://localhost:8080/v1/tickets/
```
To use the request import the collection that is in the postman folder
```
ticketsApi.postamna_collection.json
```
