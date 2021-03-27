# Eainde Customer Service
This microservice use netflix ribbon load balancer to hit two different instances of user service. 


**Note**

By default, the ribbon uses the NoOpPing strategy for checking if the services are up. However, NoOpPing is a dummy strategy. It assumes that all services are up. Thus it will kepp pinging the services even if they are down. We can configure the Ping strategy so that we stop sending requests to services that are down.

Also, Ribbon vy default uses the Round Robin load balancing strategy.

These things can be notified by adding a configuration file.
Ribbon can be configured as
```properties
<clientName>.<nameSpace>.<propertyName>=<value>
```