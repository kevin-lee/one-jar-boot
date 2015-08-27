# [One-JAR™](http://one-jar.sourceforge.net)
[![Build Status](https://travis-ci.org/Kevin-Lee/one-jar-boot.svg?branch=master)](https://travis-ci.org/Kevin-Lee/one-jar-boot)

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

# Get `one-jar-boot`

## Maven
* Add maven repository

```xml
<repositories>

  <repository>
    <snapshots>
      <enabled>false</enabled>
    </snapshots>
    <id>bintray-kevinlee-maven</id>
    <name>bintray</name>
    <url>http://dl.bintray.com/kevinlee/maven</url>
  </repository>

</repositories>
```

* Add dependency

```xml
<dependencies>

  <dependency>
    <groupId>com.simontuffs</groupId>
    <artifactId>one-jar-boot</artifactId>
    <version>0.97.3</version>
  </dependency>

</dependencies>
```


## Gradle
* Add maven repository

  In `build.gradle`, add the following repository to `repositories`.

```gradle
maven {
  url  "http://dl.bintray.com/kevinlee/maven"
}
```
  e.g.)

```gradle
repositories {
  mavenCentral()
  maven {
    url  "http://dl.bintray.com/kevinlee/maven"
  }
}
```

* Add Dependency

```gradle
compile group: 'com.simontuffs', name: 'one-jar-boot', version: '0.97.3'
```
  OR

```gradle
compile "com.simontuffs:one-jar-boot:0.97.3"
```


## SBT
* Add Resolver
```scala
resolvers += "Bintray Public Repository" at "http://dl.bintray.com/kevinlee/maven"
```

* Add Dependency
```scala
libraryDependencies += "com.simontuffs" % "one-jar-boot" % "0.97.3"
```


# LICENSE
- License file: [doc/one-jar-license.txt](doc/one-jar-license.txt)
```
/*
 * One-JAR(TM) (http://www.simontuffs.com/one-jar).  Copyright (c) 2004-2010,
 * P. Simon Tuffs (simon@simontuffs.com).  	All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of P. Simon Tuffs, nor the names of any contributors,
 * nor the name One-JAR may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * Including this file inside the built One-JAR file conforms with these terms.
 */
```
