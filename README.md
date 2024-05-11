# Meeting
Internal network meeting application


1. start the Application which is defined  in  ```package com.github.meeting.server```;

2. client starter test is ```Tests``` which is in ```test``` package and path is ```package com.github.bytecpp;```

### compile protobuf

```shell
mvn protobuf:compile protobuf:compile-custom
```


 [git github proxy](https://gist.github.com/laispace/666dd7b27e9116faece6)
```
#使用socks5代理（推荐）
git config --global http.https://github.com.proxy socks5://127.0.0.1:51837
#使用http代理（不推荐）
git config --global http.https://github.com.proxy http://127.0.0.1:58591
```


### ssl/tls
please read the following link :
[https-spring-boot-ssl-certificate](https://www.thomasvitale.com/https-spring-boot-ssl-certificate/)


### deploy 
```shell
mvn clean package -DskipTests -s settings.xml -f pom.xml 
```


