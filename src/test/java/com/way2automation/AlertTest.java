package com.way2automation;

import com.way2automation.helpers.DriverSetUp;
import com.way2automation.helpers.FileWorker;
import com.way2automation.pages.Way2automationAlertPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.*;

/**
 * Класс <class>AlertTest</class>
 */
public class AlertTest  extends DriverSetUp {

    private static final String URL = "http://way2automation.com/way2auto_jquery/alert.php";
    private Way2automationAlertPage way2automationAlertPage = new Way2automationAlertPage(driver);
    private Actions builder = new Actions(driver);

    @Test
    public void dragNdrop()throws Exception {
        openWay2automationPage(driver,URL);
        driver.manage().deleteAllCookies();
        restoreCookieFromFile("src/test/resources/cookies", "way2automation.com");
        openWay2automationPage(driver,URL);
        Assert.assertTrue("Авторизация не прошла",way2automationAlertPage.menuContainerIsVisible());
        alertCheck("Simbirsoft");
    }

    private void openWay2automationPage(WebDriver driver, String url) {
        driver.get(url);
    }

    private static void restoreCookieFromFile(String fileName, String domainName) throws FileNotFoundException {

        File file = new File(fileName);
        FileWorker.exists(fileName);
        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    Cookie.Builder cb = new Cookie.Builder(s.substring(0, s.indexOf('=')), s.substring(s.indexOf('=') + 1, s.length())).domain(domainName);
                    driver.manage().addCookie(cb.build());
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void alertCheck(String str){
        way2automationAlertPage.inputAlertMenuButtonClick();
        way2automationAlertPage.switchToFrame();
        way2automationAlertPage.alertButtonClick();
        way2automationAlertPage.setTextFromAlert(str);
        way2automationAlertPage.acceptAlert();
        //System.out.println(way2automationAlertPage.getTextFromAlert());
        Assert.assertTrue("Текст не применился",way2automationAlertPage.getTextFromAlert().equals("Hello " + str + "! How are you today?"));
        way2automationAlertPage.outFromFrame();
    }
}
