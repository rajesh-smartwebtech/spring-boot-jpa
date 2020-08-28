package com.inymbus.app.factory;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebDriverFactory {

    @Autowired
    private ObjectFactory<WebDriver> prototypeBeanObjectFactory;

    public WebDriver getPrototypeInstance(){
        return prototypeBeanObjectFactory.getObject();
    }
}
