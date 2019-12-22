AxMetrics
=========

Prometheus export metrics


Configure
---------

application.yaml
```yaml
management:
  metrics:
    export:
      prometheus:
        enabled: true
  endpoint:
    health:
      show-details: always
  endpoints:
    prometheus:
      enabled: true
    web:
      exposure:
        include: "*"
```


```java
@Configuration
@EnableAxMetrics
class MyConfigurationClass {
}
```

Usage
-----
```java



```
