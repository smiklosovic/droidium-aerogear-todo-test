package org.arquillian.droidium.openblend.utils;

import java.net.URL;

import org.openqa.selenium.WebDriver;

public class Utils {

    public static void openWebPageUrl(WebDriver browser, URL context) {
        try {
            browser.get(context.toExternalForm() + "/todo");
        } catch (final Exception ignore) {
            ignore.printStackTrace();
        }
    }

    public static void waitUtil() {
        try { // for presentation purposes so people have chance to follow what is happening
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitUtil(int millis) {
        try { // for presentation purposes so people have chance to follow what is happening
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
