package com.axgrid.metrics.repository;

import com.axgrid.metrics.AxMetricsUtils;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AxMetricCounterRepository {

    @Autowired
    MeterRegistry meterRegistry;

    final Map<String, Map<String, Counter>> data;


    public void increment(String key, double value, String tags) {
        init(key, tags);
        data.get(key).get(tags).increment(value);
    }

    public void init(String key, String tags) {
        if (!data.containsKey(key))
            data.put(key, new HashMap<>());

        if (!data.get(key).containsKey(tags))
            data.get(key).put(tags, createCounter(key, tags));
    }

    private Counter createCounter(String key, String tags) {
        Counter.Builder builder = Counter.builder(key);
        AxMetricsUtils.tags(tags).forEach(tag -> {
            if (tag.getValue() == null)
                builder.tags(tag.getKey());
            else
                builder.tag(tag.getKey(), tag.getValue().toString());
        });
        return builder.register(meterRegistry);
    }

    public AxMetricCounterRepository() {
        data = new HashMap<>();
    }
}
