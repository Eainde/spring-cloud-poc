# Discovery Server
In our CustomerMS, we had got the UserMS URI statically from the configuration data. In a cloud situation, an instance may be provisioned and de-provisioned randomly. At one time, the service may run in one port and the after some time, the cloud provider may shift it to another.

How can customer find out the current port and host where UserMS is running?

The service cannot go and modify the properties present in the configuration server every time there is a change and also all the relevant microservices have to refreshed. That will be very cumbersome.

The solution to this problem is known as the service discovery pattern.

In this pattern, a service registers itself with a centeral server called the Service Registry. Now, once it registers itself with the service Registery, two things happens
* Its details like name, port, host etc. are stored in service registry.
* A list of other registered services become available to it. 

Thus, even if one of the services were to get redeployed at a different host and port, the other services need not worry about about it. When the service gets redeployed, it would simply update its information in the service registry again. The other services would discover its updated details through the service registry.

There are many service registry solutions like Netflix Eureka, Zookeeper, Consul etc.