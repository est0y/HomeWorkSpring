package ru.est0y.config;

import com.netflix.config.ConfigurationManager;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {

    @PostConstruct
    public void configureHystrixDefaults() {
        var defaultProperties = ConfigurationManager.getConfigInstance();
        defaultProperties.setProperty(
                "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds",
                3000
        );
    }
}
