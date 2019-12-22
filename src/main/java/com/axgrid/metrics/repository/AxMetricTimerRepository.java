package com.axgrid.metrics.repository;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Repository
public class AxMetricTimerRepository {

    @Autowired
    MeterRegistry meterRegistry;

    final Map<String, Map<String, Timer>> data;

    public void record(String key, long value, TimeUnit unit) {
        record(key, value, unit,"");
    }

    public void record(String key, long value, TimeUnit unit, String tags) {
        if (!data.containsKey(key))
            data.put(key, new HashMap<>());
        if (!data.get(key).containsKey(tags))
            data.get(key).put(tags, createTimer(key, tags));
        data.get(key).get(tags).record(value, unit);
    }

    private Timer createTimer(String key, String tags) {
        Timer.Builder builder = Timer.builder(key);
        if (tags != null && !tags.equals(""))
            Arrays.stream(tags.split(Pattern.quote(";")))
                .map(item -> item.split(Pattern.quote(":")))
                .forEach(item -> {
                    if (item.length == 1)
                        builder.tags(item[0]);
                    else
                        builder.tag(item[0], item[1]);
                });
        return builder.register(meterRegistry);
    }

    public AxMetricTimerRepository() {
        data = new HashMap<>();
    }
}
