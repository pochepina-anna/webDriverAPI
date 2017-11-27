package com.way2automation;

import com.way2automation.helpers.DriverSetUp;
import com.way2automation.helpers.FileWorker;
import com.way2automation.pages.Way2automationFramesPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;

/**
 * Класс <class>FramesTest</class>
 */
public class FramesTest extends DriverSetUp {

    private static final String URL = "http://way2automation.com/way2auto_jquery/frames-and-windows.php";
    private Way2automationFramesPage way2automationFramesPage = new Way2automationFramesPage(driver);

    @Test
    public void framesTest()throws Exception {
        openWay2automationPage(driver,URL);
        driver.manage().deleteAllCookies();
        restoreCookieFromFile("src/test/resources/cookies", "way2automation.com");
        openWay2automationPage(driver,URL);
        Assert.assertTrue("Авторизация не прошла",way2automationFramesPage.menuContainerIsVisible());
        framesCheck();
        Assert.assertTrue("Дополнительная вкладка не открылась",switchToFrameAndClick());
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

    private void framesCheck(){
        way2automationFramesPage.inputMultipleMenuButtonClick();
        way2automationFramesPage.switchToFrame();
        way2automationFramesPage.linkButtonClick();
        way2automationFramesPage.outFromFrame();
    }

    private boolean switchToFrameAndClick(){
        Object[] newHandles = driver.getWindowHandles().toArray();
        if (newHandles.length>1){
            driver.switchTo().window((String)newHandles[1]);
            driver.switchTo().activeElement();
            way2automationFramesPage.linkButtonClick();
            Object[] parentHandles = driver.getWindowHandles().toArray();
            return newHandles.length!=parentHandles.length;
        }
        else {return false;}
    }
}

