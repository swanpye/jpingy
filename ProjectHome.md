Execute Configurable Ping commands with java and use results

Construct a completely configurable ping command. Execute it. And easily extract result parameters


## Sample Code ##



```
PingArguments arguments = new PingArguments.Builder().url("google.com")
				.timeout(5000).repeat(2).bytes(32).build();

PingResults results = Ping.ping(arguments,Backend.UNIX);

System.out.println(results.ttl());
System.out.println(results.rtt_min());
System.out.println(results.received());

--
/* Output: 
TTL: 52
RTT Minimum: 17.036
Received : 2
*/
```

## Features ##
  * Get a list of individual ping requests and use its data
  * Use ping statistics

## Features in development ##

  * Backend for windows