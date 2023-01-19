package com.evax.gateway.filter.allow;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lionel
 * @Description: 网关白名单
 */
@Data
@Component
@ConfigurationProperties(prefix = "allow.filter")
public class FilterProperties {
    private List<String> paths;
}