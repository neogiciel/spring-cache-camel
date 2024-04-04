<h1>Application Spring Cache Apache Camel</h1>
<img src="https://github.com/neogiciel/quarkus-cache-cafeine/assets/123723616/c56eb91d-dfb8-49a1-98b8-0da983bb9476" height=160px>

## Mise en place d'un cache Apache Camel
***
Ajout des dépendences : Pom.xml

```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
  <groupId>com.github.ben-manes.caffeine</groupId>
  <artifactId>caffeine</artifactId>
</dependency>
```
Ficheir : application.properties
```
#Configuration Apache Camel<br>
spring.cache.cache-names=test, test1<br>
spring.cache.caffeine.spec=initialCapacity=50,maximumSize=10,expireAfterAccess=300s<br>
```

<h2>Controller ApiController.java</h2><br>
<p>
```
@Path("/api")
public class ApiController {
 
    /*
     * test
     */
    @GetMapping(value = "/test",produces="application/json") 
    @Cacheable(value = "test", key = "'api-test'")
    public String test() throws InterruptedException {
        Trace.info("Appel REST test cache");
     
         //the current time in milliseconds
         long start = System.currentTimeMillis();
        // stop the main thread of the program for 5000 milliseconds (5 seconds)
        Thread.sleep(5000);
        Trace.info("The thread is paused for " + (System.currentTimeMillis() - start) + " milliseconds");

 
        return getJSON("test", "test").toString();
        //return Map.of("test", "test");
    }

 
}
```
<p>

<h1>Compilation et Lancement</h1>
<p>
Clear: <b>mvn clean</b><br>
Mise à jour des dependences: <b>mvn dependency:resolve</b><br>
Compilation et Lancement: <b>mvn spring-boot:run</b><br>
Url du service: http://localhost:8080<br>  
</p>
<h1>Fournisseur de cache</h1>
<p>
Spring Boot nécessite un fournisseur de cache sous-jacent capable de stocker et de gérer les objets mis en cache et de prendre en charge les recherches. Spring Boot configure automatiquement l'un de ces fournisseurs avec les options par défaut s'il est présent dans le chemin de classe et que nous avons activé le cache par @EnableCaching.<br>
    *JCache (JSR-107) (EhCache 3, Hazelcast, Infinispan, and others)<br>
    *EhCache <br>
    *Hazelcast<br>
    *Infinispan<br>
    *Couchbase<br>
    *Redis<br>
    *Caffeine <br>
    *Simple cache<br>

</p>

