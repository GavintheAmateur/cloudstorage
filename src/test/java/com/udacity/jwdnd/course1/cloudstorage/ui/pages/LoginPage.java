package com.udacity.jwdnd.course1.cloudstorage.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement buttonLogin;

    @FindBy(partialLinkText = "ign up")
    private WebElement linkSignup;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signUp() {
        linkSignup.click();
    }

    public void signin(String username, String password) {
        inputUsername.clear();
        inputPassword.clear();
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonLogin.click();
    }
}
