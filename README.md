## dns8
       
Custom dns resolver for java 8 bases on hosts file

## How to add to your project

File META-INF/services/sun.net.spi.nameservice.NameServiceDescriptor
```
com.payneteasy.dns8.Dns8NameService
```

## How to configure

```shell
-Dsun.net.spi.nameservice.provider.1=dns,dns8
-Dsun.net.spi.nameservice.provider.2=default
```

## How to add it into your app

### Maven

```xml
<repositories>
    <repository>
        <id>pne</id>
        <name>payneteasy repo</name>
        <url>https://maven.pne.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.payneteasy</groupId>
    <artifactId>java-dns-8-core</artifactId>
    <version>1.0-1</version>
</dependency>
```
