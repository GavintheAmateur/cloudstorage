package com.udacity.jwdnd.course1.cloudstorage.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(xpath = "//*[text()='Logout']")
    private WebElement btnLogout;

    @FindBy(xpath = "//*[text()='Upload']")
    private WebElement btnUpload;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotes;

    @FindBy(xpath = "//*[contains(text(),'Add a New Note')]")
    private WebElement btnAddNote;

    //@FindBy(xpath="//div[div/h5[text()='Note']]//div[label[text()='Title']]/input")
    @FindBy(id="note-title")
    private WebElement inputNoteTitle;

    @FindBy(id="note-description")
    private WebElement inputNoteDescription;

    @FindBy(xpath="//*[text()='Save changes']")
    private WebElement btnSave;

    @FindBy(xpath="//*[text()='Close']")
    private WebElement btnClose;

    

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,60);
        //still keep PageFactory init to avoid NPE
        PageFactory.initElements(driver,this);
    }

    public void logout() {
        btnLogout.click();
    }


    public void createNote(String title,String description) {
        wait.until(ExpectedConditions.elementToBeClickable(navNotes));
        navNotes.click();
        wait.until(ExpectedConditions.visibilityOf(btnAddNote));
        btnAddNote.click();
        wait.until(ExpectedConditions.visibilityOf(inputNoteTitle));
        inputNoteTitle.sendKeys(title);
        inputNoteDescription.sendKeys(description);
        wait.until(ExpectedConditions.visibilityOf(btnSave));
        btnSave.click();
    }
}
