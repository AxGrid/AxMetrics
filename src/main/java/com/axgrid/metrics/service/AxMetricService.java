package com.axgrid.metrics.service;

import com.axgrid.metrics.repository.AxMetricCounterRepository;
import com.axgrid.metrics.repository.AxMetricGaugeRepository;
import com.axgrid.metrics.repository.AxMetricTimerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AxMetricService {

    @Autowired
    AxMetricCounterRepository counterRepository;

    @Autowired
    AxMetricGaugeRepository gaugeRepository;

    @Autowired
    AxMetricTimerRepository timerRepository;

    /**
     * Set value for gauge by 1
     * @param key gauge name
     */
    public void set(String key) {
        if (log.isDebugEnabled()) log.debug("AxMetric set(key:{})", key);
        gaugeRepository.set(key);
    }

    /**
     * Set value for gauge
     * @param key gauge name
     * @param value value
     */
    public void set(String key, double value) {
        if (log.isDebugEnabled()) log.debug("AxMetric set(key:{}, value:{})", key, value);
        gaugeRepository.set(key, value); }

    /**
     * Set value for gauge
     * @param key gauge name
     * @param value value
     * @param tags tags separate by ; (key1:value1;key2:value2)
     */
    public void set(String key, double value, String tags) {
        if (log.isDebugEnabled()) log.debug("AxMetric set(key:{}, value:{}, tags:{})", key, value, tags);
        gaugeRepository.set(key, value, tags);
    }

    /**
     * Increment value for counter by 1
     * @param key counter name
     */
    public void increment(String key) {
        if (log.isDebugEnabled()) log.debug("AxMetric increment(key:{})", key);
        counterRepository.increment(key);
    }

    /**
     * Increment value for counter
     * @param key counter name
     * @param value increment value
     */
    public void increment(String key, double value) {
        if (log.isDebugEnabled()) log.debug("AxMetric increment(key:{}, value:{})", key, value);
        counterRepository.increment(key, value);
    }

    /**
     * Increment value for counter
     * @param key counter name
     * @param value increment value
     * @param tags tags separate by ; (key1:value1;key2:value2)
     */
    public void increment(String key, double value, String tags) {
        if (log.isDebugEnabled()) log.debug("AxMetric increment(key:{}, value:{}, tags:{})", key, value, tags);
        counterRepository.increment(key, value, tags);
    }

    /**
     * Record timer value
     * @param key timer name
     * @param value time value
     * @param unit units
     */
    public void record(String key, long value, TimeUnit unit) {
        if (log.isDebugEnabled()) log.debug("AxMetric record(key:{}, value:{}, unit:{})", key, value, unit);
        timerRepository.record(key, value, unit);
    }

    /**
     * Record timer value
     * @param key timer name
     * @param value time value
     * @param unit units
     * @param tags tags separate by ; (key1:value1;key2:value2)
     */
    public void record(String key, long value, TimeUnit unit, String tags) {
        if (log.isDebugEnabled()) log.debug("AxMetric record(key:{}, value:{}, unit:{}, tags:{})", key, value, unit, tags);
        timerRepository.record(key, value, unit, tags);
    }

}
