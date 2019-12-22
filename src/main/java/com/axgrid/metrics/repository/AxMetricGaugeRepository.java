package com.axgrid.metrics.repository;

import com.axgrid.metrics.AxMetricsUtils;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Repository
public class AxMetricGaugeRepository {

    @Autowired
    MeterRegistry meterRegistry;

    final Map<String, Map<String, Number>> data;

    public void set(String key) {
        set(key, 1, "");
    }

    public void set(String key, double value) {
        set(key, value, "");
    }

    public void set(String key, double value, String tags) {
        if (!data.containsKey(key))
            data.put(key, new HashMap<>());
        if (!data.get(key).containsKey(tags)) {
            data.get(key).put(tags, value);
            createGauge(key, tags);
        }
        data.get(key).put(tags, value);
    }

    private void createGauge(String key, String tags) {
        Gauge.Builder builder = Gauge.builder(key, () -> data.get(key).get(tags));
        AxMetricsUtils.tags(tags).forEach(tag -> {
            if (tag.getValue() == null)
                builder.tags(tag.getKey());
            else
                builder.tag(tag.getKey(), tag.getValue().toString());
        });

        builder.register(meterRegistry);
    }

    public AxMetricGaugeRepository() {
        data = new HashMap<>();
    }

}
