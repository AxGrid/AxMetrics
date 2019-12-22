package com.axgrid.metrics;

import com.axgrid.metrics.dto.AxMetricTag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class AxMetricsUtils {

    public static List<AxMetricTag> tags(String tagsString) {
        if (tagsString == null || tagsString.equals("")) return Collections.emptyList();
        return Arrays.stream(tagsString.split("(;| |,)"))
                .map(item -> item.split("(:|=)"))
                .map(item -> {
                    if (item.length == 1)
                        return new AxMetricTag(item[0], null);
                    else
                        return new AxMetricTag(item[0], item[1]);
                }).collect(Collectors.toList());
    }
}
