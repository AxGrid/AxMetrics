package com.axgrid.metrics.test;

import com.axgrid.metrics.AxMetricsUtils;
import com.axgrid.metrics.dto.AxMetricTag;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestTags {

    @Test
    public void testTagsSplit() {

        String tagString = "key1:value2";
        List<AxMetricTag> tags = AxMetricsUtils.tags(tagString);
        Assert.assertEquals(tags.size(), 1);
        Assert.assertEquals(tags.get(0).getKey(), "key1");
        Assert.assertEquals(tags.get(0).getValue(), "value2");

        tagString = "key1:value2;key2:value3";
        tags = AxMetricsUtils.tags(tagString);
        Assert.assertEquals(tags.size(), 2);
        Assert.assertEquals(tags.get(0).getKey(), "key1");
        Assert.assertEquals(tags.get(0).getValue(), "value2");
        Assert.assertEquals(tags.get(1).getKey(), "key2");
        Assert.assertEquals(tags.get(1).getValue(), "value3");


        tagString = "key1=value2 key2:value3;key4:value5";
        tags = AxMetricsUtils.tags(tagString);
        Assert.assertEquals(tags.size(), 3);
        Assert.assertEquals(tags.get(0).getKey(), "key1");
        Assert.assertEquals(tags.get(0).getValue(), "value2");
        Assert.assertEquals(tags.get(1).getKey(), "key2");
        Assert.assertEquals(tags.get(1).getValue(), "value3");
        Assert.assertEquals(tags.get(2).getKey(), "key4");
        Assert.assertEquals(tags.get(2).getValue(), "value5");

        tagString = "";
        tags = AxMetricsUtils.tags(tagString);
        Assert.assertEquals(tags.size(), 0);

        tagString = null;
        tags = AxMetricsUtils.tags(tagString);
        Assert.assertEquals(tags.size(), 0);

    }
}
