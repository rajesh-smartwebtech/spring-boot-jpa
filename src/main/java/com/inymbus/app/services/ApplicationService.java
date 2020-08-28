package com.inymbus.app.services;

import com.inymbus.app.Application;
import com.inymbus.app.factory.WebDriverFactory;
import com.inymbus.app.properties.Properties;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class ApplicationService {

    private WebDriverFactory factory;
    private Properties properties;
    private WebDriver driver;
    private ApplicationContext context;

    @Autowired
    private Application application;

    public ApplicationService(WebDriverFactory factory,Properties properties,ApplicationContext context){
        this.factory=factory;
        this.properties=properties;
        this.context=context;
    }

    public void run(){
        driver = factory.getPrototypeInstance();

        driver.get("https://www.google.com");
        pause(5);
        driver.close();



    }

    private void waitUntilPageLoaded(WebDriver webDriver) {
        log.info("call waitUntilPageLoaded method");
        //System.out.println("wait until page load....");
        ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete");
        new WebDriverWait(webDriver, 120).until(pageLoadCondition);
    }

    private void pause(long sec) {

        try {
            log.info("call pause method wait for " + sec  + " sec....");
            //System.out.println("wait for "+ sec/1000 +" sec....");
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException with pause method :" + e.getMessage());
        }
    }
}
