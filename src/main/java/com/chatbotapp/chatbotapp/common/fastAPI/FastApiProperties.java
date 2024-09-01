package com.chatbotapp.chatbotapp.common.fastAPI;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "fastapi")
public class FastApiProperties {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }
}
