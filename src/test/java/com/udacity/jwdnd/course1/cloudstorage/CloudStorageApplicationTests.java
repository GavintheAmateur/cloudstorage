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
import org.springframework.test.context.event.annotation.BeforeTestClass;

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

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	 public void beforeAll() {
		WebDriverManager.chromedriver().setup();
		this.driver = new ChromeDriver();

	}

	@BeforeEach
	public void beforeEach()  {
	}

	@AfterEach
	public void afterEach()  {
	}



	@AfterAll
	public void afterAll() {
		if (this.driver != null) {
			//driver.quit();
		}
	}

	@Test
	public void testUserLoginFlow() {
		//Write a test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible.

		signup();


		login();


		String urlHome = "http://localhost:"+port+"/home";
		driver.get(urlHome);
		Assertions.assertEquals("Home",driver.getTitle());

		HomePage homePage = new HomePage(driver);
		homePage.logout();

		driver.get(urlHome);
		Assertions.assertEquals("Login",driver.getTitle());
	}


	@Test
	public void testCreateNotes() {
		//Write a test that creates a note, and verifies it is displayed.
		signup();
		login();
		String urlHome = "http://localhost:"+port+"/home";
		driver.get(urlHome);
		HomePage homePage = new HomePage(driver);
		homePage.createNote(noteTitle,noteDescription);

	}

	@Test
	public void testEditNotes() {
		//Write a test that edits an existing note and verifies that the changes are displayed.
		throw new NotImplementedException("");
	}

	@Test
	public void testDeleteNotes() {
		//Write a test that deletes a note and verifies that the note is no longer displayed.
		throw new NotImplementedException("");
	}

	@Test
	public void testCreateCredentials() {
		//Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.  throw new NotImplementedException("");
		throw new NotImplementedException("");
	}

	@Test
	public void testEditCredentials() {
		//Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
		throw new NotImplementedException("");
	}

	@Test
	public void testDeleteCredentials() {
		//Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
		throw new NotImplementedException("");
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
