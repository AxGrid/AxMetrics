package com.axgrid.metrics;

import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AxMetricsConfiguration.class})
public @interface EnableAxMetrics {
}
