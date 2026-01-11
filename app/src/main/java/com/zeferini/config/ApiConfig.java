package com.zeferini.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "api")
@Getter
@Setter
public class ApiConfig {
    private Command command;
    private Query query;

    @Getter
    @Setter
    public static class Command {
        private String url;
    }

    @Getter
    @Setter
    public static class Query {
        private String url;
    }
}
