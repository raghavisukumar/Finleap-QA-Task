package org.fineleap.selenium.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ResourceBundle;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UserLoginTest {

    private static ResourceBundle rb = ResourceBundle.getBundle("userdetails");

    public FirefoxDriver driver;
    private static SignUpPage signUpPage;
    private static SignInPage signInPage;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.gecko.driver", rb.getString("webdriver.gecko.driver.path"));
        driver = new FirefoxDriver();
        driver.get(rb.getString("url"));
        signUpPage = new SignUpPage(driver);
        signInPage = new SignInPage(driver);
    }

    @Test
    @Order(1)
    public void verifyValidUserDetails() {
        signUpPage.enterUserDetails(rb.getString("valid.vorname"), rb.getString("valid.nachname"), rb.getString("valid.email"), rb.getString("valid.password"), true);
        signUpPage.submitVerifySignin();
    }

    @Test
    @Order(2)
    public void verifyEmptyVorName() {
        signUpPage.enterUserDetails("", rb.getString("valid.nachname"), rb.getString("valid.email"), rb.getString("valid.password"), true);
        signUpPage.verifySumitDisabled();
    }

    @Test
    @Order(3)
    public void verifyInvalidVorName() {
        signUpPage.enterUserDetails(rb.getString("invalid.vorname"), rb.getString("valid.nachname"), rb.getString("valid.email"), rb.getString("valid.password"), true);
        signUpPage.verifySumitDisabled();
    }

    @Test
    @Order(4)
    public void verifyEmptyNachName() {
        signUpPage.enterUserDetails(rb.getString("valid.vorname"), "", rb.getString("valid.email"), rb.getString("valid.password"), true);
        signUpPage.verifySumitDisabled();
    }

    @Test
    @Order(5)
    public void verifyInvalidNachName() {
        signUpPage.enterUserDetails(rb.getString("valid.vorname"), rb.getString("invalid.nachname"), rb.getString("valid.email"), rb.getString("valid.password"), true);
        signUpPage.verifySumitDisabled();
    }

    @Test
    @Order(6)
    public void verifyEmptyEmail() {
        signUpPage.enterUserDetails(rb.getString("valid.vorname"), rb.getString("valid.nachname"), "", rb.getString("valid.password"), true);
        signUpPage.verifySumitDisabled();
    }

    @Test
    @Order(7)
    public void verifyInvalidEmail() {
        signUpPage.enterUserDetails(rb.getString("valid.vorname"), rb.getString("valid.nachname"), rb.getString("invalid.email"), rb.getString("valid.password"), true);
        signUpPage.verifySumitDisabled();
    }

    @Test
    @Order(8)
    public void verifyRegisteredEmail() {
        signUpPage.enterUserDetails(rb.getString("valid.vorname"), rb.getString("valid.nachname"), rb.getString("valid.email"), rb.getString("valid.password"), true);
        signUpPage.verifyValidation();
    }

    @Test
    @Order(9)
    public void verifyEmptyPassword() {
        signUpPage.enterUserDetails(rb.getString("valid.vorname"), rb.getString("valid.nachname"), rb.getString("valid.email"), "", true);
        signUpPage.verifySumitDisabled();
    }

    @Test
    @Order(10)
    public void verifyInvalidPassword() {
        signUpPage.enterUserDetails(rb.getString("valid.vorname"), rb.getString("valid.nachname"), rb.getString("valid.email"), rb.getString("invalid.password"), true);
        signUpPage.verifySumitDisabled();
    }

    @Test
    @Order(11)
    public void submitDisabledWhenConsentIsNotChecked() {
        signUpPage.enterUserDetails(rb.getString("valid.vorname"), rb.getString("valid.nachname"), rb.getString("valid.email"), rb.getString("valid.password"), false);
        signUpPage.verifySumitDisabled();
    }

    @Test
    @Order(12)
    public void verifySuccessSignin() {
        signInPage.enterUserDetails(rb.getString("valid.email"), rb.getString("valid.password"));
        signInPage.verifyLogin();
    }

    @Test
    @Order(13)
    public void verifyValidation() {
        signInPage.enterUserDetails(rb.getString("unregistered.username"), rb.getString("unregistered.password"));
        signInPage.verifyValidation();
    }

    @AfterEach
    public void teardown() {
        driver.close();
    }
}
