package org.fineleap.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignUpPage {

    private FirefoxDriver driver;

    public SignUpPage(FirefoxDriver driver) {
        this.driver = driver;
    }

    public void enterUserDetails(String vorname, String nachname, String email, String password, boolean shouldCheckConsent) {
        driver.findElement(By.id("signup")).click();
        driver.findElement(By.id("firstName")).sendKeys(vorname);
        driver.findElement(By.id("lastName")).sendKeys(nachname);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        if (shouldCheckConsent) {
            driver.findElement(By.id("formTermsConsent")).click();
        }
    }

    public void submitVerifySignin() {
        driver.findElement(By.id("kc-submit")).click();
        assertTrue(driver.getPageSource().contains("Willkommen bei Pylot"));
        System.out.println("User logged in successfully");
    }

    public void verifySumitDisabled() {
        assertFalse((driver.findElement(By.id("kc-submit"))).isEnabled());
        System.out.println("InValid user details");
    }

    public void verifyValidation() {
        driver.findElement(By.id("kc-submit")).click();
        String validationmessage = driver.findElement(By.className("notification-text")).getText();
        assertTrue(validationmessage.contains("Diese Email wurde bereits verwendet. Bitte melden Sie sich an oder verwenden eine andere Email."));
    }
}
