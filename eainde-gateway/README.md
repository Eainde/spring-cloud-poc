# API GATEWAY
In our application, the UI application has to send requests to the different microservices directly but since the microservices are in the cloud, the host and port are changing frequently. Hence, it is not sustainable for the UI application to talk to the microservices directly.

The better approach is the UI application sends its request to a proxy server which then forwards the request to appropirate microservices. This type of proxy is also called a reverse proxy.

- http://localhost:3333/eainde-order-service/order/
