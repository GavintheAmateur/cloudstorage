package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.ui.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.ui.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.ui.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CloudStorageApplicationTests {

	@Value("${testaccount1.firstname}")
	private String firstname;

	@Value("${testaccount1.lastname}")
	private String lastname;

	@Value("${testaccount1.username}")
	private String username;

	@Value("${testaccount1.password}")
	private String password;

	@Value("${test_note_title}")
	private String noteTitle;

	@Value("${test_note_description}")
	private String noteDescription;

	@Value("${test_note_title_updated}")
	private String noteTitleUpdated;

	@Value("${test_note_description_updated}")
	private String noteDescriptionUpdated;

	@Value("${testcredential1.url}")
	private String credentialUrl;

	@Value("${testcredential1.username}")
	private String credentialUsername;

	@Value("${testcredential1.password}")
	private String credentialPassword;

	@Value("${testcredential1.username_updated}")
	private String credentialUsernameUpdated;

	@Value("${testcredential1.password_updated}")
	private String credentialPasswordUpdated;

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	 public void beforeAll() {
		WebDriverManager.chromedriver().setup();
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}


	@AfterEach
	public void afterEach()  {
	}



	@AfterAll
	public void afterAll() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void testUserLoginFlow() {
		String baseUrl = "http://localhost:"+port;
		String urlHome = baseUrl+"/home";
		String urlLogin = baseUrl + "/login";
		String urlSignup = baseUrl + "/signup";

		//Write a test that verifies that an unauthorized user can only access the login and signup pages.
		driver.get(urlHome);
		Assertions.assertNotEquals("Home",driver.getTitle());
		Assertions.assertEquals("Login",driver.getTitle());
		driver.get(urlSignup);
		Assertions.assertEquals("Sign Up",driver.getTitle());
		//Write a test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible.
		signup();
		login();

		driver.get(urlHome);
		Assertions.assertEquals("Home",driver.getTitle());

		HomePage homePage = new HomePage(driver);
		homePage.logout();

		driver.get(urlHome);
		Assertions.assertEquals("Login",driver.getTitle());
	}


	@Test
	public void testCrudNotes() {
		//Write a test that creates a note, and verifies it is displayed.
		signup();
		login();
		String urlHome = "http://localhost:"+port+"/home";
		driver.get(urlHome);
		HomePage homePage = new HomePage(driver);
		homePage.createNote(noteTitle,noteDescription);
        Assertions.assertEquals(true, homePage.isTextInSource(noteTitle));
        Assertions.assertEquals(true, homePage.isTextInSource(noteDescription));

		//Write a test that edits an existing note and verifies that the changes are displayed.

		homePage.updateNote(noteTitle,noteDescription,noteTitleUpdated,noteDescriptionUpdated);
		Assertions.assertEquals(true, homePage.isTextInSource(noteTitleUpdated));
		Assertions.assertEquals(true, homePage.isTextInSource(noteDescriptionUpdated));
		Assertions.assertEquals(false, homePage.isTextInSource(noteTitle));
		Assertions.assertEquals(false, homePage.isTextInSource(noteDescription));
		//Write a test that deletes a note and verifies that the note is no longer displayed.
		homePage.deleteNote(noteTitleUpdated,noteDescriptionUpdated);
		Assertions.assertEquals(false, homePage.isTextInSource(noteTitleUpdated));
		Assertions.assertEquals(false, homePage.isTextInSource(noteDescriptionUpdated));
	}


	@Test
	public void testCrudCredentials() {
		signup();
		login();
		//Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
		String urlHome = "http://localhost:"+port+"/home";
		driver.get(urlHome);
		HomePage homePage = new HomePage(driver);
		homePage.createCredential(credentialUrl,credentialUsername,credentialPassword);
		Assertions.assertEquals(true, homePage.isTextInSource(credentialUrl));
		Assertions.assertEquals(true, homePage.isUsernameViewableInCredentialTable(credentialUsername));
		Assertions.assertEquals(false, homePage.isPasswordViewableInCredentialTable(credentialPassword));

		//Write a test that views an existing set of credentials,
		// verifies that the viewable password is unencrypted,
		// edits the credentials, and verifies that the changes are displayed.
		homePage.viewCredential(credentialUrl);
		Assertions.assertEquals(true, homePage.isUsernameViewableInCredentialTable(credentialUsername));
		Assertions.assertEquals(true, homePage.isPasswordViewableInEditWindow(credentialPassword));
		homePage.closeEditCredentialWindow();

		homePage.updateCredential(credentialUrl,credentialUsernameUpdated,credentialPasswordUpdated);
		Assertions.assertEquals(true, homePage.isTextInSource(credentialUrl));
		Assertions.assertEquals(true, homePage.isUsernameViewableInCredentialTable(credentialUsernameUpdated));
		Assertions.assertEquals(false, homePage.isUsernameViewableInCredentialTable(credentialUsername));
		Assertions.assertEquals(false, homePage.isPasswordViewableInCredentialTable(credentialPasswordUpdated));
		homePage.viewCredential(credentialUrl);
		Assertions.assertEquals(true, homePage.isPasswordViewableInEditWindow(credentialPasswordUpdated));
		Assertions.assertEquals(false, homePage.isPasswordViewableInEditWindow(credentialPassword));
		homePage.closeEditCredentialWindow();

		//Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
		homePage.deleteCredential(credentialUrl);
		Assertions.assertEquals(false, homePage.isTextInSource(credentialUrl));

	}

	@Test
	public void testFileFlow() {
		throw new NotImplementedException("testFileFlow not implemented");
		//1. The user should be able to upload files and see any files they previously uploaded.
		//2. The user should be able to view/download or delete previously-uploaded files.
	    //3. Any errors related to file actions should be displayed.
		// For example, a user should not be able to upload two files with the same name, but they'll never know unless you tell them!
	}


	private void signup() {
		String urlSignUp = "http://localhost:"+port+"/signup";
		driver.get(urlSignUp);
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstname,lastname,username,password);
	}

	private void login() {
		String urlLogin = "http://localhost:"+port+"/login";
		driver.get(urlLogin);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.signin(username,password);
	}

}
