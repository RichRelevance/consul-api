consul-api
==========

Java client for Consul HTTP API (http://consul.io)

Supports all API endpoints (http://www.consul.io/docs/agent/http.html), all consistency modes and parameters (tags, datacenters etc.)

## How to use
```java
ConsulClient client = new ConsulClient("localhost");

// register new service
NewService newService = new NewService();
newService.setId("myapp_01");
newService.setName("myapp");
newService.setPort(8080);
client.agentServiceRegister(newService);

// KV
byte[] binaryData = new byte[] {1,2,3,4,5,6,7};
client.setKVBinaryValue("someKey", binaryData);

String value = client.getKVValue("somekey").getValue().getValue();

//list known datacenters
Response<List<String>> response = client.getCatalogDatacenters();
System.out.println("Datacenters: " + response.getValue());

```