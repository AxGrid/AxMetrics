package com.axgrid.metrics.service;

import com.axgrid.metrics.repository.AxMetricCounterRepository;
import com.axgrid.metrics.repository.AxMetricGaugeRepository;
import com.axgrid.metrics.repository.AxMetricTimerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

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
    public void set(String key) { gaugeRepository.set(key); }

    /**
     * Set value for gauge
     * @param key gauge name
     * @param value value
     */
    public void set(String key, double value) { gaugeRepository.set(key, value); }

    /**
     * Set value for gauge
     * @param key gauge name
     * @param value value
     * @param tags tags separate by ; (key1:value1;key2:value2)
     */
    public void set(String key, double value, String tags) { gaugeRepository.set(key, value, tags); }

    /**
     * Increment value for counter by 1
     * @param key counter name
     */
    public void increment(String key) { counterRepository.increment(key); }

    /**
     * Increment value for counter
     * @param key counter name
     * @param value increment value
     */
    public void increment(String key, double value) { counterRepository.increment(key, value); }

    /**
     * Increment value for counter
     * @param key counter name
     * @param value increment value
     * @param tags tags separate by ; (key1:value1;key2:value2)
     */
    public void increment(String key, double value, String tags) { counterRepository.increment(key, value, tags); }

    /**
     * Record timer value
     * @param key timer name
     * @param value time value
     * @param unit units
     */
    public void record(String key, long value, TimeUnit unit) { timerRepository.record(key, value, unit); }

    /**
     * Record timer value
     * @param key timer name
     * @param value time value
     * @param unit units
     * @param tags tags separate by ; (key1:value1;key2:value2)
     */
    public void record(String key, long value, TimeUnit unit, String tags) { timerRepository.record(key, value, unit, tags); }

}
