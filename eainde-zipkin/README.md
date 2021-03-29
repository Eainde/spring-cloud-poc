# Sleuth
Spring Cloud Sleuth is one of the projects under Spring Cloud which allows us to trace a request. Sleuth adds two things: a traceId and a spanId in our logs. TraceId ia a unique ID generated for every request. Span ID is a unique ID generated for the span or path of a single microservice. So if a request flows through two microservices, it will have one TraceId and two SpanId's. One span is created for the network hop and the other is for the application execution.

The below line shows a sample log statement. The first parameter is the Service name, the second is the traceId, third is the span id. The fourth parameter is for zipkin.

```
INFO [eainde-customer-service,6061b234f3d20ab7873279f14d9f8427,2850620477a7a886,true]
```

# Zipkin Server
Sleuth generates traceId and spanId. But it is difficult to make use of data in its raw form. This is where Zipkin is used. Zipkin is a distributed tracing system that allows us to gather information on the trace of a request. By adding an appropriate dependency, we make sleuth send all its details to a Zipkin server. The Zipkin server has an memoru DB where details are stored. Zipkin has a powerful UI application, which allows us to analyze the logs and take appropriate action. 