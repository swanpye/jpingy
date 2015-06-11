# Configuring Ping Command #
You need to create a builder for building the ping command

```
PingArguments arguments = new PingArguments.Builder().url("google.com")
                               .timeout(5000).repeat(2).bytes(32).build();
```

# Executing the Ping Command #
After you build the PingArguments you can use it to execute it.

Remember to choose the appropriate backend (In the future this may become automated or replaced by factory pattern)


```
PingResults results = Ping.ping(arguments,Backend.UNIX); 
```

The results of the ping command are returned

# Using the Ping Results #
```
//statistics
System.out.println(results.ttl());
System.out.println(results.rtt_min());
System.out.println(results.received());

//individual requests
List<PingRequest> requests = results.getRequests();
PingRequest p = requests.get(1);

System.out.println(p.ttl());
System.out.println(p.time());
System.out.println(p.bytes());
System.out.println(p.fromIP());



```

