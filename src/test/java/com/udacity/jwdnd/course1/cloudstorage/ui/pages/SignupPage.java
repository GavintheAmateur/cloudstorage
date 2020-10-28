package com.udacity.jwdnd.course1.cloudstorage.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {


    @FindBy(xpath = "//input[@placeholder='Enter First Name']")
    private WebElement inputFirstName;

    @FindBy(xpath = "//input[@placeholder='Enter Last Name']")
    private WebElement inputLastName;

    @FindBy(xpath = "//input[@placeholder='Enter Username']")
    private WebElement inputUsername;

    @FindBy(xpath = "//input[@placeholder='Enter Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign Up']")
    private WebElement btnSignup;




    public SignupPage(WebDriver driver) {

        PageFactory.initElements(driver, this);

    }

    public void signup(String firstname, String lastname, String username,String password) {
        inputFirstName.clear();
        inputFirstName.sendKeys(firstname);
        inputLastName.clear();
        inputLastName.sendKeys(lastname);
        inputUsername.clear();
        inputUsername.sendKeys(username);
        inputPassword.clear();
        inputPassword.sendKeys(password);
        btnSignup.click();
    }
}
