# ddm-starter-localization

### Overview

* This is a library used for getting localized messages.

### Usage

1. Specify dependency in your service:

```xml

<dependencies>
  ...
  <dependency>
    <groupId>com.epam.digital.data.platform</groupId>
    <artifactId>ddm-starter-localization</artifactId>
    <version>...</version>
  </dependency>
  ...
</dependencies>
```

2. Auto-configuration should be activated through the `@SpringBootApplication`
   or `@EnableAutoConfiguration` annotation in main class.
   
3. Inject `com.epam.digital.data.platform.starter.localization.MessageResolver` to your service;
4. Implement interface `com.epam.digital.data.platform.starter.localization.MessageTitle` to get
  message by MessageTitle type.

### Test execution

* Tests could be run via maven command:
    * `mvn verify` OR using appropriate functions of your IDE.
    
### License

The ddm-starter-localization is Open Source software released under
the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).