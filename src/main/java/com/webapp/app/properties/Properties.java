package com.webapp.app.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("test")
@Getter
@Setter
@Slf4j
public class Properties {

    private String chromeWebdriverPath;
    private String headlessMode;
    private String fileDownloadPath;
}
