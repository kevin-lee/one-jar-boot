# [One-JAR™](http://one-jar.sourceforge.net)
[One-JAR™](http://one-jar.sourceforge.net) is a tool to package a Java application with its dependency jars into a single jar file.

# What is One-JAR-Boot?
`one-jar-boot` is The low-level JarClassLoader and other One-JAR bootstrap mechanisms Contains source-code for the One-JAR bootstrap classes ([The description from the One-JAR web site](http://one-jar.sourceforge.net/index.php?page=downloads&file=downloads)).

## So what is this repository for?
Unfortunately, it has an issue with some libraries like 'AWS SDK for Java'. When a jar file packaged by `one-jar` contains `aws-sdk-java` causes an Exception like:

```
Exception in thread "main" java.lang.reflect.InvocationTargetException
  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
  at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
  at java.lang.reflect.Method.invoke(Method.java:483)
  at com.simontuffs.onejar.Boot.run(Boot.java:340)
  at com.simontuffs.onejar.Boot.main(Boot.java:166)
Caused by: java.lang.ExceptionInInitializerError
  at com.amazonaws.ServiceNameFactory.getServiceName(ServiceNameFactory.java:34)
  at com.amazonaws.AmazonWebServiceClient.computeServiceName(AmazonWebServiceClient.java:703)
  at com.amazonaws.AmazonWebServiceClient.getServiceNameIntern(AmazonWebServiceClient.java:676)
  at com.amazonaws.AmazonWebServiceClient.computeSignerByURI(AmazonWebServiceClient.java:278)
  at com.amazonaws.AmazonWebServiceClient.setEndpoint(AmazonWebServiceClient.java:160)
  at com.amazonaws.services.s3.AmazonS3Client.setEndpoint(AmazonS3Client.java:489)
  at com.amazonaws.services.s3.AmazonS3Client.init(AmazonS3Client.java:461)
  at com.amazonaws.services.s3.AmazonS3Client.<init>(AmazonS3Client.java:357)
  at com.amazonaws.services.s3.AmazonS3Client.<init>(AmazonS3Client.java:337)

// ...

Caused by: java.lang.IllegalStateException: Fatal: Failed to load the internal config for AWS Java SDK
  at com.amazonaws.internal.config.InternalConfig$Factory.<clinit>(InternalConfig.java:236)
  ... 37 more
Caused by: com.fasterxml.jackson.databind.JsonMappingException: No content to map due to end-of-input
 at [Source: jar:file:/adonis-apws_sjs0.5_2.11-dev-one-jar.jar!/lib/aws-java-sdk-1.7.8.1.jar!//awssdk_config_default.json; line: 1, column: 1]
  at com.fasterxml.jackson.databind.JsonMappingException.from(JsonMappingException.java:164)
  at com.fasterxml.jackson.databind.ObjectMapper._initForReading(ObjectMapper.java:3036)
  at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:2978)
  at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:2075)
  at com.amazonaws.internal.config.InternalConfig.loadfrom(InternalConfig.java:182)
  at com.amazonaws.internal.config.InternalConfig.load(InternalConfig.java:199)
  at com.amazonaws.internal.config.InternalConfig$Factory.<clinit>(InternalConfig.java:232)
  ... 37 more
```
This is [a known issue](https://github.com/aws/aws-sdk-java/issues/185).
