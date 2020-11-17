package com.udacity.jwdnd.course1.cloudstorage.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(xpath = "//*[text()='Logout']")
    private WebElement btnLogout;

    @FindBy(xpath = "//*[text()='Upload']")
    private WebElement btnUpload;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotes;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentials;

    @FindBy(xpath = "//*[contains(text(),'Add a New Note')]")
    private WebElement btnAddNote;

    @FindBy(id = "note-title")
    private WebElement inputNoteTitle;

    @FindBy(id = "note-description")
    private WebElement inputNoteDescription;

    @FindBy(xpath = "//*[contains(text(),'Add a New Credential')]")
    private WebElement btnAddCredential;

    @FindBy(id="credential-url")
    private WebElement inputCredentialUrl;


    @FindBy(id="credential-username")
    private WebElement inputCredentialUsername;

    @FindBy(id="credential-password")
    private WebElement inputCredentialPassword;

    @FindBy(id="credentialTable")
    private WebElement tableCredentials;

    @FindBy(xpath = "//div[@id='noteModal']//button[text()='Save changes']")
    private WebElement btnNoteSave;

    @FindBy(xpath = "//div[@id='credentialModal']//button[text()='Save changes']")
    private WebElement btnCredentialSave;

    @FindBy(xpath = "//*[text()='Close']")
    private WebElement btnClose;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 60);
        //still keep PageFactory init to avoid NPE
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        btnLogout.click();
    }


    public void createNote(String title, String description) {
        wait.until(ExpectedConditions.elementToBeClickable(navNotes));
        navNotes.click();
        wait.until(ExpectedConditions.visibilityOf(btnAddNote));
        btnAddNote.click();
        wait.until(ExpectedConditions.visibilityOf(inputNoteTitle));
        inputNoteTitle.sendKeys(title);
        inputNoteDescription.sendKeys(description);
        wait.until(ExpectedConditions.visibilityOf(btnNoteSave));
        btnNoteSave.click();
    }


    public Boolean isTextInSource(String text) {
        return driver.getPageSource().contains(text);
    }

    public boolean isPasswordViewableInCredentialTable(String credentialPassword) {
        int passwordColumnIndex = tableCredentials.findElements(By.xpath("//th[text()='Password']/preceding-sibling::th")).size()+1;
        String xpath = MessageFormat.format("//tbody/tr/*[position()={0}]",passwordColumnIndex);
        List<WebElement> passwordCells = tableCredentials.findElements(By.xpath(xpath));
        for (WebElement cell:passwordCells
             ) {
            String password = cell.getAttribute("innerHTML");
            if(password.equals(credentialPassword)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUsernameViewableInCredentialTable(String credentialUsername) {
        int usernameColumnIndex = tableCredentials.findElements(By.xpath("//th[text()='Username']/preceding-sibling::th")).size()+1;
        String xpath = MessageFormat.format("//tbody/tr/*[position()={0}]",usernameColumnIndex);
        List<WebElement> usernameCells = tableCredentials.findElements(By.xpath(xpath));
        for (WebElement cell:usernameCells
        ) {
            String username = cell.getAttribute("innerHTML");
            if(username.equals(credentialUsername)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPasswordViewableInEditWindow(String credentialPassword) {

        String displayedPassword = inputCredentialPassword.getAttribute("value");
        return displayedPassword.equals(credentialPassword);
    }

    public void updateNote(String noteTitleOriginal, String noteDescriptionOriginal, String noteTitleUpdated, String noteDescriptionUpdated) {
        while (!Arrays.asList(navNotes.getAttribute("class").split(" ")).contains("active")) {
            navNotes.click();
        }
        //find row of the note to edit
        String xpath = MessageFormat.format("//tr[//th[text()=''{0}''] and //td[text()=''{1}'']]", noteTitleOriginal, noteDescriptionOriginal);
        WebElement row = driver.findElement(By.xpath(xpath));
        //edit
        WebElement btnEdit = row.findElement(By.xpath("//*[text()='Edit']"));
        wait.until(ExpectedConditions.visibilityOf(btnEdit));
        btnEdit.click();

        wait.until(ExpectedConditions.visibilityOf(inputNoteTitle));
        inputNoteTitle.clear();
        inputNoteTitle.sendKeys(noteTitleUpdated);
        inputNoteDescription.clear();
        inputNoteDescription.sendKeys(noteDescriptionUpdated);
        wait.until(ExpectedConditions.visibilityOf(btnNoteSave));
        btnNoteSave.click();
    }

    public void deleteNote(String noteTitle, String noteDescription) {
        while (!Arrays.asList(navNotes.getAttribute("class").split(" ")).contains("active")) {
            navNotes.click();
        }
        //find row of the note to delete
        String xpath = MessageFormat.format("//tr[th[text()=''{0}''] and td[text()=''{1}'']]", noteTitle, noteDescription);
        WebElement row = driver.findElement(By.xpath(xpath));
        //delete
        WebElement btnDelete = row.findElement(By.xpath("//*[@id='note-delete']"));
        wait.until(ExpectedConditions.elementToBeClickable(btnDelete));
        btnDelete.click();

    }

    public void createCredential(String credentialUrl,String  credentialUsername,String  credentialPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(navCredentials));
        navCredentials.click();
        wait.until(ExpectedConditions.elementToBeClickable(btnAddCredential));
        btnAddCredential.click();
        wait.until(ExpectedConditions.elementToBeClickable(btnCredentialSave));
        inputCredentialUrl.sendKeys(credentialUrl);
        inputCredentialUsername.sendKeys(credentialUsername);
        inputCredentialPassword.sendKeys(credentialPassword);
        btnCredentialSave.click();
    }

    public void viewCredential(String credentialUrl) {
        while (!Arrays.asList(navCredentials.getAttribute("class").split(" ")).contains("active")) {
            navCredentials.click();
        }
        //find row of the credentials to edit
        String xpath = MessageFormat.format("//tr[//th[text()=''{0}'']]", credentialUrl);
        WebElement row = driver.findElement(By.xpath(xpath));
        //edit
        WebElement btnEdit = row.findElement(By.xpath("//*[text()='Edit']"));
        wait.until(ExpectedConditions.visibilityOf(btnEdit));
        btnEdit.click();
    }

    public void closeEditCredentialWindow() {
        WebElement btnClose = driver.findElement(By.xpath("//*[@id='credentialModal']//button[text()='Close']"));
        wait.until(ExpectedConditions.visibilityOf(btnClose));
        btnClose.click();
    }


    public void updateCredential(String credentialUrl, String credentialUsernameUpdated, String credentialPasswordUpdated) {
        viewCredential(credentialUrl);
        wait.until(ExpectedConditions.visibilityOf(btnCredentialSave));
        inputCredentialUsername.clear();
        inputCredentialUsername.sendKeys(credentialUsernameUpdated);
        inputCredentialPassword.clear();
        inputCredentialPassword.sendKeys(credentialPasswordUpdated);
        btnCredentialSave.click();

    }

    public void deleteCredential(String credentialUrl) {
        while (!Arrays.asList(navCredentials.getAttribute("class").split(" ")).contains("active")) {
            navCredentials.click();
        }
        //find row of the note to delete
        String xpath = MessageFormat.format("//tr[th[text()=''{0}'']]", credentialUrl);
        WebElement row = driver.findElement(By.xpath(xpath));
        //delete
        WebElement btnDelete = row.findElement(By.xpath("//*[@id='credential-delete']"));
        wait.until(ExpectedConditions.elementToBeClickable(btnDelete));
        btnDelete.click();
    }


}
