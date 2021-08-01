# ddm-starter-localization

##### This is a library used for getting localized messages

## Usage

Auto-configuration should be activated through the `@SpringBootApplication` or `@EnableAutoConfiguration` annotation in main class.

##### To start using the library:
* Specify dependency in your service:

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
* Inject `com.epam.digital.data.platform.starter.localization.MessageResolver` to your service
* Implement interface `com.epam.digital.data.platform.starter.localization.MessageTitle` to get message by MessageTitle type