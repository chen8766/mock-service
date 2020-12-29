package com.chen.mock.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author chen
 * @date 2020-11-12-1:25
 */

@ConfigurationProperties(prefix = "mock")
@Component
@Setter
public class MockData {

    private Map<String, String> mockData;

    public String get(String uri) {
        return mockData.get(uri);
    }

    public boolean contains(String uri) {
        return mockData.containsKey(uri);
    }
}
