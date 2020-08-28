package com.inymbus.app;


import com.inymbus.app.properties.Properties;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;

@Slf4j
@SpringBootApplication
public class Application {

    @Autowired
    Properties properties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }



    @Bean
    @Scope("prototype")
    public WebDriver webdriver() {

        System.setProperty("webdriver.chrome.driver", properties.getChromeWebdriverPath());

        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        log.info("-----  -----  -----  -----  -----  -----  -----  -----");

        log.info("-----  -----  -----  -----  -----  -----  -----  -----");
        chromePrefs.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromePrefs.put("download.default_directory", properties.getFileDownloadPath());
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        chromePrefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", chromePrefs);
        if(Boolean.parseBoolean(properties.getHeadlessMode())){
            options.addArguments("--headless");
            options.addArguments("--window-size=1980,800");
        }

        return new ChromeDriver(options);


    }

    public void closeApp(ApplicationContext context) {

        SpringApplication.exit(context);
    }

    private void waitUntilPageLoaded(WebDriver webDriver) {
        log.info("call waitUntilPageLoaded method");
        //System.out.println("wait until page load....");
        ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete");
        new WebDriverWait(webDriver, 120).until(pageLoadCondition);
    }

    private void pause(long mills) {

        try {
            log.info("call pause method wait for " + mills / 1000 + " sec....");
            //System.out.println("wait for "+ sec/1000 +" sec....");
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException with pause method :" + e.getMessage());
        }
    }
}
