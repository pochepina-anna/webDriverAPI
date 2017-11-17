package com.way2automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс <class>Way2aytomationDropPage</class>
 */
public class Way2automationDropPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id='wrapper']//div[contains(concat(' ',@class,' '),'responsive-tabs-default')]")
    private WebElement menuContainer;

    @FindBy(xpath = "//*[@id='draggable']")
    private WebElement dragObj;

    @FindBy(xpath = "//*[@id='droppable']")
    private WebElement dropObj;

    @FindBy(xpath = "//*[@id='example-1-tab-1']//iframe")
    private WebElement menuFrame;

    public Way2automationDropPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public WebElement getDragObj() {
        return dragObj;
    }

    public WebElement getDropObj() {
        return dropObj;
    }

    public boolean menuContainerIsVisible(){
        return wait.until(ExpectedConditions.visibilityOf(menuContainer)).isDisplayed();
    }

    public String getTextDropObj(){
        return dropObj.getText();
    }

    public void switchToFrame(){ wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(menuFrame));}

    public void outFromFrame() {driver.switchTo().defaultContent();}

}
