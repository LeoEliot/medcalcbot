package com.malef.medcalcbot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bot")
@Data
@PropertySource("classpath:application.yml")
public class BotProps {

    String name;

    String token;
}
