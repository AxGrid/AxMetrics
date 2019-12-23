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

@Component
public class DemoComponent {
    @Autowired
    AxMetricService metricService;

    public void setMetrics() {
        long startDate = new Date().getTime();
        metricService.set("mygauge", 15);
        metricService.increment("mycounter", 1, "tag1=value1;tag2=value2");
        metricService.record("mytimer", new Date().getTime() - startDate, TimeUnit.MILLISECONDS);
    }    
}

```
