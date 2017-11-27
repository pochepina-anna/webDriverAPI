package com.way2automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс <class>Way2automationFramesPage</class>
 */

public class Way2automationFramesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id='wrapper']//div[contains(concat(' ',@class,' '),'responsive-tabs-default')]")
    private WebElement menuContainer;

    @FindBy(xpath = "//a")
    private WebElement linkButton;

    @FindBy(xpath = "//*[@id='wrapper']//a[contains(concat(' ',@href,' '),'#example-1-tab-4')]")
    private WebElement inputMultipleMenuButton;

    @FindBy(xpath = "//*[@id='example-1-tab-4']//iframe")
    private WebElement menuFrame;

    public Way2automationFramesPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public boolean menuContainerIsVisible(){
        return wait.until(ExpectedConditions.visibilityOf(menuContainer)).isDisplayed();
    }

    public void switchToFrame(){ wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(menuFrame));}

    public void outFromFrame() {driver.switchTo().defaultContent();}

    public void inputMultipleMenuButtonClick() {
        inputMultipleMenuButton.click();
    }

    public void linkButtonClick(){
        linkButton.click();
    }

}
