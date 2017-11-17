package com.way2automation;

import com.way2automation.helpers.DriverSetUp;
import com.way2automation.helpers.FileWorker;
import com.way2automation.pages.Way2automationPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.Set;


/**
 * Класс <class>CursorTest</class>
  */
public class CursorTest  extends DriverSetUp {

    private static final String URL = "http://way2automation.com/way2auto_jquery/menu.php";
    private Way2automationPage way2automationPage = new Way2automationPage(driver);
    private Actions builder = new Actions(driver);

    @Test
    public void cursorOnMenu()throws Exception {
        openWay2automationPage(driver,URL);
        //Set <Cookie> allCookies=driver.manage().getCookies();
        //writeCookieToFile("src/test/resources/cookies",allCookies);
        driver.manage().deleteAllCookies();
        restoreCookieFromFile("src/test/resources/cookies", "way2automation.com");
        openWay2automationPage(driver,URL);
        Assert.assertTrue("Авторизация не прошла",way2automationPage.menuContainerIsVisible());
        selectMenu();
        cursorOnSubMenu();
    }

    private void openWay2automationPage(WebDriver driver, String url) {
        driver.get(url);
    }

    private static void writeCookieToFile(String fileName, Set<Cookie> cookies){
        StringBuilder sb = new StringBuilder();
        for (Cookie loadedCookie : cookies) {
            System.out.println(String.format("%s=%s", loadedCookie.getName(), loadedCookie.getValue()));
            sb.append(String.format("%s=%s", loadedCookie.getName(), loadedCookie.getValue()));
            sb.append("\n");
        }
        FileWorker.write(fileName,sb.toString());
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

    private void selectMenu(){
        way2automationPage.menuWithSubMenuClick();
        Assert.assertTrue("Меню не открылось",way2automationPage.menuWithSubMenuConteinerIsVisible());
    }

    private void cursorOnSubMenu() throws InterruptedException {
        way2automationPage.switchToFrame();
        way2automationPage.menuWithSubMenuChClick();
        builder.moveToElement(way2automationPage.getMenuWithSubMenuCheck()).perform();
        Assert.assertTrue("Подменю не открылось",way2automationPage.subMenuIsVisible());
        way2automationPage.outFromFrame();
    }
}
