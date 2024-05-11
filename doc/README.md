# Meeting
Internal network meeting application


1. start the Application which is defined  in  ```package com.github.meeting.server```;

2. client starter test is ```Tests``` which is in ```test``` package and path is ```package com.github.bytecpp;```

### compile protobuf

```shell
mvn protobuf:compile protobuf:compile-custom
```


### ssl/tls
please read the following link :
[https-spring-boot-ssl-certificate](https://www.thomasvitale.com/https-spring-boot-ssl-certificate/)


### deploy 
```shell
mvn clean package -DskipTests -s setting.xml -f pom.xml 
```


