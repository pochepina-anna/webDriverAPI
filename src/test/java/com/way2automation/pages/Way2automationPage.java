package com.way2automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс <class>Way2automationPage</class>
 */
public class Way2automationPage {


    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id='wrapper']//div[contains(concat(' ',@class,' '),'responsive-tabs-default')]")
    private WebElement menuContainer;

    @FindBy(xpath = "//*[@id='wrapper']//a[contains(concat(' ',@href,' '),'#example-1-tab-2')]")
    private WebElement menuWithSubMenu;

    @FindBy(xpath = "//*[@id='example-1-tab-2']")
    private WebElement menuWithSubMenuConteiner;

    @FindBy(xpath = "//*[@id='example-1-tab-2']//iframe")
    private WebElement menuFrame;

    @FindBy(xpath = "//*[@id='ui-id-2']")
    private WebElement menuWithSubMenuCheck;

    @FindBy(xpath = "//ul[contains(concat(' ',@class,' '),' ui-widget-content ui-front ')]")
    private WebElement subMenu;

    public Way2automationPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public WebElement getMenuWithSubMenuCheck() {
        return menuWithSubMenuCheck;
    }

    public void menuWithSubMenuChClick() {
        menuWithSubMenuCheck.click();
    }

    public void menuWithSubMenuClick(){
        menuWithSubMenu.click();
    }

    public boolean menuContainerIsVisible(){
        return wait.until(ExpectedConditions.visibilityOf(menuContainer)).isDisplayed();
    }

    public boolean menuWithSubMenuConteinerIsVisible(){
        return wait.until(ExpectedConditions.visibilityOf(menuWithSubMenuConteiner)).isDisplayed();
    }

    public boolean subMenuIsVisible(){
        return wait.until(ExpectedConditions.visibilityOf(subMenu)).isDisplayed();
    }

    public void switchToFrame(){ wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(menuFrame));}

    public void outFromFrame() {driver.switchTo().defaultContent();}

}
