package com.way2automation;

import com.way2automation.helpers.DriverSetUp;
import com.way2automation.helpers.FileWorker;
import com.way2automation.pages.Way2automationDropPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.*;

/**
 * Класс <class>DragnDropTest</class>
 */
public class DragnDropTest extends DriverSetUp {

    private static final String URL = "http://way2automation.com/way2auto_jquery/droppable.php";
    private Way2automationDropPage way2automationDropPage = new Way2automationDropPage(driver);
    private Actions builder = new Actions(driver);

    @Test
    public void dragNdrop()throws Exception {
        openWay2automationPage(driver,URL);
        driver.manage().deleteAllCookies();
        restoreCookieFromFile("src/test/resources/cookies", "way2automation.com");
        openWay2automationPage(driver,URL);
        Assert.assertTrue("Авторизация не прошла",way2automationDropPage.menuContainerIsVisible());
        dragAndDropCheck();
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

    private void dragAndDropCheck(){
        way2automationDropPage.switchToFrame();
        String str = way2automationDropPage.getTextDropObj();
        System.out.println(str);
        builder.dragAndDrop(way2automationDropPage.getDragObj(), way2automationDropPage.getDropObj()).perform();
        System.out.println(way2automationDropPage.getTextDropObj());
        Assert.assertFalse("Текст в объекте не поменялся!",way2automationDropPage.getTextDropObj().equals(str));
        way2automationDropPage.outFromFrame();
    }

}
