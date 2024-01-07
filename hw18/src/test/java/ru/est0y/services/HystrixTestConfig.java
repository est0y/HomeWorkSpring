package ru.est0y.services;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;
import ru.est0y.config.HystrixConfig;

@SpringBootApplication
@EnableHystrix
@EnableAutoConfiguration(exclude = {
        MongoDataAutoConfiguration.class,
        EmbeddedMongoAutoConfiguration.class,
        MongoAutoConfiguration.class})
@Import({HystrixConfig.class})
public class HystrixTestConfig {
}
