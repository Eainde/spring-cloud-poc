# Customer Service
> Click :star:if you like the project. Pull Request are highly appreciated.

This microservice use netflix ribbon load balancer to hit two different instances of user service. 


**Note**

By default, the ribbon uses the NoOpPing strategy for checking if the services are up. However, NoOpPing is a dummy strategy. It assumes that all services are up. Thus it will kepp pinging the services even if they are down. We can configure the Ping strategy so that we stop sending requests to services that are down.

Also, Ribbon vy default uses the Round Robin load balancing strategy.

These things can be notified by adding a configuration file.
Ribbon can be configured as
```properties
<clientName>.<nameSpace>.<propertyName>=<value>
```

## Resillience
Imagine a situation where the customer controller is trying to contact the User service. Due to either a network issue or server slowness or server being down oe some other errors, the customer controller is unable to contact the service.

Now, what should be the course of action? if we keep sending the requests to user thinking that it will start working after some time, that is insanity. We need to add resilience to our application sucht that the application can deal with such error situations. A try-catch block can handle errors. But if a request is repeatedly causing an error, should the request even be continued to be sent?

The better solution would be that if a particular service is taking more time than usual, then don't send any more requests to that service. Stop requests to that service. This prevents an increase in the slowing down of the other services.

The same principle is used in our electrical appliances as well. When a high volatge passes, the fuse trips thereby preventing other appliances from burning out. When the fuse trips, the circuit is open and no current passes through it. When the fuse is reset, the circuit is closed and the current starts passing through it again. This pattern, when applied in fault tolerance is called the circuit breaker pattern. 

When does a fuse trip in a house? When the current flow exceeds a threshold. Similarly, in microservices communication, when the number of errors in a given time frame is beyond an acceptable limit, the circuit opens, thereby preventing further flow and protecting other parts of application.

**Hystrix Resilience4j** APIs help to apply a circuit breaker pattern in our application. These APIs will open the circuit when the numbers of failures in a given time frame are more.

The sort of alternate arrangement when the cicuit is open is called **Fallback pattern**. Hystrix allows you to mention any alternate piece of code that you wish to run if a service is down. Obviously you don't get the same result as you wish you had. But, providing some form of data instead of an error is better.

Thus hystrix allows you to degrade gracefully. The user continues to have access to the application, but some of the features will be unavailable temporarily. Comapred to FailFast this is FailSilent pattern.
Fallback executes when:
* An error occurs
* A timeout occurs
* Circuit opens

## Declarative Client(Feign)
We have been using the Rest Template for making our calls to other services but when used with LoadBalancing APIs and Circuit Breaker APIs, it becomes cumbersome. Netflix provides an easier to use client called Feign. The Feign client automatically integrates with Load balancing APIs and Circuit Breaker APIs to provide load balancing and fallback mechanism.

Few drawbacks with RestTemplate are:
* You have to be aware of the various methods of the Rest Template to use it.
* You need a separate bean for Load balancing
* You need a separate service for Circuit breaker
* The header details of a request from Zuul are not forwarded to the other microservices using RestTemplate

Hence, we need a form of contacting other microservices which makes it eaiser by avoiding the above mentioned problems.

**Feign** is a declarative clent from Netflix. It is decalarative becuase we as developers declare the API for contacting other microservices. We define the riles in the form of owr own interfaces. At runtime, Feign will create an implementation for our interfaces automatically. Thus with minimal code and self made interfaces, we can have greater control over how one microservice communicates with the other.

**Feign** autometically uses Load balancer API. Thus all calls are automatically load balanced. Feign also works well with Circuitbreaker APIs. With appopirate dependency and configuration, Feign will automatically use a circuit breaker and fallback for all the calls without the need for a separate service class.