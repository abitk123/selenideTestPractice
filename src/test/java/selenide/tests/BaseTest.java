package selenide.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import selenide.*;


import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {
    MainPage mainPage;
    ItemPage itemPage;
    CartPage cartPage;
    RegistrationPage registrationPage;
    LoginPage loginPage;

    @BeforeEach
    void initialize() {

        Configuration.browser = "chrome";
        Configuration.browserSize = "1900, 1400";
        open("https://www.demoblaze.com/");
        mainPage = new MainPage();
        itemPage = new ItemPage();
        cartPage = new CartPage();
        registrationPage = new RegistrationPage();
        loginPage = new LoginPage();

    }

    @AfterEach
    void clearState() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        executeJavaScript("sessionStorage.clear()");
    }
}
