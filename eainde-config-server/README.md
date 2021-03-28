# Config Server
**Why do we need a config server?**

Let us consider the configuration details of our microservices. All our microservices have the below configuration, apart from few properties like application name and port.
```properties
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.platform=H2
spring.datasource.configuration.minimum-idle=1
spring.datasource.configuration.maximum-pool-size=5
```
All these are placed inside the application itself. The problem with this approach is that if we have to modify any configuration then we have to change these configurations in multiple services and redeploy all these services. If a service has multiple instances the all the instances have to be redeployed. 

**What are the some of the alternatives in placing our configuration details?**
* Placing the configuration as environment variables. But there is a limit on the creation of environment variables.
* Placing them in external files. But access to such files system is difficult in the cloud.

An ideal solution would be to use an external version control system like GIT, as it not only avoids the above-mentioned problems but also gives us the traceability of changes.
