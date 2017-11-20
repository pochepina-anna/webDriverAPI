package com.way2automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс <class>Way2automationAlertPage</class>
 */
public class Way2automationAlertPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id='wrapper']//div[contains(concat(' ',@class,' '),'responsive-tabs-default')]")
    private WebElement menuContainer;

    @FindBy(xpath = "//button")
    private WebElement alertButton;

    @FindBy(xpath = "//p")
    private WebElement textFromAlert;

    @FindBy(xpath = "//*[@id='wrapper']//a[contains(concat(' ',@href,' '),'#example-1-tab-2')]")
    private WebElement inputAlertMenuButton;

    @FindBy(xpath = "//*[@id='example-1-tab-2']//iframe")
    private WebElement menuFrame;

    public Way2automationAlertPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public boolean menuContainerIsVisible(){
        return wait.until(ExpectedConditions.visibilityOf(menuContainer)).isDisplayed();
    }

    public void alertButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(alertButton)).click();
    }

    public void inputAlertMenuButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(inputAlertMenuButton)).click();
    }

    public void switchToFrame(){ wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(menuFrame));}

    public void outFromFrame() {driver.switchTo().defaultContent();}

    public void switchToAlert(){driver.switchTo().alert();}

    public String getTextFromAlert(){return textFromAlert.getText();}

    public void setTextFromAlert(String str){driver.switchTo().alert().sendKeys(str);}

    public void acceptAlert(){driver.switchTo().alert().accept();}

}
