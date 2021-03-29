# Spring Cloud Proof of Concept
> Click :star: if you like the project. Pull Request are highly appreciated.

This POC covers below topics:
* Store configuration details on the cloud using CloudConfig
* Load Balance requests between microservices using Ribbon and Spring cloud load balancer
* Discover services in cloud using Eureka
* Increase resilience through Hystrix and Resillience4j
* Use asynchronous communication to improve performance
* Create an API gateway using Zuul and Spring cloud gateway
* Simplify REST calls through Feign
* Monitor your microservices through Sleuth and Zipkin

## Sleuth
Spring Cloud Sleuth is one of the projects under Spring Cloud which allows us to trace a request. Sleuth adds two things: a traceId and a spanId in our logs. TraceId ia a unique ID generated for every request. Span ID is a unique ID generated for the span or path of a single microservice. So if a request flows through two microservices, it will have one TraceId and two SpanId's. One span is created for the network hop and the other is for the application execution.

The below line shows a sample log statement. The first parameter is the Service name, the second is the traceId, third is the span id. The fourth parameter is for zipkin.

```
        INFO [eainde-customer-service,6061b234f3d20ab7873279f14d9f8427,2850620477a7a886,true]
```
